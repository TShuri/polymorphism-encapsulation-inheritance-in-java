package gui;
public abstract class Product {
    public String name;
    public double price;
    public int count;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setCount(int newCount) {
        this.count = newCount;
    }
}
