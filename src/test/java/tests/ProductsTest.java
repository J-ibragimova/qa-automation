package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class ProductsTest extends BaseTest {

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addGoodsToCart("Sauce Labs Onesie");
        productsPage.addGoodsToCart(0);

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
        assertTrue(productsPage.getBucketDisplayed(), "Иконка корзины не отображается");
        assertEquals(productsPage.checkCounterCssValue(), "rgba(226, 35, 26, 1)",
                "цвет заднего фона не соответствует макету");
        assertEquals(productsPage.getCountItemsBucket(), "2",
                "Неверное количество товаров в корзине");
    }

    @Test
    public void checkCartCounterSwitcher() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertFalse(productsPage.isCounterDisplayed(),
                "Счётчик не должен отображаться при пустой корзине");

        productsPage.addGoodsToCart("Sauce Labs Onesie");
        assertEquals(productsPage.getCountItemsBucket(), "1",
                "После первого товара счётчик должен быть 1");

        productsPage.addGoodsToCart(0);
        assertEquals(productsPage.getCountItemsBucket(), "2",
                "После второго товара счётчик должен быть 2");

        productsPage.removeGoodsFromCart("Sauce Labs Onesie");
        assertEquals(productsPage.getCountItemsBucket(), "1",
                "После удаления товара счётчик должен быть 1");
    }
}