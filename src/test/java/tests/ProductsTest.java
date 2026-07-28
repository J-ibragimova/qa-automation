package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class ProductsTest extends BaseTest {

    @Test
    public void checkGoodsAdded() {
        List<String> goodsList = List.of(
                "Sauce Labs Onesie",
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light");

        System.out.println("ProductsTest.checkGoodsAdded is running in Thread:"
                + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        for (String good : goodsList) {
            productsPage.addGoodsToCart(good);
        }

        assertEquals(productsPage.getTitle(), "Products",
                "Заголовок страницы не соответствует");
        assertTrue(productsPage.getBucketDisplayed(),
                "Иконка корзины не отображается");
        assertEquals(productsPage.checkCounterCssValue(), "rgba(226, 35, 26, 1)",
                "цвет заднего фона не соответствует макету");
        assertEquals(productsPage.getCountItemsBucket(), String.valueOf(goodsList.size()),
                "Неверное количество товаров в корзине");

        productsPage.switchToBasket();
        assertTrue(basketPage.getProductsName().containsAll(goodsList),
                "В корзине оказались не все добавленные товары");
    }

    @Test
    public void checkCartCounterSwitcher() {
        System.out.println("ProductsTest.checkCartCounterSwitcher is running in Thread:"
                + Thread.currentThread().getId());
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
