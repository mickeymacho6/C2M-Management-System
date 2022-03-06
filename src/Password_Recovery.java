import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;

/**
 * The page for password recovery.
 * @author Harlan Nguyen
 * Date: 03/04/2022
 */
public class Password_Recovery extends JFrame {
    private JTextField enterCodeHereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel codeSentLabel;
    private JLabel incorrectCodeLabel;
    private JLabel imageLabel;
    private JLabel enterUsernameHereLabel;
    private JTextField enterUsernameHereTextField;
    private JButton confirmUsernameButton;
    private JLabel incorrectUsernameLabel;

    public Password_Recovery() {
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
                if (!enterUsernameHereTextField.getText().isEmpty()) {
                    enterUsernameHereLabel.setVisible(false);
                    enterUsernameHereTextField.setVisible(false);
                    confirmUsernameButton.setVisible(false);
                    incorrectUsernameLabel.setVisible(false);
                    codeSentLabel.setVisible(true);
                    enterCodeHereTextField.setVisible(true);
                    submitButton.setVisible(true);
                    submitButton.setVisible(true);
                } else {
                    incorrectUsernameLabel.setVisible(true);
                }

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enterCodeHereTextField.getText().isEmpty()) {
                    dispose();
                } else {
                    incorrectCodeLabel.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
//        String to = "harlan.oogie@gmail.com";
//        String from = "harlan.oogie@gmail.com";
//        String host = "localhost";
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        Session session = Session.getDefaultInstance(properties);
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("This is the Subject Line!");
//            message.setText("This is actual message");
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
        Password_Recovery passwordRecovery = new Password_Recovery();
    }
}
