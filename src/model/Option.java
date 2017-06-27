/*
 * Alena Marchuk
 * CIS 35B
 * Due: May 14, 2017
 * Submitted: May 14, 2017
 */

package model;

import java.io.Serializable; 

//Inner Class 
public class Option implements Serializable, Comparable<Option>{
	private static final long serialVersionUID = 1L;
	private String optName;
	private float price;
	
	//Constructors
	public Option() {}
	
	public Option(String name, float price) {
		setOptName(name);
		setPrice(price);
	}
	
	//Getters
	protected String getOptName() {
		return optName;
	}
	
	protected float getPrice() {
		return price;
	}
	
	//Setters
	protected void setOptName(String name) {
		optName = name;
	}
	
	protected void setPrice(float price) {
		this.price = price;
	}
	
	//Print method
	protected void print() {
		System.out.printf("%s is $%,.2f\n", optName, price);
	}
	
	@Override
	public int compareTo(Option o) {
			return this.optName.compareTo(o.optName); 	
	}
	
	
	@Override
	public String toString() {
		StringBuffer option = new StringBuffer(optName);
		option.append(String.format(" is $%,.2f", price));
		return option.toString();
	}
}
