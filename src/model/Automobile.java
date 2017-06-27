/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import util.FileIO;
import exception.AutoException;
import adapter.Loggable;

public  abstract class Automobile implements Serializable, Loggable{
	private static final long serialVersionUID = 1L;
	private String make; 
	private String model; 
	private float basePrice; 
	private ArrayList<OptionSet> optSet;
	private ArrayList<Option> choices; 
	
	//private helper methods
	private boolean isOptSetIdxValid(int idx){
		return (idx >= 0 && idx < optSet.size() ? true : false); 
	}
	
	private int findOptSetIdx(String name){
		for (int x = 0; x < optSet.size(); x++){
			if (optSet.get(x).getOptSetName().equalsIgnoreCase(name) )
				return x; 
		}
		return -1; 
	}
	
	private void updateChoices(){
		for (int x = 0; x < optSet.size(); x++){
			if (optSet.get(x).getChoice() != null)
				choices.set(x, optSet.get(x).getChoice()); 
		}
	}
	
	private String configSetCouldNotBeFoundMssg(String optSetName){
		return String.format("\'%s\' configuration set could not be found in %s %s.", optSetName, getMake(), getModel()); 
	}
	
	private String idxOutOfBoundsMssg(int idx){
		return String.format("Index \'%d\' is out of bounds of ArrayList \'optSet\'.", idx); 
	}
		
	//Constructors
	public Automobile(){} 
	
	public Automobile(String make, String model, float basePrice, ArrayList<OptionSet> optSet) throws AutoException{
		setMake(make);
		setModel(model); 
		setBasePrice(basePrice);
		setOptSet(optSet);
	} 
	
	public Automobile(String make, String model, float basePrice, int numOfConfigs) throws AutoException{
		setMake(make); 
		setModel(model); 
		setBasePrice(basePrice); 
		setOptSet(numOfConfigs);  
	}

	//Getters
	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public ArrayList<OptionSet> getOptSet() {
		return optSet;
	}
	
	public ArrayList<Option> getChoices(){
		return choices; 
	}
	
	public OptionSet findOptSet(String name) {
		int idx = findOptSetIdx(name);
		if (idx != -1)
			return optSet.get(idx); 
		else{
			log(" findOptSet() "  + configSetCouldNotBeFoundMssg(name)); 
			return null; 
		}		
	}
	
	public OptionSet findOptSet(int idx) {
		 if (isOptSetIdxValid(idx))
			return optSet.get(idx); 
		else
			log(" findOptSet() " + idxOutOfBoundsMssg(idx)); 
		 return null; 
	}
	
	public Option findOption(String optSetName, String opName){
		int idx = findOptSetIdx(optSetName);
		if (idx != -1)
			return optSet.get(idx).findOption(opName);  
		else
			log(" findOption() " + configSetCouldNotBeFoundMssg(optSetName)); 
		return null; 
	}
	
	public Option findOption(int optSetIdx, int optionIdx) {
		if (isOptSetIdxValid(optSetIdx))
			return optSet.get(optSetIdx).findOption(optionIdx); 
		return null; 
	}

	//Setters
	public void setMake(String name) throws AutoException{
		if (!name.isEmpty())
			make = name;
		else{
			FileIO file = new FileIO(); 
			log(file.getErrorMsg(2)); 
			file = null; 
			throw new AutoException(2); 
		}
	}
	
	public void setModel(String model){
		this.model = model; 
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

	public void setOptSet(ArrayList<OptionSet> optSet) {
		this.optSet = optSet;
		setChoices(optSet.size()); 
	}
	
	public void setOptSet(int numOfConfigs) {
		if (numOfConfigs <= 0)
			log(" setOptSet() Total option set number for the model is negative or 0."); 
		else{
				optSet = new ArrayList<OptionSet>(numOfConfigs); 
				for (int x = 0; x < numOfConfigs; x++)
				optSet.add(new OptionSet());  
				setChoices(numOfConfigs); 
		}
	}
	
	public void setChoices(int numOfConfigs){
		if (numOfConfigs <= 0)
			log(" setChoices() Total choices number for the model is negative or 0."); 
		else{
				choices = new ArrayList<Option>(numOfConfigs); 
				for (int x = 0; x < numOfConfigs; x++)
				choices.add(new Option());  
		}
	}
	
	public void setOptSet(int idx, OptionSet opSet){
		if (isOptSetIdxValid(idx))
			optSet.set(idx, opSet); 
		else
			log(" setOptSet() " + idxOutOfBoundsMssg(idx)); 
	}
	

	public void setOptSetName(int optSetIdx, String name){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).setOptSetName(name);
		else
			log(" setOptSetName() " + idxOutOfBoundsMssg(optSetIdx)); 
	}

