import javax.swing.*;
import java.awt.*;

public class TwoFactorAuthentication_TextMessage extends Two_Factor_Authentication {
    private static JLabel title;
    private static JLabel verifyOptions;
    private static JFrame frame;
    private static JButton sendCodeByEmail;
    private static JButton sendCodeByText;
    private static JButton verifyCode;

    private static JPanel contentPane;
    public TwoFactorAuthentication_TextMessage() {




    }

    public static void main(String[] args) {
        frame=new JFrame();//creating instance of JFrame

        contentPane = new JPanel();
        frame.getContentPane().setBackground(new Color(255, 215, 0));//Making Background Color GOLD.


        //Verify Button to Verify Code
      /*
        verifyCode=new JButton("VERIFY");//creating instance of JButton
        verifyCode.setBounds(120,990,130, 40);//x axis, y axis, width, height
        verifyCode.setForeground(Color.BLACK);
        verifyCode.setBackground(Color.GREEN);
        verifyCode.setFocusable(true);
        verifyCode.setContentAreaFilled(false);
        verifyCode.setOpaque(true);
        */
        frame.add(title);

        frame.add(verifyOptions);
        frame.add(sendCodeByEmail);//adding button in JFrame
        frame.add(sendCodeByText);//adding button in JFrame
        frame.add(verifyCode);//adding button in JFrame

        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }


}
