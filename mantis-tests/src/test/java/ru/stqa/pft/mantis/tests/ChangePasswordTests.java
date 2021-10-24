package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

	@BeforeMethod
	public void startMailServer() {
		app.mail().start();
	}

	@Test
	public void changePasswordTest()  {
		app.registration().userLogin(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
		UserData user = selectedUser(app.db().allUsers());
		String newPassword = "newPassword";
		app.registration().resetPassword(user.getUsername());
		List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
		String link = app.registration().findLink(mailMessages, user.getEmail());
		app.registration().finish(link, newPassword);
		assertTrue(app.newSession().login(user.getUsername(), newPassword));

	}

	private UserData selectedUser(List<UserData> allUsers) {
		List<UserData> users = new ArrayList<>(allUsers);
		for (UserData user : users) {
			if (user.getUsername().equals("administrator")) {
				allUsers.remove(user);
			}
		}
		return allUsers.get((int) Math.random() * allUsers.size());
	}


	@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}
}
