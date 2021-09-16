package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		app.getContactHelper().returnToContactPage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getContactHelper().gotoAddNewContactPage();
			app.getContactHelper().createContact(new ContactData("Nikonova", "Julia", "j.n@kx.com", "89119158254",  "test1"), true);
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().editSelectedContact();
		ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Nikonova", "Julia", "j.n@kx.com", "89119158254", null);
		app.getContactHelper().fillContactForm(contact, false);
		app.getContactHelper().updateContact();
		app.getContactHelper().returnToContactPage();
		//app.logout();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(before.size() - 1);
		before.add(contact);
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);


	}
}
