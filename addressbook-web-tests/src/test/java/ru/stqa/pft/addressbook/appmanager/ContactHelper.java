package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
	private WebDriver wd;
	//protected GroupHelper groupHelper;

	public ContactHelper(WebDriver wd) {
		this.wd = wd;
	}

	public void returnToContactPage() {
		wd.findElement(By.linkText("home")).click();
	}

	public void enterContact() {
		wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
	}

	public void fillContactForm(ContactData contactData) {
		wd.findElement(By.name("firstname")).click();
		wd.findElement(By.name("firstname")).clear();
		wd.findElement(By.name("firstname")).sendKeys(contactData.getName());
		wd.findElement(By.name("lastname")).click();
		wd.findElement(By.name("lastname")).clear();
		wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
		wd.findElement(By.name("mobile")).click();
		wd.findElement(By.name("mobile")).clear();
		wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
		wd.findElement(By.name("email")).click();
		wd.findElement(By.name("email")).clear();
		wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
	}

	public void gotoAddNewContactPage() {
		wd.findElement(By.linkText("add new")).click();
	}

	public void closeDialogPage() {
		wd.switchTo().alert().accept();
	}

	public void deleteSelectedContact() {
		wd.findElement(By.xpath("//input[@value='Delete']")).click();
	}

	public void selectContact() {
		wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input")).click();
	}
}
