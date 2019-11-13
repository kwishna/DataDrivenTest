package utilities;

public class TestUtil {
	
	/**
	 * Reading Suite.xlsx
	 */
	public static boolean isSuiteRunnable(String suiteName, ExcelReader2 excel) {
		
		int rows = excel.getRowCount(Constants.SUITE_SHEET);
//		System.out.println(rows);
		
		for(int rowNum=2; rowNum<rows; rowNum++) {
			
			String data = excel.getCellData(Constants.SUITE_SHEET, Constants.SUITE_NAME_COL, rowNum); //Reading The Suite Name
		//	System.out.println(data);
		//	System.out.println(rowNum);
			
			if(data.equalsIgnoreCase(suiteName)) {
				
				String runMode = excel.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, rowNum); // Runmode WRT Suite Name
			//	System.out.println(runMode);
			//	System.out.println(rowNum);
				
				if(runMode.equalsIgnoreCase("Y")) {
					return true;
				}
				
				else
					return false;
			}
		}
		return false;
	}
	
	public static boolean isTestRunnable(String testCaseName, ExcelReader2 excel) {
		
		int rows = excel.getRowCount(Constants.TESTCASE_SHEET);
//		System.out.println(rows);
		
		for(int rowNum=2; rowNum<rows; rowNum++) {
			
			String data = excel.getCellData(Constants.TESTCASE_SHEET, Constants.TESTCASE_COL, rowNum); //Reading The Suite Name
		//	System.out.println(data);
		//	System.out.println(rowNum);
			
			if(data.equalsIgnoreCase(testCaseName)) {
				
				String runMode = excel.getCellData(Constants.TESTCASE_SHEET, Constants.RUNMODE_COL, rowNum); // Runmode WRT Suite Name
			//	System.out.println(runMode);
			//	System.out.println(rowNum);
				
				if(runMode.equalsIgnoreCase("Y")) {
					return true;
				}
				
				else
					return false;
			}
		}
		return false;
		
	}
}
