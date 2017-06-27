/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package driver;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public class Lab1 {

	public static void main(String[] args) {
		
		try{
		FileIO file = new FileIO(); 
		Automobile autoBeforeSerialization = file.buildAuto("fordFocusWagon");

		System.out.printf("\n\tAuto model properties before serialization:\n");
		autoBeforeSerialization.printAutomobile();
		file.serializeAutomobile(autoBeforeSerialization);
		
		System.out.printf("\n\tAuto model properties after serialization:\n");
		Automobile autoAfterSerialization = file.deserializeAutomobile(); 
		autoAfterSerialization.printAutomobile(); 
		} 
		catch(AutoException e){
			
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
