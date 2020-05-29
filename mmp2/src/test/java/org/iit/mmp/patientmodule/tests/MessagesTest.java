package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.iit.mmp.patientmodule.pages.MessagesPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MessagesTest extends InvokeBrowser{
	
	SoftAssert sa=new SoftAssert();

	@Test(description = "PAT009 Send Message", groups = {"PAT009", "regression", "sanity", "patientmodule" })
	public void validateMessageTest() throws InterruptedException, IOException
	{
		
		
		ProjectConfiguration pConfig=new ProjectConfiguration();
		Properties prop=pConfig.loadProperties();
		String pUsername=prop.getProperty("pUsername");
		String pPassword=prop.getProperty("pPassword");
		
		LoginPage lObj=new LoginPage(driver);
	    lObj.navigateToPtntButton();
	    lObj.patientLoginClick(pUsername, pPassword);
	    
	    HelperClass helpclassObj=new HelperClass(driver);
		helpclassObj.moduleNavigation("Messages");

	    
	    MessagesPage mObj=new MessagesPage(driver);
	    mObj.enterReason();
	    mObj.enterMessage();
	    mObj.clickSendButton();
	  String actual= mObj.validateMessageSuccessful();
	  String expected="Message sent successfully";
	  helpclassObj.captureScreenshot("PAT009 Send Message");
	  sa.assertEquals(actual, expected);
	    
	   // Message send successfully
	}
	
	@AfterClass
	public void closeApp()
	{
		HelperClass helpClassObj =new HelperClass(driver);
		helpClassObj.closeBrowser(driver);
	}

}
