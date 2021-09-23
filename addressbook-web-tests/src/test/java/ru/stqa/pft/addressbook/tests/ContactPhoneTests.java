package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests  extends TestBase{


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
    }
}
