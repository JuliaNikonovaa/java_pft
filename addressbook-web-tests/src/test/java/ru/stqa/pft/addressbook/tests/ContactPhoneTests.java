package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {


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
	public void testContactPhones() {
		app.contact().returnToContactPage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
		assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
	}

	private String mergeEmails(ContactData contact) {
		return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
						.stream().filter((s) -> !s.equals(""))
						.map(ContactPhoneTests::cleaned)
						.collect(Collectors.joining("\n"));
	}

	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
						.stream().filter((s) -> !s.equals(""))
						.map(ContactPhoneTests::cleaned)
						.collect(Collectors.joining("\n"));
	}

	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}
