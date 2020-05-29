package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PayFeesPage {
	
	WebDriver driver;
	By pagetitle=By.xpath("//h3[@class='panel-title']");
	By payNowbutton=By.xpath("//a/button[contains(text(),'Pay Now')]");
	By paymentDropdown=By.id("amount");
	By continueButton=By.xpath("//input[@value='Continue']");
	By CardInfoPageTitle=By.xpath("//h3[@class='panel-title']");
	By nameField=By.id("name");
	By cardType=By.id("card_name");
	By cardNumber=By.id("cid");
	By expMonth=By.id("cardMonth");
	By expYear=By.id("cardYear");
	By cvv=By.id("cvv");
	By submitButton=By.xpath("//input[@value='submit']");
	
	public PayFeesPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean pageTitleValidation()
	{
		boolean result=driver.findElement(pagetitle).getText().contains("Fees");
		return result;
		
	}
	public void clickPayNowButton()
	{
		driver.findElement(payNowbutton).click();
	}
		
	public void selectAmount()
	{
		new Select(driver.findElement(paymentDropdown)).selectByIndex(1);
	}

	public void clickContinueButton()
	{
		driver.findElement(continueButton).click();
		
	}
	
	public boolean enterCardDetails(String ccHolderName,String cardName,String cardNo,String cvvValue)
	{
		boolean result=driver.findElement(CardInfoPageTitle).getText().contains("Card Information");
		driver.findElement(nameField).sendKeys(ccHolderName);
		new Select(driver.findElement(cardType)).selectByVisibleText(cardName);
		driver.findElement(cardNumber).sendKeys(cardNo);
		driver.findElement(cvv).sendKeys(cvvValue);
		driver.findElement(submitButton).click();
		return result;
	}
	
	
	
	
}
