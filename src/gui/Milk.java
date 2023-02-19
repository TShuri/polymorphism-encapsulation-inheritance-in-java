package gui;
public class Milk extends Product{
    public Milk(String _name, double _price, int _count) {
        this.name = _name;
        this.price = _price;
        this.count = _count;
    }

    public void print(){
        System.out.println(type + "Молочный продукт");
        System.out.println(_name + this.name);
        System.out.println(_price + this.price);
        System.out.println(_count + this.count);
    }
}
