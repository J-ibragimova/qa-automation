import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
  loginPage.open();
  loginPage.login("standard_user", "secret_sauce");

  assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
    }

    @Test
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }
    @Test
    public void checkIncorrectPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
    }
    @Test
    public void checkBlockedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out");
    }
    @Test
    public void checkICapsLockPassword() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }
    }
