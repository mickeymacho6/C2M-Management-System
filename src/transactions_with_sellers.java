import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class transactions_with_sellers extends JFrame {

    private JPanel contentPane;
    private JTable transactionsWithSellersTable;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    transactions_with_sellers frame = new transactions_with_sellers();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public transactions_with_sellers() {
        setTitle("Card2Manage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1160, 656);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(218, 165, 32));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel transactionsWithSellersLabel = new JLabel("Transactions with Sellers");
        transactionsWithSellersLabel.setOpaque(true);
        transactionsWithSellersLabel.setForeground(new Color(218, 165, 32));
        transactionsWithSellersLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        transactionsWithSellersLabel.setBackground(Color.BLACK);
        transactionsWithSellersLabel.setBounds(0, 0, 219, 45);
        contentPane.add(transactionsWithSellersLabel);
      //  Image logo = new ImageIcon(this.getClass().getResource("card2cart_logo.jpeg")).getImage();

        JLabel budgetAvailableLabel = new JLabel("Budget available:");
        budgetAvailableLabel.setBounds(0, 56, 111, 14);
        contentPane.add(budgetAvailableLabel);

        transactionsWithSellersTable = new JTable();
        Object[] columns = {"Seller name", "Cards name", "Total Cost", "Payment Status", "Shipping Status"};
        transactionsWithSellersTable.setBounds(264, 96, 632, 356);
        contentPane.add(transactionsWithSellersTable);
        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columns);
        transactionsWithSellersTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Seller name", "Cards name", "Total Cost", "Payment Status", "Shipping Status"
                }
        ));

        transactionsWithSellersTable.setBackground(Color.white);
        transactionsWithSellersTable.setForeground(Color.black);
        transactionsWithSellersTable.setGridColor(Color.gray);
        transactionsWithSellersTable.setSelectionForeground(Color.white);
        transactionsWithSellersTable.setRowHeight(30);
        transactionsWithSellersTable.setAutoCreateRowSorter(true);





        JLabel lblNewLabel = new JLabel("");
        //Image card2cartLogo = new ImageIcon(this.getClass().getResource("card2cart_logo.jpeg")).getImage();
       // lblNewLabel.setIcon(new ImageIcon(logo));
        lblNewLabel.setBounds(805, 0, 100, 100);
        contentPane.add(lblNewLabel);
        lblNewLabel.setBounds(1044, 0, 100, 100);
        contentPane.add(lblNewLabel);

        JButton transactionsPageButton = new JButton("Transactions page");
        transactionsPageButton.setForeground(new Color(218, 165, 32));
        transactionsPageButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        transactionsPageButton.setBackground(Color.BLACK);
        transactionsPageButton.setBounds(129, 475, 229, 75);
        contentPane.add(transactionsPageButton);

        JButton homepageButton = new JButton("Homepage");
        homepageButton.setForeground(new Color(218, 165, 32));
        homepageButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        homepageButton.setBackground(Color.BLACK);
        homepageButton.setBounds(450, 475, 229, 75);
        contentPane.add(homepageButton);

        JButton addTransactionButton = new JButton("Add transaction ->");
        addTransactionButton.setForeground(new Color(218, 165, 32));
        addTransactionButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addTransactionButton.setBackground(Color.BLACK);
        addTransactionButton.setBounds(755, 475, 229, 75);
        contentPane.add(addTransactionButton);

        JLabel sortByLabel = new JLabel("Sort by:");
        sortByLabel.setBounds(43, 146, 46, 14);
        contentPane.add(sortByLabel);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Name (A-Z)");
        rdbtnNewRadioButton.setBackground(new Color(218, 165, 32));
        rdbtnNewRadioButton.setBounds(43, 168, 122, 23);
        contentPane.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNameza = new JRadioButton("Name (Z-A)");
        rdbtnNameza.setBackground(new Color(218, 165, 32));
        rdbtnNameza.setBounds(43, 194, 122, 23);
        contentPane.add(rdbtnNameza);

        JRadioButton rdbtnPricelowhigh = new JRadioButton("Price (low-high)");
        rdbtnPricelowhigh.setBackground(new Color(218, 165, 32));
        rdbtnPricelowhigh.setBounds(43, 220, 122, 23);
        contentPane.add(rdbtnPricelowhigh);

        JRadioButton rdbtnPricehighlow = new JRadioButton("Price (high-low)");
        rdbtnPricehighlow.setBackground(new Color(218, 165, 32));
        rdbtnPricehighlow.setBounds(43, 246, 122, 23);
        contentPane.add(rdbtnPricehighlow);

        JRadioButton rdbtnPaidOrders = new JRadioButton("Paid orders");
        rdbtnPaidOrders.setBackground(new Color(218, 165, 32));
        rdbtnPaidOrders.setBounds(43, 272, 122, 23);
        contentPane.add(rdbtnPaidOrders);

        JRadioButton rdbtnShippedOrders = new JRadioButton("Shipped orders");
        rdbtnShippedOrders.setBackground(new Color(218, 165, 32));
        rdbtnShippedOrders.setBounds(43, 298, 122, 23);
        contentPane.add(rdbtnShippedOrders);

        JLabel lblNewLabel_1 = new JLabel("Search for keywords:");
        lblNewLabel_1.setBounds(390, 68, 122, 14);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(513, 65, 189, 20);
        contentPane.add(textField);
        textField.setColumns(10);


    }
}
