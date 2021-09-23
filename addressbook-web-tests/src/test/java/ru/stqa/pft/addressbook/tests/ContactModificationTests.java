package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withLastname("Nikonova").withName("Julia").withEmail("j.n@kx.com").withMobile("89119158254").withGroup("test1");
        app.contact().modify(contact);
        assertThat(app.contact().сount(), equalTo(before.size()));
        //app.logout();
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
