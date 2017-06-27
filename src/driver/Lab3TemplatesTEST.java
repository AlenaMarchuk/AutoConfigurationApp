/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package driver;

import adapter.BuiltAuto;
import adapter.Creatable;
import exception.AutoException;

public class Lab3TemplatesTEST {

	public static void main(String[] args) throws AutoException {
	
		Creatable fleet= new BuiltAuto(); 
		fleet.buildAuto("Truck", "fordFocusWagon");
		fleet.buildAuto("SUV", "lexusSUV"); 
		fleet.buildAuto("Sedan", "Tesla"); 
		fleet.buildAuto("Coupe", "beetle");
		fleet.printFleet();
	}
}
