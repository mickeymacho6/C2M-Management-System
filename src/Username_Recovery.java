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
    private JTextField enterAnswerHereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel question1Label;
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
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        incorrectEmailLabel.setVisible(false);
        question1Label.setVisible(false);
        enterAnswerHereTextField.setVisible(false);
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
                try {
                    if (tempAccount.email.equals(emailText)) {
                        emailLabel.setVisible(false);
                        enterEmailHereTextField.setVisible(false);
                        emailButton.setVisible(false);
                        incorrectEmailLabel.setVisible(false);
                        question1Label.setVisible(true);
                        question1Label.setText(tempAccount.question1);
                        enterAnswerHereTextField.setVisible(true);
                        submitButton.setVisible(true);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    incorrectEmailLabel.setVisible(true);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterAnswerHereTextField.getText().equals(tempAccount.answer1)) {
                    dispose();
                    loginForm loginForm = new loginForm(null, tempAccount.name);
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
