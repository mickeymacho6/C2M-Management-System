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
 * @author Harlan Nguyen
 * Date: 03/04/2022
 */
public class Password_Recovery extends JFrame {
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
    final String DB_URL = "jdbc:mysql://localhost:3307/card2cartaccount";
    final String USERNAME = "admin";
    final String PASSWORD = "admin";
    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    Statement statement = connection.createStatement();
    private Random random = new Random();
    private int chosenID;
    PasswordRecoveryCode selectedCode;

    /**
     * Creates the Password Recovery page and features.
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
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);
        confirmUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameText = enterUsernameHereTextField.getText();
                userpass = getAuthenticatedUserpass(usernameText);

                if (userpass.username.equals(usernameText)) {
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
                        InternetAddress[] addresses = {new InternetAddress(userpass.email)};
                        msg.setRecipients(Message.RecipientType.TO, addresses);
                        msg.setSubject("Verification Code");
                        msg.setText("The verification code is: " + selectedCode.code);
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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterCodeHereTextField.getText().equals(selectedCode.code)) {
                    dispose();
                    //ChangePassword ch
                } else {
                    incorrectCodeLabel.setVisible(true);
                }
            }
        });
    }

    public Userpass userpass;
    public Userpass getAuthenticatedUserpass(String username) {
        Userpass userpass = null;
        try {
            String sql = "Select * from userpass where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(username));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userpass = new Userpass();
            userpass.username = resultSet.getString("username");
            userpass.password = resultSet.getString("password");
            userpass.email = resultSet.getString("email");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userpass;
    }

    public ArrayList<PasswordRecoveryCode> passwordRecoveryCodeArrayList = new ArrayList<>();
    public void getAuthenticatedCode() {
        PasswordRecoveryCode passwordRecoveryCode = null;
        try {
            String sql = "Select * from codes";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passwordRecoveryCode = new PasswordRecoveryCode();
                passwordRecoveryCode.id = resultSet.getString("id");
                passwordRecoveryCode.code = resultSet.getString("code");
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
