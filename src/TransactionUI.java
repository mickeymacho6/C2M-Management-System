import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Myles Eastman
 * @version 3-13-2022
 */
public class TransactionUI {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTable table;
    private JButton addButton;
    private JPanel bottom;
    private JPanel tablePanel;
    private JPanel headerPanel;
    private JButton enterButton;
    private JLabel latestTransaction;
    private Object[][] data;
    private Object[] header = {"Order#", "Date Ordered", "Date Delivered", "Product Name", "Seller", "Price"};

    public TransactionUI() {
        createTable();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void createTable() {

        table.setFillsViewportHeight(true);

        data = new Object[][]{
                {"1", "10/10/20", "11/11/20", "Babe Ruth", "Myles Eastman", "100.00"},
                {"2", "10/11/20", "11/09/20", "Jackie Robinson", "Yuji Naka", "200.00"},
                {"3", "10/14/20", "11/11/20", "Mike Trout", "John Kirby", "300.00"},
                {"4", "10/16/20", "11/12/20", "Hank Aaron", "Lyn Cell", "400.00"},
                {"5", "10/20/20", "11/30/20", "Ty Cobb", "Chris Rock", "500.00"}
        };

        //table.setModel(new DefaultTableModel(data, header));

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        table.setModel(model = new DefaultTableModel(data, header) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        table.getColumnModel().getColumn(0).setPreferredWidth(63);
        table.getColumnModel().getColumn(1).setPreferredWidth(86);
        table.getColumnModel().getColumn(2).setPreferredWidth(98);
        table.getColumnModel().getColumn(3).setPreferredWidth(89);
        table.getColumnModel().getColumn(4).setPreferredWidth(54);
        table.getColumnModel().getColumn(5).setPreferredWidth(52);

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


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel temp = (DefaultTableModel) table.getModel();
                temp.addRow(data[4]);
                latestTransaction.setText(dataToString(data[4]));

            }
        });




    }// end of createTable

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


