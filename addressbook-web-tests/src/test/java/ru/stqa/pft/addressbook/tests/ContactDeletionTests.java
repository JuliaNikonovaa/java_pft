package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


	@Test
	public void testContactDeletion() throws Exception {
		app.getContactHelper().returnToContactPage();
				if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().gotoAddNewContactPage();
			app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova", "test1"), true);
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().returnToContactPage();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeDialogPage();
		app.getContactHelper().returnToContactPage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() - 1);
	}

}
