package org.iit.mmp.adminmodule.tests;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.adminmodule.pages.AdminLoginPage;
import org.iit.mmp.adminmodule.pages.AdminScheduleAppointmentPage;
import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.testng.annotations.Test;

public class AdminScheduleAppointmentPageTest extends InvokeBrowser{
	//defining the reference variables for classes
	HelperClass helperObj;
	Utility util;
	AdminLoginPage aObj;
	AdminScheduleAppointmentPage aSObj;
	
	@Test
	public void validateAdminScheduleAppointment() throws InterruptedException, IOException
	{
		ProjectConfiguration pConfig=new ProjectConfiguration();
		Properties prop=pConfig.loadProperties();
		String ssn=prop.getProperty("searchSSN");
		String doctorName=prop.getProperty("doctorName");
		aObj=new AdminLoginPage(driver);
		aObj.navigateToAdminButton();
		Thread.sleep(2000);
		aObj.adminLoginClick();
		
		helperObj=new HelperClass(driver);
		helperObj.moduleNavigation("Patients");
		helperObj.searchPatientSSN(ssn);
		aSObj=new AdminScheduleAppointmentPage(driver);
		Thread.sleep(2000);
		aSObj.createVisitClick();
		Thread.sleep(2000);
		helperObj.selectDoctor(doctorName);
		
	}

}
