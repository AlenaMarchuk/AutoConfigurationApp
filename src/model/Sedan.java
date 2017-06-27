/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package model;

import java.util.ArrayList;

import exception.AutoException;

public class Sedan extends Automobile{

	private static final long serialVersionUID = 1L;
	private String autoType = "Sedan"; 
	
	public Sedan(){
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
