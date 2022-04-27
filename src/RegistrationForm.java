import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.JOptionPane.showInternalMessageDialog;

public class RegistrationForm extends JDialog{
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textConfirmEmail;
    private JTextField textUsername;
    private JPasswordField passwordField1;
    private JPasswordField passwordConfirm;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel RegisterPanel;
    private JTextField txtSecurityQuestion;
    private JTextField txtSecurityAnswer;

    //contracture
    public RegistrationForm(JDialog parent)
    {
        super(parent);
        setTitle("Create a new account");
        setContentPane(RegisterPanel);
        setMinimumSize(new Dimension(430,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //create a registered new user
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerUser();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //Closing the application
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true );

    }
    //implement the register user
    private void registerUser() throws SQLException {
        String name = textName.getText();
        String email = textEmail.getText();
        String confirmEmail = textConfirmEmail.getText();
        String username = textUsername.getText();
        String password = String.valueOf(passwordField1.getPassword());
        String confirm_password = String.valueOf(passwordConfirm.getPassword());
        String securityQuestion = txtSecurityQuestion.getText();
        String securityAnswer = txtSecurityAnswer.getText();

        //If filed is empty display the Error message
        if (name.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() ||  username.isEmpty()
                || password.isEmpty() || securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
            showInternalMessageDialog( RegistrationForm.this,
                    "Please fill all the blank field","Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirm_password))
        {
            JOptionPane.showMessageDialog(this,
                    "Confirmed password does not match","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Adding new user to database
        User = addUserToDatabase(name,email,confirmEmail,username,password,confirm_password,securityQuestion , securityAnswer );
        if (User != null) {
            dispose();
        } else JOptionPane.showInternalMessageDialog(this, "Please fill all the blank", "Try again",
                JOptionPane.ERROR_MESSAGE);
    }
    //global variable
    public User User;
    //returning valid user object
    private User addUserToDatabase(String name, String email, String confirmEmail, String username,
                                   String password, String confirm_password , String securityQuestion , String securityAnswer ) throws SQLException {
        User User = null;
        final String DB_URL;
        DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
        final String USERNAME = "greenhornetsadmin";
        final String PASSWORD = "GreenHornetsUp!";
        Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        System.out.println("Connected to SQL Server");
        Statement stmt = conn.createStatement();

        try{

            String sql = "INSERT INTO dbo.users (name, email, confirmEmail,username, password,confirm_password ,securityQuestion,securityAnswer ) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,confirmEmail);
            preparedStatement.setString(4,username);
            preparedStatement.setString(5,password);
            preparedStatement.setString(6,confirm_password);
            preparedStatement.setString(7,securityQuestion);
            preparedStatement.setString(8,securityAnswer);



            //Insert row into the table and execute the quarry
            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0)
            {
                User = new User();
                User.name = name;
                User.email = email;
                User.confirmEmail = confirmEmail;
                User.username = username;
                User.password = password;
                User.confirm_password = confirm_password;
                User.securityQuestion = securityQuestion;
                User.securityAnswer = securityAnswer;

            }
            //closing connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return User;
    }

    //Main method
    public static void main(String[] args)
    {   //object of registration form
        RegistrationForm my_form = new RegistrationForm(null);
        User User = my_form.User;
        if (User != null)
        {
            System.out.println("successfully registered :            " + User.name);
            System.out.println("                    email:          " + User.email);
            System.out.println(" your security question: " + User.securityQuestion);
            System.out.println("    your security answer : " + User.securityAnswer);
        }
        else{
            System.out.println("Registration canceled");

        }
    }
}
