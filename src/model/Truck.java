/*
 * Alena Marchuk
 * CIS 35B
 */

package model;

import java.util.ArrayList;

import exception.AutoException;

public class Truck  extends Automobile{

	private static final long serialVersionUID = 1L;
	private String autoType = "Truck"; 
	
	public Truck(){
		super(); 
	}
	
	public Truck(String make, String model, float basePrice, ArrayList<OptionSet> optSet) throws AutoException{
		super(make, model, basePrice, optSet); 
	} 
	
	public Truck(String make, String model, float basePrice, int numOfConfigs) throws AutoException{
		super(make, model, basePrice, numOfConfigs); 
	}
	
	public String getAutoType(){
		return autoType; 
	}
	
	@Override
	public void printAutomobile() {
		System.out.printf("\nAuto type is %s", getAutoType()); 
		super.printAutomobile();
	}
}
