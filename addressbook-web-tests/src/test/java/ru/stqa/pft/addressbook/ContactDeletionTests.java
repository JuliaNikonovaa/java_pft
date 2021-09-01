package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion() throws Exception {
    returnToContactPage();
    selectContact();
    deleteSelectedContact();
    closeDialogPage();
  }

}
