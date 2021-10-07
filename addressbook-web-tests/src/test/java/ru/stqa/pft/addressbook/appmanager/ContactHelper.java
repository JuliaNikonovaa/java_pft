package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
		attach(By.name("photo"), contactData.getPhoto());


		if (creation) {
		//	if (contactData.getGroup() != null) {
			//	new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		//	}
		}else {
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

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	//public void editSelectedContact(int id) {
		//wd.findElement(By.xpath("//img[@alt='Edit']")).click();
	//}

	public void updateContact() {
		clickcontact(By.name("update"));
	}

	public void create(ContactData contactData, boolean b) {
		fillContactForm(contactData, b);
		enterContact();
		contactCache = null;
		returnToContactPage();
	}
	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModificationById(contact.getId());
		fillContactForm(contact, false);
		updateContact();
		contactCache = null;
		returnToContactPage();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public void delete(ContactData contact) {
		returnToContactPage();
		selectContactById(contact.getId());
		deleteSelectedContact();
		closeDialogPage();
		contactCache = null;
		returnToContactPage();
	}

	public int сount() {
		return wd.findElements(By.name("selected[]")).size();
	}
	private Contacts contactCache = null;

	public Contacts all() {
		if (contactCache != null) {
			return new Contacts(contactCache);
		}
		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			List<WebElement> cells = element.findElements(By.tagName("td"));
			int lastNameNumber = 1;
			int nameNumber = 2;
			int addressNumber = 3;
			String name = cells.get(nameNumber).getText();
			String lastname = cells.get(lastNameNumber).getText();
			String address = cells.get(addressNumber).getText();
			String allEmails = cells.get(4).getText();
			String allPhones = cells.get(5).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			contactCache.add(new ContactData().withId(id).withLastname(lastname).withName(name).withAddress(address).withGroup(null)
							.withAllEmails(allEmails)
							.withAllPhones(allPhones));
		}
		return contactCache;
	}


	public ContactData infoFromEditForm(ContactData contact) {
	initContactModificationById(contact.getId());
	  String name = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getAttribute("value");
		String email = wd.findElement(By.name("email")).getAttribute("value");
		String email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname)
						.withHomePhone(home).withMobile(mobile).withWorkPhone(work)
						.withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
	}

	private void initContactModificationById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		WebElement row = checkbox.findElement(By.xpath("./../.."));
		List<WebElement> cells = row.findElements(By.tagName("td"));
		cells.get(7).findElement(By.tagName("a")).click();
	}
}
