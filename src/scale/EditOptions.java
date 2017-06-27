/*
 * Alena Marchuk
 * CIS 35B
 */

package scale;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.util.ArrayList;
import adapter.Fleet;
import adapter.ProxyAuto;
import model.Automobile; 

public class EditOptions extends ProxyAuto implements Runnable{
	private static Fleet<String, Automobile> fleet;  
	private  Thread thread; 
	static private int threadNo = 0; 
	private String autoKey; 
	private String[] options; 
	private boolean isSync; 
	int opNo; 
	
	/*
	 * tried to show thread corruption
	 * further implementation is grayed out in adapter.Fleet Class 
	* public static ArrayList<Thread> threads = new ArrayList<>(); 
	* public static ArrayList<Character> showAtomic = new ArrayList<>(); 
	*/
	
	public Thread getThread(){
		return thread; 
	}
	
	public EditOptions(String auto, int opNo, String[] options, boolean isSync){
		++threadNo; 
		fleet = getFleet(); 
		thread = new Thread(this, String.valueOf(threadNo)); 
		//threads.add(thread); 
		System.out.printf("\nNEW THREAD IS CREATED: %s\n", thread.getName()); 
		this.autoKey = auto; 
		this.opNo = opNo; 
		this.options = options; 
		this.isSync = isSync; 
		thread.start(); 
	}
	
	public synchronized void updateOptSetName(String autoKey, String optSetName, String newName){
			fleet.updateOptSetName(autoKey, optSetName, newName);
	}
	
	public  synchronized void updateOptionName(String autoKey, String optSetName, String optionName, String newName){
			fleet.updateOptionName(autoKey, optSetName, optionName, newName);
	}
	
	public synchronized void updateOptionPrice(String autoKey, String optSetName, String optionName, float newPrice){
			fleet.updateOptionPrice(autoKey, optSetName, optionName, newPrice);
	}
	
	public synchronized void deleteOptSet(String autoKey, String optSetName){
		fleet.deleteOptSet(autoKey, optSetName);
	}
	
	public synchronized void addOptSet(String autoKey, String optSetName){
		fleet.addOptSet(autoKey, optSetName);
	}
	
	public synchronized void deleteOption(String autoKey, String optSetName, String optionName){
		fleet.deleteOption(autoKey, optSetName, optionName);
	}
	
	public synchronized void addOption(String autoKey, String optSetName, String optionName, float optionPrice){
		fleet.addOption(autoKey, optSetName, optionName, optionPrice);
	}
	    
    //Synchronous edit methods: calls EditOptions synchronized mehtods 
	//Print the fleet right after it's been changed by a thread to track changes 
	public void syncEditOptions(String autoKey, int opNo, String[] args){
			randomWait(); 
			switch(opNo){
			case 1: 
				updateOptSetName(autoKey, args[0], args[1]); 
				break; 
			case 2: 
				updateOptionName(autoKey, args[0], args[1], args[2]); 
				break; 
			case 3: 
				updateOptionPrice(autoKey, args[0], args[1], Float.valueOf(args[2])); 
				break; 
			case 4: 
				deleteOptSet(autoKey, args[0]); 
				break; 
			case 5: 
				addOptSet(autoKey, args[0]); 
				break; 
			case 6: 
				deleteOption(autoKey, args[0], args[1]); 
				break; 
			case 7: 
				addOption(autoKey, args[0], args[1], Float.valueOf(args[2])); 
				break; 
			default: 
				log(" editOptions(): opNo is incorrect."); 	
			}
			System.out.printf("\nSYNCHRONOUS CHANGE IMPLEMENTED BY THREAD %s\n", thread.getName()); 
			printFleet(); 
	}
	
	//Asynchronous edit methods: calls <<Updatable>> asynchronous implementations inherited from ProxyAuto
	//Print the fleet right after it's been changed by a thread to track changes 
	public void asyncEditOptions(String autoKey, int opNo, String[] args){
		randomWait(); 
		switch(opNo){
		case 1: 
			super.updateOptSetName(autoKey, args[0], args[1]); 
			break; 
		case 2: 
			super.updateOptionName(autoKey, args[0], args[1], args[2]); 
			break; 
		case 3: 
			super.updateOptionPrice(autoKey, args[0], args[1], Float.valueOf(args[2])); 
			break; 
		case 4: 
			super.deleteOptSet(autoKey, args[0]); 
			break; 
		case 5: 
			super.addOptSet(autoKey, args[0]); 
			break; 
		case 6: 
			super.deleteOption(autoKey, args[0], args[1]); 
			break; 
		case 7: 
			super.addOption(autoKey, args[0], args[1], Float.valueOf(args[2])); 
			break; 
		default: 
			log(" editOptions(): opNo is incorrect."); 	
		}
		System.out.printf("\nASYNCHRONOUS CHANGE IMPLEMENTED BY THREAD %s", thread.getName()); 
		printFleet(); 
	}
	
    void randomWait() {
        try {
        		System.out.printf("\nRANDOM WAIT IS IMPLEMENTED ON THREAD %s\n", thread.getName()); 
        		long num = (long)(3000*Math.random()); 
			Thread.sleep(num);
			//System.out.println("\nRandom Number is %d ", num); 
        } catch(InterruptedException e) {
            System.out.printf("\nTHREAD %s IS INTERRUPTED\n", thread.getName());
        }
    }   
	
	@Override
	public void run() {
		if (thread.isAlive())
			System.out.printf("\nTHREAD %s IS RUNNING\n", thread.getName()); 
		if (isSync){
			syncEditOptions(autoKey, opNo, options); 
		}
		else{
			asyncEditOptions(autoKey, opNo, options); 
		}
		
		/*
		 * Waits at least 5000 millis for this thread to die; 
		 * Per Java API, join() uses loop of this.wait() calls conditioned on this.isAlive(). 
		 * When a thread terminates, this.notify() is invoked. It's recommended
		 * not to use wait(), notify(), or notifyAll() on Thread instances. 
		 */
		try{
			thread.join(5000); 
		}
		catch(InterruptedException e){
			 System.out.printf("\nTHREAD %s IS INTERRUPTED\n", thread.getName());
		}
	}
	
	@Override
	public void log(String error) {
	FileWriter file = null; 
	BufferedWriter buffer = null; 
	try{
		file = new FileWriter(LOGFILE, true); 
		buffer = new BufferedWriter(file); 
		StringBuffer str = new StringBuffer(new java.util.Date().toString()); 
		str.append(" @ EditOptions Class: "); 
		str.append(error); 
		buffer.write(str.toString());
		buffer.write("\n"); 
		buffer.close(); 
	}
	catch(Exception e){
		log(e.toString()); 
	}
}
}
