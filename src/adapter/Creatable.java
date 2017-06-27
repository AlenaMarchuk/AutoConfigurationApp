/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package adapter;
import exception.AutoException;

public interface Creatable {
	public void buildAuto(String autoType, String filename) throws AutoException; 
	public void printAuto(String modelName); 
	public void printFleet(); 
}
