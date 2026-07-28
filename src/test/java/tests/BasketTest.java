package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasketTest extends BaseTest {

    private static final List<String> GOODS = List.of(
            "Sauce Labs Onesie",
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light");

    @Test
    public void checkGoodsAddedToCart() {
        System.out.println("BasketTest.checkGoodsAddedToCart is running in Thread:"
                + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        for (String good : GOODS) {
            productsPage.addGoodsToCart(good);
        }

        assertTrue(productsPage.isCounterDisplayed(),
                "Счётчик корзины не отображается после добавления товаров");
        assertEquals(productsPage.getCountItemsBucket(), String.valueOf(GOODS.size()),
                "Счётчик показывает неверное количество товаров");
    }

    @Test
    public void checkBasketContent() {
        System.out.println("BasketTest.checkBasketContent is running in Thread:"
                + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        for (String good : GOODS) {
            productsPage.addGoodsToCart(good);
        }
        productsPage.switchToBasket();

        assertEquals(basketPage.getTitle(), "Your Cart",
                "Заголовок страницы корзины не соответствует");
        assertEquals(basketPage.getProductsName().size(), GOODS.size(),
                "В корзине неверное количество позиций");
        assertTrue(basketPage.getProductsName().containsAll(GOODS),
                "В корзине оказались не все добавленные товары");
    }
}
