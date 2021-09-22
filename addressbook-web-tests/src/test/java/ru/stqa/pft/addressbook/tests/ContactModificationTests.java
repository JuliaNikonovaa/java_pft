package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.сontact().returnToContactPage();
		if (app.сontact().all().size() == 0) {
			app.сontact().gotoAddNewContactPage();
			app.сontact().create(new ContactData().
					withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactModification() {

		Set<ContactData> before = app.сontact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
				.withId(modifiedContact.getId()).withLastname("Nikonova").withName("Julia3").withEmail("j.n@kx.com").withMobile("89119158254").withGroup(null);
		app.сontact().modify(contact);
		//app.logout();
		Set<ContactData> after = app.сontact().all();
		Assert.assertEquals(after.size(), before.size());

		before.remove(modifiedContact);
		before.add(contact);
		Assert.assertEquals(before, after);
	}
	}
