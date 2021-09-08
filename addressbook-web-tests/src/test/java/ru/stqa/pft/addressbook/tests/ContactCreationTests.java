package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.getContactHelper().returnToContactPage();
		app.getContactHelper().gotoAddNewContactPage();
		app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova",  null));
		//app.logout();
	}

}
