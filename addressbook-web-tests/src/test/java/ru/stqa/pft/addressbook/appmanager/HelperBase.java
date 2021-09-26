package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
	protected WebDriver wd;

	public HelperBase(WebDriver wd) {
		this.wd = wd;
	}

	protected void click(By locator) {
		wd.findElement(locator).click();
	}

	protected void type(By locator, String text) {
		click(locator);
		if (text != null) {
			String existingText = wd.findElement(locator).getAttribute("value");
			if (! text.equals(existingText)) {
				wd.findElement(locator).clear();
				wd.findElement(locator).sendKeys(text);
			}
		}
	}

	protected void clickcontact(By locator) {
		wd.findElement(locator).click();
	}

	protected void persone(By locatorcontact, String textcontact) {
		clickcontact(locatorcontact);
		wd.findElement(locatorcontact).clear();
		wd.findElement(locatorcontact).sendKeys(textcontact);
	}

	protected void attach(By locatorcontact, File file) {
		if (file != null) {
			wd.findElement(locatorcontact).sendKeys(file.getAbsolutePath());
		}
	}

	public boolean isAlertPresent() {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public boolean isElementPresent(By locator) {
		try {
			wd.findElement(locator);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
}