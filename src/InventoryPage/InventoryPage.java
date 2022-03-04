package InventoryPage;

import javax.swing.*;
import java.awt.*;

public class InventoryPage extends JFrame {
    private JPanel panel1;
    private JButton previousPageButton;
    private JButton nextPageButton;
    private JCheckBox selectCheckBox;
    private JRadioButton inStockRadioButton;

    private JFrame frame;

    public InventoryPage(){
        frame.setBackground(Color. YELLOW);
        frame = new JFrame("Inventory Page");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(2000,2000));
        frame.setResizable(true);

        frame.add(panel1);
        frame.setVisible(true);
    }
}
