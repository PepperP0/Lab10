package StrategyPattern;
import DataModels.*;
public class OrderCalculator {
    public double calculateFainalPrice(Order order , DiscountStrategy strategy){
        return strategy.applyDiscount(order);
    }
}
