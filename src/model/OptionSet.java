/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package model;

import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;

import adapter.Loggable;

public class OptionSet implements Serializable, Comparable<OptionSet>, Loggable{
	
	private String optSetName = "Undefined"; 
	private Option[] options; 
	
	//private helper method
	private boolean isOptionNull(int idx){
		return (options[idx] == null ? true : false); 
	}
	
	private boolean isOptionsIdxValid(int idx){
		return (idx >= 0 && idx < options.length ? true : false); 
	}
	
	private int findOptionIdx(String name){
		for (int x = 0; x < options.length; x++){
			if (!isOptionNull(x) && options[x].getOptName().equalsIgnoreCase(name)){
				return x; 
			}
		}
		return -1; 
	}
	
	private String optionCouldNotBeFoundMssg(String optionName){
		return String.format("%s option could not be found", optionName); 
	}
	
	private String arrIdxOutOfBoundsMssg(){
		return "Index is out of bounds of options[]."; 
	}
	
	private String optionAtIdxNullMssg(int idx){
		return String.format("Option at %d index is null", idx); 
	}

	//Constructors
	public OptionSet(){}
	
	public OptionSet(String name, int numberOfOptions){
		setOptSetName(name); 
		setOptions(numberOfOptions); 
	}

	//Getters
	protected String getOptSetName() {
		return optSetName;
	}

	protected Option[] getOptions() {
		return options;
	}
	
	protected Option findOption(String opName){
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			return options[idx]; 
		else
			log(" findOption() " + optionCouldNotBeFoundMssg(opName));  
		return null; 
	}
	
	protected Option findOption(int optionIdx){
		if(isOptionsIdxValid(optionIdx) && !isOptionNull(optionIdx))
			return options[optionIdx]; 
		else if  (isOptionsIdxValid(optionIdx) && isOptionNull(optionIdx))
			log(" findOption() " + optionAtIdxNullMssg(optionIdx)); 
		else
				log(" findOption() " + arrIdxOutOfBoundsMssg()); 
		return null; 
	}

	//Setters
	protected void setOptSetName(String name) {
		optSetName = name;
	}

	protected void setOptions(Option[] options) {
		this.options = options;
	}
	
	protected void setOptions(int numberOfOptions){
		if (numberOfOptions > 0){
			options = new Option[numberOfOptions]; 
			for (int x = 0; x < options.length; x++)
				options[x] = new Option(); 
		}
		else
			log(" setOptions(): total number of options is 0 or negative.\n"); 	
	}
	
