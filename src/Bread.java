public class Bread extends Product{
    public Bread(String _name, double _price) {
        this.name = _name;
        this.price = _price;
    }
    @Override
    void printProduct() {
        System.out.println("Тип: Хлебо-булочное изделие");
        System.out.println("Название: " + this.name);
        System.out.println("Цена: " + this.price);
    }
}
