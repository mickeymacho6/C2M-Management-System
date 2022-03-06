import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The page for username recovery.
 * @author Harlan Nguyen
 * Date: 03/04/2022
 */
public class Username_Recovery extends JFrame {
    private JTextField enterAnswer1HereTextField;
    private JTextField enterAnswer2HereTextField;
    private JTextField enterAnswer3HereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel question1Label;

    private JLabel question2Label;

    private JLabel question3Label;

    private JLabel incorrectAnswerLabel;
    private JLabel imageLabel;
    private JLabel emailLabel;
    private JTextField enterEmailHereTextField;
    private JButton emailButton;
    private JLabel incorrectEmailLabel;

    /**
     * Creates the username recovery page.
     */
    public Username_Recovery() {
        setContentPane(mainPanel);
        setTitle("Account Recovery");
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        incorrectEmailLabel.setVisible(false);
        question1Label.setVisible(false);
        enterAnswer1HereTextField.setVisible(false);
        question2Label.setVisible(false);
        enterAnswer2HereTextField.setVisible(false);
        question3Label.setVisible(false);
        enterAnswer3HereTextField.setVisible(false);
        incorrectAnswerLabel.setVisible(false);
        submitButton.setVisible(false);
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailText = enterEmailHereTextField.getText();
                account = getAuthenicatedAccount(emailText);

                if (account != null) {
                    emailLabel.setVisible(false);
                    enterEmailHereTextField.setVisible(false);
                    emailButton.setVisible(false);
                    incorrectEmailLabel.setVisible(false);
                    question1Label.setVisible(true);
                    question1Label.setText(account.question1);
                    enterAnswer1HereTextField.setVisible(true);
                    question2Label.setVisible(true);
                    question2Label.setText(account.question2);
                    enterAnswer2HereTextField.setVisible(true);
                    question3Label.setVisible(true);
                    question3Label.setText(account.question3);
                    enterAnswer3HereTextField.setVisible(true);
                    submitButton.setVisible(true);
                } else {
                    incorrectEmailLabel.setVisible(true);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterAnswer1HereTextField.getText().equals(account.answer1) | enterAnswer2HereTextField.getText().equals(account.answer2)
                        | enterAnswer3HereTextField.getText().equals(account.answer3)) {
                    dispose();
                } else {
                    incorrectAnswerLabel.setVisible(true);
                }
            }
        });
    }

    public Account account;
    private Account getAuthenicatedAccount(String email) {
        Account account = null;
        final String DB_URL = "jdbc:mysql://localhost/card2cardaccount?serverTimezone=UTC";
        final String USERNAME = "harlan";
        final String PASSWORD = "19tk9989";
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "Select * from accounts where email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            account = new Account();
            account.name = resultSet.getString("name");
            account.email = resultSet.getString("email");
            account.question1 = resultSet.getString("question1");
            account.answer1 = resultSet.getString("answer1");
            account.question2 = resultSet.getString("question2");
            account.answer2 = resultSet.getString("answer2");
            account.question3 = resultSet.getString("question3");
            account.answer3 = resultSet.getString("answer3");

        } catch (Exception e) {

        }
        return account;
    }
    public static void main(String[] args) {
        Username_Recovery accountRecovery = new Username_Recovery();
        Account account = accountRecovery.account;
        if (account != null) {
            System.out.println(account.email);
        } else {
            System.out.println("Authentication Canceled");
        }

    }
}
