package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseClass {

    @Test(priority = 1)
    public void validLogin(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        String titleOfPage = driver.getTitle();
        Assert.assertEquals(titleOfPage, "Swag Labs");

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 2)
    public void invalidUserName(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("wrong_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 3)
    public void invalidPassword(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.enterPassword("wrong_password");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 4)
    public void invalidUserNameAndPassword(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("wrong_user");
        lpg.enterPassword("wrong_password");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 5)
    public void emptyUserName(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 6)
    public void emptyPassword(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(priority = 7)
    public void emptyUserNameAndPassword(){

        LoginPage lpg = new LoginPage(driver);

        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 8)
    public void lockedOutUser(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("locked_out_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(priority = 9)
    public void userNameWithSpaces(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("     ");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 10)
    public void passwordWithSpaces(){

        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.enterPassword("     ");
        lpg.clickLoginButton();

        Assert.assertEquals(lpg.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

}