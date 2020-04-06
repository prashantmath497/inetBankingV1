package com.inetbanking.pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy(name = "uid")
	WebElement txtUserName;
	@FindBy(name = "password")
	WebElement txtPassword;
	@FindBy(name = "btnLogin")
	WebElement bttClick;
	@FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement lnkLogout;

	public void setUserName(String uName) {
		txtUserName.sendKeys(uName);
	}
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	public void clickButton() {
		bttClick.click();
	}
	public void clickLogout() {
		lnkLogout.click();
	}

}
