/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Automobile; 
import adapter.Debuggable;
import adapter.Loggable;
import exception.AutoException; 

public class FileIO implements Debuggable, Loggable{
	
	private final String ERROR_MSGS = "/Users/alena/Documents/workspace/AutoModelConfigurationApp/errorMsgs";
	
	private void setOptSetNumHelper (String line, Automobile automobile){
		try{
			automobile.setOptSet(Integer.valueOf(line)); 
		}
		catch(NumberFormatException e){
			AutoException a = new AutoException(4); 
			log(getErrorMsg(4)); 
			try{
				a.fix(a.getErrorNo()); 
			}
			catch(AutoException b){
				automobile.setOptSet(Integer.valueOf(b.getFix())); 
			}
		}
		catch(NegativeArraySizeException e){
			AutoException a = new AutoException(4); 
			log(getErrorMsg(4)); 
			try{
				a.fix(a.getErrorNo()); 
			}
			catch(AutoException b){
				automobile.setOptSet(Integer.valueOf(b.getFix())); 
			}
		}
	}
	
	public FileIO() {}
	
	public Automobile buildAuto(String fileName) throws AutoException{
		
		int counter = 0; 
		Automobile automobile = new Automobile(); 
		FileReader file = null; 
		
		try{
			 file = new FileReader(fileName);
		}
		catch(FileNotFoundException e){
			log(getErrorMsg(1)); 
			throw new AutoException(1); 
		}
		
		try{
			BufferedReader buffer = new BufferedReader(file);
			
			boolean eof = false;
			
			while (!eof) {
				
				String line = buffer.readLine();

				if (line == null) {
						eof = true; 
					
					if (DEBUG)
						System.out.printf("\nFileIO DEBUG: This is the end of file\n");
				} else {
					counter++;
					
					if (DEBUG) {
						System.out.printf("\nFileIO DEBUG: Reading line #%d", counter);
						System.out.printf("\n%s", line);
					} 
					
					if (counter == 1){
						
						try{
							automobile.setAutomobileName(line);
						}
						catch(AutoException e){
							try{
								e.fix(e.getErrorNo()); 
							}
							catch(AutoException b){
								automobile.setAutomobileName(b.getFix());
							}
						}
						
						if (DEBUG){
							automobile.printAutoName(); 
						}
					}
					
					if (counter == 2){
						
						try{
							automobile.setBasePrice(Float.valueOf(line));
						}
						catch(NumberFormatException e){
							AutoException a = new AutoException(3); 
							log(getErrorMsg(3)); 
							try{
								a.fix(a.getErrorNo()); 
							}
							catch(AutoException b){
								automobile.setBasePrice(Float.valueOf(b.getFix()));
							}
						}
						
						if (DEBUG){
							automobile.printBasePrice(); 
						}
					}
					
					if (counter == 3){
						
						setOptSetNumHelper(line, automobile); 
						
						if (DEBUG){
							System.out.printf("\nFileIO DEBUG: Number of configuration options is %s", automobile.getOptSet().length); 
						}
					}
							
					if (counter > 3 && automobile.getOptSet().length > 0){

					if (!line.isEmpty()){
							String[] lineTokens = line.split(";");
							automobile.setOptSetName(counter - 4, lineTokens[0]);					
							automobile.setNumOfOptions(counter - 4, lineTokens.length - 1);
							
							//Populate optSet with options
							for (int x = 1; x < lineTokens.length; x++){
								String lineOpts[] = lineTokens[x].split(":");
								automobile.setOption(counter - 4, x - 1, lineOpts[0], Float.valueOf(lineOpts[1]));
							}
						
							if (DEBUG){
										automobile.printOptSet(counter - 4);
							}
						}
						else if (line.isEmpty() && counter - 3 <= automobile.getOptSet().length){
							AutoException a = new AutoException(5);  //or simply set empty options set  w/t throwing exception
							log(getErrorMsg(5)); 
							try{
								a.fix(a.getErrorNo()); 
							}
							catch(AutoException b){
								String fix = b.getFix(); 
								String[] emptyOptSet = fix.split(":"); 
								
								automobile.setOptSetName(counter -4, emptyOptSet[0]); 
								automobile.setNumOfOptions(counter - 4, Integer.valueOf(emptyOptSet[1])); 
							}
						}
					}
				}
			}
			buffer.close();
		}
		catch(Exception e){
			log(e.toString());
		}
		
		if (DEBUG){
			 automobile.printAutomobile(); 
		}
		return automobile;
	}
	
	public void serializeAutomobile(Automobile automobile){
		try{
			FileOutputStream fos = new FileOutputStream("autoModelFile");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(automobile);
			if (DEBUG) {
				System.out.printf("\nFileIO DEBUG: serializing AutoModel object.\n");
			}
			oos.close(); 
		}
		catch(Exception e){
			log( e.toString());
		}
	}
	
	public Automobile deserializeAutomobile(){
		try {
			FileInputStream fis = new FileInputStream("autoModelFile");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Automobile auto = (Automobile)ois.readObject();
			if (DEBUG) {
				System.out.printf("\nFileIO DEBUG: deserializing AutoModel object.\n");
			}
			ois.close();
			return auto;
		} catch (Exception e) {
			log(e.toString());
		}
		return null;
	}	
	
	public String  getErrorMsg(int errorNo) {
		
		int counter = 0; 
		
		try{
			FileReader file = new FileReader(ERROR_MSGS);
			BufferedReader buffer = new BufferedReader(file);
			
			boolean eof = false;
			
			while (!eof) {
				
				String line = buffer.readLine();

				if (line == null) {
					eof = true;
					
					if (DEBUG)
						System.out.printf("\nFileIO ERROR_MSGS DEBUG: This is the end of file\n");
				} 
				else {
					
					counter++; 
					
					if (DEBUG) {
						System.out.printf("\nFileIO ERROR_MSGS DEBUG: Reading line #%d", counter);
						System.out.printf("\n%s", line);
					} 
				
					String[] lineTokens = line.split(":");
					
					if (Integer.valueOf(lineTokens[0]) == errorNo)
						return  lineTokens[1];
				}
			}
			
			buffer.close();
		}
			catch(Exception e){
				log(e.toString());
			}
		return (String.format("\nError %d is not known", errorNo)); 
	}

	@Override
	public void log(String error) {
		FileWriter file = null; 
		BufferedWriter buffer = null; 
		try{
			file = new FileWriter(LOGFILE, true); 
			buffer = new BufferedWriter(file); 
			StringBuffer str = new StringBuffer(new java.util.Date().toString()); 
			str.append(" @ FileIO Class: "); 
			str.append(error); 
			buffer.write(str.toString());
			buffer.write("\n"); 
			buffer.close(); 
		}
		catch(Exception e){
			log(e.toString()); 
		}
	}
}
