import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The page for username recovery.
 * @author Harlan Nguyen
 * Last Modified: 04/18/2022
 */
public class Username_Recovery extends JFrame {
    //Java Swing components
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
    public User user;

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

        //Resizes the card2cart logo
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);

        //If the email matches the one from the database, then a security question is displayed
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailText = enterEmailHereTextField.getText();
                user = getAuthenicatedAccount(emailText);
                try {
                    if (user.email.equals(emailText)) {
                        emailLabel.setVisible(false);
                        enterEmailHereTextField.setVisible(false);
                        emailButton.setVisible(false);
                        incorrectEmailLabel.setVisible(false);
                        question1Label.setVisible(true);
                        question1Label.setText(user.securityQuestion);
                        enterAnswerHereTextField.setVisible(true);
                        submitButton.setVisible(true);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    incorrectEmailLabel.setVisible(true);
                }
            }
        });

        //If the security answer is correct, then the user is taken back to the Login page with the username field already filled out
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterAnswerHereTextField.getText().equals(user.securityAnswer)) {
                    dispose();
                    loginForm loginForm = new loginForm(null, user.username);
                } else {
                    incorrectAnswerLabel.setVisible(true);
                }
            }
        });
    }

    /**
     * This method makes a SQL query to grab the matching username from the users table. Afterwards, it uses the values from the query to fill out
     * a User class's variables. If no matching usernames are found, an Exception is thrown.
     *
     * @param email Takes the string from the "enterEmailHereTextField".
     * @return a User with many of its fields filled out.
     */
        private User getAuthenicatedAccount(String email) {
            User user = null;
            final String DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
            final String USERNAME = "greenhornetsadmin";
            final String PASSWORD = "GreenHornetsUp!";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "Select * from dbo.users where email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.name = resultSet.getString("name");
            user.email = resultSet.getString("email");
            user.confirmEmail = resultSet.getString("confirmEmail");
            user.username = resultSet.getString("username");
            user.password = resultSet.getString("password");
            user.confirm_password = resultSet.getString("confirm_password");
            user.securityQuestion = resultSet.getString("securityQuestion");
            user.securityAnswer = resultSet.getString("securityAnswer");

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void main(String[] args) {
        Username_Recovery accountRecovery = new Username_Recovery();
    }
}