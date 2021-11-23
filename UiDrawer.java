package C2MProto;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Image;
import com.codename1.ui.Graphics;
import java.io.IOException;

import com.codename1.ui.Component;

public class UiDrawer extends Component {
    private int gold = ColorUtil.rgb(182, 149, 11);
    Image feature1, feature2, feature3, logo, banner, bannerButtons;

    public UiDrawer(){
        try{
            feature1 = Image.createImage("/inventory.png");
            feature2 = Image.createImage("/package.png");
            feature3 = Image.createImage("/translog.png");
            logo = Image.createImage("/logo.png");
            banner = Image.createImage("/banner.png");
            bannerButtons = Image.createImage("/bannerbuttons.png");
        }catch (IOException e) {e.printStackTrace();}



    }
//Current version of paint creates the images scaled and place for 720p browser windows.
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
        g.drawImage(logo, 1090, 80);

    }
}
