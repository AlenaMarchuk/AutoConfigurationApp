/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package driver;

import model.AutoModel;
import util.FileIO;

public class Driver {

	public static void main(String[] args) {
		
		try{
		FileIO file = new FileIO(); 
		AutoModel autoBeforeSerialization = file.buildAutoModel("/Users/alena/Documents/workspace/AutoModelConfigurationApp/fordFocusWagonConfigs");

		System.out.printf("\n\tAuto model properties before serialization:\n");
		autoBeforeSerialization.printModelConfigurations();
		file.serializeAutoModel(autoBeforeSerialization);
		
		System.out.printf("\n\tAuto model properties after serialization:\n");
		AutoModel autoAfterSerialization = file.deserializeAutoModel(); 
		autoAfterSerialization.printModelConfigurations(); 
		} 
		catch(NullPointerException e){
			System.out.print(e.toString());
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.print(e.toString());
		} 
		catch (IllegalArgumentException e) {
			System.out.print(e.toString());
		}
	}
}
