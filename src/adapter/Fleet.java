/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package adapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import model.Automobile; 

public class Fleet<K, V extends Automobile> implements Loggable{
	private LinkedHashMap<K, V> fleet; 
	
	private String autoCouldNotBeFoundMssg(K autoKey){
		return (String.format("\'%s\' could not be found.\n", autoKey.toString())); 
	}
	
		public Fleet(){
		fleet = new LinkedHashMap<>(); 
	}

	public LinkedHashMap<K, V> getFleet() {
		return fleet;
	}
	
	//Updatable implementations
	public void updateOptSetName(K autoKey, String optSetName, String newName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).updateOptSetName(optSetName, newName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void updateOptionName(K autoKey, String optSetName, String optionName, String newName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).updateOptionName(optSetName, optionName, newName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void updateOptionPrice(K autoKey, String optSetName, String optionName, float newPrice){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).updateOptionPrice(optSetName, optionName, newPrice);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void addOption(K autoKey, String optSetName, String optionName, float optionPrice){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).addOption(optSetName, optionName, optionPrice);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void addOptSet(K autoKey, String optSetName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).addOptSet(optSetName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void deleteOptSet(K autoKey, String optSetName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).deleteOptSet(optSetName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void deleteOption(K autoKey, String optSetName, String optionName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).deleteOption(optSetName, optionName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}

	public void addAuto(K autoKey, V auto){
		if (fleet.containsKey(autoKey))
			log(" addAuto(): attempt to add a duplicate auto.\n"); 
		else
			fleet.put(autoKey, auto); 
	}
	
	//Selectable implementations 
	public void selectOption(K autoKey, String optSetName, String optionName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).setOptionChoice(optSetName, optionName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public String getOptionChoice(K autoKey, String optSetName){
		if (fleet.containsKey(autoKey))
			return fleet.get(autoKey).getOptionChoice(optSetName); 
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
		return "Undefined Auto"; 
	}
	
	public float getTotalPrice(K autoKey){
		if (fleet.containsKey(autoKey))
			return fleet.get(autoKey).getTotalPrice(); 
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
		return 0; 
	}
	
	public void deleteSelection(K autoKey, String optSetName){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).deleteSelection(optSetName);
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void clearSelection(K autoKey){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).clearSelection();
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void printSelection(K autoKey){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).printChoices();
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void printAuto(K autoKey){
		if (fleet.containsKey(autoKey))
			fleet.get(autoKey).printAutomobile();
		else
			log(autoCouldNotBeFoundMssg(autoKey)); 
	}
	
	public void printFleet(){
		Collection<V> cars = fleet.values(); 
		Iterator<V> it = cars.iterator(); 
		
		while(it.hasNext()){
			V auto = (V)it.next(); 
			auto.printAutomobile();
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
			str.append(" @ Fleet Class: "); 
			str.append(error); 
			buffer.write(str.toString());
			//buffer.write("\n"); 
			buffer.close(); 
		}
		catch(Exception e){
			log(e.toString()); 
		}	
	}	
}
