package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.getContactHelper().returnToContactPage();
		int before = app.getContactHelper().getContactCount();
		app.getContactHelper().gotoAddNewContactPage();
		app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova",  "test1"), true);
			//app.logout();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before + 1);
	}

}
