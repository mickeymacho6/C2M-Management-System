import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * @author Myles Eastman
 * @version 3-13-2022
 */
public class TransactionUI {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTable table;
    private JButton nextButton;
    private JButton previousButton;
    private JPanel bottom;
    private JPanel tablePanel;
    private JPanel headerPanel;
    private Object[][] data;
    private Object[] header = {"Order#", "Date Ordered", "Date Delivered", "Product Name", "Seller", "Price"};


    public TransactionUI() {
        createTable();
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void createTable()
    {

        //table = new JTable();
        table.setFillsViewportHeight(true);
        //.setFont(new Font("Tahoma", Font.PLAIN, 20));



        data = new Object[][]{
                {"1", "10/10/20", "11/11/20", "Box3", "Me8", "100.00"},
                {"2", "10/10/20", "11/11/20", "Box2", "Me6", "200.00"},
                {"3", "10/10/20", "11/11/20", "Box5", "Me4", "300.00"},
                {"4", "10/10/20", "11/11/20", "Box6", "Me10", "400.00"},
                {"5", "10/10/20", "11/11/20", "Box8", "Me9", "500.00"}
        };

        table.setModel(new DefaultTableModel(data, header));

        //TableColumnModel columns = table.getColumnModel();
        //columns.getColumn();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        //data[1] = add(123, "10/10/20", "11/11/20", "Box", "Me", 200.00);
        //dataList.add(add(123, "10/10/20", "11/11/20", "Box", "Me", 200.00));
        table.setModel(model = new DefaultTableModel(data, header)
        {
            Class[] columnTypes = new Class[] {
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


    }

    private void textSearch()
    {

    }
}
