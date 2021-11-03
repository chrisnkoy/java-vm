package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * A super class for our VM threads
 * see the Assignment 3 specifications for the purpose
 */
public class OurVMThread implements Runnable {
    //TODO Complete class definition, alter constructor as necessary
    static LinkedList <Instruction> instructionsArea;
    static ArrayList <MemoryObject> memoryArea;
    public static boolean flag;//flag to check if end of input file is reached
    public static Lock lock;
    /**
     * Modify this constructor
     * @param instructionsArea
     * @param memoryArea
     */
    public OurVMThread(LinkedList instructionsArea, ArrayList memoryArea){
        this.instructionsArea = instructionsArea;
        this.memoryArea = memoryArea;

    }
    public OurVMThread(Lock lock, LinkedList instructionsArea, ArrayList memoryArea){
        this.instructionsArea = instructionsArea;
        this.memoryArea = memoryArea;
        this.lock = lock;

    }
    
    @Override
    public void run() {
        // This method is just to allow for polymorphism, specific VMThread run()
        // should be in the corresponding files
        
    }

}
