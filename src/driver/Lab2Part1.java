/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package driver;

import exception.AutoException;
//import exception.AutoException;
import adapter.*;

public class Lab2Part1 {

	public static void main(String[] args) throws AutoException  {
		
		Creatable auto = new BuiltAuto(); 
		auto.buildAuto("fordFocusWagon");
		auto.printAuto("Model");
		
		
		Updatable auto2 = new BuiltAuto(); 
		auto2.updateOptionSetName("Ford", "Power Moonroof", "UPDATED");
		auto2.updateOptionPrice("Ford", "UPDATED", "Not Present", -789.78f);
		
		auto2.updateOptionSetName("Edgecase", "No Such Option Set Name", "Updated Colors");
		auto2.updateOptionPrice("Edgecase", "Color", "Midnight Blue", 899);
		
		System.out.printf("\nChecking if the updates have been implemented:\n"); 
		auto.printAuto("Model");
	}
}
