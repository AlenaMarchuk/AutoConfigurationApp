/*
 * Alena Marchuk
 * CIS 35B
 */

package adapter;

public interface Selectable {

	public void selectOption(String autoKey, String optSetName, String optionName); 
	public String getOptionChoice(String autoKey, String optSetName); 
	public float getTotalPrice(String autoKey); 
	public void deleteSelection(String autoKey, String optSetName); 
	public void clearSelection(String autoKey);
	public void printSelection(String autoKey); 
}
