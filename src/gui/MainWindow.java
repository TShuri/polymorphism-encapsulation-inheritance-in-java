package gui;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;


public class MainWindow extends JFrame {
    JPanel menuPanel;
    JScrollPane jScrollPane;
    JTextField searchField;
    JButton addButton;
    JButton deleteButton;
    JButton searchButton;
    JButton resetButton;
    JTable table;
    MyTableModel myTableModel;
    TableRowSorter<MyTableModel> sorter;
    public MainWindow(){
        // Кнопка добавления товара и ее событие
        addButton = new JButton("Добавить товар");
        addButton.addActionListener(new ActionListener() { // Событие на добавление товара
            @Override
            public void actionPerformed(ActionEvent e) {
                callAdd();
            }
        });

        // Кнопка удаления товара и ее событие
        deleteButton = new JButton("Удалить товар");
        deleteButton.addActionListener(new ActionListener() { // Событие на удаление товара
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    callDelete();
                }
                catch (IndexOutOfBoundsException ex){
                    showMessage(1);
                }
            }
        });

        // Кнопка поиска и ее событие
        searchButton = new JButton("Поиск");
        searchButton.addActionListener(new ActionListener() { // Событие на поиск товара
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter
                                        .regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        System.err.println("Bad regex pattern");
                    }
                }
            }
        });

        // Кнопка сброса и ее событие
        resetButton = new JButton("Сброс");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null);
                searchField.setText("");
            }
        });

        searchField = new JTextField("Название товара");

        // Панель меню
        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.add(addButton);
        menuPanel.add(deleteButton);
        menuPanel.add(searchField);
        menuPanel.add(searchButton);
        menuPanel.add(resetButton);

        // Таблица, модель и фильтр поиска
        myTableModel = new MyTableModel(new Store());
        table = new JTable(1,4);

        sorter = new TableRowSorter<MyTableModel>(myTableModel);
        table.setRowSorter(sorter);

        table.setModel(myTableModel);
        table.isCellEditable(0,1);
        jScrollPane = new JScrollPane(table);

        // Добавление компонентов на основное окно
        this.add(menuPanel, BorderLayout.NORTH);
        this.add(jScrollPane, BorderLayout.CENTER);

        // Настройки основного окна
        setTitle("Store");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationByPlatform(true);
        setVisible(true);
    }

    private void callDelete() { // Вызов метода удаления товара
        myTableModel.delete(table.getSelectedRow());
    }

    private void showMessage(int message) { // Диалоговое сообщение
        switch (message) {
            case 1: {
                JOptionPane.showMessageDialog(MainWindow.this, "Выберите товар из таблицы");
                break;
            }
            case 2: {
                JOptionPane.showMessageDialog(MainWindow.this, "Заполните все поля");
                break;
            }
        }
    }

    private void callAdd() { // Вызов метода добавления товара
        JDialog addDialog = new JDialog(MainWindow.this, "Добавление товара", true);
        addDialog.setSize(500, 200);
        addDialog.setLocationRelativeTo(MainWindow.this);

        PlainDocument filterPrice = new PlainDocument();
        filterPrice.setDocumentFilter(new DocumentFilter() { // Фильтр для ввода цены
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });

        PlainDocument filterCount = new PlainDocument();
        filterCount.setDocumentFilter(new DocumentFilter() { // Фильтр для ввода количества
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });

        // Панель добавления товара
        JPanel addPanel = new JPanel(new GridLayout(2, 4, 20, 5));

        JLabel typeLabel = new JLabel("Тип товара");
        addPanel.add(typeLabel);
        JLabel nameLabel = new JLabel("Название товара");
        addPanel.add(nameLabel);
        JLabel priceLabel = new JLabel("Цена товара");
        addPanel.add(priceLabel);
        JLabel countLabel = new JLabel("Количество товара");
        addPanel.add(countLabel);

        String[] types = new String[] {"Хлебо-булочное изделие", "Молочный продукт"};
        JComboBox<String> typesBox = new JComboBox<String>(types);
        addPanel.add(typesBox);

        JTextField nameField = new JTextField("Название");
        addPanel.add(nameField);

        JTextField priceField = new JTextField("Цена");
        priceField.setDocument(filterPrice);
        addPanel.add(priceField);

        JTextField countField = new JTextField("Количество");
        countField.setDocument(filterCount);
        addPanel.add(countField);

        addDialog.add(addPanel, BorderLayout.NORTH);

        // Кнопка потведжения добавления товара
        JButton appendButton = new JButton("Добавить");
        appendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( nameField.getText().isEmpty() || priceField.getText().isEmpty() || countField.getText().isEmpty()) {
                    showMessage(2);
                } else {
                    myTableModel.add((String) typesBox.getSelectedItem(), nameField.getText(),
                            Double.parseDouble(priceField.getText()), Integer.parseInt(countField.getText()));
                    addDialog.dispose();
                }
            }
        });

        addDialog.add(appendButton, BorderLayout.SOUTH);

        addDialog.setVisible(true);
    }
}


