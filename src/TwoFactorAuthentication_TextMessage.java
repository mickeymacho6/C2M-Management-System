import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class TwoFactorAuthentication_TextMessage extends Two_Factor_Authentication {
    private static JLabel title;
    private static JLabel verifyOptions;
    private static JFrame frame;
    private static JButton sendCodeByEmail;
    private static JButton sendCodeByText;
    private static JButton verifyCode;

    private static JPanel contentPane;

    private final static String ACCOUNT_SID = "";
    private final static String AUTH_ID = "";

    public TwoFactorAuthentication_TextMessage() {




    }

    public boolean sendTwoFactorAuthCode(String phoneNumber, String twoFactorAuthCode) {

        return true;
    }

    public static void main(String[] args) {
        /*
        frame=new JFrame();//creating instance of JFrame

        contentPane = new JPanel();
        frame.getContentPane().setBackground(new Color(255, 215, 0));//Making Background Color GOLD.

        frame.add(title);

        frame.add(verifyOptions);
        frame.add(sendCodeByEmail);//adding button in JFrame
        frame.add(sendCodeByText);//adding button in JFrame
        frame.add(verifyCode);//adding button in JFrame

        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible

         */

        //Twilio.init(ACCOUNT_SID, AUTH_ID);
    }


}
