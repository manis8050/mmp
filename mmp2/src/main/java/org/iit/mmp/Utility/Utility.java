package org.iit.mmp.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Utility {
	WebDriver driver;
/*public Utility(WebDriver driver)
{
	this.driver=driver;
}
*/
//Define all methods in the Utility as static.
/**
 * Create a calender instance, add required no of days to the current date, covert the date to the required format using the simpledateFormat.
 * @param days
 * @param pattern
 * @return
 */
public static String getFutureDate(int days,String pattern)
{
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, days);
	Date d=cal.getTime();
	SimpleDateFormat sdf=new SimpleDateFormat(pattern);
	String date=sdf.format(d);
	return date;
	
}

//both these are overloaded methods
public static String getFutureDate(int days)
{
	Calendar cal=Calendar.getInstance();
	cal.add(Calendar.DATE, days);
	
	Date d=cal.getTime();
	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/YYYY");
	String date=sdf.format(d);
	return date;
}


public static String getRandomStringvalue(int charLength)
{
	Random rnd = new Random();
	String rndString="";
	for(int i=0;i<charLength;i++) {
		char c = (char) (rnd.nextInt(26) + 'a');
		rndString=rndString+c;
	}
	
	return rndString;
	
}

public void captureScreenshot(String tc_Name)
{
//TakesScreenshot is an interface which is implemented by the class ChromeDriver();
//Child class object assigned to the parent class

//overcasting- Converting one data type to another type. This is explicit casting where we are converting the driver
//which is webdriver type to Screenshot.


TakesScreenshot tsh=(TakesScreenshot) driver;
File srcFile=tsh.getScreenshotAs(OutputType.FILE);

//This gives the location of the file
System.out.println("Path::" +srcFile.getAbsolutePath());

String destinationPath=System.getProperty("user.dir")+"\\screenshots\\Testcase_"+tc_Name+"_"+Calendar.getInstance().getTimeInMillis()%100000000+".jpg";

File destFile=new File(destinationPath);

// When copying or read, the developer feels that there is a possibility of getting an error(like network is broken)
try {
	FileHandler.copy(srcFile,destFile);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}


public String[][] readXlsFile(String filepath,String SheetName) throws IOException
{
	File f=new File(filepath);
	FileInputStream fis=new FileInputStream(f);
	
	HSSFWorkbook hWB=new HSSFWorkbook(fis);
	HSSFSheet sheet=hWB.getSheet(SheetName);
	
	//No of rows
	int rows=sheet.getPhysicalNumberOfRows();
	System.out.println("Row count:::" +rows);
	
	//no of columns
	int cols=sheet.getRow(0).getLastCellNum();
	System.out.println("Column count:::" +cols);
	
	int k=0;
	
	String[][] data=new String[rows-1][cols];
	
	//If the first row contains the column names
	for(int i=1;i<rows;i++)
	{
		for(int j=0;j<cols;j++)
		{
			data[k][j]=sheet.getRow(i).getCell(j).toString();
			System.out.println(data[k][j]);
		}
		k++;
	}
			
	return data;
	
}


public String[][] read_xlsx(String filepath,String sheetName) throws IOException
{
	
	File f=new File(filepath);
	
	FileInputStream fis=new FileInputStream(f);
	
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	
    XSSFSheet sheet=wb.getSheet(sheetName);
  //To get no of rows
	int rows=sheet.getPhysicalNumberOfRows();
	System.out.println("row count is:::" +rows);
	
//To get no of columns
	int cols=sheet.getRow(0).getLastCellNum();
	System.out.println("Column count is:::" +cols);
	
//Loop to read the data from rows/columns

	 int k=0;
	 String[][] data=new String[rows-1][cols];
	
	 //If the first row is the column name
	for (int i=1;i<rows;i++)
	{

		for(int j=0;j<cols;j++)
		{
			
			data[k][j]=sheet.getRow(i).getCell(j).toString();
			System.out.println(data[k][j]);
			
		}
		k++;
		}

	return data;
	
}


public String[][] readExcel_POI(String filepath,String sheetName) throws InvalidFormatException, IOException
{
	
	File f=new File(filepath);
	
	FileInputStream fo=new FileInputStream(f);
	
	Workbook workbook=WorkbookFactory.create(fo);
	
	Sheet sheet=workbook.getSheet(sheetName);
 //To get no of rows
	int rows=sheet.getLastRowNum();
	System.out.println("row count is:::" +rows);
	
//To get no of columns
	int cols=sheet.getRow(0).getLastCellNum();
	System.out.println("Column count is:::" +cols);
	

	 int k=0;
	 //ideal to define an array of Object class as it can hold any type of data.
	 String[][] data=new String[rows-1][cols];
	
	 //If the first row is the column name
	for (int i=1;i<rows;i++)
	{

		for(int j=0;j<cols;j++)
		{
			
			data[k][j]=sheet.getRow(i).getCell(j).toString();
			
		}
		k++;
		}
	fo.close();
	return data;
	
}



public static String generateRandom(int n,int range)
{

String str="";
for(int i=0;i<n;i++)
{
	str=str+9;
	
	
	
}
return str+new Random().nextInt(range);
}


}

