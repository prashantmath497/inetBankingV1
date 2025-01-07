package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.util.HSSFColor.BROWN;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	public static WebDriver driver;
	public String baseURL = readConfig.getAppilcationUrl();
	// public String userName = readConfig.getUserName();
	// public String password = readConfig.getPassword();
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String broswer) throws InterruptedException {
		logger = Logger.getLogger("e1Banking");
		PropertyConfigurator.configure("log4j.properties");

		if (broswer.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + readConfig.getChromeDriverPath());
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if (broswer.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + readConfig.getFireFoxPath());
			driver = new FirefoxDriver();
		} else if (broswer.equals("ie")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + readConfig.getIePath());
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(baseURL);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//div[@id='closeBtn']")).click();

	}

	public void captureScreenShots(WebDriver driver, String tname) throws IOException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;

		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Scrennshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Taken the ScreenShot");

	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

	 public static void closeFrame()
	 {
		 driver.switchTo().frame("flow_close_btn_iframe");
			driver.findElement(By.xpath("//*[@id='closeBtn']")).click();
			driver.switchTo().defaultContent();
	 }
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
