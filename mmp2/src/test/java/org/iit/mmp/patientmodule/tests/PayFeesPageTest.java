package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.iit.mmp.patientmodule.pages.PayFeesPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class PayFeesPageTest extends InvokeBrowser{
	PayFeesPage payObj;
	HelperClass helperObj;
	LoginPage lObj;
	
	@Test(description="PAT006-Pay Fees",groups={"PAT006","sanity","regression","patientmodule"})
	public void validatePayFees() throws IOException, InterruptedException
	{
		
		ProjectConfiguration pConfig=new ProjectConfiguration();
		Properties prop=pConfig.loadProperties();

		String pUsername = prop.getProperty("pUsername");
		String pPassword = prop.getProperty("pPassword");
		String ccHolderName=prop.getProperty("ccHolderName");
		String cardName=prop.getProperty("cardName");
		String cardNo=prop.getProperty("cardNo");
		String cvvValue=prop.getProperty("cvvValue");
		
		lObj=new LoginPage(driver);
		lObj.navigateToPtntButton();
		lObj.patientLoginClick(pUsername, pPassword);
		
		helperObj=new HelperClass(driver);
		helperObj.moduleNavigation("Fees");
		
		payObj=new PayFeesPage(driver);
		boolean result=payObj.pageTitleValidation();
		Assert.assertTrue(result);
		payObj.clickPayNowButton();
		payObj.selectAmount();
		Thread.sleep(3000);
		payObj.clickContinueButton();
		boolean result1=payObj.enterCardDetails(ccHolderName, cardName, cardNo, cvvValue);
		helperObj.captureScreenshot("PAT006-Pay Fees");
		Assert.assertTrue(result1);
		Thread.sleep(3000);
		
	   
		
	}
	@AfterClass
	public void closeApp()
	{
		HelperClass helpClassObj =new HelperClass(driver);
		helpClassObj.closeBrowser(driver);
	}

}
