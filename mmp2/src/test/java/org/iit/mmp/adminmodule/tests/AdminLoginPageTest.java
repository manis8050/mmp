package org.iit.mmp.adminmodule.tests;

import java.io.IOException;

import org.iit.mmp.adminmodule.pages.AdminLoginPage;
import org.iit.mmp.base.InvokeBrowser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginPageTest extends InvokeBrowser{
	
	
	@Test
	public void AdminLoginTest() throws IOException, InterruptedException
	{
		AdminLoginPage aObj=new AdminLoginPage(driver);
		aObj.navigateToAdminButton();
		Thread.sleep(2000);
		aObj.adminLoginClick();
		
		boolean result=aObj.homePageTitle();
		Assert.assertTrue(result);
		
	}

}
