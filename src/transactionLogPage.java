import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

import java.sql.*;

public class transactionLogPage extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    transactionLogPage frame = new transactionLogPage();
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
    public transactionLogPage() {
        setTitle("Card2Manage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 922, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 236, 72));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Transaction with Customers");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setForeground(new Color(218, 165, 32));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setBounds(509, 192, 337, 75);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open transaction with sellers page
                TransactionMain transactionWithCustomers = new TransactionMain();


                String[] runTransactionWithCustomers = {};
                transactionWithCustomers.main(runTransactionWithCustomers);
                dispose();

                 
            }
        });
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Transaction with Sellers");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1.setBackground(new Color(0, 0, 0));
        btnNewButton_1.setForeground(new Color(218, 165, 32));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open transaction with sellers page
                add_and_search_transaction transactionWithSellers = new add_and_search_transaction();
                String[] runTransactionWithSellers = {};
                transactionWithSellers.main(runTransactionWithSellers);
                dispose();
            }
        });
        btnNewButton_1.setBounds(42, 192, 337, 75);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_1 = new JLabel("Card2Cart label");
        ImageIcon card2cartlogo = new ImageIcon("src/card2cart_logo.jpeg");
        lblNewLabel_1.setIcon(new ImageIcon("src/card2cart_logo.jpeg"));
        lblNewLabel_1.setBounds(803, 0, 103, 100);
        contentPane.add(lblNewLabel_1);

        JLabel transactionLogLabel = new JLabel("TRANSACTION LOG");
        transactionLogLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        transactionLogLabel.setForeground(new Color(218, 165, 32));
        transactionLogLabel.setBackground(new Color(0, 0, 0));
        transactionLogLabel.setBounds(0, 0, 197, 45);
        contentPane.add(transactionLogLabel);
        transactionLogLabel.setOpaque(true);

        JButton backToHomepageButton = new JButton("Homepage");
        backToHomepageButton.setForeground(new Color(218, 165, 32));
        backToHomepageButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        backToHomepageButton.setBackground(Color.BLACK);

        backToHomepageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open transaction with sellers page
                try {
                    Homepage goBackHomepage = new Homepage(null);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                dispose();

            }
        });


        backToHomepageButton.setBounds(677, 386, 229, 75);
        contentPane.add(backToHomepageButton);
    }
}
