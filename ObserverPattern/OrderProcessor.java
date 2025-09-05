package ObserverPattern;
import DataModels.*;
import java.util.ArrayList;
import java.util.List;
public class OrderProcessor {
    private final List<OrderObserver> observers = new ArrayList<>();
    public void register(OrderObserver observer){
        observers.add(observer);
    }
    public void unregister(OrderObserver observer){
        observers.add(observer);
    }
    public void notifyObservers (Order order){
        for (OrderObserver observer : observers ){
            observer.update(order);
        }
    }
    public void processOrder(Order order){
        System.out.println("\nProcess order" + order.orderId()+ "...");
        System.out.println("Order processed successfully");
        notifyObservers(order);
    }
}
