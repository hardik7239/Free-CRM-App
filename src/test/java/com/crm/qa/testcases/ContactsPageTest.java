package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.config.Xls_Reader;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	
	public ContactsPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.clickContactsLink();
	}
	
	@Test(priority = 1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts Label is Missing on the page");
	}
	@Test(priority = 2)
	public void selectContactsTest() throws InterruptedException{
		Assert.assertTrue(contactsPage.selectContactsByName("sunny shah"));
	}
	@Test(priority = 3)
	public void selectMultipleContactsTest() throws InterruptedException {
		Assert.assertTrue(contactsPage.selectContactsByName("Hardik Patel")); // data should be true to pass the test
		Assert.assertFalse(contactsPage.selectContactsByName("Shital Patel")); // data should be false to pass the test
		Assert.assertTrue(contactsPage.selectContactsByName("Nidhi Patel")); // data should be true to pass the test
	}
	@DataProvider (name = "data-provider")
     public Object[][] getCRMTestData(){
		 testUtil = new TestUtil();
		 Object data[][] = testUtil.getNewContactData("Contacts"); 
	 return data;
     }
		
	@Test(priority = 4, dataProvider="data-provider")
	public void validateCreateNewContacts(String firstname,String lastname,String company) {
		homePage.newContactLink();
		contactsPage.createNewContacts(firstname,lastname,company);
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