	public void setNumOfOptions(int optSetIdx, int numberOfOptions){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).setOptions(numberOfOptions);
		else
			log(" setNumOfOptions() " + idxOutOfBoundsMssg(optSetIdx));
	}
	
	public void setNumOfOptions(String optSetName, int numberOfOptions){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).setOptions(numberOfOptions); 
		else
			log(" setNumOpOptions() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOption(int optSetIdx, int optionsIdx, String name, float price){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).setOption(optionsIdx, name, price); 
		else
			log(" setOption() " + idxOutOfBoundsMssg(optSetIdx));
	}
	
	public void setOptionName(int optSetIdx, int optionIdx, String name){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).setOptionName(optionIdx, name); 
		else
			log(" setOptionName() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	public void setOptionPrice(int optSetIdx, int optionIdx, float price){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).setOptionPrice(optionIdx, price); 
		else
			log(" setOptionPrice() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	//Update methods
	public void updateOptSetName(String optSetName, String newName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			setOptSetName(idx, newName);
		else
			log(" updateOptSet() " + configSetCouldNotBeFoundMssg(optSetName)); 
	}
	
	public void updateOption(String optSetName, String optionName, String newName, float newPrice) {
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).updateOption(optionName, newName, newPrice); 
		else
			log(" updateOption() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void updateOptionName(String optSetName, String optionName, String newName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).updateOptionName(optionName,newName);
		else
			log(" updateOptionName() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void updateOptionPrice(String optSetName, String optionName, float newPrice){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).updateOptionPrice(optionName, newPrice);
		else
			log(" updateOptionPrice() " + configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	//Add methods 
	public void addOptSet(OptionSet newOptSet){
		optSet.add(newOptSet); 
		choices.add(new Option()); 
	}
	
	public void addOptSet(String optSetName){
		optSet.add(new OptionSet(optSetName)); 
		choices.add(new Option()); 
	}
	
	public void addOption(String optSetName, String optionName, float optionPrice){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).addOption(optionName, optionPrice);
		else
			log(" addOption() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void addOption(int optSetIdx, String optionName, float optionPrice){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).addOption(optionName, optionPrice);
		else
			log(" addOption() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	//Delete methods
	public void deleteOptSet(String optSetName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1){
			optSet.remove(idx);  
			choices.remove(idx); 
		}
		else
			log(" deleteOptSet() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOptSet(int optSetIdx){
		if (isOptSetIdxValid(optSetIdx)){
			optSet.remove(optSetIdx);
			choices.remove(optSetIdx); 
		}
		else
			log(" deleteOptSet() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	public void deleteOption(String optSetName, String optionName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1){
			optSet.get(idx).deleteOption(optionName);
			updateChoices(); 
		}
		else
			log(" deleteOption() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOption(int optSetIdx, int optionsIdx){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).deleteOption(optionsIdx);
		else
			log(" deleteOption() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	//Choice related methods
	public void setOptionChoice(String optSetName, String optionName){
		Option op = findOption(optSetName, optionName); 
		if (op != null)
			findOptSet(optSetName).setChoice(op); 
		else
		log(" setOptionChoice() " + " choice cannot be added.\n"); 
	}
	
	public String  getOptionChoice(String optSetName){
		if (findOptSet(optSetName) != null && findOptSet(optSetName).getChoice() != null)
			return findOptSet(optSetName).getChoice().getOptName(); 
		else
			log(" getOptionChoice() " + "Either option set or choice option is null"); 
		return "Option choice is not set"; 
	}
	
	public float getOptionChoicePrice(String optSetName){
		if (findOptSet(optSetName) == null || findOptSet(optSetName).getChoice() == null)
			log(" getOptionChoicePrice() " + "Either option set or choice option is null"); 
		return findOptSet(optSetName).getChoice().getPrice(); 
	}
	
	public float getTotalPrice(){
		updateChoices(); 
		float total = 0; 
		for (int x = 0; x < choices.size(); x++){
			total += choices.get(x).getPrice(); 
		}
		return total; 
	}
	
	public void deleteSelection(String optSetName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).setChoice(new Option());
		updateChoices(); 
	}
	
	public void clearSelection(){
		for (int x = 0; x < optSet.size(); x++){
			optSet.get(x).setChoice(new Option());
		}
		updateChoices(); 
	}

	//Print methods
	public void printAutomobile(){
		printAutoMake(); 
		printAutoModel();
		printBasePrice(); 
		printOptSet(); 
	}
	
	public void printAutoMake(){
		System.out.printf("\nAuto make is %s", getMake()); 
	}
	
	public void printAutoModel(){
		System.out.printf("\nAuto model is %s", getModel()); 
	}
	
	public void printBasePrice(){
		System.out.printf("\n%s %s base price is $%,.2f\n", getMake(), getModel(), getBasePrice()); 
	}
	
	public void printOptSet(){
		if (optSet != null){
			System.out.printf("\n%s %s configuration options and cost data:\n", getMake(), getModel()); 
			Collections.sort(optSet); 
			for (int x = 0; x < optSet.size(); x++){
					optSet.get(x).print();
			}
		}
		else
			log(" printOptSet(): Option Set is null."); 
	}
	
	public void printOptSet(String optSetName){
		int idx = findOptSetIdx(optSetName);
		if (idx != -1)
			optSet.get(idx).print();
		else
			log(" printOptSet() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void printOptSet(int optSetIdx){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).print(); 
		else
				log(" printOptSet() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	public void printOption(int optSetIdx, int optionsIdx){
		if (isOptSetIdxValid(optSetIdx))
			optSet.get(optSetIdx).print(optionsIdx); 
		else
			log(" printOption() " + idxOutOfBoundsMssg(optSetIdx)); 
	}
	
	public void printOption(String optSetName, String optionName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet.get(idx).print(optionName); 
		else
			log(" printOption() " + configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void printChoices(){
		updateChoices(); 
		System.out.printf("\nChoices for %s %s are:\n", getMake(), getModel()); 
		for (int x = 0; x < choices.size(); x++){
			if (choices.get(x).getOptName() != null)
				choices.get(x).print(); 
		}
	}

	@Override
	public String toString() {
		StringBuffer autoModelConfig = new StringBuffer(String.format("\nAuto model is %s %s", getMake(), getModel())); 
		autoModelConfig.append(String.format("\n%s base price is $%,.2f", getMake(), getBasePrice())); 
		
		if (optSet != null){
			autoModelConfig.append(String.format("\n%s %s configuration options and cost data:\n\n", getMake(), getModel())); 	
			for (int x = 0; x < optSet.size(); x++){
					autoModelConfig.append(optSet.get(x).toString());
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

