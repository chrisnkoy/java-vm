package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;

public class InstructionsThread extends OurVMThread{
    // TODO add necessary fields and modify constructor

    public InstructionsThread(Lock lock, LinkedList<Instruction> instructionsArea,ArrayList<MemoryObject> memoryArea){
        super(lock, instructionsArea,memoryArea);
    }
    @Override
    public void run(){
        synchronized(super.instructionsArea){
            // TODO implement instructions functionality
            // TODO get instructions from instruction area
            // TODO perform operations on the objects depending on the instruction
            // i.e MemoryObject = new MemoryObject if the instruction is "create"
            // TODO add relevant printing
            // TODO handle printing error if the object is not there while using or deleting
            if(!instructionsArea.isEmpty()){
                String action = super.instructionsArea.peek().getInstructionType();//look at first instruction's action to take
                switch(action){
                    case "create":
                        MemoryObject memoryObject = new MemoryObject(super.instructionsArea.peek().getObjectId());// create object
                        super.memoryArea.add(memoryObject); // add object to memory
                        super.instructionsArea.remove();//remove first instruction in the queue after execution
                        System.out.println(memoryObject+" is added to memory");
                        break;

                    case "use":
                        boolean wasUsed = false;
                        if(!super.memoryArea.isEmpty()){
                            for(int i = 0; i < memoryArea.size(); i++){
                                if(super.instructionsArea.peek().getObjectId() == super.memoryArea.get(i).getObjectId()){
                                    super.memoryArea.get(i).use();
                                    wasUsed = true;// set flag if object was used
                                }
                            }       
                        }
                        if (!wasUsed){
                            System.out.println("Error: Cannot use Object "+super.instructionsArea.peek().getObjectId()+ ". It does not currently exist in memory");
                        }
                        super.instructionsArea.remove();//remove first instruction in the queue after executing it
                        break;

                    case "delete":
                        try{
                            super.lock.lock();//acquire lock on memory area, a shared resource with garbage collection thread
                            boolean wasDeleted = false;
                            if(!super.memoryArea.isEmpty()){
                                for(int i = 0; i < memoryArea.size(); i++){
                                    if(super.instructionsArea.peek().getObjectId() == super.memoryArea.get(i).getObjectId()){
                                        super.memoryArea.get(i).delete();                                    
                                        wasDeleted = true;    
                                        break;//break out of for loop
                                    }
                                }       
                            }
                            if (!wasDeleted){
                                System.out.println("Error: Cannot delete Object "+super.instructionsArea.peek().getObjectId()+ ". It does not currently exist in memory");
                            }
                            super.instructionsArea.remove();//remove instruction
                        } catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        super.lock.unlock();//release lock
                        break;
                }
            }
            else{
                System.out.println("There are currently no instructions in the program area");
            }
        }
    }

}