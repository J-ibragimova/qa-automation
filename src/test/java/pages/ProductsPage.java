package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART =
            "//*[text() = '%s']//ancestor::div[@class='inventory_item']"
                    + "//button[text()='Add to cart']";
    public static final String REMOVE_FROM_CART =
            "//*[text() = '%s']//ancestor::div[@class='inventory_item']"
                    + "//button[text()='Remove']";

    private final By title = By.xpath("//*[@class='title']");
    private final By counter = By.xpath(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartIcon = By.xpath(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addGoodsToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    public void addGoodsToCart(int goodsIndex) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(goodsIndex).click();
    }

    public void removeGoodsFromCart(String goodsName) {
        By removeBtn = By.xpath(REMOVE_FROM_CART.formatted(goodsName));
        driver.findElement(removeBtn).click();
    }

    public boolean getBucketDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public boolean isCounterDisplayed() {
        return !driver.findElements(counter).isEmpty();
    }

    public String getCountItemsBucket() {
        return driver.findElement(counter).getText();
    }

    public String checkCounterCssValue() {
        return driver.findElement(counter).getCssValue("background-color");
    }
}