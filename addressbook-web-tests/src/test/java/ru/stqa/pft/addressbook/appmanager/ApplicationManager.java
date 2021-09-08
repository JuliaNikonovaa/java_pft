package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	protected WebDriver wd;

	private ContactHelper contactHelper;
	private GroupHelper groupHelper;
	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private String browser;

	public ApplicationManager(String browser) {

		this.browser = browser;
	}

	public void init() {
				if (browser.equals(BrowserType.FIREFOX)) {
		wd = new FirefoxDriver();
	} else if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		} else if (browser.equals(BrowserType.EDGE)) {
			wd = new EdgeDriver();
		}
		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/group.php?selected%5B%5D=1&selected%5B%5D=2&delete=Delete+group%28s%29");
		//contactHelper.groupHelper = new GroupHelper(wd);
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void logout() {
		wd.findElement(By.linkText("Logout")).click();
	}

	public void stop() {
		wd.quit();
	}


	public GroupHelper getGroupHelper() {
		return groupHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}

	public ContactHelper getContactHelper() {
		return contactHelper;
	}
}
