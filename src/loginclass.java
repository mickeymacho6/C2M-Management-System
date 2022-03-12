import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;



public class loginclass extends JFrame implements ActionListener
    {

        Container contanier=getContentPane();
        ImageIcon icon=new ImageIcon("logo.PNG"); //placing the app icon
        JLabel lable=new JLabel(icon);
        //Username and Password botton
        JLabel user=new JLabel("Username");
        JLabel password=new JLabel("Password");
        //taking text for username and password
        JTextField textuser=new JTextField();
        JPasswordField textpassword=new JPasswordField();
        // setting up the Login Button
        JButton loginButton=new JButton("Login");
        // setting up the Forget Password Button
        JButton forgetPassworrdButton=new JButton("Forget Password");
        // setting up the Reset Button
        JButton resetButton=new JButton("Reset");
        // setting up the Show password Button
        JCheckBox visiblePassword=new JCheckBox("Show Password");
        // setting up the create account Button
        JButton createAccount= new JButton("Create account");

        loginclass()
        {

            setLayoutManager();
            setLocationAndSize();
            addComponentsToContainer();

        }



        private void setLayoutManager()
        {
            contanier.setLayout(null);
        }
        //set location and size
        private void setLocationAndSize()
        {

            lable.setBounds(160,25,icon.getIconWidth(),icon.getIconHeight());
            user.setBounds(50,150,100,30);
            password.setBounds(50,220,100,30);
            textuser.setBounds(150,150,150,30);
            textpassword.setBounds(150,220,150,30);
            visiblePassword.setBounds(150,250,150,30);
            resetButton.setBounds(150,300,150,30);
            forgetPassworrdButton.setBounds(150,350,150,30);
            loginButton.setBounds(50,350,100,30);
            createAccount.setBounds(150,400,150,30);

        }
        // adding componets
        private void addComponentsToContainer()
        {

            contanier.add(user);
            contanier.add(password);
            contanier.add(createAccount);
            contanier.add(textuser);
            contanier.add(textpassword);
            contanier.add(visiblePassword);
            contanier.add(forgetPassworrdButton);
            contanier.add(loginButton);
            contanier.add(resetButton);
            contanier.add(lable);


        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String user= textuser.getText();
            char[] password=textpassword.getPassword();

        }

    }

