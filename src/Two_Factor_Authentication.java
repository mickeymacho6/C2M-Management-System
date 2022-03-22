import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


@SpringBootApplication
public class Two_Factor_Authentication {  
   
   private static JLabel title; 
   private static JLabel verifyOptions; 
   private static JFrame frame;
   private static JButton sendCodeByEmail;
   private static JButton sendCodeByText;
   private static JButton verifyCode;
   
   private static JPanel contentPane;

   private static int verificationCode;

   public static Random rand = new Random();


   public Two_Factor_Authentication() {
      
       
   
   }
   public static int generateCode() {

      verificationCode = rand.nextInt(8999) + 1000;
      return verificationCode;

   }

   public static void main(String[] args) {  




      twoFASettings();

   }

   public static void twoFASettings() {
      frame=new JFrame();//creating instance of JFrame

      contentPane = new JPanel();
      frame.getContentPane().setBackground(new Color(255, 215, 0));//Making Background Color GOLD.

      //Title: Two-Factor Authentication
      title = new JLabel("Two-Factor Verification");
      title.setBounds(70,60,250, 40);//x axis, y axis, width, height
      title.setFont(new Font("Serif", Font.BOLD, 22));

      //Prompts users on how they would like to get verification code.
      verifyOptions = new JLabel("Send Code By: ");
      verifyOptions.setBounds(130,100,100, 40);

      //Option: Via Email
      sendCodeByEmail=new JButton("Email");//creating instance of JButton
      sendCodeByEmail.setBounds(130,140,100, 40);//x axis, y axis, width, height
      sendCodeByEmail.setForeground(Color.BLUE);

      //Option: Via Text Message
      sendCodeByText=new JButton("Text");//creating instance of JButton
      sendCodeByText.setBounds(130,190,100, 40);//x axis, y axis, width, height
      sendCodeByText.setForeground(Color.BLUE);

      //Verify Button to Verify Code
      verifyCode=new JButton("VERIFY");//creating instance of JButton
      verifyCode.setBounds(120,360,130, 40);//x axis, y axis, width, height
      verifyCode.setForeground(Color.BLACK);
      verifyCode.setBackground(Color.GREEN);
      verifyCode.setFocusable(true);
      verifyCode.setContentAreaFilled(false);
      verifyCode.setOpaque(true);

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