/*
 * Alena Marchuk
 * CIS 35B
 * Due: April 30, 2017
 * Submitted: April 30, 2017
 */

package exception;
import util.FileIO; 

public class AutoException extends Exception implements Fixable{
	private static final long serialVersionUID = 1L;
	private int errorNo; 
	private String errorMsg; 
	private String fix; 
	
	public AutoException(){
		super();
		}
	
	public AutoException(int errorNo){
		super(); 
		setErrorNo(errorNo); 
		FileIO file = new FileIO(); 
		setErrorMsg(file.getErrorMsg(errorNo)); 
	}
	
	public AutoException(String fix){
		super(); 
		setFix(fix); 
	}

	public int getErrorNo() {
		return errorNo;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getFix() {
		return fix;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public void setFix(String fix) {
		this.fix = fix;
	}

	public void print(){
		System.out.printf("\nError #%d: %s", errorNo, errorMsg); 
	}

	@Override
	public void fix(int errorNo) throws AutoException{
		
		FileIOFix filefix = new FileIOFix(); 
		switch(errorNo){
		case 1: 
			String enteredFilename = filefix.fix1(); 
			throw new AutoException(enteredFilename); 
		case 2: 
			String enteredAutoName = filefix.fix2(); 
			throw new AutoException(enteredAutoName); 
		case 3: 
			String enteredPrice = filefix.fix3(); 
			throw new AutoException(enteredPrice); 
		case 4: 
			String enteredNumber = filefix.fix4(); 
			throw new AutoException(enteredNumber); 
		case 5: 
			String emptyOptSet = filefix.fix5(); 
			throw new AutoException(emptyOptSet); 
		}	
	}
}
