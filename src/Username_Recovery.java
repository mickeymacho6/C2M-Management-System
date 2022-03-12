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
                tempAccount = getAuthenicatedAccount(emailText);

                if (tempAccount.email.equals(emailText)) {
                    emailLabel.setVisible(false);
                    enterEmailHereTextField.setVisible(false);
                    emailButton.setVisible(false);
                    incorrectEmailLabel.setVisible(false);
                    question1Label.setVisible(true);
                    question1Label.setText(tempAccount.question1);
                    enterAnswer1HereTextField.setVisible(true);
                    question2Label.setVisible(true);
                    question2Label.setText(tempAccount.question2);
                    enterAnswer2HereTextField.setVisible(true);
                    question3Label.setVisible(true);
                    question3Label.setText(tempAccount.question3);
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
                if (enterAnswer1HereTextField.getText().equals(tempAccount.answer1) & enterAnswer2HereTextField.getText().equals(tempAccount.answer2)
                        & enterAnswer3HereTextField.getText().equals(tempAccount.answer3)) {
                    dispose();
                } else {
                    incorrectAnswerLabel.setVisible(true);
                }
            }
        });
    }

    public TempAccount tempAccount;
    private TempAccount getAuthenicatedAccount(String email) {
        TempAccount tempAccount = null;
        final String DB_URL = "jdbc:mysql://localhost:3307/card2cartaccount";
        final String USERNAME = "admin";
        final String PASSWORD = "admin";
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "Select * from accounts where email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            tempAccount = new TempAccount();
            tempAccount.name = resultSet.getString("name");
            tempAccount.email = resultSet.getString("email");
            tempAccount.question1 = resultSet.getString("question1");
            tempAccount.answer1 = resultSet.getString("answer1");
            tempAccount.question2 = resultSet.getString("question2");
            tempAccount.answer2 = resultSet.getString("answer2");
            tempAccount.question3 = resultSet.getString("question3");
            tempAccount.answer3 = resultSet.getString("answer3");

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempAccount;
    }
    public static void main(String[] args) {
        Username_Recovery accountRecovery = new Username_Recovery();
        TempAccount tempAccount = accountRecovery.tempAccount;
        if (tempAccount != null) {
            System.out.println(tempAccount.email);
        } else {
            System.out.println("Authentication Canceled");
        }

    }
}
