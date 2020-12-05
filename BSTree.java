// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        if (this==null){
            BSTree a=new BSTree();
            BSTree a1=new BSTree(address,size,key);
            a.right=a1;
            a1.parent=a;
            return a1;
        }
        BSTree a=this;
        while(a.parent!=null){
            a=a.parent;
        }
        a=a.right;
        if(a==null){
            BSTree a1=new BSTree(address,size,key);
            this.right=a1;
            a1.parent=this;
            return a1;
        }
        //a=a.right;
        BSTree b=a;
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
        BSTree c=new BSTree(address,size,key);
        if(t){
            b.right=c;
            c.parent=b;
        }
        else{
            b.left=c;
            c.parent=b;
        }
        return c;
    }


    private boolean deleteNode(BSTree c,BSTree t){
        //System.out.println("100%");
        if (c.right==null&&c.left==null){
            if (t!=c){
                BSTree d=c.parent;
                if (d.right==c){
                    d.right=null;
                    c.parent=null;
                    return true;
                }
                else{
                    d.left=null;
                    c.parent=null;
                    return true;
                }
            }
            else{
                BSTree d=c.parent;
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
                    return true;
                }
                else{
                    if(d.parent.right==d){
                        d.parent.right=c;
                        return true;
                    }
                    else{
                        d.parent.left=c;
                        return true;
                    }
                }
            }
        }
        else if (c.right==null&&c.left!=null){
            BSTree d=c.left;
            while (d.right!=null){
                d=d.right;
            }
            c.key=d.key;
            c.address=d.address;
            c.size=d.size;
            return this.deleteNode(d,t);
        }
        else {
            BSTree d=c.getNext();
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
        BSTree current=this;
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
        
    public BSTree Find(int key, boolean exact)
    { 
        //BSTree current = this.getFirst();
        BSTree current=this;
        while(current.parent!=null){
            current=current.parent;
        }
        current=current.right;
        if (current==null){
            return null;
        }
        if (exact){
        BSTree prev=current;
        while (current!=null){
            if (current.key==key){
                prev=current;
                current=current.left;
                continue;
            }
            if (current.key>key){
                current=current.left;
            }
            else{
                current=current.right;
            }
            //current=current.getNext();
        }
        if(prev.key==key){
            return prev;
        }
        return null;
        }
        else{
            BSTree prev=current;
            while (current!=null){
            if (current.key<key){
                current=current.right;
                continue;
            }
            if(current.key>=key){
                prev=current;
                current=current.left;
            }
            //current=current.getNext();
        }
        if(prev.key>=key){
            return prev;
        }
        return null;
        }
    }

    public BSTree getFirst()
    { 
        if (this==null){
            return null;
        }
        BSTree a=this;
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
    }

    public BSTree getLast()
    { 
        if (this==null){
            return null;
        }
        BSTree a=this;
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

    public BSTree getNext()
    { 
        if (this==null){
            return null;
        }
        if(this.parent==null){
            return null;
        }
        if (this.right!=null){
            BSTree a=this.right;
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
                BSTree a=this.parent;
                BSTree b=this;
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

    private boolean checkValid(int k1,int k2,int a1,int a2){
        boolean l=true;
        boolean r=true;
        l=(this.key>k1)||(this.key==k1&&this.address>=a1);
        r=(this.key<k2)||(this.key==k2&&this.address<=a2);
        if (this.left!=null){
            l=l&&this.left.parent==this;
            l=l&&this.left.checkValid(k1,this.key,a1,this.address);
        }
        if (this.right!=null){
            r=r&&this.right.parent==this;
            r=r&&this.right.checkValid(this.key,k2,this.address,a2);
        }
        return l&&r;
    }

    public boolean sanity()
    { 
        if(this==null){
            return true;
        }
        //return true;
        BSTree a=this;
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
        BSTree min=this.getFirst();
        BSTree max=this.getLast();
        return a.checkValid(min.key,max.key,min.address,max.address);
    }

}


 


