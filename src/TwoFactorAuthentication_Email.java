import javax.swing.*;

public class TwoFactorAuthentication_Email extends Two_Factor_Authentication {
    private static JLabel title;
    private static JLabel verifyOptions;
    private static JFrame frame;
    private static JButton sendCodeByEmail;
    private static JButton sendCodeByText;
    private static JButton verifyCode;

    private static JPanel contentPane;

    private static String email;
    public TwoFactorAuthentication_Email() {




    }

    public static String getEmailAddress() {

        return "";


    }
    public static void main(String[] args) {
        frame=new JFrame();//creating instance of JFrame

        contentPane = new JPanel();



        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }
}
