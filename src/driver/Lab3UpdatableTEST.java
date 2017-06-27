/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package driver;

import exception.AutoException;
//import exception.AutoException;
import adapter.*;

public class Lab3UpdatableTEST {

	public static void main(String[] args) throws AutoException  {
		
		Creatable auto = new BuiltAuto(); 
		auto.buildAuto("Sedan", "fordFocusWagon");
		auto.buildAuto("SUV", "lexusSUV");
		System.out.printf("\t\tAutomobiles before updates are made:\n"); 
		
		System.out.printf("\n\t\tTesting printAuto(autoKey)\n"); 
		auto.printAuto("FordFocus Wagon ZTWSedan");
		auto.printAuto("LexusRX350SUV"); 
		auto.printAuto("No Such Auto in Fleet"); 
		
		Updatable auto2 = new BuiltAuto(); 
		System.out.printf("\n\t\tTesting updateOptSetName()\n"); 
		auto2.updateOptSetName("FordFocus Wagon ZTWSedan", "Power Moonroof", "UPDATED Power Moonroof");
		auto2.updateOptSetName("FordFocus Wagon ZTWSedan", "No Such Option Set Name", "Updated Colors");
		auto2.updateOptSetName("No such model", "Side Impact Air Bags", "Air Bags");
		auto2.updateOptSetName("LexusRX350Truck", "Color", "UPDATED Colors");
		
		System.out.printf("\n\t\tTesting updateOptionPrice()\n"); 
		auto2.updateOptionPrice("FordFocus Wagon ZTWSedan", "UPDATED Power Moonroof", "Not Present", -789.78f);
		auto2.updateOptionPrice("FordFocus Wagon ZTWSedan", "Color", "Midnight Blue", 899);
		auto2.updateOptionPrice("LexusRX350SUV", "Windows", "4 windows", 405.25f);
		auto2.updateOptionPrice("LexusRX350SUV", "Doors", "4 windows", 405.25f);
		
		System.out.printf("\n\t\tTesting updateOptionName()\n"); 
		auto2.updateOptionName("FordFocus Wagon ZTWSedan", "Color", "CD Silver Clearcoat Metallic", "UPDATED CD Silver Clearcoat Metallic");
		auto2.updateOptionName("FordFocus Wagon ZTWSedan", "CD Silver Clearcoat Metallic", "Silver", "Silver Metallic");
		auto2.updateOptionName("No such model", "Color", "Silver", "Silver Metallic");
		
		System.out.printf("\n\t\tTesting addOption()\n"); 
		auto2.addOption("FordFocus Wagon ZTWSedan", "Brakes/Traction Control", "ADDED OPTION: No brakes", -500f);
		auto2.addOption("LexusRX350SUV", "Color", "ADDED OPTION: Ruby Red", 100f);
		auto2.addOption("FordFocus Wagon ZTWCOUPE", "Brakes/Traction Control", "No brakes", -500f);
		auto2.addOption("LexusRX350SUV", "NO such option set", "Ruby Red", 100f);
		
		System.out.printf("\n\t\tTesting deleteOptSet()\n"); 
		auto2.deleteOptSet("FordFocus Wagon ZTWSedan", "Side Impact Air Bags");
		auto2.deleteOptSet("LexusRX350SUV", "Side Impact Air Bags"); 
		auto2.deleteOptSet("Loggable Name", "Side Impact Air Bags");
		auto2.deleteOptSet("LexusRX350SUV", "Old Air Bags"); 
		
		System.out.printf("\n\t\tTesting deleteOption()\n"); 
		auto2.deleteOption("FordFocus Wagon ZTWSedan", "Brakes/Traction Control", "ABS"); 
		auto2.deleteOption("LexusRX350SUV", "Color", "Broody Teal"); 
		auto2.deleteOption("FordFocus", "Brakes/Traction Control", "ABS"); 
		auto2.deleteOption("LexusRX350SUV", "No such option set", "Broody Teal"); 
		
		System.out.printf("\n\t\tTesting addOptSet()\n"); 
		auto2.addOptSet("FordFocus Wagon ZTWSedan", "ADDED New Option Set"); 
		auto2.addOptSet("LexusRX350SUV", "ADDED New Option Set"); 
		auto2.addOptSet("FordFocus", "Not going to be added"); 
		auto2.addOptSet("Lexus", "Not going to be added"); 
		
		System.out.printf("\n\t\tChecking if the updates have been implemented:\n"); 
		auto.printFleet();
	}
}
