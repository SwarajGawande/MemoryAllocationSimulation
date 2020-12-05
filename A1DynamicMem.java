// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (Dictionary.java).

    public int Allocate(int blockSize) {
    	if (blockSize<=0){
    		//System.err.println("blockSize is not valid");
    		return -1;
    	}
        if (blockSize>1000000){
            //System.err.println("Asked size too large");
            return -1;
        }
        Dictionary current =this.freeBlk.Find(blockSize,false);
        if (current==null){
            //System.out.println("No block available with enough size");
            return -1;
        }
        int ad=current.address;
        A1DynamicMem a1=new A1DynamicMem(blockSize);
        Dictionary head =this.allocBlk.Insert(ad,blockSize,ad);
        if (current.size==blockSize){
            if (current.Delete(current)){
                return ad;
            }
            else {
                //System.err.println("Freeblock to be allocated not found0");
                return -1;
            }
        }
        if (current.size>blockSize){
            Dictionary ci=this.freeBlk.Insert(ad+blockSize,(current.size)-blockSize,(current.size)-blockSize);
            if (this.freeBlk.Delete(current)){
                return ad;
            }
            else {
                //System.err.println("Freeblock to be allocated not found1");
                return -1;
            }
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
    	if (startAddr<0){
    		//System.err.println("invalid startAddr");
    		return -1;
    	}
    	Dictionary f=this.allocBlk.Find(startAddr,true);
    	if (f==null){
    		//System.err.println("No block with starting address startAddr");
    		return -1;
    	}
    	int sizef =f.size;
    	Dictionary current =(this.freeBlk).Insert(startAddr,sizef,sizef);
    	if (f!=null&&f.Delete(f)){
            return 0;
        }
        else {
            //System.err.println("Freeblock to be allocated not found2");
            return -1;
        }
    }
}