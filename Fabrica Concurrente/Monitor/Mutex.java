import java.util.concurrent.Semaphore;

public class Mutex {
	Semaphore mutex;
	
	public Mutex(){
		this.mutex = new Semaphore(1,true);
	}
	
	
	
}
