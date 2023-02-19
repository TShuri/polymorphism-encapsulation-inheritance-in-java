package gui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame {
    JPanel menuPanel;
    JScrollPane jScrollPane;
    JButton addButton;
    JButton deleteButton;
    //JTable tableHeader;
    JTable table;
    MyTableModel myTableModel;
    public MainWindow(){
        addButton = new JButton("Добавить товар");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callAdd();
            }
        });

        deleteButton = new JButton("Удалить товар");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    callDelete();
                }
                catch (IndexOutOfBoundsException ex){
                    showMessage();
                }
            }
        });

        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.add(addButton);
        menuPanel.add(deleteButton);

        myTableModel = new MyTableModel(new Store());
        table = new JTable(1,4);
        table.setModel(myTableModel);
        jScrollPane = new JScrollPane(table);

        this.add(menuPanel, BorderLayout.NORTH);
        this.add(jScrollPane, BorderLayout.CENTER);

        setTitle("Store");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationByPlatform(true);
        setVisible(true);
    }

    private void callDelete() { // Вызов метода удаления товара
        myTableModel.delete(table.getSelectedRow());
    }

    private void showMessage() { // Диалоговое сообщение
        JOptionPane.showMessageDialog(MainWindow.this, "Выберите товар из таблицы");
    }

    private void callAdd() { // Вызов метода добавления товара
        JDialog addDialog = new JDialog(MainWindow.this, "Добавление товара", true);
        addDialog.setSize(500, 200);
        addDialog.setLocationRelativeTo(MainWindow.this);


        PlainDocument filterPrice = new PlainDocument();
        filterPrice.setDocumentFilter(new DocumentFilter() {
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
        filterCount.setDocumentFilter(new DocumentFilter() {
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

        JButton appendButton = new JButton("Добавить");
        appendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTableModel.add( (String)typesBox.getSelectedItem(), nameField.getText(),
                        Double.parseDouble(priceField.getText()), Integer.parseInt(countField.getText()) );
                addDialog.dispose();
            }
        });
        addDialog.add(appendButton, BorderLayout.SOUTH);


        addDialog.setVisible(true);
    }
}


