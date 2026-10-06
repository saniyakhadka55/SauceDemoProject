package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {

    private WebDriver driver;

    private By productSortDropdown = By.className("product_sort_container");
    private By firstProductName = By.cssSelector(".inventory_item_name");
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortOption(String visibleText) {
        WebElement dropdown = driver.findElement(productSortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public String getFirstProductName() {
        return driver.findElement(firstProductName).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartBackpack).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(addToCartBikeLight).click();
    }

    public int getCartCount() {
        String countText = driver.findElement(shoppingCartBadge).getText();
        return Integer.parseInt(countText);
    }

    public void clickCartIcon() {
        driver.findElement(shoppingCartLink).click();
    }

}