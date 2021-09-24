package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {


	@BeforeMethod
	public void ensurePreconditions() {
		app.contact().returnToContactPage();
		if (app.contact().all().size() == 0) {
			app.contact().gotoAddNewContactPage();
			app.contact().create(new ContactData().
							withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactPhones() {
		app.contact().returnToContactPage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
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
