package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {
	
	WebDriver driver;
	public InformationPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String fetchInformationMessage()
	{
		String actual = driver.findElement(By.xpath("//div[@class='panel-title']")).getText();
		//System.out.println("The Actual Value" + actual);
		return actual;
	}

}
