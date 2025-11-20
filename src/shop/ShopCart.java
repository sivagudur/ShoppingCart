package shop;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class ShopCart {
        public interface Fruit {
            BigDecimal getCost();
            String getName();
        }

        public enum Fruits implements Fruit {
            Apple(new BigDecimal("0.6"), "apple"),
            Orange(new BigDecimal("0.25"), "orange");

            private final BigDecimal cost;
            private final String name;

            Fruits(BigDecimal cost, String name) {
                this.cost = cost;
                this.name = name;
            }

            @Override
            public BigDecimal getCost() {
                return cost;
            }

            @Override
            public String getName() {
                return name;
            }
        }

        public static final List<Fruit> validFruits = Arrays.asList(Fruits.Apple, Fruits.Orange);

        public static List<Fruit> convert(List<String> fruits) {
            return fruits.stream()
                    .map(fruitName -> validFruits.stream()
                            .filter(f -> f.getName().equals(fruitName))
                            .findFirst())
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        }

        public static class ShoppingCart {
            private final List<Fruit> fruits;

            public ShoppingCart(List<Fruit> fruits) {
                this.fruits = fruits;
            }

            public BigDecimal getTotalCost() {
                return fruits.stream()
                        .map(Fruit::getCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }

            public List<Fruit> getFruits() {
                return fruits;
            }
        }

        public interface Offer {
            BigDecimal applyTo(BigDecimal cost, List<Fruit> fruits);
        }

        public static class FruitAmountOffer implements Offer {
            private final Fruit fruit;
            private final int offerAmount;

            public FruitAmountOffer(Fruit fruit, int offerAmount) {
                this.fruit = fruit;
                this.offerAmount = offerAmount;
            }

            @Override
            public BigDecimal applyTo(BigDecimal cost, List<Fruit> fruits) {
                long count = fruits.stream().filter(f -> f.equals(fruit)).count();
                long timesToApplyDiscount = count / offerAmount;
                BigDecimal discount = fruit.getCost().multiply(BigDecimal.valueOf(timesToApplyDiscount));
                return cost.subtract(discount);
            }
        }

        public static class Checkout {
            private final ShoppingCart shoppingCart;
            private final List<Offer> offers;

            public Checkout(ShoppingCart shoppingCart, List<Offer> offers) {
                this.shoppingCart = shoppingCart;
                this.offers = offers;
            }

            public BigDecimal getCost() {
                BigDecimal cost = shoppingCart.getTotalCost();
                for (int i = offers.size() - 1; i >= 0; i--) {
                    cost = offers.get(i).applyTo(cost, shoppingCart.getFruits());
                }
                return cost;
            }
        }
    }
