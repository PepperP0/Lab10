
import java.util.List;

import DataModels.*;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;
public class ManualTestrunner {
public static void main(String[] args) {
        Product Ipad = new Product("P001" , "Ipad" , 10000.0);
        Product appplepencil = new Product("P002" , "Apple Pencil" , 1000.0);
        Order myOrder = new Order("ORD-001" , List.of(Ipad, appplepencil), "Pp.he@example.com");

        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();

        OrderProcessor orderProcessor = new OrderProcessor();
        InventoryService inventory = new InventoryService();
        EmailService emailer = new EmailService();
        orderProcessor.register(inventory);
        orderProcessor.register(emailer);

        System.out.println("\n--- 2. Testing Strategy Pattern (Disconts) ---");
        double originalPrice = myOrder.getTotalPrice();
        System.out.println("Original Price: " + originalPrice);

        DiscountStrategy tenPercenoff = new PercentageDiscount(10);
        double priceAfterpercentage = calculator.calculateFainalPrice(myOrder, tenPercenoff);
        System.out.println("Price with 10% discount: "+ priceAfterpercentage);

        DiscountStrategy twohundred = new FixedDiscount(200);
        double priceAfterPrice = calculator.calculateFainalPrice(myOrder, twohundred);
        System.out.println("Price with 200 THB discount:"+ priceAfterPrice);
        
        System.out.println("\n---3.Testing Factory and Decorator Patterns (Shipment)---");
        Shipment standardShipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base Shipment: "+standardShipment.getInfo() + ", Cost "+ standardShipment.getCost());
        
        Shipment giftWrapped = new GiftWrapDecorator(standardShipment);
        System.out.println("Decorated"+ giftWrapped.getInfo()+", Cost "+ giftWrapped.getCost());

        Shipment fullyloaded = new InsuranceDecorator(giftWrapped, myOrder);
        System.out.println("Fully Decorated"+ fullyloaded.getInfo()+", Cost "+ fullyloaded.getCost());
        
        System.out.println("\n ---4. Printing Final Summary---");
        double finalPrice = priceAfterpercentage ;
        double totalPrice = finalPrice + fullyloaded.getCost();
        System.out.println("Final price after discount "+ finalPrice);
        System.out.println("Final price shipment cost: "+ fullyloaded.getCost());
        System.out.println("Total to pay"+totalPrice);
        orderProcessor.processOrder(myOrder);
}       
}
