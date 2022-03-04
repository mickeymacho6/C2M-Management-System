import java.awt.Color;
import java.awt.Container;

public class login {
    public static void main(String args[] )
    {

        //Creating a frame
        loginclass frame = new loginclass();
        //seetting frame visibilaty
        frame.setTitle("Card2Cart");
        frame.setVisible(true);
        frame.setSize(100,100);
        frame.setDefaultCloseOperation(loginclass.EXIT_ON_CLOSE);
        frame.setLocation(200,200);
        //Background color
        Container c=frame.getContentPane();
        c.setBackground(Color.ORANGE);
        frame.setResizable(true);

    }
}