	protected void setOption(int idx, String name, float price) {
		if (isOptionsIdxValid(idx)){
			if (isOptionNull(idx)){
				options[idx] = new Option(); 
			}
			setOptionName(idx, name); 
			setOptionPrice(idx, price); 
		}
		else
			log(" setOption() " + arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOption(String opName, String newName, float newPrice){
		int idx = findOptionIdx(opName); 
		if (idx != -1){
			updateOptionName(opName, newName);
			updateOptionPrice(opName, newPrice); 
		}
		else
			log(" updateOption() " + optionCouldNotBeFoundMssg(opName)); 
	}
	
	protected void setOptionName(int idx, String name){
		if (isOptionsIdxValid(idx)){
			if (isOptionNull(idx)){
				options[idx] = new Option(); 
			}
				options[idx].setOptName(name);
		}
		else
			log(" setOptionName() " + arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOptionName(String opName, String newName) {
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			options[idx].setOptName(newName);
		else
			log(" updateOptionName() " + optionCouldNotBeFoundMssg(opName)); 
	}
	
	protected void setOptionPrice(int idx, float price) {
		if (isOptionsIdxValid(idx)){
			if (isOptionNull(idx)){
				options[idx] = new Option(); 
			}
				options[idx].setPrice(price);
		}
		else
			log(" setOptionPrice() " + arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOptionPrice(String opName, float newPrice) {
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			options[idx].setPrice(newPrice);
		else
			log(" updateOptionPrice() " + optionCouldNotBeFoundMssg(opName)); 
	}
	

	protected void addOption(Option newOption){
		boolean isAdded= false; 
		for (int x = 0; x < options.length && !isAdded; x++){
			if (isOptionNull(x)){
				options[x] = newOption;
				isAdded = true; 
			}
		}
		if (!isAdded){
			log(" addOption(): Auto model options configuration set is full."); 
			log(" addOption(): New option could not be added."); 
		}
	}
	
	protected void addOption(String optionName, float optionPrice){
		OptionSet opset = new OptionSet(); 
		OptionSet.Option newOption = opset.new Option(optionName, optionPrice); 
		addOption(newOption); 
	}
	
	protected void deleteOption(String optionName){
		int idx = findOptionIdx(optionName); 
		if (idx != -1)
			options[idx] = null;  
		else
			log(" deleteOption() " + optionCouldNotBeFoundMssg(optionName)); 
	}
	
	protected void deleteOption(int optionsIdx){
		if (isOptionsIdxValid(optionsIdx))
			options[optionsIdx] = null; 
		else
			log(" deleteOption() " + arrIdxOutOfBoundsMssg()); 
	}

	//print methods
	protected void print(){
		if (options != null){
			System.out.printf("\n%s options:\n", getOptSetName());
			//Arrays.sort(options); 
			for (int x = 0; x < options.length; x++){
				if (!isOptionNull(x))
					options[x].print();
			}
		}
		else
			log(" print(): option is null."); 
	}
	
	protected void print(int optionsIdx){
		if (isOptionsIdxValid(optionsIdx)){
			if (!isOptionNull(optionsIdx))
				options[optionsIdx].print(); 
			else
				log(" print() " + optionAtIdxNullMssg(optionsIdx)) ; 
		}
		else
				log(" print() " + arrIdxOutOfBoundsMssg()); 
	}
	
	protected void print(String optionName){
		int idx = findOptionIdx(optionName);
		if (idx != -1)
			options[idx].print();  
		else
			log(" print() " + optionCouldNotBeFoundMssg(optionName));
	}
	
	@Override
	public int compareTo(OptionSet o) {

		if (this == null ||  o == null)
			return 0; 
		else{
			if (this.optSetName == null){
				if (o.optSetName == null)
					return 0;  //equal
				else
					return -1;  //null is before the other string
			}
			else{
				if (o.optSetName == null)
					return 1; //null is after the  other string
				else
					return this.optSetName.compareTo(o.optSetName); 
			} 
		}
	}
	
	@Override
	public String toString() {
		StringBuffer optionSet = new StringBuffer(String.format("\n%s options:\n", optSetName)); 
		for (int x = 0; x < options.length; x++){
			if (!isOptionNull(x)){
				optionSet.append(options[x].toString()); 
				optionSet.append("\n");  
			}
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

		//Inner Class 
		class Option implements Serializable, Comparable<Option>{
		private String optName = "Undefined option";
		private float price;

		//Constructors
		public Option() {}

		public Option(String name, float price) {
			setOptName(name);
			setPrice(price);
		}

		//Getters
		protected String getOptName() {
			return optName;
		}

		protected float getPrice() {
			return price;
		}

		//Setters
		protected void setOptName(String name) {
			optName = name;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		//Print method
		protected void print() {
			System.out.printf("%s is $%,.2f\n", optName, price);
		}
		
		@Override
		public int compareTo(Option o) {

			if (this == null || o == null)
				return 0; 
			else{
				if (this.optName == null){
					if (o.optName == null)
						return 0; 
					else
						return -1; 
				}
				else {
					if (o.optName == null)
						return 1; 
					else
						return this.optName.compareTo(o.optName); 
				}
			}		
		}


		@Override
		public String toString() {
			StringBuffer option = new StringBuffer(optName);
			option.append(String.format(" is $%,.2f", price));
			return option.toString();
		}
	}
}

