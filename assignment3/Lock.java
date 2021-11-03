package assignment3;
public class Lock{
	boolean isLocked;
	public Lock(){
		isLocked = false;
	}

	/*Method that implements locking mechanism*/
	public void lock() throws InterruptedException{
		synchronized(this){
			if(isLocked){
				wait();
			}
			isLocked = true;
		}
	}

	/*Method that implements unlocking mechanism*/
	public void unlock(){
		synchronized(this){
			isLocked = false;
			notifyAll();
		}
	}
}