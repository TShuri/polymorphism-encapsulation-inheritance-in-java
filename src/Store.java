import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private List<Product> products = new ArrayList<Product>();;

    public Store() {
        this.name = "Новый магазин";
        System.out.println("Магазин открыт!");
    }
    public Store(String _name) {
        this.name = _name;
        System.out.println("Открыт магазин " + name + " !");
    }

    public void addProduct(Product product) { // Добавление товара
        products.add(product);
        System.out.println("Товар успешно добавлен!");
    }

    public void deleteProduct(int index) { // Удаление товара
        products.remove(index-1);
        System.out.println("Товар успешно удален!");
    }

    public void printStore() { // Вывод списка товаров
        System.out.println("Название магазина: " + name);
        if (products.size() == 0) {
            System.out.println("Товары отсутствуют");
        }
        else {
            System.out.println("-".repeat(10) + "Список товаров" + "-".repeat(10));
            int i = 1;
            for (Product product : products) {
                System.out.println("№ " + i);
                product.printProduct();
                i++;
            }
            System.out.println("-".repeat(34) );
        }
    }

    public int getSize() {
        return products.size();
    }
}
