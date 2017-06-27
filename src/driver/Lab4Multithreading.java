/*
 * Alena Marchuk
 * CIS 35B
 */

package driver;
import scale.Editable;
import exception.AutoException;
import adapter.BuiltAuto;
import adapter.Creatable;

/*
 * Documentation: 
 * opNo 1 - updateOptSetName
 * opNo 2 - updateOptionName
 * opNo 3 - updateOptionPrice
 * opNo 4 - deleteOptSet
 * opNo 5 - addOptSet
 * opNo 6 - deleteOption
 * opNo 7 - addOption
 */

public class Lab4Multithreading {
	
	public static void main(String[] args)throws AutoException {
		Creatable fleet= new BuiltAuto(); 
		fleet.buildAuto("Truck", "fordFocusWagon");
		
		//Synchronous multithreading
		
		Editable th1  = new BuiltAuto(); 
		Editable  th2  = new BuiltAuto(); 
		th1.editOptions("FordFocus Wagon ZTWTruck", 1, new String[]{"Transmission", "Transmission edited by th1"}, true);
		th2.editOptions("FordFocus Wagon ZTWTruck", 1, new String[]{"Transmission", "Transmission edited by th2"}, true); 
		
		Editable th3  = new BuiltAuto(); 
		Editable  th4  = new BuiltAuto(); 
		th3.editOptions("FordFocus Wagon ZTWTruck", 2, new String[]{"Transmission", "Manual", "Manual" +  " edited by th3"}, true);
		th4.editOptions("FordFocus Wagon ZTWTruck", 2, new String[]{"Transmission", "Manual", "Manual" + " edited by th4"}, true); 
		
		Editable th5  = new BuiltAuto(); 
		Editable th6 = new BuiltAuto(); 
		th5.editOptions("FordFocus Wagon ZTWTruck", 3, new String[]{"Transmission", "Automatic", "55.5"}, true);
		th6.editOptions("FordFocus Wagon ZTWTruck", 3, new String[]{"Transmission", "Automatic", "105.5"}, true);
		
		Editable  th7  = new BuiltAuto(); 
		Editable  th8  = new BuiltAuto(); 
		th7.editOptions("FordFocus Wagon ZTWTruck", 4, new String[]{"Side Impact Air Bags"}, true);
		th8.editOptions("FordFocus Wagon ZTWTruck", 4, new String[]{"Side Impact Air Bags"}, true);
		
		Editable  th9  = new BuiltAuto(); 
		Editable  th10 = new BuiltAuto(); 
		th9.editOptions("FordFocus Wagon ZTWTruck", 5, new String[]{ "ADDED New Option Set"}, true); 
		th10.editOptions("FordFocus Wagon ZTWTruck", 5, new String[]{ "ADDED New Option Set"}, true); 
		
		Editable  th11  = new BuiltAuto(); 
		Editable  th12 = new BuiltAuto(); 
		th11.editOptions("FordFocus Wagon ZTWTruck", 6, new String[]{ "Brakes/Traction Control", "Standard"}, true); 
		th12.editOptions("FordFocus Wagon ZTWTruck", 6, new String[]{ "Brakes/Traction Control", "Standard"}, true); 
		
		Editable  th13  = new BuiltAuto(); 
		Editable  th14 = new BuiltAuto(); 
		th13.editOptions("FordFocus Wagon ZTWTruck", 7, new String[]{ "Brakes/Traction Control", "ADDED OPTION: No brakes", "-500f"}, true); 
		th14.editOptions("FordFocus Wagon ZTWTruck", 7, new String[]{ "Brakes/Traction Control", "ADDED OPTION: No brakes", "-500f"}, true); 
		
		//Asyncronous multithreading 
		
		/*
		Editable th1  = new BuiltAuto(); 
		Editable  th2  = new BuiltAuto(); 
		th1.editOptions("FordFocus Wagon ZTWTruck", 1, new String[]{"Transmission", "Transmission edited by th1"}, false);
		th2.editOptions("FordFocus Wagon ZTWTruck", 1, new String[]{"Transmission", "Transmission edited by th2"}, false); 
		
		Editable th3  = new BuiltAuto(); 
		Editable  th4  = new BuiltAuto(); 
		th3.editOptions("FordFocus Wagon ZTWTruck", 2, new String[]{"Transmission", "Manual", "Manual" +  " edited by th3"}, false);
		th4.editOptions("FordFocus Wagon ZTWTruck", 2, new String[]{"Transmission", "Manual", "Manual" + " edited by th4"}, false); 
		
		Editable th5  = new BuiltAuto(); 
		Editable th6 = new BuiltAuto(); 
		th5.editOptions("FordFocus Wagon ZTWTruck", 3, new String[]{"Transmission", "Automatic", "55.5"}, false);
		th6.editOptions("FordFocus Wagon ZTWTruck", 3, new String[]{"Transmission", "Automatic", "105.5"}, false);
		
		Editable  th7  = new BuiltAuto(); 
		Editable  th8  = new BuiltAuto(); 
		th7.editOptions("FordFocus Wagon ZTWTruck", 4, new String[]{"Side Impact Air Bags"}, false);
		th8.editOptions("FordFocus Wagon ZTWTruck", 4, new String[]{"Side Impact Air Bags"}, false);
		
		Editable  th9  = new BuiltAuto(); 
		Editable  th10 = new BuiltAuto(); 
		th9.editOptions("FordFocus Wagon ZTWTruck", 5, new String[]{ "ADDED New Option Set"}, false); 
		th10.editOptions("FordFocus Wagon ZTWTruck", 5, new String[]{ "ADDED New Option Set"}, false); 
		
		Editable  th11  = new BuiltAuto(); 
		Editable  th12 = new BuiltAuto(); 
		th11.editOptions("FordFocus Wagon ZTWTruck", 6, new String[]{ "Brakes/Traction Control", "Standard"}, false); 
		th12.editOptions("FordFocus Wagon ZTWTruck", 6, new String[]{ "Brakes/Traction Control", "Standard"}, false); 
		
		Editable  th13  = new BuiltAuto(); 
		Editable  th14 = new BuiltAuto(); 
		th13.editOptions("FordFocus Wagon ZTWTruck", 7, new String[]{ "Brakes/Traction Control", "ADDED OPTION: No brakes", "-500f"}, false); 
		th14.editOptions("FordFocus Wagon ZTWTruck", 7, new String[]{ "Brakes/Traction Control", "ADDED OPTION: No brakes", "-500f"}, false); 
		*/

	}
}
