package gui;
import java.util.ArrayList;
import java.util.List;

public class Store{
    private List<Product> products = new ArrayList<Product>(); // Список продуктов
    public Store() {
        products.add(new Bread("Батон", 45, 2));
        products.add(new Bread("Бутербродный", 30, 5));
        products.add(new Milk("Булка", 55, 4));
    }

    public void addProduct(Product product) { // Добавление товара
        this.products.add(product);
    }

    public void deleteProduct(int index) { // Удаление товара
        this.products.remove(index);
    }

    public int getSize() {
        return this.products.size();
    } // Размер списка продуктов

    public Product getProduct(int index) {
        return this.products.get(index);
    } // Продукт из списка по индексу
}
