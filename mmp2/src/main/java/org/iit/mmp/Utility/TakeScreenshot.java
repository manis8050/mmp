package org.iit.mmp.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenshot {
	
	
	

	public void captureScreenshot(WebDriver driver, String tc_Name)
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
	
}


