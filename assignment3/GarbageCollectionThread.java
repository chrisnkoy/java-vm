package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Class that will perform the traversal of the memory area region
 * deleting MemoryObjects from deletion
 */
public class GarbageCollectionThread extends OurVMThread {
    // TODO add fields if necessary, modify constructor if necessary
    public GarbageCollectionThread(Lock lock, LinkedList<Instruction> instructionsArea,ArrayList<MemoryObject> memoryArea){
        super(lock, instructionsArea,memoryArea);
    }
    @Override
    public void run(){
        try{
            super.lock.lock();//lock on shared resource "Memory area" in order to synchronized carbage collection threads
                              // among themselves and also with the "delete" block from instructions thread
            //TODO implement functionality
            // TODO add relevant printing
            System.out.println("Garbage Collector is searching through object memory...");
            for(int i = 0; i < memoryArea.size(); i++){
                if(super.memoryArea.get(i).isDeleted()){
                    System.out.println("Garbage collector removed "+super.memoryArea.remove(i)+" from memory");                
                }
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        super.lock.unlock();//release lock


    }


}