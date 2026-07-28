package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage {

    private final By goodsTitle = By.cssSelector(".inventory_item_name");
    private final By pageTitle = By.xpath("//*[@class='title']");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public List<String> getProductsName() {
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(goodsTitle)) {
            names.add(element.getText());
        }
        return names;
    }
}
