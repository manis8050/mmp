package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginPageTests extends InvokeBrowser{
	
	



@Test(description = "PAT002.B Login Account", groups = {"PAT002.BA", "regression", "sanity", "patientmodule" })
	public void navigateToLoginwindow() throws InterruptedException
	{
		
    	LoginPage lObj=new LoginPage(driver);
		lObj.navigateToPtntButton();
	}
	
	@Test(dataProvider = "PatientLogin_DP", dependsOnMethods = "navigateToLoginwindow", description = "PAT002.B Login Account", groups = {
			"PAT002.BA", "regression", "sanity", "patientmodule" })
	public void inValidloginTest(String username, String password) throws InterruptedException, IOException {


		LoginPage lObj = new LoginPage(driver);
		lObj.patientLoginClick(username, password);
		Thread.sleep(1000);
		String actual = lObj.alertpopup();
		System.out.println("Popup boolean value:::" + actual);
		String expected = "Wrong username and password. ";
		Assert.assertEquals(actual, expected);
	}

	
	@DataProvider(name = "PatientLogin_DP")
	public String[][] excel() throws IOException {

		Utility excelXlsObj = new Utility();
		String filepath=System.getProperty("user.dir")+"\\data\\Loginfile.xls";
	//	String[][] logindata = excelXlsObj.readXlsFile("C:\\selenium-prerequisites\\Data\\Loginfile.xls",
	//			"PatientLogin");
		
		String[][] logindata=excelXlsObj.readXlsFile(filepath,"PatientLogin");
		return logindata;
	}

	@Test(dependsOnMethods = "inValidloginTest", alwaysRun = true, description = "PAT002.A Login Account", groups = {
			"PAT002.A", "regression", "sanity", "patientmodule" })
	public void validLoginTest() throws InterruptedException, IOException {

		ProjectConfiguration pConfig = new ProjectConfiguration();
		Properties prop = pConfig.loadProperties();
		String pUsername = prop.getProperty("pUsername");
		String pPassword = prop.getProperty("pPassword");

		Thread.sleep(3000);
		LoginPage lObj1 = new LoginPage(driver);
		// lObj1.navigateToPtntButton(); (not needed as user is already on that page
		// from previous failed login case)
		lObj1.patientLoginClick(pUsername, pPassword);
		boolean result = lObj1.getHomepageTitle();
		HelperClass helpObj=new HelperClass(driver);
		helpObj.captureScreenshot("PAT002.B Login Account");
		
		Assert.assertTrue(result);
	}
	
	@AfterClass
	public void closeApp()
	{
		HelperClass helpClassObj =new HelperClass(driver);
		helpClassObj.closeBrowser(driver);
	}
	/*boolean result=alobj.isAlertPresent();
	System.out.println("Popup boolean value:::" +result);
	if(result==true)
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(2000);
	driver.switchTo().defaultContent();
	Assert.assertTrue(true);
	*/
}
