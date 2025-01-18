package com.investopedia.testbase;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Testbase {

	public static WebDriver driver;   // null
	public static Logger logger;
	
	@BeforeTest
	public void start()
	{
		logger = Logger.getLogger("Investopedia Project");
		PropertyConfigurator.configure("Log4jfile.properties");
		
		logger.info("Framework execution started----------");
	}
	
	@AfterTest
	public void end()
	{
		logger.info("Framework execution ended----------");
	}
	
	
	@BeforeMethod
	public void setup()
	{
		String browser_name = "Chrome";
		
		if(browser_name.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser_name.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser_name.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			logger.info("Provide correct browser name");
		}
		
		driver.get("https://www.investopedia.com/simulator/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().deleteAllCookies();
		
		logger.info("browser started, max, provided waits");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		logger.info("browser close");
	}
}
