package org.iit.mmp.patientmodule.pages;

import java.time.LocalDateTime;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage {
	
	WebDriver driver;
	By messageTitle=By.xpath("//body//h3");
	By subjectBox=By.id("subject");
	By messagebox=By.id("message");
	By sendButton=By.xpath("//input[@value='Send']");
	
	public MessagesPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void enterReason()
	{
		driver.findElement(subjectBox).sendKeys("Subject -Test");
	}

	
	public void enterMessage()
	{
		LocalDateTime date=java.time.LocalDateTime.now();
		java.util.Date date1=new java.util.Date();  
		System.out.println("java date: " +date1);
		driver.findElement(messagebox).sendKeys("This is test message send on " +date+ " for testing message generation." );
	}
	
	public void clickSendButton()
	{
		driver.findElement(sendButton).submit();
	}
	
	public String validateMessageSuccessful()
	{
		Alert alert =driver.switchTo().alert();
		String expected=alert.getText();
		alert.accept();
		
		return expected;
	}
}
