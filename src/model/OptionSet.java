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

import adapter.Loggable;

public class OptionSet implements Serializable, Comparable<OptionSet>, Loggable{
	private static final long serialVersionUID = 1L;
	private String optSetName; 
	private ArrayList<Option> options; 
	private Option choice; 
	
	//private helper methods
	private boolean isOptionsIdxValid(int idx){
		return (idx >= 0 && idx < options.size() ? true : false); 
	}
	
	private int findOptionIdx(String name){
		for (int x = 0; x < options.size(); x++){
			if (options.get(x).getOptName().equalsIgnoreCase(name))
				return x; 
		}
		return -1; 
	}
	
	private String optionCouldNotBeFoundMssg(String optionName){
		return String.format("\'%s\' option could not be found", optionName); 
	}
	
	private String idxOutOfBoundsMssg(int idx){
		return String.format("Index \'%d\' is out of bounds of ArrayList \'options\'.", idx); 
	}

	//Constructors
	public OptionSet(){}
	
	public OptionSet(String name, int numberOfOptions){
		setOptSetName(name); 
		setOptions(numberOfOptions); 
	}
	
	public OptionSet(String name){
		setOptSetName(name); 
		setOptions(0); 
	}
	
	public OptionSet(String name, Option option){
		setOptSetName(name); 
		options.add(option); 
	}

	//Getters
	protected String getOptSetName() {
		return optSetName;
	}

	protected ArrayList<Option> getOptions() {
		return options;
	}
	
	protected Option getChoice() {
		return choice;
	}

	protected Option findOption(String opName){
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			return options.get(idx); 
		else
			log(" findOption() " + optionCouldNotBeFoundMssg(opName));  
		return null; 
	}
	
	protected Option findOption(int optionIdx){
		if(isOptionsIdxValid(optionIdx))
			return options.get(optionIdx); 
		else
				log(" findOption() " + idxOutOfBoundsMssg(optionIdx)); 
		return null; 
	}

	//Setters
	protected void setOptSetName(String name) {
		optSetName = name;
	}

	protected void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
	
	protected void setOptions(int numberOfOptions){
		if (numberOfOptions >= 0){
			options = new ArrayList<Option>(numberOfOptions); 
			for (int x = 0; x < numberOfOptions; x++)
				options.add(new Option()); 
		}
		else
			log(" setOptions(): total number of options is 0 or negative."); 	
	}
	
	protected void setChoice(Option choice) {
		this.choice = choice;
	}

	protected void setOption(int idx, String name, float price) {
		if (isOptionsIdxValid(idx)){
			setOptionName(idx, name); 
			setOptionPrice(idx, price); 
		}
		else
			log(" setOption() " + idxOutOfBoundsMssg(idx)); 
	}
	
	protected void setOptionName(int idx, String name){
		if (isOptionsIdxValid(idx))
				options.get(idx).setOptName(name);
		else
			log(" setOptionName() " + idxOutOfBoundsMssg(idx)); 
	}
	
	protected void setOptionPrice(int idx, float price) {
		if (isOptionsIdxValid(idx))
				options.get(idx).setPrice(price);
		else
			log(" setOptionPrice() " + idxOutOfBoundsMssg(idx)); 
	}
	
	//Update methods
	protected void updateOption(String opName, String newName, float newPrice){
		int idx = findOptionIdx(opName); 
		if (idx != -1){
			updateOptionName(opName, newName);
			updateOptionPrice(opName, newPrice); 
		}
		else
			log(" updateOption() " + optionCouldNotBeFoundMssg(opName)); 
	}

	protected void updateOptionName(String opName, String newName) {
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			options.get(idx).setOptName(newName);
		else
			log(" updateOptionName() " + optionCouldNotBeFoundMssg(opName)); 
	}
	
	protected void updateOptionPrice(String opName, float newPrice) {
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			options.get(idx).setPrice(newPrice);
		else
			log(" updateOptionPrice() " + optionCouldNotBeFoundMssg(opName)); 
	}
	
	
	//Add methods
	protected void addOption(Option newOption){
		options.add(newOption); 
	}
	
	protected void addOption(String optionName, float optionPrice){
		addOption(new Option(optionName, optionPrice)); 
	}
	
	//Delete methods
	protected void deleteOption(String optionName){
		int idx = findOptionIdx(optionName); 
		if (idx != -1){
			if (options.get(idx).equals(choice))
				choice = null; 
			options.remove(idx);  
		}
		else
			log(" deleteOption() " + optionCouldNotBeFoundMssg(optionName)); 
	}
	
	protected void deleteOption(int optionsIdx){
		if (isOptionsIdxValid(optionsIdx))
			options.remove(optionsIdx); 
		else
			log(" deleteOption() " + idxOutOfBoundsMssg(optionsIdx)); 
	}

	//print methods
	protected void print(){
		if (options != null){
			System.out.printf("\n%s options:\n", getOptSetName());
			Collections.sort(options); 
			for (int x = 0; x < options.size(); x++)
					options.get(x).print();
		}
		else
			log(" print(): option is null."); 
	}
	
	protected void print(int optionsIdx){
		if (isOptionsIdxValid(optionsIdx))
				options.get(optionsIdx).print(); 
		else
				log(" print() " + idxOutOfBoundsMssg(optionsIdx)); 
	}
	
	protected void print(String optionName){
		int idx = findOptionIdx(optionName);
		if (idx != -1)
			options.get(idx).print();  
		else
			log(" print() " + optionCouldNotBeFoundMssg(optionName));
	}
	
	@Override
	public int compareTo(OptionSet o) {
			return (this.optSetName.compareTo(o.optSetName)); 
	}
	
	@Override
	public String toString() {
		StringBuffer optionSet = new StringBuffer(String.format("\n%s options:\n", optSetName)); 
		for (int x = 0; x < options.size(); x++){
				optionSet.append(options.get(x).toString()); 
				optionSet.append("\n");  
			}
		return optionSet.toString();
	}
	
	@Override
	public void log(String error) {
		FileWriter file = null; 
		BufferedWriter buffer = null; 
		try{
			file = new FileWriter(LOGFILE, true); 
			buffer = new BufferedWriter(file); 
			StringBuffer str = new StringBuffer(new java.util.Date().toString()); 
			str.append(" @ OptionSet Class: "); 
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

