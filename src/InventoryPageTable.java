import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InventoryPageTable {
    private static JTextField card_name_field;
    private static JTextField card_cost_field;
    private static JTextField card_team_field;
    private static JTextField card_number_field;

    private static JTextField search_cost_field;
    private static JTextField search_card_team_field;
    private static JTextField search_card_name_field;
    private static JTextField search_card_number_field;
    private JTable search_class_table;

    public static void main(String[] args) {
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ JTable Setup ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        JTable search_class_table = new JTable();
        Object[] columns = {"Card Name", "Card Team", "Card Cost", "Card Number", "Card Condition", "In Stock?"};
        DefaultTableModel model = new DefaultTableModel();

        JFrame search_class_frame = new JFrame("Search class");
        search_class_frame.getContentPane().setBackground(new Color(218, 165, 32));
        search_class_frame.getContentPane().setForeground(Color.WHITE);
        search_class_frame.setBounds(100, 100, 962, 813);
        search_class_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search_class_frame.getContentPane().setLayout(null);
        search_class_frame.setLocationRelativeTo(null);

        model.setColumnIdentifiers(columns);
        search_class_table.setModel(model);

        search_class_table.setBackground(Color.white);
        search_class_table.setForeground(Color.black);
        search_class_table.setGridColor(Color.gray);
        search_class_table.setSelectionForeground(Color.white);
        search_class_table.setRowHeight(30);
        search_class_table.setAutoCreateRowSorter(true);

        JScrollPane pane = new JScrollPane(search_class_table);
        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(133, 83, 667, 298);
        search_class_frame.getContentPane().add(pane);

        Object[] row = new Object[6];

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Page Title ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        JLabel lblInventoryPage = new JLabel("INVENTORY PAGE");
        lblInventoryPage.setOpaque(true);
        lblInventoryPage.setForeground(new Color(218, 165, 32));
        lblInventoryPage.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblInventoryPage.setBackground(Color.BLACK);
        lblInventoryPage.setBounds(0, 0, 289, 45);
        search_class_frame.getContentPane().add(lblInventoryPage);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(846, 0, 100, 100);
        search_class_frame.getContentPane().add(lblNewLabel_1);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Add Transactions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        JLabel lblAddItem = new JLabel("ADD NEW ITEM");
        lblAddItem.setOpaque(true);
        lblAddItem.setForeground(new Color(218, 165, 32));
        lblAddItem.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblAddItem.setBackground(Color.BLACK);
        lblAddItem.setBounds(143, 392, 191, 45);
        search_class_frame.getContentPane().add(lblAddItem);


        // Modify transactions
        JButton add_item_button = new JButton("Add");
        add_item_button.setForeground(new Color(218, 165, 32));
        add_item_button.setBackground(new Color(0, 0, 0));
        add_item_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                row[0] = card_name_field.getText();
                row[1] = card_team_field.getText();
                row[2] = card_cost_field.getText();
                row[3] = card_number_field.getText();

                model.addRow(row);
            }
        });
        add_item_button.setBounds(62, 740, 136, 23);
        search_class_frame.getContentPane().add(add_item_button);


        JButton delete_button = new JButton("Delete");
        delete_button.setForeground(new Color(218, 165, 32));
        delete_button.setBackground(new Color(0, 0, 0));
        delete_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = search_class_table.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                }
                //
                else JOptionPane.showMessageDialog(search_class_frame, "Delete error");
            }
        });
        delete_button.setBounds(259, 740, 131, 23);
        search_class_frame.getContentPane().add(delete_button);


        // Seller field
        JLabel enter_card_name = new JLabel("Card Name");
        enter_card_name.setBounds(203, 448, 89, 14);
        search_class_frame.getContentPane().add(enter_card_name);

        card_name_field = new JTextField();
        card_name_field.setBounds(149, 473, 169, 20);
        search_class_frame.getContentPane().add(card_name_field);
        card_name_field.setColumns(10);

        // Card name field
        JLabel enter_card_label = new JLabel("Card Team");
        enter_card_label.setBounds(203, 504, 115, 14);
        search_class_frame.getContentPane().add(enter_card_label);

        card_team_field = new JTextField();
        card_team_field.setBounds(149, 529, 169, 20);
        search_class_frame.getContentPane().add(card_team_field);
        card_team_field.setColumns(10);

        // Total Cost Field
        JLabel enter_cost_label = new JLabel("Card Cost");
        enter_cost_label.setBounds(224, 560, 141, 14);
        search_class_frame.getContentPane().add(enter_cost_label);

        card_cost_field = new JTextField();
        card_cost_field.setBounds(196, 585, 169, 20);
        search_class_frame.getContentPane().add(card_cost_field);
        card_cost_field.setColumns(10);


        // Tracking number field
        JLabel enter_card_number = new JLabel("Card Number");
        enter_card_number.setBounds(224, 640, 141, 14);
        search_class_frame.getContentPane().add(enter_card_number);

        card_number_field = new JTextField();
        card_number_field.setColumns(10);
        card_number_field.setBounds(196, 665, 169, 20);
        search_class_frame.getContentPane().add(card_number_field);


        // Shipping status combo box
        JLabel cardCondition = new JLabel("Card Condition");
        cardCondition.setBounds(80, 641, 200, 14);
        search_class_frame.getContentPane().add(cardCondition);

        JComboBox cardConditionComboBox = new JComboBox();
        cardConditionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedShippingValue = cardConditionComboBox.getSelectedItem().toString();
                row[4] = selectedShippingValue;
            }
        });
        cardConditionComboBox.setModel(new DefaultComboBoxModel(new String[] {"Perfect", "Good", "Fair", "Poor"}));
        cardConditionComboBox.setBounds(80, 664, 75, 22);
        search_class_frame.getContentPane().add(cardConditionComboBox);


        // Payment status combo box
        JLabel inStockLabel = new JLabel("In Stock?");
        inStockLabel.setBounds(80, 560, 200, 14);
        search_class_frame.getContentPane().add(inStockLabel);

        JComboBox inStockComboBox = new JComboBox();
        inStockComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPaymentValue = inStockComboBox.getSelectedItem().toString();
                row[5] = selectedPaymentValue;
            }

        });

        inStockComboBox.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
        inStockComboBox.setBounds(80, 584, 50, 22);
        search_class_frame.getContentPane().add(inStockComboBox);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Search Transactions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // UI Element
        JLabel search_transaction_label = new JLabel("SEARCH INVENTORY");
        search_transaction_label.setOpaque(true);
        search_transaction_label.setForeground(new Color(218, 165, 32));
        search_transaction_label.setFont(new Font("Dialog", Font.PLAIN, 20));
        search_transaction_label.setBackground(Color.BLACK);
        search_transaction_label.setBounds(579, 392, 232, 45);
        search_class_frame.getContentPane().add(search_transaction_label);


        // Search by seller
        JLabel card_name_enter_field = new JLabel("Card name");
        card_name_enter_field.setBounds(664, 448, 103, 14);
        search_class_frame.getContentPane().add(card_name_enter_field);

        search_card_name_field = new JTextField();
        search_card_name_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                DefaultTableModel modelThree = (DefaultTableModel)search_class_table.getModel();
                TableRowSorter<DefaultTableModel> table_row_three = new TableRowSorter<DefaultTableModel>(modelThree);
                search_class_table.setRowSorter(table_row_three);
                table_row_three.setRowFilter(RowFilter.regexFilter(search_card_name_field.getText().trim()));

            }
        });
        search_card_name_field.setBounds(613, 473, 169, 20);
        search_class_frame.getContentPane().add(search_card_name_field);
        search_card_name_field.setColumns(10);


        // Search by card name
        JLabel team_name_label = new JLabel("Team name");
        team_name_label.setBounds(664, 504, 136, 14);
        search_class_frame.getContentPane().add(team_name_label);

        search_card_team_field = new JTextField();
        search_card_team_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                DefaultTableModel modelTwo = (DefaultTableModel)search_class_table.getModel();
                TableRowSorter<DefaultTableModel> table_row = new TableRowSorter<DefaultTableModel>(modelTwo);
                search_class_table.setRowSorter(table_row);
                table_row.setRowFilter(RowFilter.regexFilter(search_card_team_field.getText().trim()));
            }
        });
        search_card_team_field.setBounds(613, 529, 169, 20);
        search_class_frame.getContentPane().add(search_card_team_field);
        search_card_team_field.setColumns(10);


        JLabel test_label = new JLabel("TEST");
        test_label.setBounds(900, 560, 103, 14);
        search_class_frame.getContentPane().add(test_label);



        // Search by card cost
        JLabel cost_label = new JLabel("Card cost");
        cost_label.setBounds(664, 560, 103, 14);
        search_class_frame.getContentPane().add(cost_label);

        search_cost_field = new JTextField();
        search_cost_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                DefaultTableModel modelTwo = (DefaultTableModel)search_class_table.getModel();
                TableRowSorter<DefaultTableModel> table_row_two = new TableRowSorter<DefaultTableModel>(modelTwo);
                search_class_table.setRowSorter(table_row_two);
                table_row_two.setRowFilter(RowFilter.regexFilter(search_cost_field.getText().trim()));
            }
        });
        search_cost_field.setText("");
        search_cost_field.setBounds(613, 582, 169, 20);
        search_class_frame.getContentPane().add(search_cost_field);
        search_cost_field.setColumns(10);


        // Search by tracking number
        JLabel card_number_label = new JLabel("Card Number");
        card_number_label.setBounds(664, 613, 103, 14);
        search_class_frame.getContentPane().add(card_number_label);

        search_card_number_field = new JTextField();
        search_card_number_field.setText("");
        search_card_number_field.setColumns(10);
        search_card_number_field.setBounds(613, 634, 169, 20);
        search_class_frame.getContentPane().add(search_card_number_field);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Refresh Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        search_class_frame.revalidate();
        search_class_frame.setVisible(true);



    }//main

}