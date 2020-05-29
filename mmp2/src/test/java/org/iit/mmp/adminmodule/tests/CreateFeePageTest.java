package org.iit.mmp.adminmodule.tests;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.adminmodule.pages.AdminLoginPage;
import org.iit.mmp.adminmodule.pages.CreateFeePage;
import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateFeePageTest extends InvokeBrowser{
	HelperClass helpConfig;
	Utility util;
	AdminLoginPage aObj;
	CreateFeePage feePageObj;
	
	@Test
	public void validateFeeCreation() throws IOException, InterruptedException
	{
		
		
		ProjectConfiguration pConfig=new ProjectConfiguration();
		Properties prop=pConfig.loadProperties();
		String ssn=prop.getProperty("searchSSN");
		aObj=new AdminLoginPage(driver);
		aObj.navigateToAdminButton();
		Thread.sleep(2000);
		aObj.adminLoginClick();
		
		helpConfig=new HelperClass(driver);
		helpConfig.moduleNavigation("Patients");
		helpConfig.searchPatientSSN("46237904");
		
		feePageObj=new CreateFeePage(driver);
		feePageObj.createFeeClick();
		Thread.sleep(2000);
		boolean result=feePageObj.pageTitleValidation();
	    Assert.assertTrue(result);
		
	    feePageObj.patientFeeCreation();
	  String actual= feePageObj.fetchFeeSuccessMessage();
	  String expected="Fee Successfully Entered.";
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(actual, expected);
		
	}

}
