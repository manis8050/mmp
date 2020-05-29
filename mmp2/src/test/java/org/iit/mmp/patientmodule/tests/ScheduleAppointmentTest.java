package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ScheduleAppointmentTest extends InvokeBrowser {
	
	
	

	@Test(description="PAT004 Schedule Appointment",groups={"PAT010","regression","sanity","patientmodule"})
	public void validateScheduleAppointment() throws InterruptedException, IOException
	{
		
	 //Loading the properties from config file
	  ProjectConfiguration pConfig=new ProjectConfiguration();
	  Properties prop=pConfig.loadProperties();
	  String pUsername=prop.getProperty("pUsername");
	  String pPassword=prop.getProperty("pPassword");
	  String doctorName=prop.getProperty("doctorName");
	  
	  
		LoginPage lPageobj=new LoginPage(driver);
		lPageobj.navigateToPtntButton();
		lPageobj.patientLoginClick(pUsername, pPassword);
		
		 HelperClass helpclassObj=new HelperClass(driver);
		helpclassObj.moduleNavigation("Schedule Appointment");
		
		ScheduleAppointmentPage sPageObj=new ScheduleAppointmentPage(driver);
		sPageObj.clickOnCreateAppointmentButton();
		HashMap<String,String> hMap=sPageObj.selectDoctor(doctorName);
		
		SoftAssert sa=new SoftAssert();
		Thread.sleep(3000);
		sa.assertTrue(sPageObj.validateAppointmentDetailsInHomePage(hMap));
		// boolean result=sPageObj.validateAppointmentDetailsInHomePage(hMap);
		// Assert.assertTrue(result);
		
		sa.assertTrue(sPageObj.validateAppointmentDetailsInSchedulePage(hMap));
		sa.assertAll();
		
		

}
	@AfterClass
	public void closeApp()
	{
		HelperClass helpClassObj =new HelperClass(driver);
		helpClassObj.closeBrowser(driver);
	}
}
