import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The page for username recovery.
 * @author Harlan Nguyen
 * Date: 03/04/2022
 */
public class Username_Recovery extends JFrame {
    private JTextField insertAnswer1HereTextField;
    private JTextField insertAnswer2HereTextField;
    private JTextField insertAnswer3HereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel question1Label;
    private JLabel insertQuestion1Label;
    private JLabel question2Label;
    private JLabel insertQuestion2Label;
    private JLabel question3Label;
    private JLabel insertQuestion3Label;
    private JLabel incorrectAnswerLabel;
    private JLabel imageLabel;
    private JLabel emailLabel;
    private JTextField enterEmailHereTextField;
    private JButton emailButton;
    private JLabel incorrectEmailLabel;

    /**
     * Creates the username recovery page.
     */
    public Username_Recovery() {
        setContentPane(mainPanel);
        setTitle("Account Recovery");
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        incorrectEmailLabel.setVisible(false);
        question1Label.setVisible(false);
        insertQuestion1Label.setVisible(false);
        insertAnswer1HereTextField.setVisible(false);
        question2Label.setVisible(false);
        insertQuestion2Label.setVisible(false);
        insertAnswer2HereTextField.setVisible(false);
        question3Label.setVisible(false);
        insertQuestion3Label.setVisible(false);
        insertAnswer3HereTextField.setVisible(false);
        incorrectAnswerLabel.setVisible(false);
        submitButton.setVisible(false);
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enterEmailHereTextField.getText().isEmpty()) {
                    emailLabel.setVisible(false);
                    enterEmailHereTextField.setVisible(false);
                    emailButton.setVisible(false);
                    incorrectEmailLabel.setVisible(false);
                    question1Label.setVisible(true);
                    insertQuestion1Label.setVisible(true);
                    insertAnswer1HereTextField.setVisible(true);
                    question2Label.setVisible(true);
                    insertQuestion2Label.setVisible(true);
                    insertAnswer2HereTextField.setVisible(true);
                    question3Label.setVisible(true);
                    insertQuestion3Label.setVisible(true);
                    insertAnswer3HereTextField.setVisible(true);
                    submitButton.setVisible(true);
                } else {
                    incorrectEmailLabel.setVisible(true);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incorrectAnswerLabel.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Username_Recovery accountRecovery = new Username_Recovery();
    }
}
