package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class SauceDemoAssignmentTests extends BaseClass {

    @Test(priority = 1)
    public void testProductFiltering() {
        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        InventoryPage inventoryPage = new InventoryPage(driver);

        // Verify Name (Z to A) filter
        inventoryPage.selectSortOption("Name (Z to A)");
        String firstItemZtoA = inventoryPage.getFirstProductName();
        Assert.assertEquals(firstItemZtoA, "Test.allTheThings() T-Shirt (Red)");

        // Verify Price (Low to High) filter
        inventoryPage.selectSortOption("Price (low to high)");
        String firstItemLowToHigh = inventoryPage.getFirstProductName();
        Assert.assertEquals(firstItemLowToHigh, "Sauce Labs Onesie");
    }

    @Test(priority = 2)
    public void testAddItemsToCart() {
        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();

        Assert.assertEquals(inventoryPage.getCartCount(), 2);
    }

    @Test(priority = 3)
    public void testCompleteCheckoutFlow() {
        LoginPage lpg = new LoginPage(driver);

        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterShippingInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!");
    }

}