package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.getContactHelper().returnToContactPage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().gotoAddNewContactPage();
		app.getContactHelper().createContact(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova",  "test1"), true);
			//app.logout();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);
	}

}
