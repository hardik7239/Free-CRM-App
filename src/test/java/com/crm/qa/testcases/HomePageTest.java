package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	public HomePageTest() {
		super();
	}
@BeforeMethod
public void setUp() throws InterruptedException {
	initialization();
	 loginPage = new LoginPage();
	 testUtil = new TestUtil();
	 homePage = new HomePage();
	 homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
}
@Test(priority = 1)
public void verifyHomePageTitleTest() {
	String homePageTitle = homePage.verifyHomePageTitle();
	Assert.assertEquals(homePageTitle, "Cogmento CRM","HomePage Title Not Matched");
}
@Test(priority = 2)
public void verifyUserNameTest() {
	//testUtil.switchToFrame();
	Assert.assertTrue(homePage.verifyUsername());
}
@Test(priority = 3)
public void verifyContactsLinkTest(){
	contactsPage = homePage.clickContactsLink();
}
@AfterMethod
public void tearDown() {
	driver.quit();
}
}
