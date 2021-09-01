package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		gotoAddNewContactPage();
		fillContactForm(new ContactData("Julia", "89119119191", "j.n@kx.com", "Nikonova"));
		enterContact();
		returnToContactPage();
		logout();
	}

}
