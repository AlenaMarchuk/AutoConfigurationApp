/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package model;

import model.OptionSet.Option;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Arrays;

import util.FileIO;
import exception.AutoException;
import adapter.Debuggable;
import adapter.Loggable;

public class Automobile implements Serializable, Loggable{
	private String automobileName; 
	private float basePrice; 
	private OptionSet[] optSet;
	
	//private helper methods
	private boolean isOptSetNull(int idx){
		return (optSet[idx] == null ? true : false); 
	}
	
	private boolean isOptSetIdxValid(int idx){
		return (idx >= 0 && idx < optSet.length ? true : false); 
	}
	
	private int findOptSetIdx(String name){
		for (int x = 0; x < optSet.length; x++){
			if (!isOptSetNull(x) && optSet[x].getOptSetName().equalsIgnoreCase(name))
				return x; 
		}
		return -1; 
	}
	
	private String configSetCouldNotBeFoundMssg(String optSetName){
		return String.format("%s configuration set could not be found in %s.", optSetName, getAutomobileName()); 
	}
	
	private String arrIdxOutOfBoundsMssg(){
		return "Index is out of bounds of optSet[]."; 
	}
	
	private String optSetAtIdxNullMssg(int idx){
		return String.format("Option set at %d index is null", idx); 
	}
		
	//Constructors
	public Automobile(){} 
	
	public Automobile(String name, float basePrice, OptionSet[] optSet) throws AutoException{
		setAutomobileName(name);
		setBasePrice(basePrice);
		setOptSet(optSet);
	} 
	
	public Automobile(String name, float basePrice, int numOfConfigs) throws AutoException{
		setAutomobileName(name); 
		setBasePrice(basePrice); 
		setOptSet(numOfConfigs);  
	}

