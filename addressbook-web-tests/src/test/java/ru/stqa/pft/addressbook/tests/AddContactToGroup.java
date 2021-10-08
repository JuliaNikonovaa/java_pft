package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroup extends TestBase {

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
			app.contact().create( newContact,true);
		}
	}

	@Test
	public void AddContactToGroup() {
		ContactData contact = app.db().contacts().iterator().next();
		GroupData groupsBeforeTest = addedGroupsBeforeTest(contact);
		app.contact().addToGroup(contact, groupsBeforeTest);
		app.contact().returnToContactPage();
		Groups groupsAfterTest = listGroupsBeforeTest(contact);
assertThat(groupsAfterTest.size(), equalTo(contact.getGroups().size() +1);

		assertThat(groupsAfterTest, equalTo(contact.getGroups().withAdded(groupsBeforeTest
										.withId(maxIdFromGroupsAfterOperation))));
	}

	private GroupData addedGroupsBeforeTest(ContactData contactBeforeTest) {
		ContactData contactBeforeTest

	}
}
