package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginPage_001 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginTest(String userName, String password) throws IOException, InterruptedException {

		logger.info("Base URL is Opened");
		LoginPage lp = new LoginPage(driver);
		//driver.switchTo().frame(1).findElement(By.id("flow_close_btn_iframe")).click();
//		driver.switchTo().frame("flow_close_btn_iframe");
//		driver.findElement(By.xpath("//*[@id='closeBtn']")).click();
//		driver.switchTo().defaultContent();
		
		closeFrame();
		
		lp.setUserName(userName);
		logger.info("UserName is passed ");
		lp.setPassword(password);
		logger.info("Password is passed ");
		lp.clickButton();
		logger.info("Button is Clicked");
		
		Thread.sleep(1000);

		if (isAlertPresent() == true) {
			closeFrame();
			driver.switchTo().alert().accept();// close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {
			closeFrame();
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();

		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException nap) {
			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);// 1
																				// 0
			}

		}
		return logindata;
	}

	
}
