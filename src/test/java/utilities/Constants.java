package utilities;

public class Constants {

	public static String SUITE_SHEET = "Suite"; // Suite.xlsx
	public static String SUITE_NAME_COL = "SuiteName"; // Suite.xlsx
	public static String RUNMODE_COL = "Runmode";
	
	public static String TESTCASE_SHEET = "TestCases"; // BankManagerSuite.xlsx, Sheet 1 ::: It Can Be Different xlsx File But Col Name Must Be Same
	public static String TESTCASE_COL = "Testcases"; // BankManagerSuite.xlsx , Sheet 1 ::: It Can Be Different xlsx File But Col Name Must Be Same
	 
	public static String TEST_DATA_SHEET = "TestData"; // BankManagerSuite.xlsx, Sheet 2 ::: It Can Be Different xlsx File But Sheet Name Must Be Same
	
	public static String FILE_LOCATION = System.getProperty("user.dir")+"/src/test/resources/excel/";
		
}
