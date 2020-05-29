package org.iit.mmp.patientmodule.pages;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditProfilePage{
	
	WebDriver driver;
	HelperClass helpObj;
	Random rand=new Random();
	HashMap<String,String> hMap=new HashMap<String,String>();
	By editButton=By.id("Ebtn");
	By saveButton=By.id("Sbtn");
	By fname=By.id("fname");
	By lname=By.id("lname");
	By license=By.id("licn");
	By ssn=By.id("ssn");
	By state=By.id("state");
	By city=By.id("city");
	By address=By.id("addr");
	By zip=By.id("zip");
	By age=By.id("age");
	By height=By.id("height");
	By weight=By.id("weight");
	By providerInfo=By.id("proinfo");
	By insuranceInfo=By.id("Insinfo");
	
	
	public EditProfilePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
public void clickonEditProfileButton()
{
	driver.findElement(editButton).click();
	
}
public HashMap<String,String> updateProfileDetails()
{
	
	updateFirstName();
	updateLastName();
	updateLicense();
	updateSSN();
	updateAddressValue();
	updateAgeValue();
	updateWeightValue();
	updateHeightValue();
	updateCity();
	updateState();
	updateZipCodeValue();
	
	System.out.println("hMap size is:::" +hMap.size());
	
	Set<String> keys=hMap.keySet();
	for(String s:keys)
	{System.out.println("Key is:::" +s +"value is:::" +hMap.get(s));
	}
	
	
	return hMap;
}


public void updateFirstName()
{
	driver.findElement(fname).clear();
	String fnameValue="Monica"+((char)(65+rand.nextInt(26)));
	driver.findElement(fname).sendKeys(fnameValue);
	hMap.put("FName", fnameValue);
	
}

public void updateLastName()
{
	driver.findElement(lname).clear();
	String lnameValue="Smith"+((char)(65+rand.nextInt(26)));
	driver.findElement(lname).sendKeys(lnameValue);
	hMap.put("LName", lnameValue);
}

public void updateLicense()
{
	driver.findElement(license).clear();
	String licenseValue = 9999999+ rand.nextInt(1000000)+"";
	driver.findElement(license).sendKeys(licenseValue);
	hMap.put("License", licenseValue);
	
	
}
public void updateSSN()
{
	driver.findElement(ssn).clear();
	String ssnValue = Calendar.getInstance().getTimeInMillis()%1000000000+"";
	driver.findElement(ssn).sendKeys(ssnValue);
	hMap.put("SSN", ssnValue);
}
public void updateState()
{
	driver.findElement(state).clear();
	String stateValue = "Georgia";
	driver.findElement(state).sendKeys(stateValue);
	hMap.put("State", stateValue);
	
}

public void updateCity()
{
	driver.findElement(city).clear();
	String cityValue = "Rockdale";
	driver.findElement(city).sendKeys(cityValue);
	hMap.put("City", cityValue);
	
}

public void updateAddressValue()
{
	driver.findElement(address).clear();
	String addressValue="125 SpringLane";
	driver.findElement(address).sendKeys(addressValue);
	hMap.put("Address",addressValue);
}

 public void updateZipCodeValue()
{
   driver.findElement(zip).clear();
   String zipCodeValue = 9999+rand.nextInt(1000)+"";
   driver.findElement(zip).sendKeys(zipCodeValue);
   hMap.put("ZipCode", zipCodeValue);
}
public void updateAgeValue()
{
  driver.findElement(age).clear();
  String ageValue =  rand.nextInt(100)+"";
  driver.findElement(age).sendKeys(ageValue);
  hMap.put("Age", ageValue);
}

public void updateHeightValue() {
	driver.findElement(height).clear();
	String heightValue = rand.nextInt(100) + "";
	driver.findElement(height).sendKeys(heightValue);
	hMap.put("Height", heightValue);
}

public void updateWeightValue() {
	driver.findElement(weight).clear();
	String weightValue = rand.nextInt(100) + "";
	driver.findElement(weight).sendKeys(weightValue);
	hMap.put("Weight", weightValue);

}

public void clickOnSaveButton()
{
	driver.findElement(saveButton).submit();
	
}


public String readEditSuccessMessage()
{
	Alert alert=driver.switchTo().alert();
	String alertText=alert.getText();
	System.out.println("ERROR: (ALERT BOX DETECTED) - ALERT MSG : " + alertText);
	alert.accept();
	return alertText;
	
	
}
public void instantiateDriver()
{
	WebDriverManager.chromedriver().setup();
	driver  = new ChromeDriver();
}


public boolean validateProfileChanges(HashMap<String,String> hMap)
{
	HashMap<String,String> updatedhMap=new HashMap<String,String>();
	
	String uFname=driver.findElement(fname).getAttribute("value");
	System.out.println("uFname:::" +uFname);
	updatedhMap.put("FName",uFname);
	
	String uLname=driver.findElement(lname).getAttribute("value");
	System.out.println("uFname:::" +uLname);
	updatedhMap.put("LName",uLname);
	
    String uLicense=driver.findElement(license).getAttribute("value");
    updatedhMap.put("License",uLicense);
	
    String uSSN=driver.findElement(ssn).getAttribute("value");
    updatedhMap.put("SSN", uSSN);
    
    String uAddress=driver.findElement(address).getAttribute("value");
    updatedhMap.put("Address", uAddress);
	
    String uzipCode=driver.findElement(zip).getAttribute("value");
    updatedhMap.put("ZipCode", uzipCode);
   
     String uAge=driver.findElement(age).getAttribute("value");
     updatedhMap.put("Age", uAge);
     
     String uHeight=driver.findElement(height).getAttribute("value");
     updatedhMap.put("Height", uHeight);
     
     
    String uWeight=driver.findElement(weight).getAttribute("value");
    updatedhMap.put("Weight", uWeight);
    
    
    String uCity=driver.findElement(city).getAttribute("value");
    updatedhMap.put("City",uCity);
    
	
	String uState=driver.findElement(state).getAttribute("value");
	updatedhMap.put("State", uState);
	
	System.out.println("Size of updatedHmap is:::" +updatedhMap.size());

	
	Set<String> keys1=updatedhMap.keySet();
	for(String j:keys1)
	{System.out.println("Key is:::" +j +"value is:::" +updatedhMap.get(j));
	}
	boolean result=(hMap.equals(updatedhMap));
	return result;
}


@Parameters({"pUsername","pPassword"})
@Test
public void validateEditProfileTest(String pUsername,String pPassword) throws InterruptedException
{
	
	instantiateDriver();
	helpObj = new HelperClass(driver);
	helpObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
	LoginPage lObj1=new LoginPage(driver);
	lObj1.navigateToPtntButton();
	lObj1.patientLoginClick(pUsername, pPassword);
	

	helpObj.moduleNavigation("Profile");
	
	clickonEditProfileButton();
	updateProfileDetails();
	clickOnSaveButton();
	String actual=readEditSuccessMessage();
	String expected="Your Profile has been updated.";
	Assert.assertEquals(actual, expected);
	
	boolean result=validateProfileChanges(hMap);
    Assert.assertTrue(result);
	
}



}
