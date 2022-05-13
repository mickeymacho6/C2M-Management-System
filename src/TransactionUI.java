import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Myles Eastman
 * @version 3-13-2022
 */
public class TransactionUI extends JFrame{
    private JPanel rootPanel;
    private JTextField textField1;
    private JTable table;
    private JButton updateButton;
    private JPanel bottom;
    private JPanel tablePanel;
    private JPanel headerPanel;
    private JButton enterButton;
    private JLabel latestTransaction;
    private JButton transactionLogButton;
    private JButton homepageButton;
    private JButton clearButton;
    //private List<Object> row;
    private ArrayList<Object[]> rows = new ArrayList<>();
    private Object[][] data;
    private Object[] header = {"Order#", "Tracking#", "Date Ordered", "Shipped", "Product Name", "Seller", "Price"};
    private TransactionUI ui;
    private String sql = "";
    private Connection connection;

    public TransactionUI() {


        //database connection
        final String DB_URL = "jdbc:sqlserver://greenhornetscard2manage.database.windows.net:1433;database=Green Hornets Card 2 Manage;encrypt=true;trustServerCertificate=true;";
        final String USERNAME = "greenhornetsadmin";
        final String PASSWORD = "GreenHornetsUp!";
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to SQL Server");
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Not connected yet");
        }



        createTable();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }


    public void createTable() {

        table.setFillsViewportHeight(true);


        DefaultTableModel model = new DefaultTableModel();


        data = new Object[][]{
                {"1", "54378", "10/10/20", "11/11/20", "Babe Ruth", "Myles Eastman", "100.00"},
                {"2", "37809", "10/11/20", "11/09/20", "Jackie Robinson", "Yuji Naka", "200.00"}
                //{"3", "54290", "10/14/20", "11/11/20", "Mike Trout", "John Kirby", "300.00"},
                //{"4", "54324", "10/16/20", "11/12/20", "Hank Aaron", "Lyn Cell", "400.00"},
                //{"5", "65306", "10/20/20", "11/30/20", "Ty Cobb", "Chris Rock", "500.00"}
        };


        model.setColumnIdentifiers(header);

        table.setModel(model = new DefaultTableModel(data, header) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setPreferredWidth(45);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(35);


        updateTable(model);

        // search results appear after hitting the enter key
        /*textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelThree = (DefaultTableModel) table.getModel();
                TableRowSorter<DefaultTableModel> table_row_three = new TableRowSorter<DefaultTableModel>(modelThree);
                table.setRowSorter(table_row_three);
                table_row_three.setRowFilter(RowFilter.regexFilter(textField1.getText().toLowerCase().trim()));
            }
        });*/

        // search results appear after pressing the enter button on the form
        /*enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelThree = (DefaultTableModel) table.getModel();
                TableRowSorter<DefaultTableModel> table_row_three = new TableRowSorter<DefaultTableModel>(modelThree);
                table.setRowSorter(table_row_three);
                table_row_three.setRowFilter(RowFilter.regexFilter(textField1.getText().toLowerCase().trim()));
            }
        });*/

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                DefaultTableModel modelThree = (DefaultTableModel) table.getModel();
                TableRowSorter<DefaultTableModel> table_row_three = new TableRowSorter<DefaultTableModel>(modelThree);
                table.setRowSorter(table_row_three);
                table_row_three.setRowFilter(RowFilter.regexFilter(textField1.getText().trim()));
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel temp = (DefaultTableModel) table.getModel();
                updateTable(temp);
            }
        });
        transactionLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open transaction log page

                transactionLogPage transactionLog = new transactionLogPage();
                transactionLog.setVisible(true);
                dispose();
            }
        });

        homepageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open homepage
                try {
                    Homepage goBackHomepage = new Homepage(null);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel temp = (DefaultTableModel) table.getModel();
                clearTable(temp);
            }
        });


    }// end of createTable


    private void updateTable(DefaultTableModel t)
    {
        //DefaultTableModel temp = (DefaultTableModel) table.getModel();
        Object[] row = new Object[7];
        int i = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM dbo.Transactions");
            while (res.next()) {
                String order = res.getString("Order#");
                order = order.replaceAll("\\s", "");

                String tracking = res.getString("TrackingNum");
                tracking = tracking.replaceAll("\\s", "");

                String date = res.getString("DateOrdered");
                date = date.replaceAll("\\s", "");

                String shipped = res.getString("Shipped");
                shipped = shipped.replaceAll("\\s", "");


                String card = res.getString("CardName");
                card = card.replaceAll("\\s", "");


                String seller = res.getString("SellerName");
                seller = seller.replaceAll("\\s", "");

                String cost = res.getString("Cost");
                cost = cost.replaceAll("\\s", "");
                if(data.length == i)
                {
                    data = insertRow(data, i, new Object[7]);
                }


                data[i][0] = order;
                data[i][1] = tracking;
                data[i][2] = date;
                data[i][3] = shipped;
                data[i][4] = card;
                data[i][5] = seller;
                data[i][6] = cost;
                t.addRow(data[i]);
                //t.addRow(dataTable.get(i).toArray());
                i++;
            }
            latestTransaction.setText(dataToString(data[i - 1]));

            //DefaultTableModel model = new DefaultTableModel();
            t.setColumnIdentifiers(header);
            t.getDataVector().toArray();
            table.setModel(t = new DefaultTableModel(data, header) {
                Class[] columnTypes = new Class[]{
                        String.class, String.class, String.class, String.class, String.class, String.class, String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
            });
        } catch (SQLException ex) {
            System.out.println("didnt work");
        }
    }
    private Object[][] insertRow(Object[][] m, int r, Object[] data) {
        Object[][] out = new Object[m.length + 1][];
        for (int i = 0; i < r; i++) {
            out[i] = m[i];
        }
        out[r] = data;
        for (int i = r + 1; i < out.length; i++) {
            out[i] = m[i - 1];
        }
        return out;
    }

    private void clearTable(DefaultTableModel t)
    {
        t.setColumnIdentifiers(header);
        t.getDataVector().toArray();
        table.setModel(t = new DefaultTableModel(null, header) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
    }

    private String dataToString(Object[] d)
    {
        String s = "";
        for (int i = 0; i < d.length; i++)
        {
            s += header[i] + ": " + d[i];
            if (i != d.length - 1)
                s += " | ";
        }
        return s;
    }
}


