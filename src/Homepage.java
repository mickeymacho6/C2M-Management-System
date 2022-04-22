package C2M;

import InventoryPage.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


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
    private JLabel Zone1;
    private JLabel Zone2;
    private JLabel zone3;
    private JLabel zone4;
    private JLabel zone5;
    private JLabel time1;
    private JLabel time2;
    private JLabel time4;
    private JLabel time3;
    private JLabel time5;
    private JLabel displayCurrency;

    public Homepage() throws Exception
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
        Image button5 = new ImageIcon(this.getClass().getResource("/logOut.png")).getImage();

        logoLabel.setIcon(new ImageIcon(logo));
        inventoryManagementButton.setIcon(new ImageIcon(button1));
        packageInformationButton.setIcon(new ImageIcon(button2));
        transactionLogButton.setIcon(new ImageIcon(button3));
        logOut.setIcon(new ImageIcon(button5));
        logoLabel.setSize(150,100);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE. MMM d, yyyy");
        String currentDate = sdf.format(new Date());
        date.setText(currentDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        TimeZone PHT = TimeZone.getTimeZone("Asia/Manila");
        TimeZone PST = TimeZone.getTimeZone("PST8PDT");
        TimeZone MST = TimeZone.getTimeZone("MST7MDT");
        TimeZone CST = TimeZone.getTimeZone("CST6CDT");
        TimeZone EST = TimeZone.getTimeZone("EST5EDT");

        URL url = new URL("http://data.fixer.io/api/latest?access_key=cad2cf17e01c03d9f3226162e57e569d");
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        result.toString();
        String accessVal = result.substring(result.indexOf("\"success\":")+10);
        accessVal = accessVal.substring(0, 4);
        if(accessVal.equals("true")){
            String USDValue = result.substring(result.indexOf("\"USD\":") + 6), PHPValue = result.substring(result.indexOf("\"PHP\":") + 6);
            USDValue = USDValue.substring(0, 5);
            PHPValue = PHPValue.substring(0, 5);
            float exchangeValue = Float.parseFloat(PHPValue) / Float.parseFloat(USDValue);
            DecimalFormat decimal2nd = new DecimalFormat("0.00");
            displayCurrency.setText("1 USD = " + decimal2nd.format(exchangeValue) + " PHP");
        }
        else{
            displayCurrency.setText("Cannot display current USD to PHP exchange rate at this time");
        }

        inventoryManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View inventory = new View("Inventory");
                inventory.setVisible(true);
                dispose();
            }
        });

        packageInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               PackageSearch packageInfo = new PackageSearch();
               packageInfo.setVisible(true);
               dispose();
            }
        });

        transactionLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransactionMain transactionLog = new TransactionMain();
                String[] runTransactionLog = {};
                transactionLog.main(runTransactionLog);
                dispose();
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        do {
            Date currentTime = new Date();
            timeFormat.setTimeZone(PHT);
            time1.setText(timeFormat.format(currentTime));
            timeFormat.setTimeZone(PST);
            time2.setText(timeFormat.format(currentTime));
            timeFormat.setTimeZone(MST);
            time3.setText(timeFormat.format(currentTime));
            timeFormat.setTimeZone(CST);
            time4.setText(timeFormat.format(currentTime));
            timeFormat.setTimeZone(EST);
            time5.setText(timeFormat.format(currentTime));
        }while(true);
    }
}
