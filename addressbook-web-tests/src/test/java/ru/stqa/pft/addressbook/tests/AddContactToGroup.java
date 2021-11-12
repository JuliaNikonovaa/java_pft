package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
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
		Contacts сontactsAll = app.db().contacts();
		Groups allGroups = app.db().groups();
		GroupData groupForAdd = new GroupData();
		ContactData selectedContact = new ContactData();
		for (ContactData contact : сontactsAll) {
			Set<GroupData> groupsBefore = contact.getGroups();
			if (allGroups.size() != groupsBefore.size()) {
				for (GroupData group : allGroups) {
					if (!groupsBefore.contains(group)) {
						groupForAdd = group;
						selectedContact = contact;
						break;
					}
				}
			}
		}
		if (groupForAdd.getName() == null) {
			app.goTo().groupPage();
			groupForAdd = new GroupData().withName("newgroup4");
			app.group().create(groupForAdd);
			Groups groupsAfter = app.db().groups();
			GroupData groups = groupForAdd.withId(groupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
			groupForAdd = groups;

			ContactData anyContact = app.db().contacts().iterator().next();
			selectedContact = anyContact;
		}
		app.contact().returnToContactPage();
		app.contact().addToGroup(selectedContact, groupForAdd);
		Set<GroupData> contactGroupsAfter = app.db().userById(selectedContact.getId()).getGroups();
		assertThat(contactGroupsAfter.size(), equalTo(selectedContact.getGroups().size() + 1));
	}
}
