package rough;

import utilities.Constants;
import utilities.ExcelReader2;

public class Roughwork {

	public static void main(String[] args) {
	//	System.out.println(System.getProperty("user.dir"));
		ExcelReader2 rdr = new ExcelReader2(System.getProperty("user.dir")+"/src/test/resources/excel/BankManagerSuite.xlsx");
		int rows = rdr.getRowCount(Constants.TEST_DATA_SHEET);
		
		String testName = "OpenAccountTest";
	
		// Row At Which Test Case Starts	
		int testCaseStartRowNo = 0;
		for(testCaseStartRowNo = 0; testCaseStartRowNo<rows; testCaseStartRowNo++) {
			
			String data = rdr.getCellData(Constants.TEST_DATA_SHEET, 0, testCaseStartRowNo);
			if(data.equalsIgnoreCase(testName)) 
			break;
			
		}
		
		System.out.println("Testcase Starts From Row : "+testCaseStartRowNo);
		
		// Checking Total Test Data Rows In A Test
		int dataRowsCount = 0;
		while(!(rdr.getCellData(Constants.TEST_DATA_SHEET, 0, testCaseStartRowNo+dataRowsCount+2).equalsIgnoreCase(""))) {
			
			dataRowsCount++;
		}
		System.out.println("Total Rows Of Data Are : "+dataRowsCount);
		
		// Counting Total Cell In A Test Case 
		int totalCellNo = 0;
		while((!(rdr.getCellData(Constants.TEST_DATA_SHEET, totalCellNo, testCaseStartRowNo+1).equalsIgnoreCase("")))) {
			totalCellNo++;
		}
		System.out.println("Total Cell Numbers In The Test Case : "+totalCellNo);
		
		// Printing Cell Data
		for(int i=testCaseStartRowNo+2; i<dataRowsCount+testCaseStartRowNo+2; i++) {
			for(int j=0; j<totalCellNo; j++) {
				System.out.println(rdr.getCellData(Constants.TEST_DATA_SHEET, j, i));
			}
		}
	}
}
