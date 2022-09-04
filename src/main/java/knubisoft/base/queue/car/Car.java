package knubisoft.base.queue.car;

import java.util.List;
import java.util.PriorityQueue;

public class Car extends CarComparator {

    private final String brand;
    private final double price;

    public Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

}
