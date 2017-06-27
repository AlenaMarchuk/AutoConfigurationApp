/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package adapter;

public interface Updatable {
	public void updateOptionSetName(String modelName, String optionSetName, String newName); 
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice); 
}
