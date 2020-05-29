package org.iit.mmp.adminmodule.pages;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AdminScheduleAppointmentPage {
	
	//Creating the reference variables for classes and later we create a object of the class.
	WebDriver driver;
	Utility util;
	HelperClass helperObj;
	
	
	By createVisitButton=By.xpath("//div[@id='container_body']//a/input[@value='Create Visit']");
	String docBookApptBtnXpath="//h4[contains(text(),'%doctorName%')]/ancestor::td//button[@id='opener']";
	By datePicker=By.id("datepicker");
	By time=By.id("time");
	By continueButton=By.id("ChangeHeatName");
	
	public AdminScheduleAppointmentPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	
	public void createVisitClick()
	{
		driver.findElement(createVisitButton).click();
		
	}
	
	
	//Moved to helper class as it can be used by both Admin and Patent module.
/*	public void selectDoctor(String docName) throws InterruptedException, IOException
	{
		String docBookApptButton=docBookApptBtnXpath.replace("%doctorName%", docName);
		HashMap<String,String> hMap=new HashMap<String,String>();
		driver.findElement(By.xpath(docBookApptButton)).click();
		helperObj=new HelperClass(driver);
		driver=helperObj.switchtoAFrameAvailable("myframe", 30);

		String dateOfAppointment=Utility.getFutureDate(20, "MM/dd/YYYY");
	    driver.findElement(datePicker).sendKeys(dateOfAppointment);
	    //There is an overlap with the pop-up and time drop down. So sleep time is needed. Implicit wait and explicit wait did not work.
	    Thread.sleep(3000);
	    String timeValue="12Pm";
	    new Select(driver.findElement(time)).selectByIndex(2);
	    Thread.sleep(2000);
	    driver.findElement(continueButton).click();
	    
	    helperObj.enterPatientSymptoms();
	    
	    
	    
		
	}*/

}
