/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package driver;

import adapter.BuiltAuto;
import adapter.Creatable;
import adapter.Selectable;
import exception.AutoException;

public class Lab3SelectableTEST {

	public static void main(String[] args) throws AutoException {
		
		Creatable auto = new BuiltAuto(); 
		auto.buildAuto("Sedan", "fordFocusWagon");
		auto.buildAuto("SUV", "lexusSUV");
		System.out.printf("\t\tCurrent automobiles in the fleet:\n");
		auto.printFleet();
		
		Selectable choice = new BuiltAuto(); 
		
		System.out.printf("\n\t\tTesting selectOption()\n"); 
		choice.selectOption("FordFocus Wagon ZTWSedan", "Color", "Cloud 9 White Clearcoat"); 
		choice.selectOption("FordFocus Wagon ZTWSedan", "Brakes/Traction Control", "ABS"); 
		choice.selectOption("FordFocus Wagon ZTWSedan", "Power Moonroof", "Present"); 
		choice.selectOption("FordFocus Wagon ZTWSedan", "Transmission", "Manual"); 
		
		choice.selectOption("LexusRX350SUV", "Color", "Coffee Latte"); 
		choice.selectOption("LexusRX350SUV", "Brakes/Traction Control", "Stoppable"); 
		choice.selectOption("LexusRX350SUV", "Windows", "4 windows"); 
		choice.selectOption("LexusRX350SUV", "Wheels", "4 wheels"); 
		
		System.out.printf("\n\t\tTesting printSelection()\n"); 
		choice.printSelection("FordFocus Wagon ZTWSedan");
		choice.printSelection("LexusRX350SUV");
		
		System.out.printf("\n\t\tTesting getTotalPrice()\n"); 
		System.out.printf("\nTotal price for Ford Focus is $%.2f\n", choice.getTotalPrice("FordFocus Wagon ZTWSedan")); 
		System.out.printf("\nTotal price for Lexus is $%.2f\n", choice.getTotalPrice("LexusRX350SUV")); 
		System.out.printf("\nTotal price for Non-existing model is $%.2f\n", choice.getTotalPrice("No such model")); 
		
		System.out.printf("\n\t\tTesting getOptionChoice()\n"); 
		
		System.out.printf(choice.getOptionChoice("FordFocus Wagon ZTWSedan", "Color")); 
		System.out.printf("\n"); 
		System.out.printf(choice.getOptionChoice("FordFocus Wagon ZTWSedan", "Brakes/Traction Control")); 
		System.out.printf("\n"); 
		System.out.printf(choice.getOptionChoice("LexusRX350SUV", "Color")); 
		System.out.printf("\n"); 
		System.out.printf(choice.getOptionChoice("Lexus", "Color")); 
		
		System.out.printf("\n\t\tTesting deleteSelection()."); 
		choice.deleteSelection("FordFocus Wagon ZTWSedan", "Color"); 
		choice.deleteSelection("LexusRX350SUV", "Windows"); 
		choice.deleteSelection("FordFocus Wagon ZTWSedan",  "Color");
		
		choice.printSelection("FordFocus Wagon ZTWSedan");
		choice.printSelection("LexusRX350SUV");
		
		System.out.printf("\n\t\tTesting clearSelection()\n"); 
		choice.clearSelection("FordFocus Wagon ZTWSedan"); 
		choice.clearSelection("LexusRX350SUV"); 
		choice.clearSelection("No such car");
		
		choice.printSelection("FordFocus Wagon ZTWSedan");
		choice.printSelection("LexusRX350SUV");
		choice.printSelection("No Such Car");
	}
}
