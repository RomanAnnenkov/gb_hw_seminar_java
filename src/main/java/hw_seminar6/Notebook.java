package hw_seminar6;

import java.util.Objects;

public class Notebook {
    String brand;
    String model;
    int ramGb;
    String storageType;
    int storageCapacityGb;
    String color;
    int price;

    public Notebook(String brand, String model, int ramGb, String storageType, int storageCapacityGb, String color, int price) {
        this.brand = brand;
        this.model = model;
        this.ramGb = ramGb;
        this.storageType = storageType;
        this.storageCapacityGb = storageCapacityGb;
        this.color = color;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ramGb=" + ramGb +
                ", storageType='" + storageType + '\'' +
                ", storageCapacityGb=" + storageCapacityGb +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, ramGb, storageType, storageCapacityGb, color, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return ramGb == notebook.ramGb && storageCapacityGb == notebook.storageCapacityGb && price == notebook.price && Objects.equals(brand, notebook.brand) && Objects.equals(model, notebook.model) && Objects.equals(storageType, notebook.storageType) && Objects.equals(color, notebook.color);
    }

}
