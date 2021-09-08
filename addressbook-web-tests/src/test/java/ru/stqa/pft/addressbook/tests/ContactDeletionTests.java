package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {


	@Test
	public void testContactDeletion() throws Exception {
		app.getContactHelper().returnToContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeDialogPage();
	}

}
