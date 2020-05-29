package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.EditProfilePage;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditProfilePageTest extends InvokeBrowser{
	


@Test(description="PAT003- Edit profile",groups={"PAT003","regression","sanity","patientmodule"})
public void validateEditProfileTest() throws InterruptedException, IOException
{
	

	ProjectConfiguration pConfig=new ProjectConfiguration();
	Properties pro=pConfig.loadProperties();
	String pUsername=pro.getProperty("pUsername");
	String pPassword=pro.getProperty("pPassword");
	LoginPage lObj1=new LoginPage(driver);
	lObj1.navigateToPtntButton();
	lObj1.patientLoginClick(pUsername, pPassword);
	
	
	HelperClass helpclassObj=new HelperClass(driver);
	helpclassObj.moduleNavigation("Profile");

	
    EditProfilePage eObj=new EditProfilePage(driver);
    
    eObj.clickonEditProfileButton();
    HashMap<String,String> hMap=eObj.updateProfileDetails();
    eObj.clickOnSaveButton();
    String actual=eObj.readEditSuccessMessage();
	String expected="Your Profile has been updated.";
	
	
	helpclassObj.captureScreenshot("PAT003- Edit profile");
	Assert.assertEquals(actual, expected);
	
	boolean result=eObj.validateProfileChanges(hMap);
	helpclassObj.captureScreenshot("PAT003- Edit profile");
    Assert.assertTrue(result);
    helpclassObj.closeBrowser(driver);
	
}
  

}
