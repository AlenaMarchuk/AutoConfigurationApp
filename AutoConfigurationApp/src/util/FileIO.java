/*
 * Alena Marchuk
 * CIS 35B
 * Lab1
 * Due: April 20, 2017
 * Submitted: April 20, 2017
 */

package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.AutoModel; 
import adapter.Debuggable;

public class FileIO implements Debuggable{
	
	public FileIO() {}
	
	public AutoModel buildAutoModel(String fileName){
		
		int counter = 0; 
		AutoModel autoModel = new AutoModel(); 
		
		try{
			FileReader file = new FileReader(fileName);
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
						autoModel.setAutoModelName(line);
						
						if (DEBUG){
							autoModel.printModelName(); 
						}
					}
					
					if (counter == 2){
						autoModel.setBasePrice(Float.valueOf(line));
						
						if (DEBUG){
							autoModel.printBasePrice(); 
						}
					}
					
					if (counter == 3){
						
						autoModel.setOptSet(Integer.valueOf(line)); 
						
						if (DEBUG){
							System.out.printf("\nFileIO DEBUG: Number of configuration options is %s", autoModel.getOptSet().length); 
						}
					}
					
					if (counter > 3){
						
						//Call AutoModel wrapper methods to build OptionSet and options[]
						String[] lineTokens = line.split(";");
						autoModel.setOptSetName(counter - 4, lineTokens[0]);
						autoModel.setNumOfOptions(counter - 4, lineTokens.length - 1);
						
						//Populate optSet with options
						for (int x = 1; x < lineTokens.length; x++){
							String lineOpts[] = lineTokens[x].split(":");
							autoModel.setOption(counter - 4, x - 1, lineOpts[0], Float.valueOf(lineOpts[1]));
						}
						
						if (DEBUG){
							autoModel.printOptSet(lineTokens[0]);
						}
					}				
				}
			}
			buffer.close();
		}
		catch(Exception e){
			System.out.printf("\n" + e.toString());
		}
		
		if (DEBUG){
			 autoModel.printModelConfigurations(); 
		}
		return autoModel;
	}
	
	public void serializeAutoModel(AutoModel autoModel){
		try{
			FileOutputStream fos = new FileOutputStream("autoModelFile");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(autoModel);
			if (DEBUG) {
				System.out.printf("\nFileIO DEBUG: serializing AutoModel object.\n");
			}
			oos.close(); 
		}
		catch(Exception e){
			System.out.printf("FileIO DEBUG: " + e.toString());
		}
	}
	
	public AutoModel deserializeAutoModel(){
		try {
			FileInputStream fis = new FileInputStream("autoModelFile");
			ObjectInputStream ois = new ObjectInputStream(fis);
			AutoModel auto = (AutoModel)ois.readObject();
			if (DEBUG) {
				System.out.printf("\nFileIO DEBUG: deserializing AutoModel object.\n");
			}
			ois.close();
			return auto;
		} catch (Exception e) {
			System.out.printf("FileIO DEBUG: " + e.toString());
		}
		return null;
	}	
}
