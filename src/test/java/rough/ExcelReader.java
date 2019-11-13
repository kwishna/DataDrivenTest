package rough;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
 * This Class Reads The Excel File.
 */
public class ExcelReader{
	
	private FileInputStream fin;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
/**
 * When All The Methods Are Static And Variables Are Also Static There Is No Meaning Of Putting Constructor In The Class.
 * All The Methods Will Can Be Accessed By Class Name Directly. 
 * But, It Is Also Good To Put Just One Method To Initialize. As We Can Initialize It Once In Base Class And Use
 * Everywhere.
 * 	
 * @param excelPath
 */
	public ExcelReader(String excelPath) { // Earlier It Was A Constructor. One Object Was Creating In Base Class.
		File f = new File(excelPath);
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workbook = new XSSFWorkbook(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Getting Physical No Of Rows
	public int getCountOfRows(String sheetName) {
		XSSFSheet sheet = workbook.getSheet(sheetName);
		return sheet.getPhysicalNumberOfRows();
		
	}
	
	//Getting Physical No Of Cols
	public int getCountOfCols(String filePath, String sheetName, int row) {
		
		XSSFSheet sheetx = workbook.getSheet(sheetName);
		XSSFRow rowx = sheetx.getRow(row);
		return rowx.getPhysicalNumberOfCells();
		
	}
	
	
	//Get Data As Object[][] From Excel
	public Object[][] readAs2DObjectArray(String sheetName) {
//		new ExcelReader(config.getProperty("excelfilepath")); //Creating Object Of ExcelReader;
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount-1][];
		for(int i=1; i<rowCount; i++) {
			row = sheet.getRow(i);
			int cellCount = row.getPhysicalNumberOfCells();
			data[i-1] = new Object[cellCount];
			for(int j=0; j<cellCount; j++) {
				cell = row.getCell(j);
				data[i-1][j] = cell.getStringCellValue();
				//System.out.println(s);
			}	
		}
		return data;
	}
	
//Get Data As String From A Particular Cell Of A Sheet	
	public String readFromCell(int row, int col, String sheetName) {
		String value="";
		XSSFSheet s = workbook.getSheet(sheetName);
		try {
			XSSFRow r = s.getRow(row);
			XSSFCell c = r.getCell(col);
			value = c.getStringCellValue();
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		
		return value;
	}
	
// For Reading Which Test Case To Run & Which Is To Be Skipped.
// We Can Create Different Type Of Suite In Excel Sheet In Each Sheet.
// So, Pass The Required Sheet & Run The Test
	public boolean isRunnable(String testCaseName, String sheetName) {
		boolean flag = false;
		String permisson;
		XSSFSheet s = workbook.getSheet(sheetName);
		int rowCount = s.getPhysicalNumberOfRows();
		HashMap<String, String> test = new HashMap<String, String>();
		for(int k=1; k<rowCount; k++) {
			test.put(readFromCell(k, 0, sheetName), readFromCell(k, 1, sheetName));
		}

		Set<String> keyset = test.keySet();
		if(keyset.contains(testCaseName)) {
			permisson = test.get(testCaseName);
			if(permisson.equalsIgnoreCase("Y")) {
				flag=true;
			}
		}
		else {
			flag=false;
		}
		return flag;
	}
	
//  Instead Of Using Object Array. We Can Put Hashtable Array In Object[][]
	public Object[][] readWithHashtable(String sheetName) {
//		new ExcelReader(config.getProperty("excelfilepath")); //Creating Object Of ExcelReader;
//		String sheetName=config.getProperty("ymaildata");
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount-1][1];
		Hashtable<String, String> hash = null;
		for(int i=1; i<rowCount; i++) {
			XSSFRow r = sheet.getRow(i);
			int colCount =  r.getPhysicalNumberOfCells();
			hash = new Hashtable<String, String>();
			for(int j=0; j<colCount; j++) {
				
				String x = readFromCell(0, j, sheetName);
				String y = readFromCell(i, j, sheetName);
				hash.put(x, y);
//				System.out.println(hash);
			}	
			data[i-1][0] = hash;
		}
		
		return data;
	}

//	This DataProvider Is Sending Data To A Test In Another Class. For That, It Must Be A static Method
//	Commenting Below Code, When Separate DataProvider Class Is Created
//	@DataProvider(name="baba")
//	public static Object[][] dataForYahoo(){
		
//		return(excelRead("GoogleSignUpTest"));
//	}
	
	
	
	
	/* To Provide Data To More Than One @Test Using Single Data Provider.
	@DataProvider(name="dynamic")
	public static Object[][] dataForAnyTestCase(Method m) throws FileNotFoundException, IOException{
		String methodName = m.getName();
		System.out.println(m);
		if(methodName.equalsIgnoreCase("login")){
		return DataGenerator.getExcelValue("Login"); //"Login" Is SheetName
		}
		
		else if(m.getName().equalsIgnoreCase("register")){
			return DataGenerator.getExcelValue("Register"); //"Register" Is SheetName
		}
		
		else{
			return null;
		}
	}*/
	
}
