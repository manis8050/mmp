package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.Properties;

import org.iit.mmp.base.InvokeBrowser;
import org.iit.mmp.config.ProjectConfiguration;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.InformationPage;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InformationPageTest extends InvokeBrowser{

		@Test(description="PAT005 View information",groups={"PAT005","regression","sanity","patientmodule"})
		public void validateInformationMsg() throws InterruptedException, IOException
		{
			ProjectConfiguration pConfig=new ProjectConfiguration();
			Properties pro=pConfig.loadProperties();
			String pUsername=pro.getProperty("pUsername");
			String pPassword=pro.getProperty("pPassword");
			LoginPage lObj1=new LoginPage(driver);
			lObj1.navigateToPtntButton();
			lObj1.patientLoginClick(pUsername, pPassword);
			
			HelperClass helpclassObj=new HelperClass(driver);
			helpclassObj.moduleNavigation("Information");
			
			InformationPage iObj=new InformationPage(driver);
			
			String actual1=iObj.fetchInformationMessage();
			helpclassObj.captureScreenshot("PAT005 View information");
			System.out.println("actual string is" +actual1);
			String expected1="     Manage My Patient (MMP) is a medical practice management solution that boosts productivity by automating the day-to-day tasks that can slow an office manager down. Central delivers much more than medical billing software. Sure, it has the tools to help generate cleaner claims and reduce denials, but our easy-to-use practice management software also streamlines your workflow to deliver seamless handoffs across departments.\n\n"
		               + 
			  "    Manage My Patient (MMP) becomes your practice’s command center, delivering robust, real-time analytics through customizable reports and dashboards to ensure you know how your business is performing on the metrics that matter most.";
			
			Assert.assertEquals(actual1, expected1);
			helpclassObj.closeBrowser(driver);
	}
}
