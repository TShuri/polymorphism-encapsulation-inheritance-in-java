package gui;
public interface Printable {
    String type = "Тип: ";
    String _name = "Название: ";
    String _price = "Цена: ";
    String _count = "Количество";
    default void defaultMethod(int num){
        System.out.println(privateMethod() + num);
    }
    static void staticMethod(int num){
        System.out.println(privateMethod() + num);
    }
    private static String privateMethod(){
        return "Информация о продукте № ";
    }
    void print();
}
