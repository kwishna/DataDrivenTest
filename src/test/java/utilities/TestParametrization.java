package utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParametrization {
	
	@Test(dataProvider="dp")
	public void testing(String a, String b, String c, String d) {
		
		System.out.println("::: START :::");
		System.out.println("------- : "+a);
		System.out.println("------- : "+b);
		System.out.println("------- : "+c);
		System.out.println("------- : "+d);
		System.out.println("::: STOP :::");
		
	}
	
	
	@DataProvider(name="dp")
	public Object[][] getData() {
		
//		System.out.println(System.getProperty("user.dir"));
			ExcelReader2 rdr = new ExcelReader2(Constants.FILE_LOCATION+"BankManagerSuite.xlsx"); // Any Suite xlsx
			int rows = rdr.getRowCount(Constants.TEST_DATA_SHEET); // All Rows In The Suite
			
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
			while(!(rdr.getCellData(Constants.TEST_DATA_SHEET, 0, testCaseStartRowNo+2 + dataRowsCount).equalsIgnoreCase(""))) {
				// 2 Is Added Because Data Starts After 2 Rows. These 2 Rows Are Header.
				dataRowsCount++;
			}
		//	System.out.println("Total Rows Of Data Are : "+dataRowsCount);
			
			// Counting Total Cell In A Test Case 
			int totalCellNo = 0;
			while((!(rdr.getCellData(Constants.TEST_DATA_SHEET, totalCellNo, testCaseStartRowNo+1).equalsIgnoreCase("")))) {
				totalCellNo++;
			}
		//	System.out.println("Total Cell Numbers In The Test Case : "+totalCellNo);
			
			
			// Printing Cell Data And Data Provide
			Object[][] data = new Object[dataRowsCount][totalCellNo];
			for(int i=testCaseStartRowNo+2; i<testCaseStartRowNo+2 + dataRowsCount; i++) {
				
				for(int j=0; j<totalCellNo; j++) {
			//		System.out.println(rdr.getCellData(Constants.TEST_DATA_SHEET, j, i));
					data[i-(testCaseStartRowNo+2)][j] = rdr.getCellData(Constants.TEST_DATA_SHEET, j, i);
			}
		}
			return data;
	}
}
