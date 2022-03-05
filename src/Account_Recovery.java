import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The page for account recovery.
 * @author Harlan Nguyen
 * Date: 03/04/2022
 */
public class Account_Recovery extends JFrame {
    private JTextField insertAnswer1HereTextField;
    private JTextField insertAnswer2HereTextField;
    private JTextField insertAnswer3HereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel question1Label;
    private JLabel insertQuestion1Label;
    private JLabel question2Label;
    private JLabel insertQuestion2Label;
    private JLabel question3Label;
    private JLabel insertQuestion3Label;
    private JLabel incorrectAnswerLabel;
    private JLabel imageLabel;
    private JLabel emailLabel;
    private JTextField enterEmailHereTextField;
    private JButton emailButton;

    public Account_Recovery() {
        setContentPane(mainPanel);
        setTitle("Account Recovery");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        question1Label.setVisible(false);
        insertQuestion1Label.setVisible(false);
        question2Label.setVisible(false);
        insertQuestion2Label.setVisible(false);
        question3Label.setVisible(false);
        insertQuestion3Label.setVisible(false);
        incorrectAnswerLabel.setVisible(false);
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enterEmailHereTextField.getText().isEmpty()) {
                    question1Label.setVisible(true);
                    insertQuestion1Label.setVisible(true);
                    question2Label.setVisible(true);
                    insertQuestion2Label.setVisible(true);
                    question3Label.setVisible(true);
                    insertQuestion3Label.setVisible(true);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incorrectAnswerLabel.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Account_Recovery accountRecovery = new Account_Recovery();
    }
}
