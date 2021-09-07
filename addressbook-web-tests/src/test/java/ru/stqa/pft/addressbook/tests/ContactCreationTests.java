package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		app.getContactHelper().gotoAddNewContactPage();
		app.getContactHelper().fillContactForm(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova", "test1"), true);
		app.getContactHelper().enterContact();
		app.getContactHelper().returnToContactPage();
		app.logout();
	}

}
