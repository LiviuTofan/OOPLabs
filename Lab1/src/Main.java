// Main.java

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Red", "BMW", 2.0, "Gasoline");

        System.out.println("Color: " + myCar.getColor());
        System.out.println("Brand: " + myCar.getBrand());
        System.out.println("Engine Size: " + myCar.getEngineSize() + " liters");
        System.out.println("Fuel Type: " + myCar.getFuelType());
    }
}
