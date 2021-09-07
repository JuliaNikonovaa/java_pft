package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

	@Test
	public void testContactModification(){
		app.getContactHelper().returnToContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().editSelectedContact();
		app.getContactHelper().fillContactForm(new ContactData("Julia", "89119119193", "j.n@kx.com", "Nikonova", null), false);
		app.getContactHelper().updateContact();
		app.getContactHelper().returnToContactPage();
		app.logout();
	}
}
