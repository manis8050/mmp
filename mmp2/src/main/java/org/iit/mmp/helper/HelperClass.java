package org.iit.mmp.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.config.ProjectConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {

	WebDriver driver;
	String moduleXpath="//span[contains(text(),'%moduleName%')]";
	By searchField=By.xpath("//input[@id='search']");
	By searchButton=By.xpath("//input[@value='search']");
	By searchPatientName=By.xpath("//div[@id='show']//tbody/tr/td/a");
	By symptomsTextField=By.xpath("//textarea[@name='sym']");
	By submitButton=By.xpath("//input[@value='Submit']");
	String docBookApptBtnXpath="//h4[contains(text(),'%doctorName%')]/ancestor::td//button[@id='opener']";
	By datePicker=By.id("datepicker");
	By time=By.id("time");
	By continueButton=By.id("ChangeHeatName");
	
	
public HelperClass(WebDriver driver)
{
	this.driver=driver;
}


public void moduleNavigation(String moduleName)
{
String moduleValue=moduleXpath.replace("%moduleName%", moduleName);

	driver.findElement(By.xpath(moduleValue)).click();

}
public void launchApplicationURL(String url)
{
	driver.get(url);
	driver.manage().window().maximize();
}

public WebDriver switchtoAFrameAvailable(String frameId,int timeinSecs)
{
	
	WebDriverWait wait=new WebDriverWait(driver,timeinSecs);
	driver=wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
	return driver;

	
}

public void searchPatientSSN(String ssn) throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(searchField).sendKeys(ssn);
	Thread.sleep(2000);
	driver.findElement(searchButton).click();
	Thread.sleep(3000);
	driver.findElement(searchPatientName).click();
	
}

public void selectDoctor(String docName) throws InterruptedException, IOException
{
	String docBookApptButton=docBookApptBtnXpath.replace("%doctorName%", docName);
	HashMap<String,String> hMap=new HashMap<String,String>();
	driver.findElement(By.xpath(docBookApptButton)).click();
	driver=switchtoAFrameAvailable("myframe", 30);

	String dateOfAppointment=Utility.getFutureDate(20, "MM/dd/YYYY");
    driver.findElement(datePicker).sendKeys(dateOfAppointment);
    //There is an overlap with the pop-up and time drop down. So sleep time is needed. Implicit wait and explicit wait did not work.
    Thread.sleep(3000);
    String timeValue="12Pm";
    new Select(driver.findElement(time)).selectByIndex(2);
    Thread.sleep(2000);
    driver.findElement(continueButton).click();
    enterPatientSymptoms();
    
    
    
	
}

public void enterPatientSymptoms() throws IOException, InterruptedException
{
	//Loading the patient symptoms from config file
	ProjectConfiguration pConfig=new ProjectConfiguration();
	Properties prop=pConfig.loadProperties();
	String patientSymptoms=prop.getProperty("ptntSymptoms");
	Thread.sleep(2000);
	driver.findElement(symptomsTextField).clear();
	driver.findElement(symptomsTextField).sendKeys(patientSymptoms);
	driver.findElement(submitButton).click();
	
	
	
}
public void closeBrowser(WebDriver driver)
{
	driver.close();
}




public void captureScreenshot(String tc_Name)
{
//TakesScreenshot is an interface which is implemented by the class ChromeDriver();
//Child class object assigned to the parent class

//overcasting- Converting one data type to another type. This is explicit casting where we are converting the driver
//which is webdriver type to Screenshot.


TakesScreenshot tsh=(TakesScreenshot)driver;
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


