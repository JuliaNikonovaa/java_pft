package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void returnToContactPage() {
		clickcontact(By.linkText("home"));
	}

	public void enterContact() {
		clickcontact(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void fillContactForm(ContactData contactData) {
		persone(By.name("firstname"), contactData.getName());
		persone(By.name("lastname"), contactData.getLastname());
		persone(By.name("mobile"), contactData.getMobile());
		persone(By.name("email"), contactData.getEmail());
	}

	public void gotoAddNewContactPage() {
		clickcontact(By.linkText("add new"));
	}

	public void closeDialogPage() {
		wd.switchTo().alert().accept();
	}

	public void deleteSelectedContact() {
		clickcontact(By.xpath("//input[@value='Delete']"));
	}

	public void selectContact() {
		clickcontact(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
	}

	public void editSelectedContact() {
		clickcontact(By.xpath("//img[@alt='Edit']"));
	}

	public void updateContact() {
		clickcontact(By.name("update"));
	}
}
