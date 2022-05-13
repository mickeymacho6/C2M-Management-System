import javax.swing.*;


/**
 * Transaction with customers
 * @author Myles Eastman
 * @version 3-13-2022
 */
public class TransactionMain {

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI() {
        TransactionUI ui = new TransactionUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setSize(840,625);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
