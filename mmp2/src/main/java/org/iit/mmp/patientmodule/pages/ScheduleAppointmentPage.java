package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentPage{
	
	WebDriver driver;
	HelperClass helperObj;
	Utility util;
	//Identify all the web elements using locators
	
	By patientLoginButton=By.xpath("//a[text()='Patient Login']");
	By LoginButton=By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]");
	By userName=By.id("username");
	By pwd=By.id("password");
	By signinButton=By.xpath("//input[@name='submit']");
	By schApptButton=By.xpath("//span[contains(text(),'Schedule Appointment')]");
	By createapptButton=By.xpath("//a/input[@value='Create new appointment']");
	String docBookApptBtnXpath="//h4[contains(text(),'%doctorName%')]/ancestor::td//button[@id='opener']";
	By datePicker=By.id("datepicker");
	By timepicker=By.id("time");
	By continueButton=By.id("ChangeHeatName");
	By sympTextbox=By.id("sym");
	By submitButton=By.xpath("//input[@value='Submit']");
	
	By homeAppointmentDateText=By.xpath("//table[@class='table']//tr[1]/td[1]");
	By homeAppointmentTimeText=By.xpath("//table[@class='table']//tr[1]/td[2]");
	By homeAppointmentSymptomsText=By.xpath("//table[@class='table']//tr[1]/td[3]");
	By homeAppointmentDocText=By.xpath("//table[@class='table']//tr[1]/td[4]");
	By scheduleAppointmentDateText=By.xpath("//div[@class='col-md-3']//h3");
	By scheduleAppointmentTimeText=By.xpath("//a[contains(text(),'Time :')]");
	By scheduleAppointmentProviderText=By.xpath("//a[contains(text(),'Provider:')]");
	By scheduleAppointmentSymptomsText=By.xpath("//a[contains(text(),'Symptoms:')]");
	
	
	public ScheduleAppointmentPage(WebDriver driver) {
		this.driver=driver;
	}



	public void instantiateDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver  = new ChromeDriver();
	}
	
	
	
	public void clickOnCreateAppointmentButton()
	{
		driver.findElement(createapptButton).click();
	}

	
	public HashMap<String,String> selectDoctor(String doctorName) throws InterruptedException
	{
		
		String docBookApptBtn=docBookApptBtnXpath.replace("%doctorName%", doctorName);
		HashMap<String,String> hMap=new HashMap<String,String>();
		/*try {
			Thread.sleep(2000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		driver.findElement(By.xpath(docBookApptBtn)).click();	
		helperObj=new HelperClass(driver);
		driver=helperObj.switchtoAFrameAvailable("myframe",10);
	
		String dateOfAppointment=util.getFutureDate(11);
		driver.findElement(datePicker).sendKeys(dateOfAppointment);
		Thread.sleep(3000);
		String time="11Am";
		new Select(driver.findElement(timepicker)).selectByVisibleText(time);
		Thread.sleep(3000);
		driver.findElement(continueButton).click();
		String symptoms="Need an appointment with "+doctorName+" on date "+dateOfAppointment+" for Cold and Cough Symptoms";
		driver.findElement(sympTextbox).sendKeys(symptoms);
		driver.findElement(submitButton).click();
		hMap.put("dateOfAppointment", dateOfAppointment);
		hMap.put("time", time);
		hMap.put("symptoms",symptoms);
	
	//	hMap.put("doctorName", doctorName.substring(3)); will store original value and use contains when comparing
		hMap.put("doctorName", doctorName);
		
		return hMap;
	}
	
	
	public boolean validateAppointmentDetailsInHomePage(HashMap<String,String> hMap)
	{
		boolean result=false;
		helperObj.moduleNavigation("HOME");
		if( hMap.get("dateOfAppointment").equals(driver.findElement(homeAppointmentDateText).getText())
				&& hMap.get("time").equals(driver.findElement(homeAppointmentTimeText).getText())
				&& hMap.get("symptoms").equals(driver.findElement(homeAppointmentSymptomsText).getText())
				&& hMap.get("doctorName").contains(driver.findElement(homeAppointmentDocText).getText()))		
		{
			result=true;
		}
		return result;
	
	}
	      
   public boolean validateAppointmentDetailsInSchedulePage(HashMap<String,String> hMap)
    {
	
	   
	   boolean result=false;
	   helperObj.moduleNavigation("Schedule Appointment");
    	   WebElement we1 = driver.findElement(scheduleAppointmentTimeText);
    	   WebElement we2 = driver.findElement(scheduleAppointmentProviderText);
    	   WebElement we3 = driver.findElement(scheduleAppointmentSymptomsText);
    	 //Splitting the string based on ":" delimiter to compare on home and Schedule appt screens.
    	   String appTime[]=we1.getText().split(":");
    	   String providerName[]=we2.getText().split(":");
    	   String symptoms[]=we3.getText().split(":");
    	if( hMap.get("dateOfAppointment").equals(driver.findElement(scheduleAppointmentDateText).getText().trim())
    		
				&& hMap.get("time").equals(appTime[1].trim())
    		    && hMap.get("doctorName").contains(providerName[1])
    		    && hMap.get("symptoms").equals(symptoms[1])
    		    && we3.getText().contains(hMap.get("symptoms")))
    	
    	    {
				result = true;
			}
    	  
		return result;
    	
    	
    }
}

