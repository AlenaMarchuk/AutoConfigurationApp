/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package model;

import model.OptionSet.Option;
import java.io.Serializable;
import java.util.Arrays;

public class AutoModel implements Serializable{
	private String autoModelName; 
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
		for (int x = 0; x < optSet.length &&  !isOptSetNull(x) ; x++){
			if (optSet[x].getOptSetName().equalsIgnoreCase(name))
				return x; 
		}
		return -1; 
	}
	
	private String configSetCouldNotBeFoundMssg(String optSetName){
		return String.format("\n%s configuration set could not be found in %s.", optSetName, getAutoModelName()); 
	}
	
	private String arrIdxOutOfBoundsMssg(){
		return "Index is out of bounds of optSet[]."; 
	}
		
	//Constructors
	public AutoModel(){ setAutoModelName("Undefined Auto Model");}
	
	public AutoModel(String name, float basePrice, OptionSet[] optSet) {
		setAutoModelName(name);
		setBasePrice(basePrice);
		setOptSet(optSet);
	} 
	
	public AutoModel(String name, float basePrice, int numOfConfigs){
		setAutoModelName(name); 
		setBasePrice(basePrice); 
		setOptSet(numOfConfigs);  
	}

	//Getters
	public String getAutoModelName() {
		return autoModelName;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public OptionSet[] getOptSet() {
		return optSet;
	}
	
	public OptionSet findOptSet(String name) throws NullPointerException{
		int idx = findOptSetIdx(name);
		if (idx != -1)
			return optSet[idx]; 
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(name)); 
	}
	
	public OptionSet findOptSet(int idx) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(idx))
			return optSet[idx]; 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public Option findOption(String optSetName, String opName) throws NullPointerException{
		int idx = findOptSetIdx(optSetName);
		if (idx != -1)
			return optSet[idx].findOption(opName);  
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName)); 
	}
	
	public Option findOption(int optSetIdx, int optionIdx) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			return optSet[optSetIdx].findOption(optionIdx); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}

	//Setters
	public void setAutoModelName(String name) {
		autoModelName = name;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public void setOptSet(OptionSet[] optSet) {
		this.optSet = optSet;
	}
	
	public void setOptSet(int numOfConfigs) throws IllegalArgumentException{
		if (numOfConfigs > 0){
			optSet = new OptionSet[numOfConfigs]; 
			for (int x = 0; x < optSet.length; x++)
				optSet[x] = new OptionSet(); 
		}
		else
			throw new IllegalArgumentException("Number of auto model configuration set options cannot be 0 or negative.\n"); 
	}
	
	public void setOptSet(int idx, OptionSet opSet) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(idx))
			optSet[idx] = opSet; 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void setOptSetName(int optSetIdx, String name) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOptSetName(name);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg());
	}
	
	public void setOptSetOptions(int optSetIdx, Option[] options) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOptions(options);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg());
	}
	
	public void setOptSetOptions(String optSetName, Option[] options) throws NullPointerException{
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].setOptions(options);
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setNumOfOptions(int optSetIdx, int numberOfOptions) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOptions(numberOfOptions);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg());
	}
	
	public void setNumOfOptions(String optSetName, int numberOfOptions) throws NullPointerException{
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].setOptions(numberOfOptions); 
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOption(int optSetIdx, int optionsIdx, String name, float price) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOption(optionsIdx, name, price); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg());
	}
	
	public void updateOption(String optSetName, String optionName, String newName, float newPrice) throws NullPointerException{
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOption(optionName, newName, newPrice); 
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOption(int optSetIdx, int optionIdx, Option opt) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOption(optionIdx, opt);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	} 
	
	public void updateOption(String optSetName, String optionName, Option opt) throws NullPointerException{
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOption(optionName, opt);
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOptionName(int optSetIdx, int optionIdx, String name) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOptionName(optionIdx, name); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void updateOptionName(String optSetName, String optionName, String newName) throws NullPointerException{
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOptionName(optionName,newName);
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));  
	}
	
	public void setOptionPrice(int optSetIdx, int optionIdx, float price) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].setOptionPrice(optionIdx, price); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void updateOptionPrice(String optSetName, String optionName, float newPrice) throws NullPointerException{
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].updateOptionPrice(optionName, newPrice);
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));  
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
			System.out.printf("\nAuto model configuration set is full.\n"); 
			System.out.printf("\nNew option set could not be added.\n"); 
		}
	}
	
	public void addOption(int optSetIdx, Option newOption) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].addOption(newOption);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void addOption(String optSetName, Option newOption) {
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].addOption(newOption);
		else
			System.out.printf(configSetCouldNotBeFoundMssg(optSetName));
	}
	
	//Delete methods
	public void deleteOptSet(String optSetName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx] = null; 
		else
			System.out.printf(configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOptSet(int optSetIdx) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx] = null;
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void deleteOption(String optSetName, Option option){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].deleteOption(option);
		else
			System.out.printf(configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOption(String optSetName, String optionName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].deleteOption(optionName);
		else
			System.out.printf(configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void deleteOption(int optSetIdx, int optionsIdx) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].deleteOption(optionsIdx);
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}

	//Print methods
	public void printModelConfigurations(){
		printModelName(); 
		printBasePrice(); 
		printOptSet(); 
	}
	
	public void printModelName(){
		System.out.printf("\nAuto model is %s", getAutoModelName()); 
	}
	
	public void printBasePrice(){
		System.out.printf("\n%s base price is $%,.2f", getAutoModelName(), getBasePrice()); 
	}
	
	public void printOptSet(){
		if (optSet != null){
			System.out.printf("\n%s configuration options and cost data:\n", getAutoModelName()); 
			Arrays.sort(optSet); 
			for (int x = 0; x < optSet.length && !isOptSetNull(x); x++){
				optSet[x].print();
			}
		}
	}
	
	public void printOptSet(String optSetName){
		int idx = findOptSetIdx(optSetName);
		if (idx != -1)
			optSet[idx].print();
		else
			System.out.printf(configSetCouldNotBeFoundMssg(optSetName));
	}
	
	public void printOptSet(int idx) throws ArrayIndexOutOfBoundsException {
		if (isOptSetIdxValid(idx))
			optSet[idx].print(); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void printOption(int optSetIdx, int optionsIdx) throws ArrayIndexOutOfBoundsException{
		if (isOptSetIdxValid(optSetIdx))
			optSet[optSetIdx].print(optionsIdx); 
		else
			throw new ArrayIndexOutOfBoundsException(arrIdxOutOfBoundsMssg()); 
	}
	
	public void printOption(String optSetName, String optionName){
		int idx = findOptSetIdx(optSetName); 
		if (idx != -1)
			optSet[idx].print(optionName); 
		else
			throw new NullPointerException(configSetCouldNotBeFoundMssg(optSetName));
	}

	@Override
	public String toString() {
		StringBuffer autoModelConfig = new StringBuffer(String.format("\nAuto model is %s", getAutoModelName())); 
		autoModelConfig.append(String.format("\n%s base price is $%,.2f", getAutoModelName(), getBasePrice())); 
		
		if (optSet != null){
			autoModelConfig.append(String.format("\n%s configuration options and cost data:\n\n", getAutoModelName())); 	
			for (int x = 0; x < optSet.length && !isOptSetNull(x); x++){
				autoModelConfig.append(optSet[x].toString());
			}	
		}
		return autoModelConfig.toString(); 
	}
}

