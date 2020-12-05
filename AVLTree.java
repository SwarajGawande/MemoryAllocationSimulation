// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the AVLTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    { 
    	if (this==null){
            AVLTree a=new AVLTree();
            AVLTree a1=new AVLTree(address,size,key);
            a.right=a1;
            a1.parent=a;
            return a1;
        }
        AVLTree a=this;
        while(a.parent!=null){
            a=a.parent;
        }
        a=a.right;
        if(a==null){
            AVLTree a1=new AVLTree(address,size,key);
            this.right=a1;
            a1.parent=this;
            return a1;
        }
        AVLTree b=a;
        boolean t=true;
        while(a!=null){
            if (a.key>key||(a.key==key&&a.address>=address)||(a.key==key&&a.address==address&&a.size>=size)){
                b=a;
                a=a.left;
                t=false;
                continue;
            }
            if (a.key<key||(a.key==key&&a.address<address)||(a.key==key&&a.address==address&&a.size<size)){
                b=a;
                a=a.right;
                t=true;
            }
        }
        AVLTree c=new AVLTree(address,size,key);
        if(t){
            b.right=c;
            c.parent=b;
        }
        else{
            b.left=c;
            c.parent=b;
        }
        //System.out.println(c.address);
        c.checkBalance(true);
        //System.out.println(c.address);
        return c;
        //return null;
    }

    private void checkBalance(boolean insert){
    	AVLTree current=this;
    	boolean rt=true;
    	while (current!=null&&current.parent!=null){
    		if (current.left==null&&current.right==null){
    			current.height=0;
    			current=current.parent;
    		}
    		else if (current.right==null){
    			if (current.left.height==0){
    				current.height=1;
    				current=current.parent;
    			}
    			else{
    				rt=false;
    				current=current.rebalance(rt,insert);
    				continue;
    			}
    		}
    		else if (current.left==null){
    			if (current.right.height==0){
    				current.height=1;
    				current=current.parent;
    			}
    			else{
    				rt=true;
    				current=current.rebalance(rt,insert);
    				continue;
    			}
    		}
    		else{
    			int diff=current.left.height-current.right.height;
    			if (diff==0||diff==1||diff==-1){
    				if (diff==1||diff==0){
    					current.height=current.left.height+1;
    				}
    				if (diff==-1){
    					current.height=current.right.height+1;
    				}
    				current=current.parent;
    			}
    			else{
    				if (diff>0){
    					rt=false;
    				}
    				else{
    					rt=true;
    				}
    				//System.err.println(diff);
    				current=current.rebalance(rt,insert);
    			}
    		}
    	}
    }

    private AVLTree rebalance(boolean rt,boolean insert){
    	AVLTree x,y,z;
    	z=this;
    	boolean rtx=true;
    	if (rt){
    		y=z.right;
    	}
    	else{
    		y=z.left;
    	}
    	if (y.right==null&&y.left==null){
    		z.height=1;
    		return z.parent;
    	}
    	if (y.right==null){
    		x=y.left;
    		rtx=false;
    		return z.rotate(x,y,z,rt,rtx,insert);

    	}
    	else if (y.left==null){
    		x=y.right;
    		rtx=true;
    		return z.rotate(x,y,z,rt,rtx,insert);
    	}
    	else{
    		if (y.right.height>y.left.height){
    			x=y.right;
    			rtx=true;
    			return z.rotate(x,y,z,rt,rtx,insert);
    		}
    		if (y.right.height<y.left.height){
    			x=y.left;
    			rtx=false;
    			return z.rotate(x,y,z,rt,rtx,insert);
    		}
    		else{
    			if (rt){
    				rtx=true;
    				x=y.right;
    				return z.rotate(x,y,z,rt,rtx,insert);
    			}
    			else{
    				rtx=false;
    				x=y.left;
    				return z.rotate(x,y,z,rt,rtx,insert);
    			}
    		}
    	}
    	//if (x==null){
    	//	return false;
    	//}
    	//return true;
    }

    private AVLTree rotate(AVLTree x,AVLTree y,AVLTree z,boolean rt,boolean rtx,boolean insert){
    	if (rt&&rtx){
    		if (z.parent.left==z){
    			z.parent.left=y;
    		}
    		else{
    			z.parent.right=y;
    		}
    		y.parent=z.parent;
    		z.parent=y;
    		z.right=y.left;
    		if (y.left!=null){
    			y.left.parent=z;
    		}
    		y.left=z;
   			if (insert){
   				x.height=z.height-1;
   				y.height=z.height;
   				z.height--;
   				return y.parent;
   			}
   			else{
   				if (z.right==null){
   					z.height=0;
   				}
   				else{
   					z.height=z.right.height+1;
   				}
   				y.height=z.height+1;
   				return y.parent;
   			}
    	}
    	else if (!rt&&!rtx){
    		if (z.parent.left==z){
    			z.parent.left=y;
    		}
    		else{
    			z.parent.right=y;
    		}
    		y.parent=z.parent;
    		z.parent=y;
    		z.left=y.right;
    		if (y.right!=null){
    			y.right.parent=z;
    		}
    		y.right=z;
   			if (insert){
   				x.height=z.height-1;
   				y.height=z.height;
   				z.height--;
   				return y.parent;
   			}
   			else{
   				if (z.left==null){
   					z.height=0;
   				}
   				else{
   					z.height=z.left.height+1;
   				}
   				y.height=z.height+1;
   				return y.parent;
   			}
    	}
    	else if(!rt&&rtx){
    		if (z.parent.left==z){
    			z.parent.left=x;
    		}
    		else{
    			z.parent.right=x;
    		}
    		x.parent=z.parent;
    		z.parent=x;
    		y.parent=x;
    		y.right=x.left;
    		z.left=x.right;
    		if (x.left!=null){
    			x.left.parent=y;
    		}
    		if (x.right!=null){
    			x.right.parent=z;
    		}
    		x.left=y;
    		x.right=z;
    		if (insert){
   				y.height=z.height-1;
   				x.height=z.height;
   				z.height--;
   			}
   			else{
   				y.height=y.height-1;
   				x.height++;
   				z.height=y.height;
   			}
   			return x.parent;
    	}
    	else{
    		if (z.parent.left==z){
    			z.parent.left=x;
    		}
    		else{
    			z.parent.right=x;
    		}
    		x.parent=z.parent;
    		z.parent=x;
    		y.parent=x;
    		y.left=x.right;
    		z.right=x.left;
    		if (x.left!=null){
    			x.left.parent=z;
    		}
    		if (x.right!=null){
    			x.right.parent=y;
    		}
    		x.right=y;
    		x.left=z;
    		if (insert){
   				y.height=z.height-1;
   				x.height=z.height;
   				z.height--;
   			}
   			else{
   				y.height=y.height-1;
   				x.height++;
   				z.height=y.height;
   			}
   			return x.parent;
    	}
    }

    private boolean deleteNode(AVLTree c,AVLTree t){
        //System.out.println("100%");
        if (c.right==null&&c.left==null){
            if (t!=c){
                AVLTree d=c.parent;
                if (d.right==c){
                    d.right=null;
                    c.parent=null;
                    d.checkBalance(false);
                    return true;
                }
                else{
                    d.left=null;
                    c.parent=null;
                    d.checkBalance(false);
                    return true;
                }
            }
            else{
                AVLTree d=c.parent;
                c.key=d.key;
                c.address=d.address;
                c.size=d.size;
                c.parent=d.parent;
                if (d.right==c){
                    if(d.left!=null){
                        d.left.parent=c;
                    }
                    c.left=d.left;
                    d.right=null;
                    //return true;
                }
                else{
                    if(d.right!=null){
                        d.right.parent=c;
                    }
                    c.right=d.right;
                    d.left=null;
                    //return true;
                }
                if (d.parent==null){
                	c.checkBalance(false);
                    return true;
                }
                else{
                    if(d.parent.right==d){
                        d.parent.right=c;
                        c.checkBalance(false);
                        return true;
                    }
                    else{
                        d.parent.left=c;
                        c.checkBalance(false);
                        return true;
                    }
                }
            }
        }
        else if (c.right==null&&c.left!=null){
            AVLTree d=c.left;
            while (d.right!=null){
                d=d.right;
            }
            c.key=d.key;
            c.address=d.address;
            c.size=d.size;
            return this.deleteNode(d,t);
        }
        else {
            AVLTree d=c.getNext();
            c.key=d.key;
            c.address=d.address;
            c.size=d.size;
            return this.deleteNode(d,t);
        }
        //return false;
    }

    public boolean Delete(Dictionary e)
    {
    	if(e==null){
            return false;
        }
        if (this==null){
            return false;
        }
        AVLTree current=this;
        while(current.parent!=null){
            current=current.parent;
        }
        current=current.right;
        if (current==null){
            return false;
        }
        while (current!=null){
            if (current.key==e.key&&current.address==e.address&&current.size==e.size){
                return this.deleteNode(current,this);
            }
            if (current.key>e.key||(current.key==e.key&&current.address>e.address)||(current.key==e.key&&current.address==e.address&&current.size>e.size)){
                current=current.left;
                continue;
            }
            if (current.key<e.key||(current.key==e.key&&current.address<e.address)||(current.key==e.key&&current.address==e.address&&current.size<e.size)){
                current=current.right;
            }
            //current=current.getNext();
        }
        return false;
    }
        
    public AVLTree Find(int k, boolean exact)
    { 
    	//BSTree current = this.getFirst();
        AVLTree current=this;
        while(current.parent!=null){
            current=current.parent;
        }
        current=current.right;
        if (current==null){
            return null;
        }
        //System.out.println("100");
        if (exact){
        AVLTree prev=current;
        while (current!=null){
        	//System.out.println(current.address);
            if (current.key==k){
                prev=current;
                current=current.left;
                continue;
            }
            if (current.key>k){
                current=current.left;
            }
            else{
                current=current.right;
            }
            //current=current.getNext();
        }
        //System.out.println(""+prev.key+k);
        if(prev.key==k){
        	//System.out.println(prev.key);
            return prev;
        }
        //System.out.println("not found");
        return null;
        }
        else{
            AVLTree prev=current;
            while (current!=null){
            if (current.key<k){
                current=current.right;
                continue;
            }
            if(current.key>=k){
                prev=current;
                current=current.left;
            }
            //current=current.getNext();
        }
        if(prev.key>=k){
            return prev;
        }
        return null;
        }
        //return null;
    }

    public AVLTree getFirst()
    { 
    	if (this==null){
            return null;
        }
        AVLTree a=this;
        while(a.parent!=null){
            a=a.parent;
        }
        if(a.right!=null){
        a=a.right;
        while(a.left!=null){
            a=a.left;
        }
        return a;
        }
        else{
            return null;
        }
        //return null;
    }

    public AVLTree getNext()
    {
    	if (this==null){
            return null;
        }
        if(this.parent==null){
            return null;
        }
        if (this.right!=null){
            AVLTree a=this.right;
            while(a.left!=null){
                a=a.left;
            }
            return a;
        }
        else{
            if(this.parent.parent==null&&this.parent.left==null){
                return null;
            }
            else{
                AVLTree a=this.parent;
                AVLTree b=this;
                while(a!=null&&a.parent!=null&&a.right==b){
                    if(a.parent.parent==null&&a.parent.left==null){
                        return null;
                    }
                    b=a;
                    a=a.parent;
                }
                //System.out.println(a.parent);
                return a;
            }
        }
        //return null;
    }

    private boolean checkHeight(){
    	int diff= this.left.height-this.right.height;
    	boolean p;
    	if (diff>=0){
    		p=this.height==this.left.height+1;
    	}
    	else{
    		p=this.height==this.right.height+1;
    	}
    	return (diff==0||diff==1||diff==-1)&&p;
    }

    private boolean checkValid(int k1,int k2,int a1,int a2){
        boolean l=true;
        boolean r=true;
        l=(this.key>k1)||(this.key==k1&&this.address>=a1);
        r=(this.key<k2)||(this.key==k2&&this.address<=a2);
        if (this.left==null&&this.right==null){
        	l=l&&this.height==0;
        }
        if (this.left!=null){
        	if (this.right==null){
        		l=l&&this.left.height==0&&this.height==1;
        	}
        	else{
        		if(l&&!this.checkHeight()){
        			System.err.println("Height unbalanced");
        		}
        		l=l&&this.checkHeight();
        	}
            l=l&&this.left.parent==this;
            l=l&&this.left.checkValid(k1,this.key,a1,this.address);
        }
        if (this.right!=null){
        	if (this.left==null){
        		l=l&&this.right.height==0&&this.height==1;
        	}
        	else{
        		if(r&&!this.checkHeight()){
        			System.err.println("Height unbalanced");
        		}
        		r=r&&this.checkHeight();
        	}
            r=r&&this.right.parent==this;
            r=r&&this.right.checkValid(this.key,k2,this.address,a2);
        }
        return l&&r;
    }

    public AVLTree getLast()
    { 
        if (this==null){
            return null;
        }
        AVLTree a=this;
        while(a.parent!=null){
            a=a.parent;
        }
        if(a.right!=null){
        while(a.right!=null){
            a=a.right;
        }
        return a;
        }
        else{
            return null;
        }
    }

    public boolean sanity()
    { 
    	//return true;
        if(this==null){
            return true;
        }
        //return true;
        AVLTree a=this;
        while(a.parent!=null){
            a=a.parent;
            if (a==this){
                System.out.println("Cycles present");
                return false;
            }
        }
        if (a.left!=null){
            System.out.println("Root sentinal is not correct");
            return false;
        }
        a=a.right;
        if (a==null){
            return true;
        }
        AVLTree min=this.getFirst();
        AVLTree max=this.getLast();
        return a.checkValid(min.key,max.key,min.address,max.address);
    }
}


