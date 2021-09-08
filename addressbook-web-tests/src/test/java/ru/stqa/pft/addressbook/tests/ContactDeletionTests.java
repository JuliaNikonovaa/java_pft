package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


	@Test
	public void testContactDeletion() throws Exception {

			if (! app.getContactHelper().isThereAContact()) {
		app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova", null), true);
		}
		app.getContactHelper().returnToContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeDialogPage();
	}

}
