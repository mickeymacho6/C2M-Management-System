//import org.springframework.stereotype.Service;

import javax.swing.*;
//@Service
public class TwoFactorAuthentication_Email {
    private static JLabel title;
    private static JLabel verifyOptions;
    private static JFrame frame;
    private static JButton sendCodeByEmail;
    private static JButton sendCodeByText;
    private static JButton verifyCode;

    private static JPanel contentPane;

    private static String email = "vyassrinivasan@csus.edu";

    private static final String username = "vyassrinivasan@csus.edu";
    private static final String password = "*********************";
    private JButton verifyButton;

    public TwoFactorAuthentication_Email(JFrame parent) {
        //super(parent);
    }

    public static boolean sendEmail(String emailAddress, String twoFactorAuthenticationCode, String subject, String message) {
        SMTP emailCode = new SMTP();
        message = "Your Two-Factor Authentication code is" + twoFactorAuthenticationCode;
        emailCode.send(username, password, emailAddress, subject, message);

        return true;
    }

    public static String getEmailAddress() {

        return email;


    }
    public static void main(String[] args) {
        /*
        //Two_Factor_Authentication twoFA = new Two_Factor_Authentication(null);
        //JButton verify = twoFA.verifyButton;

        if (verify != null) {
            System.out.println("Verified");
        }  else {
            System.out.println("Error");
        }

         */
    }
}
