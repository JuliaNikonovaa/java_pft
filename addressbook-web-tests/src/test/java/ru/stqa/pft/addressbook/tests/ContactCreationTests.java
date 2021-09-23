package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.contact().returnToContactPage();
        Contacts before = app.contact().all();
        app.contact().gotoAddNewContactPage();
        ContactData contact = new ContactData().withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1");
        app.contact().create(contact, true);
        assertThat(app.contact().сount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

//app.logout();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
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
