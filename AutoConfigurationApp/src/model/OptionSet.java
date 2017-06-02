/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package model;

import java.util.Arrays;
import java.io.Serializable;

public class OptionSet implements Serializable, Comparable<OptionSet>{
	
	private String optSetName; 
	private Option[] options; 
	
	//private helper method
	private boolean isOptionNull(int idx){
		return (options[idx] == null ? true : false); 
	}
	
	private boolean isOptionsIdxValid(int idx){
		return (idx >= 0 && idx < options.length ? true : false); 
	}
	
	private int findOptionIdx(String name){
		for (int x = 0; x < options.length && !isOptionNull(x); x++){
			if (options[x].getOptName().equalsIgnoreCase(name)){
				return x; 
			}
		}
		return -1; 
	}
	
	private String optionCouldNotBeFoundMssg(String optionName){
		return String.format("\n%s option could not be found", optionName); 
	}
	
	private String arrIdxOutOfBoundsMssg(){
		return "Index is out of bounds of options[]."; 
	}

	//Constructors
	public OptionSet(){}
	
	public OptionSet(String name, Option[] options) {
		setOptSetName(name);
		setOptions(options);
	}	
	
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
	
	protected Option findOption(String opName) throws NullPointerException{
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			return options[idx]; 
		else
			throw new NullPointerException(optionCouldNotBeFoundMssg(opName));  
	}
	
	protected Option findOption(int optionIdx) throws ArrayIndexOutOfBoundsException{
		if(isOptionsIdxValid(optionIdx))
			return options[optionIdx]; 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}

	//Setters
	protected void setOptSetName(String name) {
		optSetName = name;
	}

	protected void setOptions(Option[] options) {
		this.options = options;
	}
	
	protected void setOptions(int numberOfOptions) throws  IllegalArgumentException{
		if (numberOfOptions > 0){
			options = new Option[numberOfOptions]; 
			for (int x = 0; x < options.length; x++)
				options[x] = new Option(); 
		}
		else
			throw new IllegalArgumentException("Number of options cannot be 0 or negative.\n");
		
	}
	
	protected void setOption(int idx, Option opt) throws ArrayIndexOutOfBoundsException{
		if (isOptionsIdxValid(idx))
			options[idx] = opt; 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOption(String opName, Option opt) throws NullPointerException{
		int idx = findOptionIdx(opName); 
		if (idx != -1)
		 options[idx] = opt; 
		else
			throw new NullPointerException(optionCouldNotBeFoundMssg(opName)); 
	}
	
	protected void setOption(int idx, String name, float price) throws ArrayIndexOutOfBoundsException{
		if (isOptionsIdxValid(idx)){
			setOptionName(idx, name); 
			setOptionPrice(idx, price); 
		}
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOption(String opName, String newName, float newPrice) throws NullPointerException{
		int idx = findOptionIdx(opName); 
		if (idx != -1){
			updateOptionName(opName, newName);
			updateOptionPrice(opName, newPrice); 
		}
		else
			throw new NullPointerException(optionCouldNotBeFoundMssg(opName)); 
	}
	
	protected void setOptionName(int idx, String name) throws ArrayIndexOutOfBoundsException{
		if (isOptionsIdxValid(idx))
			options[idx].setOptName(name);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOptionName(String opName, String newName) throws NullPointerException{
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			options[idx].setOptName(newName);
		else
			throw new NullPointerException(optionCouldNotBeFoundMssg(opName)); 
	}
	
	protected void setOptionPrice(int idx, float price) throws ArrayIndexOutOfBoundsException{
		if (isOptionsIdxValid(idx))
			options[idx].setPrice(price);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	protected void updateOptionPrice(String opName, float newPrice) throws NullPointerException{
		int idx = findOptionIdx(opName); 
		if (idx != -1)
			options[idx].setPrice(newPrice);
		else
			throw new NullPointerException(optionCouldNotBeFoundMssg(opName)); 
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
			System.out.printf("\nAuto model options configuration set is full.\n"); 
			System.out.printf("\nNew option could not be added.\n"); 
		}
	}
	
	//Delete methods
	protected void deleteOption(Option op){
		int idx = findOptionIdx(op.getOptName()); 
		if (idx != -1)
			options[idx] = null;  
		else
			System.out.print(optionCouldNotBeFoundMssg(op.getOptName())); 
	}
	
	protected void deleteOption(String optionName){
		int idx = findOptionIdx(optionName); 
		if (idx != -1)
			options[idx] = null;  
		else
			System.out.print(optionCouldNotBeFoundMssg(optionName)); 
	}
	
	protected void deleteOption(int optionsIdx) throws ArrayIndexOutOfBoundsException{
		if (isOptionsIdxValid(optionsIdx))
			options[optionsIdx] = null; 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}

	//print methods
	protected void print(){
		if (options != null){
			System.out.printf("\n%s options:\n", getOptSetName());
			Arrays.sort(options); 
			for (int x = 0; x < options.length && !isOptionNull(x); x++){
				options[x].print();
			}
		}
	}
	
	protected void print(int optionsIdx) throws ArrayIndexOutOfBoundsException{
		if (isOptionsIdxValid(optionsIdx))
			options[optionsIdx].print(); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	protected void print(String optionName) throws NullPointerException{
		int idx = findOptionIdx(optionName);
		if (idx != -1)
			options[idx].print();  
		else
			throw new  NullPointerException(optionCouldNotBeFoundMssg(optionName));
	}
	
	@Override
	public int compareTo(OptionSet o) {
		if (getOptSetName() != null && o != null) 
			return (getOptSetName().compareTo(o.getOptSetName())); 
		else
			return 0; 
	}
	
	@Override
	public String toString() {
		StringBuffer optionSet = new StringBuffer(String.format("\n%s options:\n", optSetName)); 
		for (int x = 0; x < options.length && !isOptionNull(x); x++){
			optionSet.append(options[x].toString()); 
			optionSet.append("\n");  
		}
		return optionSet.toString();
	}

	//Inner Class 
	class Option implements Serializable, Comparable<Option>{
		private String optName;
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
			if (getOptName() != null && o != null)
				return (getOptName().compareTo(o.getOptName())); 
			else 
				return 0; 
		}

		@Override
		public String toString() {
			StringBuffer option = new StringBuffer(optName);
			option.append(String.format(" is $%,.2f", price));
			return option.toString();
		}
	}
}

