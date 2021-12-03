package C2MProto;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import java.io.IOException;

import com.codename1.ui.Component;
import com.codename1.ui.geom.Rectangle;


public class UiDrawer extends Component {

    private int gold = ColorUtil.rgb(182, 149, 11);
    Image feature1, feature2, feature3, logo, banner, bannerButtons;

    //Placeholder strings for information banner. When homepage is linked to other pages this will be replace by
    // String = pageName.getInformationX();
    String time = "Philippines     Pacific      Mountain      Central        Eastern",
            time2="  2:12PM        10:12PM     11:12PM      12:12AM     1:12AM", currency = "1 USD = 50.40 PHP", lastTransaction = "Last Transaction: Order #123019    Shipping Status: Shipped    ", lastTransaction2 =
            "Tracking Number: PO569037832012293     Estimated Delivery: Feb 14th, 2022";
    String date = "Sunday November 28th, 2021";
    public UiDrawer(){
        try{
            feature1 = Image.createImage("/inventory.png");
            feature2 = Image.createImage("/package.png");
            feature3 = Image.createImage("/translog.png");
            logo = Image.createImage("/logo.png");
            banner = Image.createImage("/blankbanner.png");
            bannerButtons = Image.createImage("/bannerbuttons.png");
        }catch (IOException e) {e.printStackTrace();}



    }
//Current version of paint creates the images scaled and placed for 720p browser windows.
//Next step will have paint check for the resolution of the window and scale/place the UI elements appropriately
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(gold);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.drawImage(feature1, 210, 570);
        g.drawImage(feature2, 510, 570);
        g.drawImage(feature3, 810, 570);
        g.drawImage(bannerButtons, 0 , 70);
        g.drawImage(banner, 0, 0, 1280, 75);
        g.drawImage(logo, 1095, 80);
        g.drawString(time, 60, 10);
        g.drawString(time2, 60, 30);
        g.drawString(currency, 595, 30);
        g.drawString(lastTransaction, 820, 10);
        g.drawString(lastTransaction2, 820, 45);
        g.setColor(ColorUtil.LTGRAY);
        g.drawString(date, 1095, 260);



    }



}
