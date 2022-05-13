import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


//Setting the New password form panel author Reza salmanian
public class NewPassword extends JDialog {
    private JPanel newPassword;
    private JButton goButton;
    private JLabel NewPassword;
    private JLabel Confirm;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public NewPassword (JFrame parent)
    {
        super(parent);
        setTitle(" Card2Cart New Password Form");
        setContentPane(newPassword);
        setMinimumSize(new Dimension(350,450));
        setModal(true);
        setLocationRelativeTo(parent);
        //setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    dispose ();
                    loginForm login = new loginForm (parent,null);


            }
        });setVisible (true);


    }
    private void Password_User() {

        String password = String.valueOf(passwordField1.getPassword());
        String confirm_password= String.valueOf(passwordField2.getPassword());

        //If filed is empty display the Error message
        if (  confirm_password.isEmpty()) {
            JOptionPane.showInternalMessageDialog( NewPassword.this,
                    "Please fill all the blank field","Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals( confirm_password))
        {
            JOptionPane.showMessageDialog(this,
                    "Confirmed password does not match","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            JOptionPane.showMessageDialog (NewPassword.this,"Confirmed password is match");
        }
        //Adding new user to database
        User = addUserToDatabase(password, confirm_password);
        if (User != null)
            dispose();
        else{
            JOptionPane.showInternalMessageDialog( this,"Please fill all the blank","Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }



    //global variable
    public User User;
    //returning valid user
    private User addUserToDatabase(String password, String  confirm_password)
    {
        User User = null;
        final String DB_URL;
        DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
        final String USERNAME = "greenhornetsadmin";
        final String PASSWORD = "GreenHornetsUp!";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            System.out.println("Connected to SQL Server");
            //concreting to database successfully
            Statement st = conn.createStatement();
            String sql = "Update users set password=?,confirm_password=? WHERE username=?" + User + "";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf (password));
            preparedStatement.setString(2,String.valueOf (confirm_password));

            //Insert row into the table and execute the quarry
            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0)
            {
                User = new User();
                User.password= password;
                User.confirm_password = confirm_password;

            }
            //closing connection
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return User;

    }

    //Main method
    public static void main(String[] args)
    {   //object of registration form
        NewPassword new_form = new NewPassword(null);
        User User = new_form.User;
        if (User != null)
        {
            System.out.println("successfully registered new password : " + User.password);
        }
        else{
            System.out.println("Registration canceled");

        }
    }

}
