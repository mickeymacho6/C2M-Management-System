package C2MProto;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import javax.swing.*;
import java.io.IOException;


public class C2M extends Form {

    private UiDrawer UI;
    public C2M() {


        UI = new UiDrawer();
        Form display = new Form("C2M UI Prototype", new BorderLayout());
        display.add(BorderLayout.CENTER, UI);
        display.show();



    }


}
