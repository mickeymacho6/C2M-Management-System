package C2M;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

public class Homepage extends JFrame {
    private JPanel HomepageForm;
    private JPanel Banner;
    private JPanel LastTransaction;
    private JPanel Currency;
    private JButton inventoryManagementButton;
    private JButton packageInformationButton;
    private JButton transactionLogButton;
    private JPanel Footer;
    private JLabel logoLabel;
    private JLabel date;
    private JPanel center;
    private JButton logOut;
    private JButton accountOptions;

    public Homepage()
    {
        setContentPane(HomepageForm);
        setTitle("C2M");
        setSize(1525, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
        Image button1 = new ImageIcon(this.getClass().getResource("/inventory.png")).getImage();
        Image button2 = new ImageIcon(this.getClass().getResource("/package.png")).getImage();
        Image button3 = new ImageIcon(this.getClass().getResource("/translog.png")).getImage();
        Image button4 = new ImageIcon(this.getClass().getResource("/accountOptions.png")).getImage();
        Image button5 = new ImageIcon(this.getClass().getResource("/logOut.png")).getImage();

        logoLabel.setIcon(new ImageIcon(logo));
        inventoryManagementButton.setIcon(new ImageIcon(button1));
        packageInformationButton.setIcon(new ImageIcon(button2));
        transactionLogButton.setIcon(new ImageIcon(button3));
        accountOptions.setIcon(new ImageIcon(button4));
        logOut.setIcon(new ImageIcon(button5));
        logoLabel.setSize(150,100);

        //To do: getBannerInformation();
        //To do: JLabel.setText(); for all JLabels in the banner

        accountOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Would have opened another page");
                //To do: openAccountOptions();
            }
        });

        inventoryManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Would have opened another page");
                //To do: openInventoryManagement();
            }
        });

        packageInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Would have opened another page");
                //To do: openPackageInformation();
            }
        });

        transactionLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Would have opened another page");
                //To do: openTransactionLog();
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Would have opened another page");
                //To do: logOut();
            }
        });
    }
}
