package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.сontact().returnToContactPage();
		List<ContactData> before = app.сontact().contactList();
		app.сontact().gotoAddNewContactPage();
		ContactData contact = new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test");
		app.сontact().create(contact, true);
		List<ContactData> after = app.сontact().contactList();
		Assert.assertEquals(after.size(), before.size() + 1);
//app.logout();

		before.add(contact);
		Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}

}
