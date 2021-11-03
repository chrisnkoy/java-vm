package assignment3;
/**
 * Class to encoding instructions
 */
public class Instruction {
    // TODO populate class fields and the constructor
    int instructionId;
    String instructionType;
    int objectId;

    public Instruction(int instructionId, String instructionType, int objectId){
    	this.instructionId = instructionId;
    	this.instructionType = instructionType;
    	this.objectId = objectId;
    }

    public String getInstructionType(){
    	return this.instructionType;
    }

    public int getObjectId(){
    	return this.objectId;
    }

    public int getInstructionId(){
    	return this.instructionId;
    }

    public String toString(){
    	return "\""+instructionId+" "+instructionType+" "+objectId+"\"";
    }
}
