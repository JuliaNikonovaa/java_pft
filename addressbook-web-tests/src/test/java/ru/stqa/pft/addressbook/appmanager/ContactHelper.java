package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

	public void fillContactForm(ContactData contactData, boolean creation) {
		persone(By.name("firstname"), contactData.getName());
		persone(By.name("lastname"), contactData.getLastname());
		persone(By.name("mobile"), contactData.getMobile());
		persone(By.name("email"), contactData.getEmail());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
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

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void editSelectedContact() {
		clickcontact(By.xpath("//img[@alt='Edit']"));
	}

	public void updateContact() {
		clickcontact(By.name("update"));
	}

	public void createContact(ContactData contactData, boolean b) {
		fillContactForm(contactData, b);
		enterContact();
		returnToContactPage();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			List<WebElement> cells = element.findElements(By.tagName("td"));
			int lastNameNumber = 1;
			int nameNumber = 2;
			String name = cells.get(nameNumber).getText();
			String lastname = cells.get(lastNameNumber).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			ContactData contact = new ContactData(id, lastname, name, null, null, null);
			contacts.add(contact);
		}
		return contacts;
	}
}
