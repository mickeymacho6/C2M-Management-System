import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
public class add_and_search_transaction {
    private static JTextField seller_field;
    private static JTextField total_cost_field;
    private static JTextField card_field;
    private static JTextField search_cost_field;
    private static JTextField search_card_field;
    private static JTextField search_seller_field;
    private static JTextField tracking_field;
    private static JTextField search_tracking_field;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JTable search_class_table = new JTable();
        Object[] columns = {"Seller Name", "Card Name", "Total Cost", "Payment Status", "Shipping Status", "Tracking #"};
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

        JLabel card_name_label = new JLabel("Card name");
        card_name_label.setBounds(664, 504, 136, 14);
        search_class_frame.getContentPane().add(card_name_label);

        JLabel cost_label = new JLabel("Card cost");
        cost_label.setBounds(664, 560, 103, 14);
        search_class_frame.getContentPane().add(cost_label);

        Object[] row = new Object[6];

        JButton add_transaction_button = new JButton("Add");
        add_transaction_button.setForeground(new Color(218, 165, 32));
        add_transaction_button.setBackground(new Color(0, 0, 0));
        add_transaction_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                row[0] = seller_field.getText();
                row[1] = card_field.getText();
                row[2] = total_cost_field.getText();
                row[5] = tracking_field.getText();



