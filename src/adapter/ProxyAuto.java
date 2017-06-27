/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package adapter;

import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class ProxyAuto {
	private static Automobile auto; 
	
	public void buildAuto(String filename) throws AutoException{
		FileIO file = new FileIO(); 
		try{
		auto = file.buildAuto(filename);
		}
		catch(AutoException e){		
			try{
				e.fix(e.getErrorNo());
			}
			catch(AutoException b){
				auto= file.buildAuto(b.getFix()); 
			}
		}
	}
	
	public void printAuto(String modelName){
		auto.printAutomobile();
	}
	
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		auto.updateOptSetName(optionSetName, newName);
	}
	
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice){
		auto.updateOptionPrice(optionSetName, optionName, newPrice);
	}
}
