// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
    	A1List a1=new A1List(address,size,key);
    	if (this.key==-1&&this.next==null){
    		//System.err.println("Insertion after tail");
    		return null;
    	}
    	if (this==null){
    		A1List a=new A1List();
    		A1List a2= a.next;
    		a.next=a1;
    		a1.prev=a;
    		a1.next=a2;
    		a2.prev=a1;
    		return a1;
    	}
    	a1.prev=this;
    	a1.next=this.next;
    	A1List a2=this.next;
    	a2.prev=a1;
    	this.next=a1;
        return a1;
    }

    public boolean Delete(Dictionary d) 
    {
    	if (d==null){
    		return false;
    	}
    	boolean t=false;
    	A1List a=this;
    	//A1List head=this.getFirst();
        //A1List a=head.getNext();
    	while(a!=null&&!(a.next==null&&a.key==-1)){
    		if (a.key==d.key&&a.address==d.address&&a.size==d.size){
    			if(this==a){
    				if (this.next.next==null) {
    					this.key=this.next.key;
    					this.address=this.next.address;
    					this.size=this.next.size;
    					this.next.prev=null;
    					this.next=null;
    					return true;
    				}
    				else{
    					this.key=this.next.key;
    					this.address=this.next.address;
    					this.size=this.next.size;
    					a=a.getNext();
    				}
       			}
       			if (a.next==null){
       				a.prev.next=null;
       				return true;
       			}
       			if (a.prev==null){
       				a.next.prev=null;
       			}
    			A1List a1=a.next;
    			A1List a2=a.prev;
    			a2.next=a1;
    			a1.prev=a2;
    			return true;
    		}
    		a=a.getNext();
    	}
    	while(a!=null&&!(a.prev==null&&a.key==-1)){
    		if (a.key==d.key&&a.address==d.address&&a.size==d.size){
    			if(this==a){
    				if (this.next.next==null) {
    					this.key=this.next.key;
    					this.address=this.next.address;
    					this.size=this.next.size;
    					this.next.prev=null;
    					this.next=null;
    					return true;
    				}
    				else{
    					this.key=this.next.key;
    					this.address=this.next.address;
    					this.size=this.next.size;
    					a=a.getNext();
    				}
       			}
       			if (a.next==null){
       				a.prev.next=null;
       				return true;
       			}
       			if (a.prev==null){
       				a.next.prev=null;
       			}
    			A1List a1=a.next;
    			A1List a2=a.prev;
    			a2.next=a1;
    			a1.prev=a2;
    			return true;
    		}
    		a=a.prev;
    	}
        return t;
    }

    public A1List Find(int k, boolean exact)
    { 
    	A1List a=this.getFirst();
    	//a=a.getNext();
    	while(a!=null&&!(a.next==null&&a.key==-1)){
    		if ((a.key==k&&exact==true)||(a.key>=k&&exact==false)){
    			return a;
    		}
    		a=a.getNext();
    	}
        return null;
    }

    public A1List getFirst()
    {
    	A1List a=this;
        A1List c=a;
    	while(a.prev!=null){
    		a=a.prev;
            if(a==c){
                //System.err.println("cycle present");
                return null;
            }
    	}
    	if (a.key==-1){
    		return a.next;
    	}
    	//System.err.println("First node not headsentinal");
        return null;
    }
    
    public A1List getNext() 
    {
    	if (this!=null){
        	return this.next;
    	}
    	return null;
    }

    public boolean sanity()
    {
    	//A1List head = this.getFirst();
    	//head=head.prev;
    	if (this==null){
    		return true;
    	}
    	A1List a=this;
        A1List c=a;
    	while(a!=null&&c!=null&&c.prev!=null){
    		a=a.prev;
    		c=c.prev.prev;
            if(a==c){
                //System.err.println("cycle present");
                return false;
            }
    	}
    	a=this;
    	while(a.prev!=null){
    		if(a.prev.next!=a){
    			return false;
    		}
    		a=a.prev;
    	}
    	if (a.key!=-1){
    		return false;
    	}
    	a=this;
    	c=a;
      	while(a!=null&&c!=null&&c.next!=null){
    		a=a.next;
    		c=c.next.next;
    		if(a==c){
    			return false;
    		}
    	}
        a=this;
    	while(a.next!=null){
    		if (a.next.prev!=a){
    			//System.err.println("Deque has branches");
    			return false;
    		}
    		a=a.next;
    	}
    	if (a.key!=-1){
    		//System.out.println("tail sentinel not last node");
    		return false;
    	}
        return true;
    }
    //return true;
	//}
}


