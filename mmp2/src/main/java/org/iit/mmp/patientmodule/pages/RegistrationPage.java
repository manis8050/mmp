package org.iit.mmp.patientmodule.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrationPage {
	
	WebDriver driver;
	HelperClass helpClassObj;
	LoginPage lObj;
	Random rand=new Random();
	HashMap<String,String> hMap=new HashMap<String,String>();
	
	
	//Identify all the webelements using locators
	By registerbtn=By.xpath("//a[contains(text(),'Register')]");
	By fname=By.id("firstname");
	By lname=By.id("lastname");
	By DOB=By.id("datepicker");
	By license=By.id("license");
	By ssn=By.id("ssn");
	By state=By.id("state");
	By city=By.id("city");
	By address=By.id("address");
	By zip=By.id("zipcode");
	By age=By.id("age");
	By height=By.id("height");
	By weight=By.id("weight");
	By pharmacy=By.id("pharmacy");
	By pharma_address=By.id("pharma_adress");
	By email=By.id("email");
	By password=By.id("password");
	By username=By.id("username");
	By confirmpwd=By.id("confirmpassword");
	By securityQstn=By.id("security");
	By securityAnswr=By.id("answer");
	By savebtn=By.name("register");
	By patientLoginButton=By.xpath("//a[text()='Patient Login']");
	By LoginButton=By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]");
		//	//a[@class='button button-alt'][text()='Login'];
	By userName=By.id("username");
	By pwd=By.id("password");
	By signinButton=By.xpath("//input[@name='submit']");
	

	
	
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void clickRegisterButton()
	{
		driver.findElement(registerbtn).click();
	}
	


	
	public void enterFirstName()
	{
		String fnameValue="AUTFName"+((char)(65+rand.nextInt(26)));
		driver.findElement(fname).sendKeys(fnameValue);
		hMap.put("FName", fnameValue);
		
	}
	
	public void enterLastName()
	{
		String lnameValue="AUTLName"+((char)(65+rand.nextInt(26)));
		driver.findElement(lname).sendKeys(lnameValue);
		hMap.put("FName", lnameValue);
	}
	
	public void enterDateOfBirth()
	{
		
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datePickerValue = sdf.format(d);
		driver.findElement(DOB).sendKeys(datePickerValue);
		hMap.put("DatePicker", datePickerValue);
	}
	
	public void enterLicense()
	{
		String licenseValue = 9999999+ rand.nextInt(1000000)+"";
		driver.findElement(By.id("license")).sendKeys(licenseValue);
		hMap.put("License", licenseValue);
		
		
	}
	public void enterSSN()
	{
		String ssnValue = Calendar.getInstance().getTimeInMillis()%1000000000+"";
		driver.findElement(ssn).sendKeys(ssnValue);
		hMap.put("SSN", ssnValue);
	}
	public void enterState()
	{
		
		String stateValue = "New York";
		driver.findElement(state).sendKeys(stateValue);
		hMap.put("State", stateValue);
		
	}
	
	public void enterCity()
	{
		
		String cityValue = "New York";
		driver.findElement(city).sendKeys(cityValue);
		hMap.put("City", cityValue);
		
	}
	
	public void enterAddressValue()
	{
		String addressValue="125 SpringLane";
		driver.findElement(address).sendKeys(addressValue);
		hMap.put("Address",addressValue);
	}

     public void enterZipCodeValue()
    {

       String zipCodeValue = 9999+rand.nextInt(1000)+"";
       driver.findElement(zip).sendKeys(zipCodeValue);
       hMap.put("ZipCode", zipCodeValue);
    }
    public void enterAgeValue()
    {
      String ageValue =  rand.nextInt(100)+"";
      driver.findElement(age).sendKeys(ageValue);
      hMap.put("Age", ageValue);
	}

	public void enterHeightValue() {

		String heightValue = rand.nextInt(100) + "";
		driver.findElement(height).sendKeys(heightValue);
		hMap.put("Height", heightValue);
	}

	public void enterWeightValue() {

		String weightValue = rand.nextInt(100) + "";
		driver.findElement(weight).sendKeys(weightValue);
		hMap.put("Weight", weightValue);

	}

	public void enterPharmaDetails() {

		String pharmacyValue = "MMP Pharmacy";
		driver.findElement(pharmacy).sendKeys(pharmacyValue);
		hMap.put("Pharma", pharmacyValue);

		String pharma_adressValue = "225 riverwalk Crossing";
		driver.findElement(pharma_address).sendKeys(pharma_adressValue);
		hMap.put("PharmaAddress", pharma_adressValue);
	}

	public void enterUserDetails() {
		String emailValue = "WalesQA2" + rand.nextInt(1000) + "@gmail.com";
		driver.findElement(email).sendKeys(emailValue);
		hMap.put("Email", emailValue);

		String usernameValue = "WalesQA2" + rand.nextInt(1000);
		driver.findElement(username).sendKeys(usernameValue);
		hMap.put("Username", usernameValue);

		String passwordValue = "WalesQA2" + rand.nextInt(1000);
		driver.findElement(password).sendKeys(passwordValue);
		hMap.put("Password", passwordValue);

		driver.findElement(confirmpwd).sendKeys(passwordValue);
		hMap.put("ConfirmPassword", passwordValue);
	}

	public void enterSecurityInfo() {
		new Select(driver.findElement(securityQstn)).selectByVisibleText("What is your mother maiden name");
		hMap.put("SecurityQuestion", "What is your mother maiden name");

		String answerValue = "WalesQA1" + rand.nextInt(100);
		driver.findElement(securityAnswr).sendKeys(answerValue);
		hMap.put("SecurityAnswer", answerValue);

	}

	public void clickOnSaveButton() {

		String alertText = null;
		try {
			driver.findElement(By.name("register")).click();
		}

		catch (UnhandledAlertException f) {
			try {

				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				System.out.println("ERROR: (ALERT BOX DETECTED) - ALERT MSG : " + alertText);
				alert.accept();

			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
	}
	
	public HashMap<String,String> fillData()
	{
		enterFirstName();
		enterLastName();
		enterDateOfBirth();
		enterLicense();
		enterSSN();
		enterState();
		enterCity();
		enterAddressValue();
		enterZipCodeValue();
		enterAgeValue();
		enterHeightValue();
		enterWeightValue();
		enterPharmaDetails();
		enterUserDetails();
		enterSecurityInfo();
		clickOnSaveButton();
		
		return hMap;
		
	}

	public String readSuccessMessage()
{
		String msg=null;
		try{
			Alert alrt  = driver.switchTo().alert();
			msg = alrt.getText();
			System.out.println("Error:(Alert Box dected)- message-:" +msg);
/*			helpClassObj=new HelperClass(driver);
			helpClassObj.captureScreenshot("PAT001 Register Account");*/
			alrt.accept();
		//	driver.close();
			return msg;
		}
		catch(NoAlertPresentException  e)
		{
			e.printStackTrace();
			return msg;
		}
}
	
	
	public void loginwithoutActivation(HashMap<String,String> hMap) throws InterruptedException
	{
		
		String username=hMap.get("Username");
		System.out.println("username:" +username);
		String password=hMap.get("Password");
		lObj=new LoginPage(driver);
		lObj.navigateToPtntButton();
		lObj.patientLoginClick(username, password);
		
	}
	
	public String readApprovalMessage()
	{
		Alert alrt=driver.switchTo().alert();
		String msg=alrt.getText();
		alrt.accept();
		return msg;
	}
}
