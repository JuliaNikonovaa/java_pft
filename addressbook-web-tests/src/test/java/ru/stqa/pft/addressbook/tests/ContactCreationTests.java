package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() throws Exception {
		app.сontact().returnToContactPage();
		Set<ContactData> before = app.сontact().all();
		app.сontact().gotoAddNewContactPage();
		ContactData contact = new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1");
		app.сontact().create(contact, true);
		Set<ContactData> after = app.сontact().all();
		Assert.assertEquals(after.size(), before.size() + 1);
//app.logout();

contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
		before.add(contact);
	Assert.assertEquals(before, after);
	}

}
