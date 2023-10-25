// Car.java

public class Car {
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

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
