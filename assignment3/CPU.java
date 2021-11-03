package assignment3;

import java.util.Random;

public class CPU{

    private int maxCores;
    private Random r;
    /**
     * This is the default constructor for a CPU class object
     * @param cores - integer describing maximum number of cores
     * for part 2 - this value will be always one
     * for part 3 - this value will be changed for allowing multiple threads
     */
    public CPU(int cores){
        this.maxCores = cores;
        this.r = new Random();
    }
    /**
     * This function returns a number of threads that CPU "allocated"
     * for our machine - treat it like scheduler allocated threads
     * range [1..maxCores]
     * @return an integer
     */
    public int getNumberOfAvailableThreads(){
        int result = r.nextInt(maxCores)+1;
        return result;
    }
}