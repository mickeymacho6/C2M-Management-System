import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Stack;
import java.sql.*;


public class PackageSearch extends JFrame {

    private Homepage homepage;
    private JPanel contentPane;
    private JTextField searchString, trackID, keywords;
    private JLabel keywordIn, keywordOut, trackingIn;
    private JComboBox comboBox;
    private JTable table;
    private JButton search_btn, reset_btn, reverse_btn, track_btn, back_btn;
    private JScrollPane tableScroll;
    private PackageOrder pbuilder;
    private ArrayList<PackageOrder> master_package_list, keyword_package_list;
    private int total_packages, current_packages;
    private char[] bannedChars = {'!','#','%','^','&','*','='};
    private String keyword_list="";

    final String DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
    final String USERNAME = "greenhornetsadmin";
    final String PASSWORD = "GreenHornetsUp!";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PackageSearch frame = new PackageSearch(null);
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
    public PackageSearch(Homepage hp) throws SQLException, ClassNotFoundException {
        homepage=hp;
        master_package_list = new ArrayList<>();
        total_packages=0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to SQL Server");
            PreparedStatement headerReq = connection.prepareStatement("SELECT * FROM SalesLT.SalesOrderHeader");
            ResultSet headerSet = headerReq.executeQuery();
            while(headerSet.next()){
                int SID = headerSet.getInt("SalesOrderID");
                String CID = headerSet.getString("CustomerID");
                PreparedStatement customerReq = connection.prepareStatement("SELECT * FROM SalesLT.Customer WHERE " +
                        "CustomerID="+CID);
                ResultSet customerSet = customerReq.executeQuery();
                customerSet.next();
                CID = customerSet.getString("FirstName")+" "
                        +customerSet.getString("MiddleName")+" "
                        +customerSet.getString("LastName");
                double total = headerSet.getDouble("TotalDue");
                String AID = headerSet.getString("ShipToAddressID");
                PreparedStatement addressReq = connection.prepareStatement("SELECT * FROM SalesLT.Address WHERE " +
                        "AddressID="+AID);
                ResultSet addressSet = addressReq.executeQuery();
                addressSet.next();
                AID = addressSet.getString("AddressLine1")+", "+
                        addressSet.getString("PostalCode")+", "+
                        addressSet.getString("StateProvince")+", "+
                        addressSet.getString("CountryRegion");
                String OrderDate = headerSet.getString("OrderDate");
                String DueDate = headerSet.getString("DueDate");
                String ShipDate = headerSet.getString("ShipDate");

                String trackID = headerSet.getString("TrackingURL");
                PreparedStatement orderReq = connection.prepareStatement("SELECT * FROM SalesLT.SalesOrderDetail WHERE " +
                        "SalesOrderID="+SID);
                ResultSet orderSet = orderReq.executeQuery();
                while(orderSet.next()){
                    int q = orderSet.getInt("OrderQty");
                    double up = orderSet.getDouble("UnitPrice");
                    String IID = orderSet.getString("ProductID");
                    PreparedStatement productReq = connection.prepareStatement("SELECT * FROM SalesLT.Product WHERE " +
                            "ProductID="+IID);
                    ResultSet productSet = productReq.executeQuery();
                    productSet.next();
                    String pname = productSet.getString("Name");
                    pbuilder = new PackageOrder(SID,CID,total,pname,q,up,
                            AID,OrderDate, DueDate, ShipDate, trackID);
                    master_package_list.add(pbuilder);
                    total_packages++;
                }



            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)screenSize.getHeight();
        int screenWidth = (int)screenSize.getWidth();
        int hBorder = screenHeight/50;
        int wBorder = screenWidth/75;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, screenWidth, screenHeight);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(182, 149, 11));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //container creation
        searchString = new JTextField();
        trackID = new JTextField();
        keywords = new JTextField();
        comboBox = new JComboBox();
        search_btn = new JButton("Search");
        reset_btn = new JButton("Reset Results");
        track_btn = new JButton("Track Package");
        reverse_btn = new JButton("Reverse Results");
        back_btn = new JButton("Homepage");
        tableScroll = new JScrollPane();
        keywordIn = new JLabel("Keyword Search");
        keywordOut = new JLabel("Current Search Terms:");
        trackingIn = new JLabel("Tracking URL Search");

        //button data
        search_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String safe_search = whitelist(searchString.getText());
                updateTable(safe_search,0);
                if(!safe_search.equals("")){
                    keyword_list=keyword_list+"\""+safe_search+"\" ";
                    keywords.setText(keyword_list);
                }

                tableScroll.setViewportView(table);
            }
        });

        reset_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initTable();
                keyword_list="";
                keywords.setText(keyword_list);
                keyword_package_list=master_package_list;
                current_packages=total_packages;
                tableScroll.setViewportView(table);
            }
        });

        track_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                try {
                    Desktop.getDesktop().browse(URI.create(trackID.getText()));
                } catch (IOException ignored) {

                }
            }
        });

        reverse_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTable("",1);
                tableScroll.setViewportView(table);
            }
        });

        back_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getPS().setVisible(false);
                homepage.setVisible(true);
            }
        });

        //container sizing
        searchString.setBounds(wBorder, hBorder*2, wBorder*8, hBorder);
        trackID.setBounds(wBorder*24, hBorder*2, wBorder*8, hBorder);
        keywords.setBounds(wBorder*7, hBorder*6, screenWidth-(wBorder*8), hBorder);
        comboBox.setBounds(wBorder, hBorder*4, wBorder*8, hBorder);
        search_btn.setBounds(wBorder*10, hBorder*2, wBorder*6, hBorder);
        reset_btn.setBounds(wBorder*17, hBorder*2, wBorder*6, hBorder);
        track_btn.setBounds(wBorder*33, hBorder*2, wBorder*6, hBorder);
        reverse_btn.setBounds(wBorder*10, hBorder*4, wBorder*6, hBorder);
        back_btn.setBounds(wBorder*50, hBorder*2, wBorder*6, hBorder);
        tableScroll.setBounds(wBorder, hBorder*8, screenWidth-(wBorder*2), screenHeight-(hBorder*11));
        keywordIn.setBounds(wBorder, hBorder, wBorder*8, hBorder);
        trackingIn.setBounds(wBorder*24, hBorder, wBorder*8, hBorder);
        keywordOut.setBounds(wBorder, hBorder*6, wBorder*6, hBorder);


        //add containers and extra
        contentPane.add(searchString);

        contentPane.add(trackID);

        contentPane.add(keywords);

        comboBox.setMaximumRowCount(4);
        comboBox.addItem("Recent");
        comboBox.addItem("Item Price");
        comboBox.addItem("Total Price");
        comboBox.addItem("Customer A-Z");
        contentPane.add(comboBox);

        contentPane.add(search_btn);

        contentPane.add(reset_btn);

        contentPane.add(track_btn);

        contentPane.add(reverse_btn);

        contentPane.add(back_btn);

        current_packages=total_packages;
        keyword_package_list = master_package_list;

        initTable();
        tableScroll.setViewportView(table);
        contentPane.add(tableScroll);

        contentPane.add(keywordIn);

        contentPane.add(keywordOut);

        contentPane.add(trackingIn);



    }
    public PackageSearch getPS(){
        return this;
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
        String[] columnNames = {"Package ID", "Customer", "Total Price", "Item", "Quantity", "Item Price"
                , "Destination", "Date of Purchase", "Delivery Date", "Shipping Date", "Tracking Link"};
        String[][] table_data = new String[total_packages][11];
        int rows=0;
        for(PackageOrder package_order : master_package_list) {
            table_data[rows] = package_order.getPackage();
            rows++;
        }
        table = new JTable(table_data, columnNames);
    }
    public void updateTable(String keyword, int r){
        ArrayList<PackageOrder> temp_package_list = new ArrayList<>();
        String[] columnNames =  {"Package ID", "Customer", "Total Price", "Item", "Quantity", "Item Price"
                , "Destination", "Date of Purchase", "Delivery Date", "Shipping Date", "Tracking Link"};
        int results=0;
        for(PackageOrder package_order : keyword_package_list){
            if(package_order.findKeyword(keyword) || keyword.equals("")){
                temp_package_list.add(package_order);
                results++;
            }
        }
        current_packages=results;
        keyword_package_list = temp_package_list;
        String sortParam = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        if(r==1){
            keyword_package_list=reverseList(keyword_package_list);
        }
        else{
            switch (sortParam) {
                case "Recent":
                    keyword_package_list=sortByNew(keyword_package_list);
                    break;
                case "Item Price":
                    keyword_package_list=sortByItemPrice(keyword_package_list);
                    break;
                case "Total Price":
                    keyword_package_list=sortByTotal(keyword_package_list);
                    break;
                case "Customer A-Z":
                    keyword_package_list=sortByName(keyword_package_list);
                    break;
            }
        }



        String[][] table_data = new String[results][11];
        int rows=0;
        for(PackageOrder package_order : keyword_package_list) {
            table_data[rows] = package_order.getPackage();
            rows++;
        }

        table = new JTable(table_data, columnNames);
    }
    public ArrayList<PackageOrder> reverseList(ArrayList<PackageOrder> list) {
        ArrayList<PackageOrder> tempList = new ArrayList<>();
        Stack<PackageOrder> reverser = new Stack<>();
        int counter = current_packages;
        for(PackageOrder sub_list : list){
            reverser.push(sub_list);
        }
        while(counter!=0){
            tempList.add(reverser.pop());
            counter--;
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

    public ArrayList<PackageOrder> sortByTotal(ArrayList<PackageOrder> list){
        ArrayList<PackageOrder> tempList = new ArrayList<>();
        PackageOrder tempPackage;
        while(!list.isEmpty()){
            tempPackage = list.get(0);
            for(PackageOrder sub_list : list){
                if(tempPackage.isTotalCheaper(sub_list)){
                    tempPackage=sub_list;
                }
            }
            tempList.add(tempPackage);
            list.remove(tempPackage);
        }
        return tempList;
    }
    public ArrayList<PackageOrder> sortByItemPrice(ArrayList<PackageOrder> list){
        ArrayList<PackageOrder> tempList = new ArrayList<>();
        PackageOrder tempPackage;
        while(!list.isEmpty()){
            tempPackage = list.get(0);
            for(PackageOrder sub_list : list){
                if(tempPackage.isItemCheaper(sub_list)){
                    tempPackage=sub_list;
                }
            }
            tempList.add(tempPackage);
            list.remove(tempPackage);
        }
        return tempList;
    }

    public ArrayList<PackageOrder> sortByName(ArrayList<PackageOrder> list){


        ArrayList<PackageOrder> tempList = new ArrayList<>();
        PackageOrder tempPackage;
        while(!list.isEmpty()){
            tempPackage = list.get(0);
            for(PackageOrder sub_list : list){
                if(tempPackage.getName().compareTo(sub_list.getName()) > 0){
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