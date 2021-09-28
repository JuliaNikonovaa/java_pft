package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContactFromJson() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
		String json = "";
		String line = reader.readLine();
		while (line != null) {
			json += line;
			line = reader.readLine();
		}
		Gson gson = new Gson();
		List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
		}.getType());
		return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
	}

	@Test(dataProvider = "validContactFromJson")
	public void testContactCreation(ContactData contact) throws Exception {
		app.contact().returnToContactPage();
		Contacts before = app.contact().all();
		app.contact().gotoAddNewContactPage();
		File photo = new File("src/test/resources/stru.png");
		// ContactData contact = new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withPhoto(photo).withGroup("test1");
		app.contact().create(contact, true);
		assertThat(app.contact().сount(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();

//app.logout();

		assertThat(after, equalTo(
						before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}

	@Test(enabled = false)
	public void testBadContactCreation() throws Exception {
		app.contact().returnToContactPage();
		Contacts before = app.contact().all();
		app.contact().gotoAddNewContactPage();
		ContactData contact = new ContactData().withLastname("Nikonova'").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1");
		app.contact().create(contact, true);
		assertThat(app.contact().сount(), equalTo(before.size()));
		Contacts after = app.contact().all();

//app.logout();

		assertThat(after, equalTo(before));
	}
}
