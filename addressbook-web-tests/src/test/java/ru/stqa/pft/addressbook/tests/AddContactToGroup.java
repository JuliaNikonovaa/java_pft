package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


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
			app.contact().create(newContact, true);
		}
	}

	@Test
	public void AddContactToGroup() {
		ContactData contact = app.db().contacts().iterator().next();
		GroupData groupsForAdd = addedGroupsBeforeTest(contact);
		app.contact().addToGroup(contact, groupsForAdd);
		app.contact().returnToContactPage();
		Groups groupsAfterTest = listGroupsBeforeTest(contact);
		assertTrue(app.db().groups().contains(groupsForAdd));
		int maxGroupIdAfterTest = groupsAfterTest.stream().mapToInt((g) -> g.getId()).max().getAsInt();
		assertThat(groupsAfterTest, equalTo(contact.getGroups().withAdded(groupsForAdd
						.withId(maxGroupIdAfterTest))));
	}

	private Groups listGroupsBeforeTest(ContactData contactBeforeTest) {
		ContactData contactAfterTest = app.db().contacts().getUser(contactBeforeTest);
		return contactAfterTest.getGroups();
	}

	private GroupData addedGroupsBeforeTest(ContactData contact) {
		Groups contactGroups = contact.getGroups();
		Groups allGroups = app.db().groups();
		return getGroupsToAdd(allGroups, contactGroups);
	}

	private GroupData getGroupsToAdd(Groups allGroups, Groups contactGroups) {
		GroupData groupsToAdd;
		for (GroupData groups : contactGroups) {
			if (allGroups.contains(groups)) {
				allGroups.remove(groups);
			}
			} if (allGroups.isEmpty()) {
				app.goTo().groupPage();
				groupsToAdd = new GroupData().withName("test2");
				app.group().create(groupsToAdd);
			} else {
				groupsToAdd = allGroups.iterator().next();
			}
			return groupsToAdd;
		}
	}
