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

public class add_transaction extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    add_transaction frame = new add_transaction();
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
    public add_transaction() {
        setTitle("Card2Manage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 922, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(218, 165, 32));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Transactions with sellers");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setForeground(new Color(218, 165, 32));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setBounds(442, 192, 295, 75);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Add transactions");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1.setBackground(new Color(0, 0, 0));
        btnNewButton_1.setForeground(new Color(218, 165, 32));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_1.setBounds(134, 192, 229, 75);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("");
       // Image logo = new ImageIcon(this.getClass().getResource("card2cart_logo.jpeg")).getImage();
       // lblNewLabel.setIcon(new ImageIcon(logo));
        lblNewLabel.setBounds(805, 0, 100, 100);
        contentPane.add(lblNewLabel);

        JLabel transactionLogLabel = new JLabel("TRANSACTION LOG");
        transactionLogLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        transactionLogLabel.setForeground(new Color(218, 165, 32));
        transactionLogLabel.setBackground(new Color(0, 0, 0));
        transactionLogLabel.setBounds(0, 0, 197, 45);
        contentPane.add(transactionLogLabel);
        transactionLogLabel.setOpaque(true);
    }
}
