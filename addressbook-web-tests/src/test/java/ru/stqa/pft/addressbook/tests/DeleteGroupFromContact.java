package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DeleteGroupFromContact extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {

		GroupData newGroup = new GroupData();
		ContactData newContact = new ContactData();

		if (app.db().groups().size() == 0) {
			newGroup = new GroupData().withName("test1");
			app.goTo().groupPage();
			Groups groupsBefore = app.db().groups();
			app.group().create(newGroup);
			assertThat(app.group().count(), equalTo(groupsBefore.size() + 1));
		}

		if (app.db().contacts().size() == 0) {
			newContact = new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254");
			app.contact().returnToContactPage();
			Contacts contactBefore = app.db().contacts();
			app.contact().create(newContact, true);
			assertThat(app.contact().count(), equalTo(contactBefore.size() + 1));
		}
	}

	@Test
	public void DeleteGroupFromContact() {
			ContactData deletedContact = app.db().contacts().iterator().next();
			Groups groups = app.db().groups();
			GroupData deletedGroup = new GroupData();
			app.contact().returnToContactPage();
			if (deletedContact.getGroups().size() == 0) {
				deletedGroup = groups.iterator().next();
				app.contact().addToGroup(deletedContact, deletedGroup);
				Set<GroupData> contactAfter = app.db().userById(deletedContact.getId()).getGroups();
				assertTrue(contactAfter.contains(deletedGroup));
			} else {
				deletedGroup = deletedContact.getGroups().iterator().next();
			}
			app.contact().selectedContactInGroups(deletedGroup);
			app.contact().deleteContactFromGroup(deletedContact);
			Set<GroupData> contactGroupsAfter = app.db().userById(deletedContact.getId()).getGroups();
			assertFalse(contactGroupsAfter.contains(deletedGroup));
		}
}
