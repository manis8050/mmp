package org.iit.mmp.patientmodule.tests;

import java.util.HashMap;
import java.util.Set;

import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class RegistrationPatientTests extends InvokeBrowser{
	
	HelperClass helpClassObj;
	RegistrationPage robj;
	HashMap<String,String> hMap;
	@Test(description="PAT001 Register Account",groups={"PAT001","regression","sanity","patientmodule"})
	public void validateRegistration() throws InterruptedException
	{  
		robj=new RegistrationPage(driver);
		robj.clickRegisterButton();
		hMap=robj.fillData();
	    String actual = robj.readSuccessMessage();
		//helpClassObj=new HelperClass(driver);
		//helpClassObj.captureScreenshot("PAT001 Register Account");
		System.out.println(actual);
	 String expected ="Thank you for registering with MMP. ";
	 Assert.assertEquals(actual, expected);
	 robj.loginwithoutActivation(hMap);
	 System.out.println(hMap.get("SSN"));
	 String actual1=robj.readApprovalMessage();
	 String expected1="Admin Approval is pending. ";
	 Assert.assertEquals(actual1,expected1);
	}

	
	
	@AfterClass
	public void closeApp()
	{
		helpClassObj =new HelperClass(driver);
		helpClassObj.closeBrowser(driver);
	}

}
	
	

