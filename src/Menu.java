import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Store store;
    private static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() { // Главное меню
        int komand = -1;
        do {
            try {
                System.out.println("-> Главное меню");
                System.out.println("-> 1 - Открыть магазин");
                System.out.println("-> 2 - Добавить товар");
                System.out.println("-> 3 - Удалить товар");
                System.out.println("-> 4 - Вывести список товаров");
                System.out.println("-> 0 - Выход из программы");
                komand = checkKomand(1);
                switch (komand) {
                    case 1:
                        createStore();
                        break;
                    case 2:
                        addProductMenu();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        store.printStore();
                        //printProduct();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Нет такого пункта меню");
                }
            } catch (NullPointerException ex) {
                System.out.println("Сначала необходимо открыть магазин");
            }
        } while (komand != 0);
    }

    private static void addProductMenu() { // Меню добавления товара
        int komand = -1;
        String name;
        double price;
        do {
            try {
                System.out.println("->-> Меню добавления товара");
                System.out.println("->-> 1 - Добавить хлебо-булочное изделие");
                System.out.println("->-> 2 - Добавить молочный продукт");
                System.out.println("->-> 0 - Назад");
                komand = checkKomand(2);
                switch (komand) {
                    case 1:
                        System.out.println("->->-> Введите название");
                        System.out.print("->->-> ");
                        name = scanner.nextLine();
                        System.out.println("->->-> Введите цену");
                        price = checkPrice(3);
                        store.addProduct(new Bread(name, price));
                        break;
                    case 2:
                        System.out.println("->->-> Введите название");
                        System.out.print("->->-> ");
                        name = scanner.nextLine();
                        System.out.println("->->-> Введите цену");
                        price = checkPrice(3);
                        System.out.println("->->-> Введите объем");
                        double liter = checkPrice(3);
                        store.addProduct(new Milk(name, price, liter));
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Нет такого пункта меню");
                }
            } catch (NullPointerException ex) {
                System.out.println("Магазин не открыт!");
            }
        } while (komand != 0);
    }

    private static void deleteProduct() { // Функция удаления товара
        if (store.getSize() != 0) {
            store.printStore();
            System.out.println("->-> Введите номер удаляемого товара");
            int numb = checkNumb(2, store.getSize());
            store.deleteProduct(numb);
        }
        else {
            System.out.println("Вы не можете удалить товар, так как товары отсутствуют");
        }
    }

    private static int checkKomand(int level) { // Функция проверки корректного ввода команды меню
        System.out.print("->".repeat(level) + " ");
        int _komand;
        try {
            _komand = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException ex) {
            System.out.println("Ошибка! Вводите только значения из пунктов меню");
            _komand = checkKomand(level);
        }
        return _komand;
    }

    private static double checkPrice(int level) { // Функция проверки ввода цены
        System.out.print("->".repeat(level) + " ");
        double _price;
        try {
            _price = Double.parseDouble(scanner.nextLine());
        }
        catch (NumberFormatException ex) {
            System.out.println("Ошибка! Введите число");
            _price = checkPrice(level);
        }

        if (_price <= 0) {
            System.out.println("Значение не может быть меньше или равным нулю");
            _price = checkPrice(level);
        }
        return _price;
    }

    private static int checkNumb(int level, int size) { // Функция проверки корректного ввода номера
        System.out.print("->".repeat(level) + " ");         // удаляемого товара
        int _numb;
        try {
            _numb = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException ex) {
            System.out.println("Ошибка! Введите число");
            _numb = checkNumb(level, size);
        }

        if (_numb <= 0 || _numb > size) {
            System.out.println("Значение не может быть меньше нуля или больше чем количество товаров");
            _numb = checkNumb(level, size);
        }
        return _numb;
    }

    private static void createStore() { // Функция создания нового магазина
        System.out.println("->-> Введите название магазина");
        System.out.print("->-> ");
        String name = scanner.nextLine();
        if (name == "") {
            store = new Store();
        }
        else {
            store = new Store(name);
        }
    }
}
