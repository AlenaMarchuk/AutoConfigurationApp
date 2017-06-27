/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package adapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class ProxyAuto implements Loggable {
	private static Fleet<String, Automobile> fleet = new Fleet<>(); 
	
	public void buildAuto(String autoType, String filename) throws AutoException{
		FileIO file = new FileIO(); 
		try{
			if (autoType.equalsIgnoreCase("Coupe")){
				Coupe auto =  (Coupe)file.buildAuto(new Coupe(), filename); 				
				StringBuffer key = new StringBuffer(auto.getMake()); 
				key.append(auto.getModel()); 
				key.append(auto.getAutoType()); 
				fleet.addAuto(key.toString(), auto); 
			}
			else if (autoType.equalsIgnoreCase("Sedan")){
				Sedan auto = (Sedan)file.buildAuto(new Sedan(), filename); 
				StringBuffer key = new StringBuffer(auto.getMake()); 
				key.append(auto.getModel()); 
				key.append(auto.getAutoType()); 
				fleet.addAuto(key.toString(), auto); 
			}
			else if (autoType.equalsIgnoreCase("SUV")){
				SUV auto = (SUV)file.buildAuto(new SUV(), filename); 
				StringBuffer key = new StringBuffer(auto.getMake()); 
				key.append(auto.getModel()); 
				key.append(auto.getAutoType()); 
				fleet.addAuto(key.toString(), auto); 
			}
			else if (autoType.equalsIgnoreCase("Truck")) {
				Truck auto = (Truck)file.buildAuto(new Truck(), filename); 
				StringBuffer key = new StringBuffer(auto.getMake()); 
				key.append(auto.getModel()); 
				key.append(auto.getAutoType()); 
				fleet.addAuto(key.toString(), auto); 
			}
			else{
				log(" buildAuto(): undefined automobile type."); 
			}
		}
			catch(AutoException e){		
				try{
					e.fix(e.getErrorNo());
				}
				catch(AutoException b){
					if (autoType.equalsIgnoreCase("Coupe")){
						Coupe auto =  (Coupe)file.buildAuto(new Coupe(), b.getFix()); 				
						StringBuffer key = new StringBuffer(auto.getMake()); 
						key.append(auto.getModel()); 
						key.append(auto.getAutoType()); 
						fleet.addAuto(key.toString(), auto); 
					}
					else if (autoType.equalsIgnoreCase("Sedan")){
						Sedan auto = (Sedan)file.buildAuto(new Sedan(), b.getFix()); 
						StringBuffer key = new StringBuffer(auto.getMake()); 
						key.append(auto.getModel()); 
						key.append(auto.getAutoType()); 
						fleet.addAuto(key.toString(), auto); 
					}
					else if (autoType.equalsIgnoreCase("SUV")){
						SUV auto = (SUV)file.buildAuto(new SUV(), b.getFix()); 
						StringBuffer key = new StringBuffer(auto.getMake()); 
						key.append(auto.getModel()); 
						key.append(auto.getAutoType()); 
						fleet.addAuto(key.toString(), auto); 
					}
					else if (autoType.equalsIgnoreCase("Truck")) {
						Truck auto = (Truck)file.buildAuto(new Truck(), b.getFix()); 
						StringBuffer key = new StringBuffer(auto.getMake()); 
						key.append(auto.getModel()); 
						key.append(auto.getAutoType()); 
						fleet.addAuto(key.toString(), auto); 
					}
					else{
						log(" buildAuto(): undefined automobile type."); 
					}
				}
			}
	}
	
	//Updatable wrappers 
	public void updateOptSetName(String autoKey, String optSetName, String newName) {
		fleet.updateOptSetName(autoKey, optSetName, newName);
	}
	
	public void updateOptionName(String autoKey, String optSetName, String optionName, String newName){
		fleet.updateOptionName(autoKey, optSetName, optionName, newName);
	}
	
	public void updateOptionPrice(String autoKey, String optSetName, String optionName, float newPrice){
		fleet.updateOptionPrice(autoKey, optSetName, optionName, newPrice);
	}
	
	public void addOptSet(String autoKey, String optSetName){
		fleet.addOptSet(autoKey, optSetName); 
	}
	
	public void addOption(String autoKey, String optSetName, String optionName, float optionPrice){
		fleet.addOption(autoKey, optSetName, optionName, optionPrice);
	}
	
	public void deleteOptSet(String autoKey, String optSetName){
		fleet.deleteOptSet(autoKey, optSetName);
	}
	
	public void deleteOption(String autoKey, String optSetName, String optionName){
		fleet.deleteOption(autoKey, optSetName, optionName); 
	}
	
	//Selectable wrappers
	public void selectOption(String autoKey, String optSetName, String optionName){
		fleet.selectOption(autoKey, optSetName, optionName);
	}
	
	public String getOptionChoice(String autoKey, String optSetName){
		return fleet.getOptionChoice(autoKey, optSetName); 
	}
	
	public float getTotalPrice(String autoKey){
		return fleet.getTotalPrice(autoKey); 
	}
	
	public void deleteSelection(String autoKey, String optSetName){
		fleet.deleteSelection(autoKey, optSetName);
	}
	
	public void clearSelection(String autoKey){
		fleet.clearSelection(autoKey);
	}
	
	public void printSelection(String autoKey){
		fleet.printSelection(autoKey);
	}
	
	public void printAuto(String autoKey){
		fleet.printAuto(autoKey); 
	}
	
	public void printFleet(){
		fleet.printFleet();
	}

	@Override
		public void log(String error) {
		FileWriter file = null; 
		BufferedWriter buffer = null; 
		try{
			file = new FileWriter(LOGFILE, true); 
			buffer = new BufferedWriter(file); 
			StringBuffer str = new StringBuffer(new java.util.Date().toString()); 
			str.append(" @ ProxyAuto Class: "); 
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
