package test.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shop.ShopCart;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OfferSpecificationTest {
    @Test
    public void fruitAmountOffer_should_apply_offer_2x1_on_apples() {
        List<ShopCart.Fruit> fruits = Arrays.asList(ShopCart.Fruits.Apple, ShopCart.Fruits.Apple);
        BigDecimal fruitsCost = fruits.stream()
                .map(ShopCart.Fruit::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        ShopCart.FruitAmountOffer offer = new ShopCart.FruitAmountOffer(ShopCart.Fruits.Apple, 2);
        BigDecimal expectedCostAfterOffer = ShopCart.Fruits.Apple.getCost();
        System.out.println("expectedCostAfterOffer: " + expectedCostAfterOffer);
        BigDecimal cost = offer.applyTo(fruitsCost, fruits);
        System.out.println("actualCostAfterOffer: " + cost);
        Assertions.assertEquals(expectedCostAfterOffer, cost);
    }

    @Test
    public void fruitAmountOffer_should_apply_offer_3x2_on_oranges() {
        List<ShopCart.Fruit> fruits = Arrays.asList(ShopCart.Fruits.Orange, ShopCart.Fruits.Orange, ShopCart.Fruits.Orange);
        BigDecimal fruitsCost = fruits.stream()
                .map(ShopCart.Fruit::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        ShopCart.FruitAmountOffer offer = new ShopCart.FruitAmountOffer(ShopCart.Fruits.Orange, 3);
        BigDecimal expectedCostAfterOffer = ShopCart.Fruits.Orange.getCost().multiply(BigDecimal.valueOf(2));
        System.out.println("expectedCostAfterOffer: " + expectedCostAfterOffer);

        BigDecimal cost = offer.applyTo(fruitsCost, fruits);
        System.out.println("actualCostAfterOffer: " + cost);
        Assertions.assertEquals(expectedCostAfterOffer, cost);
    }
}
