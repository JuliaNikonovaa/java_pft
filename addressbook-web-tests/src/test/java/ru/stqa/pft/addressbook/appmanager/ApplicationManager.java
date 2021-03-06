package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	private final Properties properties;
	protected WebDriver wd;

	private ContactHelper contactHelper;
	private GroupHelper groupHelper;
	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private String browser;
	private DbHelper dbHelper;


	public ApplicationManager(String browser)  {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

		dbHelper = new DbHelper();

		if ("".equals(properties.getProperty("selenium.server"))) {
			if (browser.equals(BrowserType.FIREFOX)) {
				wd = new FirefoxDriver();
			} else if (browser.equals(BrowserType.CHROME)) {
				wd = new ChromeDriver();
			} else if (browser.equals(BrowserType.EDGE)) {
				wd = new EdgeDriver();
			}
		}else {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browser);
			capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")) );
			wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
		}

		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get(properties.getProperty("web.baseUrl"));
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
	}

	public void logout() {
		wd.findElement(By.linkText("Logout")).click();
	}

	public void stop() {
		wd.quit();
	}


	public GroupHelper group() {
		return groupHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}

	public ContactHelper contact() {
		return contactHelper;
	}

	public DbHelper db() {
		return dbHelper;
	}
}
