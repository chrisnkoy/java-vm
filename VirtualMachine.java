import assignment3.*;
import java.util.ArrayList;
import java.util.LinkedList;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class VirtualMachine{
	// Storage for instructions - replace with an  ADT of choice
	static LinkedList <Instruction> instructionsStorage;
	// Storage for memory - replace with an  ADT of choice
	static ArrayList <MemoryObject> objectsInMemory;
	static boolean flag;//flag to check if end of input file is reached
	static Lock lock = new Lock();

	//Method to check if an array of OurVMThread contains an instance InstructionsThread
	//up to a given index
	static boolean hasInstructionsThreadInstance(OurVMThread [] t, int index){
		for(int i = 0; i < index; i++){
			if(t[i] instanceof InstructionsThread){
				//System.out.println("THERE IS ONE INSTANCE OF INSTRUCTIONS THREAD, SO NONE WILL BE CREATED");
				return true;		
			}
		}
		//System.out.println("NO INSTANCE OF INSTRUCTIONS THREAD, THEREFORE ONE IS CREATED");
		return false;
	}


     
  
	public static void main(String[] args) {
	    if (args.length != 2 )
	    {
	      System.out.println("Please provide input filename and number of threads as the first and second argument respectively.\nExample execution(Linux):\njava VirtualMachine ./inputs/input.txt 3\nExample execution(Windows):\njava VirtualMachine .\\inputs\\input.txt 3");
	      System.exit(0);
	    }
	    Integer maxNumberOfThreads = Integer.parseInt(args[1]);
	    //System.out.println("Number of threads: "+maxNumberOfThreads);
	    CPU ourCPU = new CPU(maxNumberOfThreads);
	    BufferedReader br = null;
	    try {
	      // The following four lines were taken and modified
	      // from https://www.programmersought.com/article/3743304911/

	      String root = System.getProperty("user.dir");
	      String FileName=args[0];
	      String filePath = root+File.separator+FileName;
	      FileReader fr = new FileReader(filePath);
	      br = new BufferedReader(fr);
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	      System.exit(0);
	    }
	    boolean programFinished = false;
	    Random r = new Random();
	    
	    // Initialize instructionsStorage and objectsInMemory
	    instructionsStorage = new LinkedList<>();
	    objectsInMemory = new ArrayList<>();
	    
	    // Loop for as long as the programFinished is false
	    while (!programFinished)
	    {
	      int threadsAllotted = ourCPU.getNumberOfAvailableThreads();
	      System.out.println("Our CPU has "+ threadsAllotted+" available threads");
	      Thread[] javaThread = new Thread [threadsAllotted];
	      OurVMThread [] t = new OurVMThread [threadsAllotted];
	      for (int i = 0 ; i < threadsAllotted; i++){
	        Integer threadType = r.nextInt(3);
	        t[i]= null; 
	        switch(threadType){
	          case 0:
	          t[i] = new FileReaderThread(br, instructionsStorage,objectsInMemory);
	          break;

	          case 1:
	          //The if statement makes sure there is never more than one InstructionsThread
	          if(!hasInstructionsThreadInstance(t, i)){
	          	t[i] = new InstructionsThread(lock, instructionsStorage,objectsInMemory);
	          }
	          break;

	          case 2:
	          t[i] = new GarbageCollectionThread(lock, instructionsStorage,objectsInMemory);
	          break;

	        }

	        for(int j = 0; j < threadsAllotted; j++){
	        	javaThread[j] = new Thread(t[j]);
	        	javaThread[j].start();
	        }
	        
	      
	        try {
	          Thread.sleep(1000);
	          System.out.println("One CPU tick finished");
	          //Current state of memory
	          System.out.println("Instruction area: "+instructionsStorage.toString());
	          System.out.println("Memory area: "+objectsInMemory.toString());
	          System.out.println();
	          System.out.println();
	        } catch (InterruptedException e1) {
	          e1.printStackTrace();
	        }
	        
	        try {
	        	for(int j = 0; j < threadsAllotted; j++){
	          		javaThread[j].join();
	        	}
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	      }

	      //program is finished if end of input file is reached and program memory is empty
	      flag = OurVMThread.flag;
	      if(flag && instructionsStorage.isEmpty()){
	        programFinished = true;
	      }
	    }
	    // Print instruction area and memory area contents
	    System.out.println("\nMemory area and instruction area at the end of execution:");
	    System.out.println("Instruction area: "+instructionsStorage.toString());
	    System.out.println("Memory area: "+objectsInMemory.toString());
	}
}