/*
 * Alena Marchuk
 * CIS 35B
 */

package adapter;

public interface Updatable {
	public void updateOptSetName(String  autoKey, String optSetName, String newName); 
	public void updateOptionName(String autoKey, String optSetName, String optionName, String newName); 
	public void updateOptionPrice(String autoKey, String optSetName, String optionName, float newPrice); 
	public void addOption(String autoKey, String optSetName, String optionName, float price); 
	public void addOptSet(String autoKey, String optSetName); 
	public void deleteOptSet(String autoKey, String optSetName); 
	public void deleteOption(String autoKey, String optSetName, String optionName); 
}