                model.addRow(row);
            }
        });
        add_transaction_button.setBounds(62, 740, 136, 23);
        search_class_frame.getContentPane().add(add_transaction_button);

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

        seller_field = new JTextField();
        seller_field.setBounds(149, 473, 169, 20);
        search_class_frame.getContentPane().add(seller_field);
        seller_field.setColumns(10);

        total_cost_field = new JTextField();
        total_cost_field.setBounds(196, 585, 169, 20);
        search_class_frame.getContentPane().add(total_cost_field);
        total_cost_field.setColumns(10);

        JLabel enter_seller_label = new JLabel("Seller name");
        enter_seller_label.setBounds(203, 448, 89, 14);
        search_class_frame.getContentPane().add(enter_seller_label);

        JLabel enter_cost_label = new JLabel("Total cost (in Dollars)");
        enter_cost_label.setBounds(224, 560, 141, 14);
        search_class_frame.getContentPane().add(enter_cost_label);

        card_field = new JTextField();
        card_field.setBounds(149, 529, 169, 20);
        search_class_frame.getContentPane().add(card_field);
        card_field.setColumns(10);

        JLabel enter_card_label = new JLabel("Card name");
        enter_card_label.setBounds(203, 504, 115, 14);
        search_class_frame.getContentPane().add(enter_card_label);

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

        search_card_field = new JTextField();
        search_card_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {

                DefaultTableModel modelTwo = (DefaultTableModel)search_class_table.getModel();
                TableRowSorter<DefaultTableModel> table_row = new TableRowSorter<DefaultTableModel>(modelTwo);
                search_class_table.setRowSorter(table_row);
                table_row.setRowFilter(RowFilter.regexFilter(search_card_field.getText().trim()));
            }
        });
        search_card_field.setBounds(613, 529, 169, 20);
        search_class_frame.getContentPane().add(search_card_field);
        search_card_field.setColumns(10);

        search_seller_field = new JTextField();
        search_seller_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                DefaultTableModel modelThree = (DefaultTableModel)search_class_table.getModel();
                TableRowSorter<DefaultTableModel> table_row_three = new TableRowSorter<DefaultTableModel>(modelThree);
                search_class_table.setRowSorter(table_row_three);
                table_row_three.setRowFilter(RowFilter.regexFilter(search_seller_field.getText().trim()));

            }
        });
        search_seller_field.setBounds(613, 473, 169, 20);
        search_class_frame.getContentPane().add(search_seller_field);
        search_seller_field.setColumns(10);

        JLabel seller_name_enter_field = new JLabel("Seller name");
        seller_name_enter_field.setBounds(664, 448, 103, 14);
        search_class_frame.getContentPane().add(seller_name_enter_field);

        JLabel lblAddTransaction = new JLabel("ADD TRANSACTION");
        lblAddTransaction.setOpaque(true);
        lblAddTransaction.setForeground(new Color(218, 165, 32));
        lblAddTransaction.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblAddTransaction.setBackground(Color.BLACK);
        lblAddTransaction.setBounds(143, 392, 191, 45);
        search_class_frame.getContentPane().add(lblAddTransaction);

        JLabel search_transaction_label = new JLabel("SEARCH TRANSACTION");
        search_transaction_label.setOpaque(true);
        search_transaction_label.setForeground(new Color(218, 165, 32));
        search_transaction_label.setFont(new Font("Dialog", Font.PLAIN, 20));
        search_transaction_label.setBackground(Color.BLACK);
        search_transaction_label.setBounds(579, 392, 232, 45);
        search_class_frame.getContentPane().add(search_transaction_label);

        JLabel enter_shipping_label = new JLabel("Tracking # (If applicable)");
        enter_shipping_label.setBounds(224, 640, 141, 14);
        search_class_frame.getContentPane().add(enter_shipping_label);

        tracking_field = new JTextField();
        tracking_field.setColumns(10);
        tracking_field.setBounds(196, 665, 169, 20);
        search_class_frame.getContentPane().add(tracking_field);

        JLabel tracking_number_label = new JLabel("Tracking #");
        tracking_number_label.setBounds(664, 613, 103, 14);
        search_class_frame.getContentPane().add(tracking_number_label);

        search_tracking_field = new JTextField();
        search_tracking_field.setText("");
        search_tracking_field.setColumns(10);
        search_tracking_field.setBounds(613, 634, 169, 20);
        search_class_frame.getContentPane().add(search_tracking_field);

        JLabel lblTransactionWithSellers = new JLabel("TRANSACTION WITH SELLERS");
        lblTransactionWithSellers.setOpaque(true);
        lblTransactionWithSellers.setForeground(new Color(218, 165, 32));
        lblTransactionWithSellers.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblTransactionWithSellers.setBackground(Color.BLACK);
        lblTransactionWithSellers.setBounds(0, 0, 289, 45);
        search_class_frame.getContentPane().add(lblTransactionWithSellers);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(846, 0, 100, 100);
        search_class_frame.getContentPane().add(lblNewLabel_1);

        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setBounds(10, 138, 67, 14);
        search_class_frame.getContentPane().add(sortLabel);

        //combo box selection change
        JComboBox sortComboBox = new JComboBox();
        sortComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                String query = sortComboBox.getSelectedItem().toString();

                sortData(query, search_class_table, model);

            }

        });
        sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Name (A-Z)", "Name (Z-A)", "Price (low-high)", "Price (high-low)", "Paid orders", "Shipped orders"}));
        sortComboBox.setBounds(10, 163, 103, 22);
        search_class_frame.getContentPane().add(sortComboBox);

        JLabel shippedLabel = new JLabel("Shipped?");
        shippedLabel.setBounds(109, 641, 57, 14);
        search_class_frame.getContentPane().add(shippedLabel);

        JComboBox shippingStatusComboBox = new JComboBox();
        shippingStatusComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedShippingValue = shippingStatusComboBox.getSelectedItem().toString();
                row[4] = selectedShippingValue;
            }
        });
        shippingStatusComboBox.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
        shippingStatusComboBox.setBounds(99, 664, 67, 22);
        search_class_frame.getContentPane().add(shippingStatusComboBox);

        JLabel paidLabel = new JLabel("Paid?");
        paidLabel.setBounds(120, 560, 46, 14);
        search_class_frame.getContentPane().add(paidLabel);



        JComboBox paymentStatusComboBox = new JComboBox();
        paymentStatusComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPaymentValue = paymentStatusComboBox.getSelectedItem().toString();
                row[3] = selectedPaymentValue;
            }

        });

        paymentStatusComboBox.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
        paymentStatusComboBox.setBounds(99, 584, 67, 22);
        search_class_frame.getContentPane().add(paymentStatusComboBox);



        search_class_frame.revalidate();
        search_class_frame.setVisible(true);


		/*
		JLabel lblNewLabel = new JLabel("");
		Image card2cartLogo = new ImageIcon(this.getClass().getResource("card2cart_logo.jpeg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(logo));
		lblNewLabel.setBounds(805, 0, 100, 100);
		contentPane.add(lblNewLabel);
		lblNewLabel.setBounds(1044, 0, 100, 100);
		contentPane.add(lblNewLabel);
		*/

		/*
		 Sort function
		 Arrays.sort(myArray); // sort in ascending order
		 Arrays.sort(myArray, Comparator.reverseOrder()); // sort in descending order

		 */
        //call sort function to sort data





    }//main

    //sort function
    private static void sortData(String theQuery, JTable search_table, DefaultTableModel tableModel) {

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        search_table.setRowSorter(sorter);

        if(theQuery != "None") {

            sorter.setRowFilter(RowFilter.regexFilter(theQuery));
        }

        else if( (theQuery == "Name (Z-A)") || (theQuery == "Price (low-high)") ){

            search_table.setRowSorter(sorter);
            //sorter.setRowFilter(RowFilter.regexFilter(theQuery));
        }

					/*
					else if( (theQuery == "Name (A-Z)") || (theQuery == "Price (high-low)") ){

						//search_table.setRowSorter(sorter);

					}
					*/


    }//sortData
}//add_and_search_function