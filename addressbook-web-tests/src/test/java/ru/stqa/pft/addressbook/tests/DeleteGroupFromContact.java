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

//	@BeforeMethod
//	public void ensurePreconditions() {

//}

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
