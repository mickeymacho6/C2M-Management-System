import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class PackageSearch extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JComboBox comboBox;
    private JTable table;
    private JButton btn;
    private JScrollPane tableScroll;
    private Image c2cLogo;
    private PackageOrder test1,test2,test3,test4,test5,test6,test7;
    private ArrayList<PackageOrder> master_package_list, keyword_package_list;
    private int total_packages;
    private char[] bannedChars = {'!','#','%','^','&','*','='};

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PackageSearch frame = new PackageSearch();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PackageSearch() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)screenSize.getHeight();
        int screenWidth = (int)screenSize.getWidth();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, screenWidth, screenHeight);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(182, 149, 11));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(10, 68, 209, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        comboBox = new JComboBox();
        comboBox.setMaximumRowCount(2);
        comboBox.setBounds(10, 105, 97, 22);
        comboBox.addItem("Recent");
        comboBox.addItem("Older");

        contentPane.add(comboBox);

        btn = new JButton("Search");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTable(whitelist(textField.getText()));
                tableScroll.setViewportView(table);
            }
        });
        btn.setBounds(229, 70, 89, 23);
        contentPane.add(btn);

        test1=new PackageOrder(0001, 7, "John Johnson", "1234 Real St", "09/12/21",
                "10/13/21", "Delivered", "Baseball Card", 17.17);
        test2=new PackageOrder(0001, 6, "John Johnson", "1234 Real St", "09/12/21",
                "10/13/21", "Delivered", "Basketball Card", 32.18);
        test3=new PackageOrder(0001, 1, "John Johnson", "1234 Real St", "09/12/21",
                "10/13/21", "Delivered", "Basketball Card", 12.09);
        test4=new PackageOrder(0002, 10, "Jim Jackson", "444 four cir", "03/13/21",
                "05/22/21", "Delivered", "Soccer Card", 1.01);
        test5=new PackageOrder(0002, 1, "Jim Jackson", "444 four cir", "03/13/21",
                "05/22/21", "Delivered", "Baseball Card", 99.02);
        test6=new PackageOrder(0003, 1, "Real Personson", "567 longroad blvd", "12/12/21",
                "01/12/22", "Delivered", "Soccer Card", 106.66);
        test7=new PackageOrder(0004, 5, "Real Personson", "567 shortroad blvd", "02/23/22",
                "NULL", "Shipped", "Baseball Card", 44.95);
        master_package_list = new ArrayList<>();
        master_package_list.add(test1);
        master_package_list.add(test2);
        master_package_list.add(test3);
        master_package_list.add(test4);
        master_package_list.add(test5);
        master_package_list.add(test6);
        master_package_list.add(test7);
        total_packages=7;

        tableScroll = new JScrollPane();
        tableScroll.setBounds(10, 138, 900, 800);
        initTable();
        tableScroll.setViewportView(table);
        contentPane.add(tableScroll);



    }
    protected String whitelist(String in){
        String out = "";
        boolean safe = false;
        for(int char_pos=0; char_pos<in.length(); char_pos++){
            for(int banned_char=0; banned_char<7; banned_char++){
                if(!(in.charAt(char_pos)==bannedChars[banned_char])){
                    safe=true;
                }
                else{
                    safe=false;
                    banned_char=7;
                }
            }

            if(safe){
                out = out + in.charAt(char_pos);
            }
        }
        return out;
    }
    public void initTable() {
        String[] columnNames = {"Package ID", "Customer ID", "Destination", "Date of Purchase", "Delivery Date", "Total Price"
                , "Shipping Status", "Item Id", "Quantity", "Sub-Total"};
        String[][] table_data = new String[total_packages][10];
        int rows=0;
        for(PackageOrder package_order : master_package_list) {
            table_data[rows] = package_order.getPackage();
            rows++;
        }
        table = new JTable(table_data, columnNames);
    }
    public void updateTable(String keyword){
        keyword_package_list = new ArrayList<>();
        String[] columnNames = {"Package ID", "Customer ID", "Destination", "Date of Purchase", "Delivery Date", "Total Price"
                , "Shipping Status", "Item Id", "Quantity", "Sub-Total"};
        int results=0;
        for(PackageOrder package_order : master_package_list){
            if(package_order.findKeyword(keyword) || keyword.equals("")){
                keyword_package_list.add(package_order);
                results++;
            }
        }
        String sortParam = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        switch (sortParam) {
            case "Older":
                keyword_package_list=sortByOld(keyword_package_list);
                break;
            case "Recent":
                keyword_package_list=sortByNew(keyword_package_list);
                break;
        }
        String[][] table_data = new String[results][10];
        int rows=0;
        for(PackageOrder package_order : keyword_package_list) {
            table_data[rows] = package_order.getPackage();
            rows++;
        }

        table = new JTable(table_data, columnNames);
    }
    public ArrayList<PackageOrder> sortByOld(ArrayList<PackageOrder> list){
        ArrayList<PackageOrder> tempList = new ArrayList<>();
        PackageOrder tempPackage;
        while(!list.isEmpty()){
            tempPackage = list.get(0);
            for(PackageOrder sub_list : list){
                if(tempPackage.isOlder(sub_list)){
                    tempPackage=sub_list;
                }
            }
            tempList.add(tempPackage);
            list.remove(tempPackage);
        }
        return tempList;
    }

    public ArrayList<PackageOrder> sortByNew(ArrayList<PackageOrder> list){
        ArrayList<PackageOrder> tempList = new ArrayList<>();
        PackageOrder tempPackage;
        while(!list.isEmpty()){
            tempPackage = list.get(0);
            for(PackageOrder sub_list : list){
                if(!tempPackage.isOlder(sub_list)){
                    tempPackage=sub_list;
                }
            }
            tempList.add(tempPackage);
            list.remove(tempPackage);
        }
        return tempList;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Code Graveyard