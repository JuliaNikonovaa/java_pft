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
		persone(By.name("lastname"), contactData.getLastname());
		persone(By.name("firstname"), contactData.getName());
		persone(By.name("email"), contactData.getEmail());
		persone(By.name("mobile"), contactData.getMobile());


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

	public void editSelectedContact(int index) {
		wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
	}

	public void updateContact() {
		clickcontact(By.name("update"));
	}

	public void create(ContactData contactData, boolean b) {
		fillContactForm(contactData, b);
		enterContact();
		returnToContactPage();
	}
	public void modify(int index, ContactData contact) {
		selectContact(index);
		editSelectedContact(index);
		fillContactForm(contact, false);
		updateContact();
		returnToContactPage();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}
		public void delete(int index) {
			returnToContactPage();
			selectContact(index);
			deleteSelectedContact();
			closeDialogPage();
			returnToContactPage();
		}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> contactList() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			List<WebElement> cells = element.findElements(By.tagName("td"));
			int lastNameNumber = 1;
			int nameNumber = 2;
			String name = cells.get(nameNumber).getText();
			String lastname = cells.get(lastNameNumber).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			contacts.add(new ContactData().withId(id).withLastname(lastname).withName(name).withEmail(null).withMobile(null).withGroup(null));
		}
		return contacts;
	}
}
