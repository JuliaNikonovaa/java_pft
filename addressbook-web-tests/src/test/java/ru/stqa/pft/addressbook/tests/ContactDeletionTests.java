package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.сontact().returnToContactPage();
		if (app.сontact().all().size() == 0) {
			app.сontact().gotoAddNewContactPage();
			app.сontact().create(new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactDeletion() throws Exception {
		Set<ContactData> before = app.сontact().all();
		ContactData deletedContact = before.iterator().next();
		app.сontact().delete(deletedContact);
		Set<ContactData> after = app.сontact().all();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(deletedContact);
		Assert.assertEquals(before, after);
	}
}
