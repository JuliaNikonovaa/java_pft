package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.сontact().returnToContactPage();
		if (app.сontact().contactList().size() == 0) {
			app.сontact().gotoAddNewContactPage();
			app.сontact().create(new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactDeletion() throws Exception {
		List<ContactData> before = app.сontact().contactList();
		int index = before.size() - 1;
		app.сontact().delete(index);
		List<ContactData> after = app.сontact().contactList();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(index);
		Assert.assertEquals(before, after);
	}
}
