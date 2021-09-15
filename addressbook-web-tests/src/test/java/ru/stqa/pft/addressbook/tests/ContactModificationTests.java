package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		app.getContactHelper().returnToContactPage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getContactHelper().gotoAddNewContactPage();
			app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova", "test1"), true);
		}
		int before = app.getContactHelper().getContactCount();
		app.getContactHelper().selectContact(before - 1);
		app.getContactHelper().editSelectedContact();
		app.getContactHelper().fillContactForm(new ContactData("Julia", "89119119193", "j.n@kx.com", "Nikonova", null), false);
		app.getContactHelper().updateContact();
		app.getContactHelper().returnToContactPage();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before);
		//app.logout();
	}
}
