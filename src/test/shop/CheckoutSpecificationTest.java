package test.shop;

import org.junit.jupiter.api.Test;
import shop.ShopCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

public class CheckoutSpecificationTest {

    @Test
    public void checkoutShouldApplySpecificOffersToShoppingCartAndExpectTheCorrectCost() {
        ShopCart.FruitAmountOffer twoForOneAppleOffer = new ShopCart.FruitAmountOffer(ShopCart.Fruits.Apple, 2);
        ShopCart.FruitAmountOffer threeForTwoOrangeOffer = new ShopCart.FruitAmountOffer(ShopCart.Fruits.Orange, 3);
        List<ShopCart.Fruit> fruits = Arrays.asList(ShopCart.Fruits.Orange, ShopCart.Fruits.Orange, ShopCart.Fruits.Apple, ShopCart.Fruits.Orange, ShopCart.Fruits.Apple);

        ShopCart.ShoppingCart shoppingCart = new ShopCart.ShoppingCart(fruits);
        ShopCart.Checkout checkout = new ShopCart.Checkout(shoppingCart, Arrays.asList(twoForOneAppleOffer, threeForTwoOrangeOffer));
        double expectedCost = ShopCart.Fruits.Orange.getCost().doubleValue() * 2 + ShopCart.Fruits.Apple.getCost().doubleValue();
        System.out.println("Expected Cost:" + expectedCost);
        double actualCost = checkout.getCost().doubleValue();
        System.out.println("Actual Cost:" + actualCost);
        assertEquals(expectedCost, actualCost);
    }
}