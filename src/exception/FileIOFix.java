/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package exception;
import java.util.Scanner;
import java.io.File; 

public class FileIOFix {
	private Scanner input; 
	
	public FileIOFix(){
		input = new Scanner(System.in); 
	}
	
	public String fix1() {
		do{
			System.out.printf("\nPlease enter the name of the file:\n"); 
			String filename = input.nextLine(); 
			File file = new File(filename); 
			if (file.exists())
				return filename; 
			else
				System.out.printf("\nThe file you have entered does not exist."); 
		}while(true); 
	}	
	
	public String fix2(){
		do{
			System.out.printf("\nPlease enter the name of the Auto Model:\n"); 
			String automodel = input.nextLine(); 
			if (!automodel.matches("^\\s*$"))
				return automodel; 
			else
				System.out.printf("\nAuto Model name is empty."); 
		}while(true); 
	}
	
	public String fix3(){
		do{
			System.out.printf("\nPlease enter the base price of the auto model:\n"); 
			String price = input.nextLine(); 
			
			if(price.matches("^[-]?\\d*\\.?\\d*$") && !price.matches("^\\s*$"))
				return price; 
			else	
				System.out.printf("\nPrice entry should be a number."); 			
		}while(true); 
	}
	
	public String fix4(){
		do{
			System.out.printf("\nPlease enter the number of total option sets for the auto model:\n"); 
			String number = input.nextLine(); 
			
			if (number.matches("^\\d*$") && !number.matches("^\\s*$"))
				return number; 
			else
				System.out.printf("\nTotal option sets entry should be a number."); 
		}while(true); 
	}
	
	public String fix5(){
		String fix = ""; 
		String optSetName = null;
		String optSetNumber = null; 
		
		System.out.printf("\nThe option set data is missing."); 
		do{
			System.out.printf("\nPlease enter the name of the option set:\n");
			optSetName = input.nextLine(); 
			if (!optSetName.matches("^\\s*$")){
				fix += optSetName; 
				fix += ":"; 
			}
			else
				System.out.printf("\nEntered option set name is empty."); 
		}while(optSetName.matches("^\\s*$")); 
		
		do{
			System.out.printf("\nPlease enter the number of options for the \'%s\' option set:\n", optSetName); 
			optSetNumber = input.nextLine(); 
			if (optSetNumber.matches("^\\d*$") && !optSetNumber.matches("^\\s*$"))
				fix += optSetNumber; 
			else
				System.out.printf("\nTotal option sets entry should be a number."); 
			
		}while(!optSetNumber.matches("^\\d*$") || optSetNumber.matches("^\\s*$")); 
		
		return fix; 
	}
}
