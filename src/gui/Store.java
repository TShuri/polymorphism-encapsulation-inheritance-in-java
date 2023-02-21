package gui;
import java.util.ArrayList;
import java.util.List;

public class Store{
    //private String name; // Название магазина
    private List<Product> products = new ArrayList<Product>(); // Список продуктов
    public Store() {
        //this.name = "Новый магазин";
        System.out.println("Магазин открыт!");

        products.add(new Bread("Батон", 45, 2));
        products.add(new Bread("Бутербродный", 30, 5));
        products.add(new Milk("Булка", 55, 4));
    }

    public void addProduct(Product product) { // Добавление товара
        this.products.add(product);
        System.out.println("Товар успешно добавлен!");
    }

    public void deleteProduct(int index) { // Удаление товара
        this.products.remove(index);
        System.out.println("Товар успешно удален!");
    }

    public int getSize() {
        return this.products.size();
    }

    public Product getProduct(int index) {
        return this.products.get(index);
    }
}
