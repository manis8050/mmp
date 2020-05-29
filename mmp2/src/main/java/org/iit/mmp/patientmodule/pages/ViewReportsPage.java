package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewReportsPage {
	WebDriver driver;
	
	By ReportsButton=By.xpath("//button[contains(text(),'ViewReports')]");
	By reportPageTitle=By.xpath("//h3[@class='panel-title']");
	
	public ViewReportsPage(WebDriver Driver)
	{
		this.driver=driver;
	}
	
	public boolean pageTitleValidation()
	{
		boolean result=driver.findElement(reportPageTitle).getText().contains("View Reports");
		return result;
		
	}

}
