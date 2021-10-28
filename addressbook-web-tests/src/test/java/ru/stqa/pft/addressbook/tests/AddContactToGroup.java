package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
		Groups allGroups = app.db().groups();
		boolean unsetContactExists = false;
		Contacts contacts = app.db().contacts();
		for (ContactData contactData : contacts) {
			Groups contactGroups = contactData.getGroups();
			for (GroupData oneFromAllGroups : allGroups) {
				if (!contactGroups.contains(oneFromAllGroups)) {
					contactGroups.add(oneFromAllGroups);
					app.contact().addToGroup(contactData, oneFromAllGroups );
					unsetContactExists = true;
				assertTrue(contactGroups.contains(oneFromAllGroups));
					break;
				}
			}
			if (unsetContactExists) {
				break;
			}
		}

		if (!unsetContactExists) {
			app.goTo().groupPage();
			GroupData newGroup = new GroupData().withName("test group");
			app.group().create(newGroup);
			ContactData contactData = contacts.iterator().next();
			contactData.getGroups().add(newGroup);
			app.contact().addToGroup(contactData, newGroup);
//			assertTrue(app.db().groups().contains(newGroup));
			assertTrue(contactData.getGroups().contains(newGroup));
		}
	}
}
