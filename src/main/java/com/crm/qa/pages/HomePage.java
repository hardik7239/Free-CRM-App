package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	
@FindBy(xpath = "//span[contains(text(),'Hardik Patel')]")
WebElement verifyUser;

@FindBy(xpath = "//span[contains(text(),'Contacts')]")
WebElement clickContacts;

@FindBy(xpath = "//div[@id='main-nav']/div[5]")
WebElement clickDeals;

@FindBy(xpath = "//div[@id='main-nav']/div[6]")
WebElement clickTasks;

@FindBy(xpath = "//div[@id='main-nav']/div[3]/button")
WebElement newContact;


//initializing page objects:
public HomePage() {
	PageFactory.initElements(driver, this);
}

public String verifyHomePageTitle(){
	return driver.getTitle();
}
public boolean verifyUsername(){
	return verifyUser.isDisplayed();
}

public ContactsPage clickContactsLink() {
	clickContacts.click();
	return new ContactsPage();
}
public DealsPage clickDealsLink() {
	clickDeals.click();
	return new DealsPage();
}
public TasksPage clickTasksLink() {
	clickTasks.click();
	return new TasksPage();
}
public void newContactLink() {
	Actions action = new Actions(driver);
	action.moveToElement(clickContacts).build().perform();
	newContact.click();
}

}
