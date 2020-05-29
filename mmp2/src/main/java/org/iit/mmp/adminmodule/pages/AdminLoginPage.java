package org.iit.mmp.adminmodule.pages;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.config.ProjectConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage {
	
	WebDriver driver;
	By officeLoginButton=By.xpath("//a[contains(text(),'Office Login')]");
	By AdminLoginButton=By.xpath("//section[@id='features']//a[1]");
	By adminUsername=By.id("username");
	By adminPwd=By.id("password");
	By signInButton=By.xpath("//input[@name='admin']");
	By homepageTitle=By.xpath("//li[contains(text(),'HOME')]");
	
	
	public AdminLoginPage(WebDriver driver)
	{
		this.driver=driver;
	}

	
	public void navigateToAdminButton() throws InterruptedException
	{
		driver.findElement(officeLoginButton).click();
		Thread.sleep(2000);
		driver.findElement(AdminLoginButton).click();
		
	}
	public void adminLoginClick() throws IOException
	{
		ProjectConfiguration pConfig=new ProjectConfiguration();
		Properties prop=pConfig.loadProperties();
		String adUsername=prop.getProperty("adminUsername");
		String adPassword=prop.getProperty("adminPassword");
		
	    driver.findElement(adminUsername).sendKeys(adUsername);
	    driver.findElement(adminPwd).sendKeys(adPassword);
	    driver.findElement(signInButton).click();
	    
		
	}
	
	public boolean homePageTitle()
	{
		boolean result=driver.findElement(homepageTitle).getText().contains("HOME");
		return result;
	}
	
}
