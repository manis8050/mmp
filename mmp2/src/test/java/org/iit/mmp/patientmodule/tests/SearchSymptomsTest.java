package org.iit.mmp.patientmodule.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.Utility.Utility;
import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.iit.mmp.patientmodule.pages.SearchSymptomsPage;

public class SearchSymptomsTest extends InvokeBrowser {
	
	
	SoftAssert sa=new SoftAssert();
	@Test(description="PAT010 Search Symptoms",groups={"PAT010","regression","sanity","patientmodule"})
	public void LoginTest() throws InterruptedException, IOException {

		ProjectConfiguration pConfig = new ProjectConfiguration();
		Properties prop = pConfig.loadProperties();
		String pUsername = prop.getProperty("pUsername");
		String pPassword = prop.getProperty("pPassword");

		LoginPage lPageobj = new LoginPage(driver);
		lPageobj.navigateToPtntButton();
		lPageobj.patientLoginClick(pUsername, pPassword);

		boolean result = lPageobj.getHomepageTitle();
		System.out.println("Page title result:::" + result);

		sa.assertTrue(result);

		SearchSymptomsPage searchpageObj = new SearchSymptomsPage(driver);
		searchpageObj.searchSymptoms(driver);
		HelperClass helperObj=new HelperClass(driver);
		helperObj.captureScreenshot("PAT010 Search Symptoms");

	}
	


	@Test(dataProvider="DP_Symptoms",description="Search Symptoms",groups= {"PAT010","regression","sanity","patientmodule"} )
	public void SearchSymptoms(String DP_Symptoms) throws InterruptedException
	{
		
		SearchSymptomsPage searchpageObj=new SearchSymptomsPage(driver);
		searchpageObj.validateSymptoms(DP_Symptoms);
		
	}
	

	
	@DataProvider(name="DP_Symptoms")
	public String[][] excelData() throws BiffException, IOException
	{
		String filepath=System.getProperty("user.dir")+"\\data\\Symptoms.xls";
		Utility excelXlsObj = new Utility();
		String[][] symptomData=excelXlsObj.readXlsFile(filepath,"Symptoms");
		return symptomData;
	}
	
	
	@AfterClass
	public void closeApp()
	{
		
		HelperClass helpObj=new HelperClass(driver);
		helpObj.closeBrowser(driver);
	
	}
}

