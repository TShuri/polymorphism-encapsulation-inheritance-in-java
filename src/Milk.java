public class Milk extends Product{
    public double liter;

    public Milk(String _name, double _price, double _liter) {
        this.name = _name;
        this.price = _price;
        this.liter = _liter;
    }

    @Override
    void printProduct() {
        System.out.println("Тип: Молочный продукт");
        System.out.println("Название: " + this.name);
        System.out.println("Цена: " + this.price);
        System.out.println("Объем: " + this.liter);
    }
}
