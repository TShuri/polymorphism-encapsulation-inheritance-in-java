package gui;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private Store data;
    public MyTableModel(Store store){
        this.data = store;
    }
    @Override
    public int getRowCount() {
        return this.data.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override // Заполнение данных таблицы
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: {
                Product p = this.data.getProduct(rowIndex);
                if (p instanceof Bread) {
                    return "Хлебо-булочное изделие";
                } else {
                    return "Молочный продукт";
                }
            }
            case 1: {
                return this.data.getProduct(rowIndex).getName();
            }
            case 2: {
                return this.data.getProduct(rowIndex).getPrice();
            }
            case 3: {
                return this.data.getProduct(rowIndex).getCount();
            }
        }
        return "default";
    }

    @Override // Переименование заголовок таблицы
    public String getColumnName(int column){
        switch (column){
            case 0: return "Тип";
            case 1: return "Название";
            case 2: return "Цена";
            case 3: return "Количество";
        }
        return "";
    }

    public void delete(int ind){ // Удаление товара
        this.data.deleteProduct(ind);
        fireTableDataChanged();
    }

    public void add(String type, String name, double price, int count){
        switch (type) {
            case "Хлебо-булочное изделие": {
                this.data.addProduct( new Bread(name, price, count) );
                break;
            }
            case "Молочный продукт": {
                this.data.addProduct( new Milk(name, price, count));
                break;
            }
        }
        fireTableDataChanged();
    }
}
