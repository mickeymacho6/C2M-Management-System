package CSC191_Card2Cart_AccountPasswordRecovery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account_Recovery extends JFrame {
    private JTextField insertAnswer1HereTextField;
    private JTextField insertAnswer2HereTextField;
    private JTextField insertAnswer3HereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel question1Label;
    private JLabel InsertQuestion1Label;
    private JLabel question2Label;
    private JLabel insertQuestion2Label;
    private JLabel question3Label;
    private JLabel insertQuestion3Label;
    private JLabel incorrectAnswerLabel;
    private JLabel imageLabel;

    public Account_Recovery() {
        setContentPane(mainPanel);
        setTitle("Account Recovery");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        incorrectAnswerLabel.setVisible(false);
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);
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
