package driver;

import exception.AutoException;
import util.FileIO;
import model.Automobile;
import model.OptionSet;

public class Lab1MethodsTests {

	public static void main(String[] args) throws AutoException {

		FileIO file = new FileIO();
		Automobile ford = null; 
		Automobile auto = null; 
		Automobile empty = null; 
		Automobile incomplete = null; 
		
		
try{
		String fordFile = "fordFocusWagon";
		String autoFile = "anotherAuto";
		String emptyFile = "empty";
		String incompleteFile = "incomplete"; 

		System.out.printf("\n\tReading in data about Ford Focus\n"); 
		ford = file.buildAuto(fordFile);
		System.out.printf("\n\tReading in data about another model\n"); 
		auto = file.buildAuto(autoFile);
		System.out.printf("\n\tReading in an empty file.\n"); 
		empty = file.buildAuto(emptyFile);
		System.out.printf("\n\n\tReading in an incomplete file.\n"); 
		incomplete = file.buildAuto(incompleteFile); 

		Automobile[] models = new Automobile[] { auto, ford, empty, incomplete};

		System.out.printf("\n\n\t***Testing if the models are configured correctly.***\n"); 
		
		for (int x = 0; x < models.length; x++) {
			System.out.printf("\n\n\tProperties of auto model #%d\n", x + 1);
			models[x].printAutomobile();
			models[x].printBasePrice();
			models[x].printOptSet();
		}

		models = new Automobile[]{auto, ford}; 
		System.out.printf("\n\n\t***Testing findOptSet(String name) and printOptSet(String name) methods.***\n");

		String optset = "Color";
		System.out.printf("\n\tFirst testing with valid option set '%s'.\n", optset); 

		for (int x = 0; x < models.length; x++) {
				models[x].findOptSet(optset);
				System.out.printf("\n%s has %s configuration", models[x].getAutomobileName(), optset);
				models[x].printOptSet(optset);
		}
		
		optset = "Windows"; 
		System.out.printf("\n\tNow testing with option set '%s' that only one of the models has.\n", optset); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		/*
		for (int x = 0; x < models.length; x++) {
				models[x].findOptSet(optset);
				System.out.printf("\n%s has %s configuration", models[x].getAutoModelName(), optset);
				models[x].printOptSet(optset);
			} 
		*/
		
		ford.printOptSet(optset); 
				

		System.out.printf("\n\n\t***Testing findOptSet(int idx) and printOptSet(int idx) methods.***\n");

		int optSetIdx = 0;
		System.out.printf("\n\tFirst testing with valid index %d.\n", optSetIdx); 

		for (int x = 0; x < models.length; x++) {
			models[x].findOptSet(optSetIdx);
			System.out.printf("\n%s has the following option set:\n", models[x].getAutomobileName());
			models[x].printOptSet(optSetIdx);
		}
		
		optSetIdx = 10; 
		System.out.printf("\n\tTesting with invalid index %d.\n", optSetIdx); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		/*
		for (int x = 0; x < models.length; x++) {
			models[x].findOptSet(optSetIdx);
			System.out.printf("\n%s has the following option set:\n", models[x].getAutoModelName());
			models[x].printOptSet(optSetIdx);
		}
		*/
		
		/*
		auto.printOptSet(optSetIdx); 
		*/
		
		System.out.printf("\n\n\t***Testing findOption(String optSetName, String opName) and printOption(optSetName, String optionName) methods.***\n");
		
		optset = "Side Impact Air Bags"; 
		String option = "Present"; 
		System.out.printf("\n\tFirst testing with valid option set '%s' and its valid '%s' option\n", optset, option); 
		
		for (int x = 0; x < models.length; x++){
			models[x].findOption(optset, option); 
			models[x].printOption(optset, option);
		}
		
		option = "notValidOption"; 
		System.out.printf("\n\tNow testing with valid option set '%s' and its invalid '%s' option\n", optset, option); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		/*
		for (int x = 0; x < models.length; x++){
			models[x].findOption(optset, option); 
			models[x].printOption(optset, option);
		}
		*/
		
		optset = "Picnic table"; 
		System.out.printf("\n\tNow testing with invalid option set '%s' and its invalid '%s' option\n", optset, option); 
		System.out.printf("\n\tCode is commented out.\n"); 

		/*
		for (int x = 0; x < models.length; x++){
			models[x].findOption(optset, option); 
			models[x].printOption(optset, option);
		}
		*/
		
		System.out.printf("\n\n\t***Testing findOption(int optSetIdx, int optionIdx) and printOption(int optSetIdx, int optionsIdx) methods.***\n");
		
		optSetIdx = 1;
		int optionsIdx = 1; 
		System.out.printf("\n\tFirst testing with valid options set array index %d and options array index %d\n", optSetIdx, optionsIdx); 
		
		for (int x = 0; x < models.length; x++){
			models[x].findOption(optSetIdx, optionsIdx); 
			models[x].printOption(optSetIdx, optionsIdx);
		}
		
		optSetIdx = 1;
		optionsIdx = -1; 
		System.out.printf("\n\tNow testing with valid options set array index %d and INVALID options array index %d\n", optSetIdx, optionsIdx); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		/*
		for (int x = 0; x < models.length; x++){
			models[x].findOption(optSetIdx, optionsIdx); 
			models[x].printOption(optSetIdx, optionsIdx);
		}
		*/
		
		optSetIdx = 15;
		optionsIdx = -1; 
		System.out.printf("\n\tNow testing with INVALID options set array index %d and INVALID options array index %d\n", optSetIdx, optionsIdx); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		/*
		for (int x = 0; x < models.length; x++){
			models[x].findOption(optSetIdx, optionsIdx); 
			models[x].printOption(optSetIdx, optionsIdx);
		}
		*/
		
		System.out.printf("\n\n\t***Testing setOptSet(int numOfConfigs) method.***\n");
		
		int numberOfConfigs = 0; 
		System.out.printf("\n\tFirst testing with %d number of configurations.\n", numberOfConfigs); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		Automobile testModel = new Automobile(); 
		
		/*
		testModel.setOptSet(numberOfConfigs);
		*/
		
		numberOfConfigs = 2 ;
		System.out.printf("\n\tNow testing with %d number of configurations.\n", numberOfConfigs); 
		testModel.setOptSet(numberOfConfigs);
		testModel.setAutomobileName("Test Auto Model");
		testModel.setBasePrice(1000);
		
		
		System.out.printf("\n\n\t***Testing setOptSetName(), setNumOfOptions(), setOption(), printOptSet() methods.***\n");
		int testIdx = 0; 
		int testOptionIdx = 0; 
		int numberOfOptions = 1; 
		String testName = "Wheels"; 

		System.out.printf("\n\tFirst testing with VALID option set index.\n", numberOfConfigs); 
		
		testModel.setOptSetName(testIdx,  testName);
		testModel.setNumOfOptions(testIdx, numberOfOptions);
		testModel.setOption(testIdx, testOptionIdx, "Fancy Wheels", 500);
		testModel.printOptSet(testIdx);
		
		System.out.printf("\n\tTesting with INVALID option set index.\n", numberOfConfigs); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		testIdx = 5; 
		
		/*
		testModel.setOptSetName(testIdx,  testName);
		*/
		
		/*
		testModel.setNumOfOptions(testIdx, numberOfOptions);
		*/
		
		/*
		testModel.setOption(testIdx, testOptionIdx, "Fancy Wheels", 500);
		*/
		
		/*
		testIdx = 0; 
		testOptionIdx = 2; 
		testModel.setOption(testIdx, testOptionIdx, "Fancy Wheels", 500);
		*/
		
		/*
		testModel.printOptSet(testIdx);
		*/

		System.out.printf("\n\tCompleting the option set in 'Test Auto Model' with valid data.\n"); 
		System.out.printf("\nTesting setOptionName(), setOptionPrice() methods");
		
		testIdx = 1; 
		testOptionIdx = 0; 
		numberOfOptions = 1; 
		testName = "Seats"; 
		
		testModel.setOptSetName(testIdx,  testName);
		testModel.setNumOfOptions(testIdx, numberOfOptions);
		testModel.setOption(testIdx, testOptionIdx, "Leather seats", 500);
		testModel.printOptSet(testIdx);
		testModel.printAutomobile();
		
		System.out.printf("\n\n\t***Testing addOptSet(OptionSet newOptSet) methods.***\n");
		OptionSet newOptSet = new OptionSet(); 
		testModel.addOptSet(newOptSet);
		
		System.out.printf("\n\n\t***Testing deleteOptSet(int optSetIdx) methods.***\n");
		
		testIdx = 3; 
		System.out.printf("\n\tFirst trying to delete an option set with INVALID index %d.\n", testIdx); 
		System.out.printf("\n\tCode is commented out.\n"); 
		
		/*
		testModel.deleteOptSet(testIdx);
		*/
		
		System.out.printf("\n\tNow trying to delete an option set with INVALID name.\n");
		testModel.deleteOptSet("Color");
		
		testIdx = 0; 
		System.out.printf("\n\n\tNow deleting an option set with VALID index %d.\n", testIdx); 
		testModel.deleteOptSet(testIdx);
		
		System.out.printf("\nAdding new option set in place of deleted index and setting its properties.\n"); 
		numberOfOptions = 2; 
		testModel.addOptSet(newOptSet);
		testModel.setOptSetName(testIdx, "Wheel");
		testModel.setNumOfOptions(testIdx, numberOfOptions);
		testModel.setOption(testIdx, numberOfOptions - 2, "Present", 0);
		testModel.setOptionName(testIdx, numberOfOptions - 1, "Not Present");
		testModel.setOptionPrice(testIdx, numberOfOptions - 1,  -800); 
		testModel.printAutomobile();
		
		System.out.printf("\n\n\t***Testing deleteOption(String optSetName, String optionName) methods.***\n");
		System.out.printf("\n\n\t***Testing deleteOption(int optSetIdx,int optionIdx) methods.***\n");
		
		testModel.deleteOption("Wheels", "Present"); 	
		testModel.deleteOption(1, 0);
		testModel.printAutomobile();
		
		testModel.setOption(0, 1, "Present", 25.24f);
		testModel.printAutomobile(); 
		
		System.out.printf("\n\n\t***Testing toString()  method.***\n");
		
		System.out.printf(testModel.toString()); 
	}
catch(AutoException e){		
	try{
		e.fix(e.getErrorNo());
	}
	catch(AutoException b){
		ford = file.buildAuto(b.getFix()); 
	}
}
}
}


