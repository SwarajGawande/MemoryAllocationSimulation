// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        if (this.freeBlk==null){
            return;
        }
        A2DynamicMem fr=new A2DynamicMem(this.M,this.type);
        Dictionary freead=fr.allocBlk;
        Dictionary c=this.freeBlk.getFirst();
        if(c==null){
            return;
        }
        Dictionary d=freead.Insert(c.address,c.size,c.address);
        while(c.getNext()!=null){
            c=c.getNext();
            d=freead.Insert(c.address,c.size,c.address);
        }
        Dictionary a=freead.getFirst();
        while(a.getNext()!=null){
            Dictionary b=a.getNext();
            if (a.address+a.size==b.address){
                int a1=a.address;
                int a2=a.size;
                int b2=b.size;
                int b1=b.address;
                a.key=a.size;
                b.key=b.size;
                if(this.freeBlk.Delete(a)&&this.freeBlk.Delete(b)){
                    a.key=a1;
                    b.key=b1;
                    if(freead.Delete(a)){
                        b=freead.Find(b1,true);
                        boolean t=freead.Delete(b);
                        Dictionary f=this.freeBlk.Insert(a1,a2+b2,a2+b2);
                        Dictionary e=freead.Insert(a1,a2+b2,a1);
                        a=e;
                    }
                    else{
                        //System.err.println("unable to delete in freead");
                        return ;
                    }
                    continue;
                }
                else{
                    //System.err.println("unable to delete in freeBlk");
                }
            }
            a=b;
        }
        a=null;
        d=null;
        freead=null;
        return ;
    }
}