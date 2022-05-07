

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;




public class loginForm extends JDialog{
    private JPanel icon;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnLogin;
    private JButton btnCreateAccount;
    private JButton btnCancel;
    private JPanel LoginPanel;
    private JButton forgotPasswordButton;

    //constructor
    public loginForm(JFrame parent, String username)
    {
        super(parent);
        setTitle(" Card2Cart Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(400,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        textField1.setText(username);

        // return true if the user has been registered otherwise will go to registration form
        final User[] User = {loginForm.User};
        if(User[0] != null)
        {
            setLocationRelativeTo(null);
            setVisible(true);
        }

        else{
            btnCreateAccount.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    RegistrationForm registrationForm = new RegistrationForm( loginForm.this);
                    User User = registrationForm.User;
                }
            });
            dispose();
        }

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = String.valueOf(passwordField1.getPassword());

                try {
                    User[0] = getAuthenticatedUser(username, password);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (User[0] != null)
                {
                    JOptionPane.showMessageDialog( loginForm.this, "You are successfully logged in ");
                    dispose ();
                    Homepage homepage = null;
                    try {
                        homepage = new Homepage(null);
                    } catch (Exception ex) {
                        ex.printStackTrace ( );
                    }
                    homepage.setVisible(true);

                }

                else{
                    JOptionPane.showMessageDialog( loginForm.this, "Username or password is invalid",
                            "Try again",JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        //setting the cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose ( );

            }
        });
        forgotPasswordButton.addActionListener (new ActionListener ( ) {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ForgotPassword recovery = new ForgotPassword();
                } catch (SQLException ex) {
                    ex.printStackTrace ( );
                }
                dispose ();
            }
        });
        setVisible(true);



    }


    public static User User;
    private User getAuthenticatedUser(String username, String password) throws SQLException {
        User User =null;
        //return User;
        final String DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
        final String USERNAME = "greenhornetsadmin";
        final String PASSWORD = "GreenHornetsUp!";


        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            System.out.println("Connected to SQL Server");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM dbo.users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                User= new User();
                User.name = resultSet.getString("name");
                User.email = resultSet.getString("email");
                User.confirmEmail = resultSet.getString("confirmEmail");
                User.username = resultSet.getString("username");
                User.password = resultSet.getString("password");
                User.confirm_password = resultSet.getString("confirm_password");
                stmt.close();
                conn.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }


        return User;
    }
    public static void main(String[] args)
    {
        loginForm loginForm = new loginForm(null, null);
        User User = loginForm.User;
        if (User != null)
        {
            System.out.println("Successful Authentication of " + User.name);
            System.out.println("                        email " + User.email);
            System.out.println("                        username " + User.username);



        }
        else{
            System.out.println(" Not Authorized ");

        }
    }



    private void actionPerformed(ActionEvent e) {
        textField1.setText(null);
        passwordField1.setText(null);

    }
}
