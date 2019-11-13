package utilities;

public class Test {

	public static void main(String[] args) {
	
		boolean b = TestUtil.isSuiteRunnable("BankManagerSuite", new ExcelReader2(Constants.FILE_LOCATION+"Suite.xlsx"));
		System.out.println(b);
		
		boolean b1 = TestUtil.isTestRunnable("AddCustomerTest", new ExcelReader2(Constants.FILE_LOCATION+"BankManagerSuite.xlsx"));
		System.out.println(b1);
	}

}