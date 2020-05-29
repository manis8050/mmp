package org.iit.mmp.adminmodule.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateFeePage {

	//Declaring the reference variables for all calling classes
	WebDriver driver;
	
	
	//Locating all the elements in the webpage
	By createFeeButton=By.xpath("//div[@id='container_body']//a/input[@value='Create Fee']");
	By feePageTitle=By.xpath("//h3[@class='panel-title']");
	By apptDate=By.id("app_date");
	By serviceName=By.id("service");
	By submitButton=By.xpath("//input[@value='submit']");
	
	
	public CreateFeePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void createFeeClick()
	{
		driver.findElement(createFeeButton).click();
		
	}
	public boolean pageTitleValidation()
	{
		boolean result=driver.findElement(feePageTitle).getText().contains("Fee");
		return result;
	}
	
	public void patientFeeCreation()
	{
		new Select(driver.findElement(apptDate)).selectByIndex(1);
		new Select(driver.findElement(serviceName)).selectByIndex(1);
		driver.findElement(submitButton).click();
		
	}
	
	
	public String fetchFeeSuccessMessage()
	{
		Alert alrt=driver.switchTo().alert();
		String alertText=alrt.getText();
		System.out.println("Error:(Alert Box dected)- message-:" +alertText);
		alrt.accept();
		return alertText;
		
	}
}
