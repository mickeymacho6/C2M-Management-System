import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * The page for password recovery.
 *
 * @author Harlan Nguyen
 * Last Modified: 04/18/2022
 */
public class Password_Recovery extends JFrame {
    //Java Swing components
    private JTextField enterCodeHereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel codeSentLabel;
    private JLabel incorrectCodeLabel;
    private JLabel imageLabel;
    private JLabel enterUsernameHereLabel;
    private JTextField enterUsernameHereTextField;
    private JButton confirmUsernameButton;
    private JLabel incorrectUsernameLabel;
    private JLabel titleLabel;

    //Allows access to the database
    final String DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
    final String USERNAME = "greenhornetsadmin";
    final String PASSWORD = "GreenHornetsUp!";
    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    Statement statement = connection.createStatement();

    //Classes to store the info from the database
    public User user;
    PasswordRecoveryCode selectedCode;

    //Used for the password recovery codes
    private Random random = new Random();
    private int chosenID;
    public ArrayList<PasswordRecoveryCode> passwordRecoveryCodeArrayList = new ArrayList<>();


    /**
     * Creates the Password Recovery page and features.
     *
     * @throws SQLException
     */
    public Password_Recovery() throws SQLException {
        setContentPane(mainPanel);
        setTitle("Account Recovery");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        incorrectUsernameLabel.setVisible(false);
        codeSentLabel.setVisible(false);
        enterCodeHereTextField.setVisible(false);
        submitButton.setVisible(false);
        incorrectCodeLabel.setVisible(false);
        submitButton.setVisible(false);

        //Resizes the card2cart logo
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);

        //If the username matches the one from the database, then a code is sent via email
        confirmUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameText = enterUsernameHereTextField.getText();
                user = getAuthenticatedUserpass(usernameText);

                if (user.username.equals(usernameText)) {
                    enterUsernameHereLabel.setVisible(false);
                    enterUsernameHereTextField.setVisible(false);
                    confirmUsernameButton.setVisible(false);
                    incorrectUsernameLabel.setVisible(false);
                    codeSentLabel.setVisible(true);
                    enterCodeHereTextField.setVisible(true);
                    submitButton.setVisible(true);
                    submitButton.setVisible(true);
                    getAuthenticatedCode();
                    chosenID = random.nextInt(passwordRecoveryCodeArrayList.size());
                    selectedCode = passwordRecoveryCodeArrayList.get(chosenID);

                    //SMTP mailing process
                    String host = "smtp.mail.yahoo.com";
                    String email = "greenhornetscard2manage@yahoo.com";
                    String password = "gsultfeqjngamtjr";
                    boolean sessionDebug = false;
                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "host");
                    properties.put("mail.smtp.port", "587");
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.required", "true");
                    properties.put("mail.smtp.ssl.trust", "smtp.mail.yahoo.com");
                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    Session mailSession = Session.getDefaultInstance(properties, null);
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    try {
                        msg.setFrom(new InternetAddress(email));
                        InternetAddress[] addresses = {new InternetAddress(user.email)};
                        msg.setRecipients(Message.RecipientType.TO, addresses);
                        msg.setSubject("Verification Code");
                        msg.setText("The verification code is: " + selectedCode.passwordcode);
                        Transport transport = mailSession.getTransport("smtp");
                        transport.connect(host, email, password);
                        transport.sendMessage(msg, msg.getAllRecipients());
                        transport.close();
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    incorrectUsernameLabel.setVisible(true);
                }

            }
        });

        //If the correct code is used, then the user is taken to the NewPassword page
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterCodeHereTextField.getText().equals(selectedCode.passwordcode)) {
                    dispose();
                    NewPassword newPassword = new NewPassword(null);
                } else {
                    incorrectCodeLabel.setVisible(true);
                }
            }
        });
    }

    /**
     * This method makes a SQL query to grab the matching username from the users table. Afterwards, it uses the values from the query to fill out
     * a User class's variables. If no matching usernames are found, an Exception is thrown.
     *
     * @param username Takes the string from the "enterUsernameHereTextField".
     * @return a User with many of its fields filled out.
     */
    public User getAuthenticatedUserpass(String username) {
        User user = null;
        try {
            String sql = "Select * from dbo.users where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(username));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * This method makes a SQL query to take all the codes from the passwordrecoverycodes table and stores them into an ArrayList.
     * An Exception is thrown if no codes are found.
     */
    public void getAuthenticatedCode() {
        PasswordRecoveryCode passwordRecoveryCode = null;
        try {
            String sql = "Select * from dbo.passwordrecoverycodes";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passwordRecoveryCode = new PasswordRecoveryCode();
                passwordRecoveryCode.codeid = resultSet.getString("codeid");
                passwordRecoveryCode.passwordcode = resultSet.getString("passwordcode");
                passwordRecoveryCodeArrayList.add(passwordRecoveryCode);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Password_Recovery passwordRecovery = new Password_Recovery();
    }
}