	//Getters
	public String getAutomobileName() {
		return automobileName;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public OptionSet[] getOptSet() {
		return optSet;
	}
	
	public OptionSet findOptSet(String name) {
		int idx = findOptSetIdx(name);
		if (idx != -1)
			return optSet[idx]; 
		else{
			log(" findOptSet() "  + configSetCouldNotBeFoundMssg(name)); 
			return null; 
		}		
	}
	
	public OptionSet findOptSet(int idx) {
		 if (isOptSetIdxValid(idx)){
				if (isOptSetNull(idx))
					log(optSetAtIdxNullMssg(idx)); 
			return optSet[idx]; 
		 }
		else
			log(" findOptSet() " + arrIdxOutOfBoundsMssg()); 
		 return null; 
	}
	
	public Option findOption(String optSetName, String opName){
		int idx = findOptSetIdx(optSetName);
		if (idx != -1)
			return optSet[idx].findOption(opName);  
		else
			log(" findOption() " + configSetCouldNotBeFoundMssg(optSetName)); 
		return null; 
	}
	
	public Option findOption(int optSetIdx, int optionIdx) {
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			return optSet[optSetIdx].findOption(optionIdx); 
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx)) 
			log(" findOption() " + optSetAtIdxNullMssg(optSetIdx)); 
		return null; 
	}

	//Setters
	public void setAutomobileName(String name) throws AutoException{
		if (!name.isEmpty())
			automobileName = name;
		else{
			FileIO file = new FileIO(); 
			log(file.getErrorMsg(2)); 
			file = null; 
			throw new AutoException(2); 
		}
	}

	public void setBasePrice(float basePrice) throws AutoException{
		try{
			this.basePrice = basePrice;
		}
		catch(NumberFormatException e){
			AutoException a = new AutoException(3); 
			FileIO file = new FileIO(); 
			log(file.getErrorMsg(3)); 
			file = null; 
			try{
				a.fix(a.getErrorNo());
			}
			catch(AutoException b){
				this.basePrice = Float.valueOf(b.getFix()); 
			}
		}
	}

	public void setOptSet(OptionSet[] optSet) {
		this.optSet = optSet;
	}
	
	public void setOptSet(int numOfConfigs) {
		if (numOfConfigs <= 0)
			log(" setOptSet() Total option set number for the model is negative or 0."); 
		else{
				optSet = new OptionSet[numOfConfigs]; 
				for (int x = 0; x < optSet.length; x++)
				optSet[x] = new OptionSet(); 
		}
	}
	
	public void setOptSet(int idx, OptionSet opSet){
		if (isOptSetIdxValid(idx))
			optSet[idx] = opSet; 
		else
			log(" setOptSet() " + arrIdxOutOfBoundsMssg()); 
	}
	

	public void setOptSetName(int optSetIdx, String name){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].setOptSetName(name);
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" setOptSetName() " + optSetAtIdxNullMssg(optSetIdx)); 
	}

	
	public void updateOptSetName(String optSetName, String newName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			setOptSetName(idx, newName);
		else
			log(" updateOptSet() " + configSetCouldNotBeFoundMssg(optSetName)); 
	}
	
	public void setNumOfOptions(int optSetIdx, int numberOfOptions){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].setOptions(numberOfOptions);
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" setNumOpOptions() " + optSetAtIdxNullMssg(optSetIdx)); 
		else
			log(" setNumOfOptions() " + arrIdxOutOfBoundsMssg());
	}
	
	public void setNumOfOptions(String optSetName, int numberOfOptions){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].setOptions(numberOfOptions); 
		else
			log(" setNumOpOptions() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOption(int optSetIdx, int optionsIdx, String name, float price){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].setOption(optionsIdx, name, price); 
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log("  setOption() " + optSetAtIdxNullMssg(optSetIdx)); 
		else
			log(" setOption() " + arrIdxOutOfBoundsMssg());
	}
	
	public void updateOption(String optSetName, String optionName, String newName, float newPrice) {
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOption(optionName, newName, newPrice); 
		else
			log(" updateOption() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOptionName(int optSetIdx, int optionIdx, String name){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].setOptionName(optionIdx, name); 
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" setOptionName() " + optSetAtIdxNullMssg(optSetIdx)); 
		else
			log(" setOptionName() " + arrIdxOutOfBoundsMssg()); 
	}
	
	public void updateOptionName(String optSetName, String optionName, String newName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOptionName(optionName,newName);
		else
			log(" updateOptionName() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOptionPrice(int optSetIdx, int optionIdx, float price){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].setOptionPrice(optionIdx, price); 
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" setOptionPrice() " + optSetAtIdxNullMssg(optSetIdx)); 
		else
			log(" setOptionPrice() " + arrIdxOutOfBoundsMssg()); 
	}
	
	public void updateOptionPrice(String optSetName, String optionName, float newPrice){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOptionPrice(optionName, newPrice);
		else
			log(" updateOptionPrice() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	//Update methods
	public void addOptSet(OptionSet newOptSet){
		boolean isAdded= false; 
		for (int x = 0; x < optSet.length && !isAdded; x++){
			if (isOptSetNull(x)){
				optSet[x] = newOptSet;
				isAdded = true; 
			}
		}
		if (!isAdded){
			log(" addOptSet(): Auto model configuration set is full."); 
			log(" addOptSet(): New option set could not be added.\n"); 
		}
	}
	
	public void addOption(String optSetName, String optionName, float optionPrice){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].addOption(optionName, optionPrice);
		else
			log(" addOption() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void addOption(int optSetIdx, String optionName, float optionPrice){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].addOption(optionName, optionPrice);
		else
			log(" addOption() " + arrIdxOutOfBoundsMssg()); 
	}
	
	//Delete methods
	public void deleteOptSet(String optSetName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx] = null; 
		else
			log(" deleteOptSet() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOptSet(int optSetIdx){
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx] = null;
		else
			log(" deleteOptSet() " + arrIdxOutOfBoundsMssg()); 
	}
	
	public void deleteOption(String optSetName, String optionName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].deleteOption(optionName);
		else
			log(" deleteOption() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOption(int optSetIdx, int optionsIdx){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].deleteOption(optionsIdx);
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" deleteOption() " + optSetAtIdxNullMssg(optSetIdx)); 
		else
			log(" deleteOption() " + arrIdxOutOfBoundsMssg()); 
	}

	//Print methods
	public void printAutomobile(){
		printAutoName(); 
		printBasePrice(); 
		printOptSet(); 
	}
	
	public void printAutoName(){
		System.out.printf("\nAuto model is %s", getAutomobileName()); 
	}
	
	public void printBasePrice(){
		System.out.printf("\n%s base price is $%,.2f", getAutomobileName(), getBasePrice()); 
	}
	
	public void printOptSet(){
		if (optSet != null){
			System.out.printf("\n%s configuration options and cost data:\n", getAutomobileName()); 
			//Arrays.sort(optSet); 
			for (int x = 0; x < optSet.length; x++){
				if (!isOptSetNull(x))
					optSet[x].print();
			}
		}
		else
			log(" printOptSet(): Option Set is null."); 
	}
	
	public void printOptSet(String optSetName){
		int idx = findOptSetIdx(optSetName);
		if (idx != -1)
			optSet[idx].print();
		else
			log(" printOptSet() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void printOptSet(int optSetIdx){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].print(); 
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" printOptSet() " + optSetAtIdxNullMssg(optSetIdx));
		else
				log(" printOptSet() " + arrIdxOutOfBoundsMssg()); 
	}
	
	public void printOption(int optSetIdx, int optionsIdx){
		if (isOptSetIdxValid(optSetIdx) && !isOptSetNull(optSetIdx))
			optSet[optSetIdx].print(optionsIdx); 
		else if (isOptSetIdxValid(optSetIdx) && isOptSetNull(optSetIdx))
			log(" printOpton() " + optSetAtIdxNullMssg(optSetIdx));
		else
			log(" printOption() " + arrIdxOutOfBoundsMssg()); 
	}
	
	public void printOption(String optSetName, String optionName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].print(optionName); 
		else
			log(" printOption() " + configSetCouldNotBeFoundMssg(optSetName));
	}

	@Override
	public String toString() {
		StringBuffer autoModelConfig = new StringBuffer(String.format("\nAuto model is %s", getAutomobileName())); 
		autoModelConfig.append(String.format("\n%s base price is $%,.2f", getAutomobileName(), getBasePrice())); 
		
		if (optSet != null){
			autoModelConfig.append(String.format("\n%s configuration options and cost data:\n\n", getAutomobileName())); 	
			for (int x = 0; x < optSet.length; x++){
				if (!isOptSetNull(x))
					autoModelConfig.append(optSet[x].toString());
			}	
		}
		return autoModelConfig.toString(); 
	}

	@Override
	public void log(String error) {
		FileWriter file = null; 
		BufferedWriter buffer = null; 
		try{
			file = new FileWriter(LOGFILE, true); 
			buffer = new BufferedWriter(file); 
			StringBuffer str = new StringBuffer(new java.util.Date().toString()); 
			str.append(" @ Automobile Class: "); 
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

