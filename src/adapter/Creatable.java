/*
 * Alena Marchuk
 * CIS 35B
 */

package adapter;
import exception.AutoException;

public interface Creatable {
	public void buildAuto(String autoType, String filename) throws AutoException; 
	public void printAuto(String modelName); 
	public void printFleet(); 
}
