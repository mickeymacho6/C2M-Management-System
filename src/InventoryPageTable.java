import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

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
        search_class_frame.setBounds(100, 100, 1000, 800);
        search_class_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search_class_frame.getContentPane().setLayout(null);
        search_class_frame.setLocationRelativeTo(null);

        model.setColumnIdentifiers(columns);
        search_class_table.setModel(model);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Table Setup ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        search_class_table.setBackground(Color.white);
        search_class_table.setForeground(Color.black);
        search_class_table.setGridColor(Color.gray);
        search_class_table.setSelectionForeground(Color.white);
        search_class_table.setRowHeight(30);
        search_class_table.setAutoCreateRowSorter(true);


        JScrollPane pane = new JScrollPane(search_class_table);
        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(10, 80, 600, 600);
        search_class_frame.getContentPane().add(pane);

        Object[] row = new Object[6];

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Page Title ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        JLabel lblInventoryPage = new JLabel("C2M Inventory Page", SwingConstants.CENTER);
        lblInventoryPage.setOpaque(true);
        lblInventoryPage.setForeground(new Color(218, 165, 32));
        lblInventoryPage.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblInventoryPage.setBackground(Color.BLACK);
        lblInventoryPage.setBounds(0, 0, 230, 45);
        search_class_frame.getContentPane().add(lblInventoryPage);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(850, 0, 100, 100);
        search_class_frame.getContentPane().add(lblNewLabel_1);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Add Transactions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        JLabel lblAddItem = new JLabel("Add New Card", SwingConstants.CENTER);
        lblAddItem.setOpaque(true);
        lblAddItem.setForeground(new Color(218, 165, 32));
        lblAddItem.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblAddItem.setBackground(Color.BLACK);
        lblAddItem.setBounds(725, 80, 150, 45);
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

                // Insert SQL code here to write to database!
            }
        });
        add_item_button.setBounds(650, 315, 136, 23);
        search_class_frame.getContentPane().add(add_item_button);


        JButton delete_button = new JButton("Delete");
        delete_button.setForeground(new Color(218, 165, 32));
        delete_button.setBackground(new Color(0, 0, 0));
        delete_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = search_class_table.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);

                    // Insert SQL code here to delete from database!

                }
                //
                else JOptionPane.showMessageDialog(search_class_frame, "Delete error");
            }
        });
        delete_button.setBounds(800, 315, 131, 23);
        search_class_frame.getContentPane().add(delete_button);


        // Seller field
        JLabel enter_card_name = new JLabel("Card Name");
        enter_card_name.setBounds(675, 150, 80, 15);
        search_class_frame.getContentPane().add(enter_card_name);

        card_name_field = new JTextField();
        card_name_field.setBounds(625, 170, 160, 20);
        search_class_frame.getContentPane().add(card_name_field);
        card_name_field.setColumns(10);

        // Card team field
        JLabel enter_card_label = new JLabel("Card Team");
        enter_card_label.setBounds(675, 200, 80, 15);
        search_class_frame.getContentPane().add(enter_card_label);

        card_team_field = new JTextField();
        card_team_field.setBounds(625, 220, 160, 20);
        search_class_frame.getContentPane().add(card_team_field);
        card_team_field.setColumns(10);

        // Total Cost Field
        JLabel enter_cost_label = new JLabel("Card Cost");
        enter_cost_label.setBounds(675, 250, 140, 15);
        search_class_frame.getContentPane().add(enter_cost_label);

        card_cost_field = new JTextField();
        card_cost_field.setBounds(625, 270, 160, 20);
        search_class_frame.getContentPane().add(card_cost_field);
        card_cost_field.setColumns(10);


        // Card number field
        JLabel enter_card_number = new JLabel("Card Number");
        enter_card_number.setBounds(840, 150, 140, 15);
        search_class_frame.getContentPane().add(enter_card_number);

        card_number_field = new JTextField();
        card_number_field.setColumns(10);
        card_number_field.setBounds(800, 170, 160, 20);
        search_class_frame.getContentPane().add(card_number_field);


        // Card condition combo box
        JLabel cardCondition = new JLabel("Card Condition");
        cardCondition.setBounds(840, 2000, 200, 15);
        search_class_frame.getContentPane().add(cardCondition);

        JComboBox cardConditionComboBox = new JComboBox();
        cardConditionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedShippingValue = cardConditionComboBox.getSelectedItem().toString();
                row[4] = selectedShippingValue;
            }
        });
        cardConditionComboBox.setModel(new DefaultComboBoxModel(new String[] {"Perfect", "Good", "Fair", "Poor"}));
        cardConditionComboBox.setBounds(800, 220, 160, 20);
        search_class_frame.getContentPane().add(cardConditionComboBox);


        // Stock status combo box
        JLabel inStockLabel = new JLabel("In Stock?");
        inStockLabel.setBounds(840, 250, 200, 15);
        search_class_frame.getContentPane().add(inStockLabel);

        JComboBox inStockComboBox = new JComboBox();
        inStockComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPaymentValue = inStockComboBox.getSelectedItem().toString();
                row[5] = selectedPaymentValue;
            }

        });

        inStockComboBox.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
        inStockComboBox.setBounds(800, 270, 160, 20);
        search_class_frame.getContentPane().add(inStockComboBox);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Search Transactions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // UI Element
        JLabel search_transaction_label = new JLabel("Search Inventory", SwingConstants.CENTER);
        search_transaction_label.setOpaque(true);
        search_transaction_label.setForeground(new Color(218, 165, 32));
        search_transaction_label.setFont(new Font("Dialog", Font.PLAIN, 20));
        search_transaction_label.setBackground(Color.BLACK);
        search_transaction_label.setBounds(700, 380, 200, 45);
        search_class_frame.getContentPane().add(search_transaction_label);


        // Search by seller
        JLabel card_name_enter_field = new JLabel("Card name");
        card_name_enter_field.setBounds(760, 450, 100, 15);
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
        search_card_name_field.setBounds(715, 470, 160, 20);
        search_class_frame.getContentPane().add(search_card_name_field);
        search_card_name_field.setColumns(10);


        // Search by card name
        JLabel team_name_label = new JLabel("Team name");
        team_name_label.setBounds(760, 500, 140, 15);
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
        search_card_team_field.setBounds(715, 529, 160, 20);
        search_class_frame.getContentPane().add(search_card_team_field);
        search_card_team_field.setColumns(10);


        // Search by card cost
        JLabel cost_label = new JLabel("Card cost");
        cost_label.setBounds(760, 560, 100, 15);
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
        search_cost_field.setBounds(715, 582, 160, 20);
        search_class_frame.getContentPane().add(search_cost_field);
        search_cost_field.setColumns(10);


        // Search by tracking number
        JLabel card_number_label = new JLabel("Card Number");
        card_number_label.setBounds(760, 610, 100, 15);
        search_class_frame.getContentPane().add(card_number_label);

        search_card_number_field = new JTextField();
        search_card_number_field.setText("");
        search_card_number_field.setColumns(10);
        search_card_number_field.setBounds(715, 634, 160, 20);
        search_class_frame.getContentPane().add(search_card_number_field);


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Refresh Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        search_class_frame.revalidate();
        search_class_frame.setVisible(true);



    }
}