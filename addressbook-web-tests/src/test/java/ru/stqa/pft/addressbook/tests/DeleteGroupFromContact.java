package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteGroupFromContact extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.db().contacts().size() == 0) {
			if (app.db().groups().size() == 0) {
				app.goTo().groupPage();
				app.group().create(new GroupData().withName("test1"));
			}
			Groups groups = app.db().groups();
			ContactData newContact = new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254")
							.inGroup(groups.iterator().next());
			app.contact().returnToContactPage();
			app.contact().gotoAddNewContactPage();
			app.contact().create(newContact, true);
			app.contact().returnToContactPage();
			ContactData contact = app.db().contacts().iterator().next();
			app.contact().addToGroup(contact, groups.iterator().next());
			app.contact().returnToContactPage();
		}
	}

	@Test
	public void DeleteGroupFromContact() {
		app.contact().returnToContactPage();
		ContactData contactBeforeTest = Contacts.getContactsWithGroups(app.db().contacts());
		Groups groupsBefore = contactBeforeTest.getGroups();
		GroupData deleteGroup = contactBeforeTest.getGroups().iterator().next();
		app.contact().deleteContactFromGroup(contactBeforeTest, deleteGroup);
		ContactData contactAfterTest = app.db().contacts().getUser(contactBeforeTest);
		Groups groupsAfter = contactAfterTest.getGroups();
		assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
		assertThat(groupsAfter, equalTo(groupsBefore.without(deleteGroup)));

	}
}
