//import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;


//@SpringBootApplication
public class Two_Factor_Authentication extends JDialog {
   
   private static JLabel title; 
   private static JLabel verifyOptions; 
   private static JFrame frame;
   private static JButton sendCodeByEmail;
   private static JButton sendCodeByText;
   private static JButton verifyCode;

   private static JButton backButton;

   public JButton settingsButton;
   
   private static JPanel contentPane;

   private static int verificationCode;

   public static Random rand = new Random();
   private JButton emailButton;
   private JButton textMessageButton;
   public JButton verifyButton;

   private static String email = "vyassrinivasan@csus.edu";

   private static final String username = "vyassrinivasan@csus.edu";
   private static final String password = "*********************";

   private static String verificationCodeStr;



   public Two_Factor_Authentication(JFrame parent) {
      super(parent);
      setTitle(" Card2Cart Login");
      setContentPane(contentPane);
      setMinimumSize(new Dimension(400,475));
      setModal(true);
      setLocationRelativeTo(parent);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
   
   }

   public static boolean sendEmail(String emailAddress, String twoFactorAuthenticationCode, String subject, String message) {
      SMTP emailCode = new SMTP();
      message = "Your Two-Factor Authentication code is" + twoFactorAuthenticationCode;
      emailCode.send(username, password, emailAddress, subject, message);

      return true;
   }


   public static int generateCode() {

      verificationCode = rand.nextInt(900000) +100000;
      verificationCodeStr = Integer.toString(verificationCode);
      System.out.println(verificationCodeStr);
      //sendEmail(username, verificationCodeStr, "Two Factor Authentication Code", "The Two-factor Authentication Code is " + verificationCodeStr );
      return verificationCode;

   }

   public static void main(String[] args) {
      twoFAMenu();
      Two_Factor_Authentication twoFA = new Two_Factor_Authentication(null);


      JButton verify = twoFA.verifyButton;

      if (verify != null) {
         System.out.println("Verified!!!");
      }  else {
         System.out.println("Error!");
      }
      //twoFASettings();

   }

