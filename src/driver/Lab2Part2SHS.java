/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package driver;

import adapter.BuiltAuto;
import adapter.Creatable;
import adapter.Updatable;
import model.Automobile;
import exception.AutoException;
import util.FileIO;

public class Lab2Part2SHS {

	public static void main(String[] args) throws AutoException {
	
		//anotherAuto file is used for testing
		Creatable auto = new BuiltAuto(); 
		auto.buildAuto("wrongFileName");
		auto.printAuto("Model");
		
		Updatable auto2 = new BuiltAuto(); 
		auto2.updateOptionSetName("Lexus", "Color", "UPDATED Color");
		auto2.updateOptionPrice("Lexus", "UPDATED Color", "Golden Sun", -799.25f);
		
		auto.printAuto("Model");
	}
}
