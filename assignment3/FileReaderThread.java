package assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileReaderThread extends OurVMThread{
    BufferedReader r;
    //static boolean hasLock;
    /** 
    * Constructor for a FileReader ojbect - uncomment
    * @param r - buffered reader, that rememebers the position of cursor
    */

    public FileReaderThread(BufferedReader r, LinkedList<Instruction> instructionsArea, ArrayList<MemoryObject> memoryArea){
        super(instructionsArea,memoryArea);
        this.r = r;
    }

    public FileReaderThread(LinkedList<Instruction> instructionsArea,ArrayList<MemoryObject> memoryArea){
        super(instructionsArea,memoryArea);
    }

    // public void lock() throws InterruptedException{
    //     synchronized(FileReaderThread.class){
    //         while(hasLock){
    //             wait();
    //         }
    //         hasLock = true;
    //     }
    // }

    // public void unlock() throws IllegalMonitorStateException{
    //     synchronized(FileReaderThread.class){
    //         hasLock = false;
    //         notifyAll();
    //     }
    // }

    @Override
    public void run(){
        synchronized(super.instructionsArea){
            //lock();

            String line = "Nothing to read";

            // Some error handling, may change it if you will
            if (r == null){
                System.out.println(line);
                return;
            }
             
            // TODO unless properly handled, eof shou
                try {
                
                    line = r.readLine();
                    // TODO hanlde instruction creation
                    if(line != null){
                        String [] temp = line.split(" ");
                        Instruction instruction = new Instruction(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]));
                        // TODO handle placing the instruction to the instruction
                        // area
                        System.out.println("Storing "+instruction+" instruction into program memory...");
                        if(!super.instructionsArea.add(instruction)){
                            System.out.println(instruction+" could not be added to instructionsArea");
                        }
                    }
                    // TODO handle last instruction signal
                    else{
                        System.out.println("Instructions file is empty");
                        super.flag = true;
                    }
        

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        // } catch(InterruptedException e){
        //     e.printStackTrace();
        
        // } catch(IllegalMonitorStateException e){
        //     e.printStackTrace();

        // } finally{
        //     unlock();   
        // }
    
    
        

    }


}