   public static void twoFAMenu() {

      frame=new JFrame();//creating instance of JFrame

      contentPane = new JPanel();
      frame.getContentPane().setBackground(new Color(255, 215, 0));//Making Background Color GOLD.

      //Title: Two-Factor Authentication
      title = new JLabel("Two-Factor Verification");
      title.setBounds(70,60,280, 40);//x axis, y axis, width, height
      title.setFont(new Font("Serif", Font.BOLD, 22));

      JLabel menuText = new JLabel("Menu");
      menuText.setBounds(150,80,100, 40);//x axis, y axis, width, height
      menuText.setFont(new Font("Serif", Font.ITALIC, 18));

      //Prompts users on how they would like to get verification code.
      verifyOptions = new JLabel("Send Code By: ");
      verifyOptions.setBounds(130,100,100, 40);

      //Option: Via Email
      sendCodeByEmail=new JButton("Email");//creating instance of JButton
      sendCodeByEmail.setBounds(130,140,100, 40);//x axis, y axis, width, height
      sendCodeByEmail.setForeground(Color.BLUE);
      //setting the cancel button
      sendCodeByEmail.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            twoFAEmail();
            System.out.println(generateCode());

         }

      });
      //setVisible(true);

      /*
      //Option: Via Text Message
      sendCodeByText=new JButton("Text");//creating instance of JButton
      sendCodeByText.setBounds(130,190,100, 40);//x axis, y axis, width, height
      sendCodeByText.setForeground(Color.BLUE);
      */

      //SettingsButton
      /*
      Icon settingsIcon = new ImageIcon("settingsicon.png");
      settingsButton=new JButton("Settings");//creating instance of JButton
      settingsButton.setBounds(155,430,50, 40);//x axis, y axis, width, height
      settingsButton.setForeground(Color.BLUE);

      settingsButton.setIcon(settingsIcon);
      //settingsButton.setBackground(Color.ORANGE);
      */
      //Verify Button to Verify Code
      verifyCode=new JButton("VERIFY");//creating instance of JButton
      verifyCode.setBounds(120,360,130, 40);//x axis, y axis, width, height
      verifyCode.setForeground(Color.BLACK);
      verifyCode.setBackground(Color.GREEN);
      verifyCode.setFocusable(true);
      verifyCode.setContentAreaFilled(false);
      verifyCode.setOpaque(true);

      frame.add(new JLabel(new ImageIcon("logo.PNG")));
      frame.add(title);
      frame.add(menuText);

      frame.add(verifyOptions);
      //frame.add(settingsButton);
      frame.add(sendCodeByEmail);//adding button in JFrame

      //frame.add(sendCodeByText);//adding button in JFrame
      //frame.add(verifyCode);//adding button in JFrame

      frame.setSize(400,500);//400 width and 500 height
      frame.setLayout(null);//using no layout managers
      frame.setVisible(true);//making the frame visible

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public static void twoFAEmail() {

      frame = new JFrame();//creating instance of JFrame

      contentPane = new JPanel();
      frame.getContentPane().setBackground(new Color(255, 215, 0));//Making Background Color GOLD.

      //Title: Two-Factor Authentication
      title = new JLabel("Two-Factor Verification");
      title.setBounds(70,60,280, 40);//x axis, y axis, width, height
      title.setFont(new Font("Serif", Font.BOLD, 22));

      JLabel emailText = new JLabel("Email");
      emailText.setBounds(150,80,100, 40);//x axis, y axis, width, height
      emailText.setFont(new Font("Serif", Font.ITALIC, 18));

      //Prompts users on how they would like to get verification code.
      JLabel enterSixDigitCode = new JLabel("Enter the 6-digit code: ");
      enterSixDigitCode.setBounds(110,100,200, 40);

      //Option: Via Email
      sendCodeByEmail=new JButton("Email");//creating instance of JButton
      sendCodeByEmail.setBounds(130,140,100, 40);//x axis, y axis, width, height
      sendCodeByEmail.setForeground(Color.BLUE);

      backButton = new JButton("BACK");
      backButton.setBounds(20,20,70,30);


      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            twoFAMenu();

         }

      });

      JTextField authCode = new JTextField();
      authCode.setBounds(130,140,100, 40);//x axis, y axis, width, height

      verifyCode.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("Success");
            if (verificationCodeStr.equals(authCode.toString())) {
               System.out.println("Success");

            } else {
               JOptionPane.showMessageDialog(null, "Username or password is invalid",
                       "Try again", JOptionPane.ERROR_MESSAGE);
            }
         }

      });


      /*
      //Option: Via Text Message
      sendCodeByText=new JButton("Text");//creating instance of JButton
      sendCodeByText.setBounds(130,190,100, 40);//x axis, y axis, width, height
      sendCodeByText.setForeground(Color.BLUE);
      */

      //Resend Button


     JButton resendCodeButton=new JButton("Resend Code");//creating instance of JButton
      resendCodeButton.setBounds(120,430,130, 40);//x axis, y axis, width, height
      resendCodeButton.setForeground(Color.BLUE);

      resendCodeButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println(generateCode());

         }

      });

      //settingsButton.setBackground(Color.ORANGE);

      //Verify Button to Verify Code
      verifyCode=new JButton("VERIFY");//creating instance of JButton
      verifyCode.setBounds(120,360,130, 40);//x axis, y axis, width, height
      verifyCode.setForeground(Color.BLACK);
      verifyCode.setBackground(Color.GREEN);
      verifyCode.setFocusable(true);
      verifyCode.setContentAreaFilled(false);
      verifyCode.setOpaque(true);

      frame.add(new JLabel(new ImageIcon("logo.PNG")));
      frame.add(title);
      frame.add(emailText);

      frame.add(enterSixDigitCode);
      frame.add(backButton);
      frame.add(authCode);
      frame.add(resendCodeButton);
      //frame.add(sendCodeByEmail);//adding button in JFrame
      //frame.add(sendCodeByText);//adding button in JFrame
      frame.add(verifyCode);//adding button in JFrame

      frame.setSize(400,500);//400 width and 500 height
      frame.setLayout(null);//using no layout managers
      frame.setVisible(true);//making the frame visible

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public static void twoFASettings() {
      frame=new JFrame();//creating instance of JFrame

      contentPane = new JPanel();
      frame.getContentPane().setBackground(new Color(255, 215, 0));//Making Background Color GOLD.

      //Title: Two-Factor Authentication
      title = new JLabel("Two-Factor Verification");
      title.setBounds(70,60,280, 40);//x axis, y axis, width, height
      title.setFont(new Font("Serif", Font.BOLD, 18));

      JLabel settingsText = new JLabel("Settings");
      settingsText.setBounds(130,80,100, 40);//x axis, y axis, width, height
      settingsText.setFont(new Font("Serif", Font.ITALIC, 18));

      frame.add(title);
      frame.add(settingsText);



      frame.setSize(400,500);//400 width and 500 height
      frame.setLayout(null);//using no layout managers
      frame.setVisible(true);//making the frame visible



   }


   private void createUIComponents() {
      // TODO: place custom component creation code here
   }
}