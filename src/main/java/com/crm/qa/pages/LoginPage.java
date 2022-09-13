/*
 * Author: Hardik Patel
 * 
 */
 
package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage extends TestBase {

	//Page Factory - OR
	@FindBy(name = "email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
	WebElement LoginBtn;
	
	@FindBy(linkText = "Sign Up")
	WebElement signUp;
	
	@FindBy(xpath="//img[@alt='free crm logo']")
	WebElement crmLogo;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav navbar-right']/li[1]")
	WebElement loginLink;
	
	//initializing page objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	public HomePage login(String un, String pwd) throws InterruptedException {
		Thread.sleep(5000);
		loginLink.click();
		Thread.sleep(3000);
		username.sendKeys(un);
		password.sendKeys(pwd);
		LoginBtn.click();
		
		return new HomePage();
	}
}
