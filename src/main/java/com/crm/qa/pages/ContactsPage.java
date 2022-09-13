package com.crm.qa.pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//div[@class='ui header item mb5 light-black']")
	@CacheLookup //it will keep the given xpath value temporary so everytime we call this webelement variable it does not have to find xpath on page everytime 
	WebElement contactsLabel;
	
	@FindBy(name = "first_name")
	WebElement firstName;
	
	@FindBy(name = "last_name")
	WebElement lastName;
	
	@FindBy(xpath = "//div[@name = 'company']/input")
	WebElement companyName;
	
	@FindBy(xpath = "//button[text() = 'Save']")
	WebElement saveButton;
	
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	public boolean verifyContactsLabel(){
		return contactsLabel.isDisplayed();
	}
	public boolean selectContactsByName(String name) throws InterruptedException{
		//Dynamic Xpath :: //a[text() = '"+ name +"']//parent::td//preceding-sibling::td/div//self::input[@name='id']
		/*//manually navigating page number
		 * Boolean status = false;
		for(int i = 3;i<6;i++) {
		Thread.sleep(3000);
		WebElement pageNum = driver.findElement(By.xpath("//div[@class='ui right floated pagination menu custom-pagination']/a["+i+"]"));
		pageNum.click();
		List<WebElement> findContacts = driver.findElements(By.xpath("//a[text() = '"+ name +"']"));
		Thread.sleep(2000);
		if(findContacts.size() > 0){
			status = true;
		}
		else {
			status = false;
		}
		}
		return status; */
		//automatic navigating till the element find on the page
		List<WebElement> findContacts;
		TestUtil.IMPLICIT_WAIT = 2;
		Thread.sleep(3000);
		List<WebElement> totalPages = driver.findElements(By.xpath("//div[@class='ui right floated pagination menu custom-pagination']/a"));
		System.out.println(totalPages.size());
		int currentPage = 1;
		driver.findElement(By.xpath("//div[@class='ui right floated pagination menu custom-pagination']/a[2]")).click();
		WebElement itemEnabled = driver.findElement(By.xpath("//div[@class='ui right floated pagination menu custom-pagination']/a["+totalPages.size()+"]"));
		
		do{
			System.out.println("Searching On Page : "+currentPage+"..................");
			findContacts = driver.findElements(By.xpath("//a[text() = '"+ name +"']"));
			// will avoid obstruct element
			currentPage++;
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", itemEnabled);
			//itemEnabled.click();
			if(currentPage == totalPages.size()-1) {
				break;
			}
			}while(findContacts.size() <=0);
		if(findContacts.size() > 0){
			return true;
		}
		else {
			return false;
		}	
	}
	public void createNewContacts(String fName,String lName,String comp) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		companyName.sendKeys(comp);
		saveButton.click();
	}
}
