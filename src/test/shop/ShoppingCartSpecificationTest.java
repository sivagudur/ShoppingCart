package test.shop;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartSpecificationTest {
    enum Fruit {
        Apple, Orange
    }

    static class ShopCart {
        List<Fruit> fruits;

               public ShopCart(List<Fruit> fruitStrings) {
            this.fruits = fruitStrings.stream()
                    .map(s -> {
                        switch (s.toString().toLowerCase()) {
                            case "apple":
                                return Fruit.Apple;
                            case "orange":
                                return Fruit.Orange;
                            default:
                                throw new IllegalArgumentException("Unknown fruit: " + s);
                        }
                    })
                    .collect(Collectors.toList());
        }

        public List<Fruit> getFruits() {
            return fruits;
        }

        public double totalCost() {
            // Assuming prices: Apple = 0.60, Orange = 0.25
            double total = 0.0;
            for (Fruit fruit : fruits) {
                switch (fruit) {
                    case Apple:
                        total += 0.60;
                        break;
                    case Orange:
                        total += 0.25;
                        break;
                }
            }
            return total;
        }
    }

    @Test
    public void testHaveListOfFruits() {
        List<Fruit> fruits = asList(Fruit.Apple, Fruit.Apple, Fruit.Orange, Fruit.Apple);
        ShopCart shoppingCart = new ShopCart(fruits.stream().toList());

        assertTrue(shoppingCart.getFruits().containsAll(fruits) && fruits.containsAll(shoppingCart.getFruits()));
    }

    @Test
    public void testOutputsExpectedTotalCost() {
        List<Fruit> fruits = asList(Fruit.Apple, Fruit.Apple, Fruit.Orange, Fruit.Apple);
        ShopCart shoppingCart = new ShopCart(fruits);
        double expectedCost = 2.05;

        assertEquals(expectedCost, shoppingCart.totalCost(), 0.001);
    }

    @Test
    public void testInputListOfStrings() {
        List<Fruit> fruits = asList(Fruit.Apple, Fruit.Apple, Fruit.Orange, Fruit.Apple);
        ShopCart shoppingCart = new ShopCart(fruits);

        List<Fruit> expectedFruits = asList(Fruit.Apple, Fruit.Apple, Fruit.Orange, Fruit.Apple);
        assertTrue(shoppingCart.getFruits().containsAll(expectedFruits) && expectedFruits.containsAll(shoppingCart.getFruits()));
    }
}
