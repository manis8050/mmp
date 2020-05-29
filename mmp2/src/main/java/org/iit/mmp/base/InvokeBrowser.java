package org.iit.mmp.base;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.config.ProjectConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeBrowser {
	
	protected WebDriver driver;
	
	@BeforeClass
		public void invokeBrowser() throws IOException
		{
			ProjectConfiguration pConfig=new ProjectConfiguration();
			Properties pro=pConfig.loadProperties();
			String browserType=pro.getProperty("browserType");
			String url=pro.getProperty("url");
			switch(browserType)
			{
			case "FF":
			
			//geckodriver.exe
				System.out.println("in FF");
			//	System.setProperty("webdriver.gecko.driver","C:\\selenium-prerequisites\\executables\\geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
			
			case "CH":
			//ChromeDriver.exe
				System.out.println("in Chrome");
			//	System.setProperty("webdriver.chrome.driver","C:\\selenium-prerequisites\\executables\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
			
			case "IE":
			//IEDriverServer.exe
				System.out.println("in IE");
			//	System.setProperty("webdriver.ie.driver","C:\\selenium-prerequisites\\executables\\IEDriverServer.exe");
				WebDriverManager.iedriver().setup();
			driver =new InternetExplorerDriver();
			break;
			
			case "ED":
			//MicrosodtWebDriver.exe
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
			
			}
			
		    driver.manage().window().maximize();
			driver.get(url);
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}

	
	


	}

