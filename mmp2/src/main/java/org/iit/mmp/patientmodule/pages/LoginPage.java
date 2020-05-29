package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	
	//Identify all the webelements using locators
	By patientLoginButton=By.xpath("//a[text()='Patient Login']");
	By LoginButton=By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]");
		//	//a[@class='button button-alt'][text()='Login'];
	By userName=By.id("username");
	By pwd=By.id("password");
	By signinButton=By.xpath("//input[@name='submit']");
	By homePageTitle=By.xpath("//a[contains(text(),'HOME')]");
	
	/**
	 * parameterized constructor for assigning the passed  driver object
	 * @param driver
	 */
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	
	
	/**
	 * Navigate to the Patient Login buton
	 * @throws InterruptedException
	 */
	public void navigateToPtntButton() throws InterruptedException
	{
		driver.findElement(patientLoginButton).click();
		Thread.sleep(2000);
		driver.findElement(LoginButton).click();
		Thread.sleep(2000);
	}
	
	
	/**
	 * Enter valid username /pwd and Login
	 * @param username
	 * @param password
	 * @return 
	 * @return
	 * @throws InterruptedException
	 */
	public  void patientLoginClick(String username,String password) throws InterruptedException
	{
		
		driver.findElement(userName).sendKeys(username);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(signinButton).click();
		
		
	}
	
	
	/**
	 * Get the patient portal home page title
	 * @return
	 */
	public boolean getHomepageTitle()
	{
		boolean result=driver.findElement(homePageTitle).getText().contains("HOME");
		return result;
	
		
	}
	
	
	

	/**
	 * invalid Login Alert 
	 * @return
	 */
	public String alertpopup()
	{
		String popText=null;
		try {
			Alert alrt=driver.switchTo().alert();
			popText=alrt.getText();
			alrt.dismiss();
			return popText;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return popText;
		}
		
	}

	
	/*public boolean alertp()
	{
		Alert alrt=driver.switchTo().alert();
		String popText=alrt.getText();
		System.out.println("Invalid Credentials Error:  " +popText);
		alrt.dismiss();
		driver.close();
		return true;
	}
*/
	


	
}
