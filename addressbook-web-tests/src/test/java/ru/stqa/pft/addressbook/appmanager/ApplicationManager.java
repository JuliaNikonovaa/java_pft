package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	WebDriver wd;

	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;

	public void init() {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/group.php?selected%5B%5D=1&selected%5B%5D=2&delete=Delete+group%28s%29");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		login("admin", "secret");
	}

	public void login(String username, String password) {
		wd.findElement(By.name("user")).click();
		wd.findElement(By.name("user")).click();
		wd.findElement(By.name("user")).clear();
		wd.findElement(By.name("user")).sendKeys(username);
		wd.findElement(By.name("pass")).clear();
		wd.findElement(By.name("pass")).sendKeys(password);
		wd.findElement(By.xpath("//input[@value='Login']")).click();
		wd.findElement(By.xpath("//div[@id='content']/h1")).click();
	}

	public void logout() {
		wd.findElement(By.linkText("Logout")).click();
	}

	public void stop() {
		navigationHelper.wd.quit();
	}

	public boolean isElementPresent(By by) {
		try {
			groupHelper.wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isAlertPresent() {
		try {
			groupHelper.wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void returnToContactPage() {
		groupHelper.wd.findElement(By.linkText("home")).click();
	}

	public void enterContact() {
		groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
	}

	public void fillContactForm(ContactData contactData) {
		groupHelper.wd.findElement(By.name("firstname")).click();
		groupHelper.wd.findElement(By.name("firstname")).clear();
		groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactData.getName());
		groupHelper.wd.findElement(By.name("lastname")).click();
		groupHelper.wd.findElement(By.name("lastname")).clear();
		groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
		groupHelper.wd.findElement(By.name("mobile")).click();
		groupHelper.wd.findElement(By.name("mobile")).clear();
		groupHelper.wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
		groupHelper.wd.findElement(By.name("email")).click();
		groupHelper.wd.findElement(By.name("email")).clear();
		groupHelper.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
	}

	public void gotoAddNewContactPage() {
		groupHelper.wd.findElement(By.linkText("add new")).click();
	}

	public void closeDialogPage() {
		groupHelper.wd.switchTo().alert().accept();
	}

	public void deleteSelectedContact() {
		groupHelper.wd.findElement(By.xpath("//input[@value='Delete']")).click();
	}

	public void selectContact() {
		groupHelper.wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input")).click();
	}

	public GroupHelper getGroupHelper() {
		return groupHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}
}
