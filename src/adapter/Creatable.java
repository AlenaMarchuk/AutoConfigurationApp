/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package adapter;

import exception.AutoException;

public interface Creatable {
	public void buildAuto(String filename) throws AutoException; 
	public void printAuto(String modelName); 
}
