package test.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static shop.ShopCart.Fruits.Apple;
import static shop.ShopCart.Fruits.Orange;

public class FruitSpecificationTest {
    @Test
    public void appleShouldCost60p() {
        System.out.println("appleShouldCost60p" + Apple.getCost());
        assertEquals(0.6, Apple.getCost().doubleValue());
    }

    @Test
    public void orangeShouldCost25p() {
        System.out.println("OrangeShouldCost60p" + Orange.getCost());
        assertEquals(0.25, Orange.getCost().doubleValue());
    }
}
