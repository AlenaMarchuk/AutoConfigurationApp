/*
 * Alena Marchuk
 * CIS 35B
 */

package model;

import java.util.ArrayList;

import exception.AutoException;

public class SUV extends Automobile{

	private static final long serialVersionUID = 1L;
	private String autoType = "SUV"; 
	
	public SUV(){
		super(); 
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
