package assignment3;
/**
 * This class is to be placed in memory area
 */
public class MemoryObject {
    //TODO complete the class
    boolean deleted;
    boolean used;
    int objectId;
    public MemoryObject(int id){
    	this.objectId = id;
    }

    public void delete(){
    	deleted = true;
    	System.out.println(this+" has been marked for deletion");
    }

    public void use(){
    	used = true;
    	System.out.println(this+" is used");
    	
    }

    public boolean isDeleted(){
    	return this.deleted;
    }

    public boolean isUsed(){
    	return this.used;
    }

    public int getObjectId(){
    	return this.objectId;
    }

    public String toString(){
    	return "Object "+objectId;
    }
}
