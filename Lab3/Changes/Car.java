public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Red", "BMW", 2.0, "Gasoline");

        System.out.println("Car:");
        System.out.println("Color: " + myCar.getColor());
        System.out.println("Brand: " + myCar.getBrand());
        System.out.println("Engine Size: " + myCar.getEngineSize() + " liters");
        System.out.println("Fuel Type: " + myCar.getFuelType());

        Person person = new Person("John", 30);

        System.out.println("\nPerson:");
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
    }
}

class Car {
    private String color;
    private String brand;
    private double engineSize;
    private String fuelType;

    public Car(String color, String brand, double engineSize, String fuelType) {
        this.color = color;
        this.brand = brand;
        this.engineSize = engineSize;
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}