package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


	@Test
	public void testContactDeletion() throws Exception {
		app.getContactHelper().returnToContactPage();
		int before = app.getContactHelper().getContactCount();
			if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().gotoAddNewContactPage();
			app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova", "test1"), true);
		}
		app.getContactHelper().returnToContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeDialogPage();
		app.getContactHelper().returnToContactPage();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before - 1);
	}

}
