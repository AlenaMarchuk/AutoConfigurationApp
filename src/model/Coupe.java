/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package model;

import java.util.ArrayList;

import exception.AutoException;

public class Coupe extends Automobile{
	private static final long serialVersionUID = 1L;
	private String autoType = "Coupe"; 
	
	public Coupe(){
		super(); 
	}
	
	public Coupe(String make, String model, float basePrice, ArrayList<OptionSet> optSet) throws AutoException{
		super(make, model, basePrice, optSet); 
	} 
	
	public Coupe(String make, String model, float basePrice, int numOfConfigs) throws AutoException{
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
