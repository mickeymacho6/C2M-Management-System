package C2MProto;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ShareButton;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import javax.swing.*;
import java.io.IOException;

import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.scene.TextPainter;
import com.codename1.ui.plaf.Style;


public class C2M extends Form {

    private UiDrawer UI;
    Image buttonImage;

    public C2M() {

        try{
            buttonImage = Image.createImage("/optionButton.png");

        }catch (IOException e) {e.printStackTrace();}



        UI = new UiDrawer();
        Button inventoryButton = new Button(buttonImage);
        Button packageButton = new Button(buttonImage);
        Button transactionButton = new Button(buttonImage);

        Container buttons = new Container();
        buttons.add(inventoryButton);
        buttons.add(packageButton);
        buttons.add(transactionButton);


        //Placeholder messages for clicking option tabs. When homepage is linked to other pages this would be changed to open the selected page.
        ActionListener selectInventory = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent evt) {
                InteractionDialog promp = new InteractionDialog();
                Container option = new Container(new GridLayout(1, 1));
                promp .setLayout(new BorderLayout());
                promp .add(BorderLayout.CENTER, new Label("Would open another page"));
                Button ok = new Button("ok");
                ok.addActionListener((ee)-> promp .dispose());
                option.add(ok);
                promp.addComponent(BorderLayout.SOUTH, option);
                promp.show(100, 100, 100, 100);
            }
        };

        ActionListener selectPackage = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent evt) {
                InteractionDialog promp = new InteractionDialog();
                Container option = new Container(new GridLayout(1, 1));
                promp .setLayout(new BorderLayout());
                promp .add(BorderLayout.CENTER, new Label("Would open another page"));
                Button ok = new Button("ok");
                ok.addActionListener((ee)-> promp .dispose());
                option.add(ok);
                promp.addComponent(BorderLayout.SOUTH, option);
                promp.show(100, 100, 100, 100);
            }
        };

        ActionListener selectTransaction = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent evt) {
                InteractionDialog promp = new InteractionDialog();
                Container option = new Container(new GridLayout(1, 1));
                promp .setLayout(new BorderLayout());
                promp .add(BorderLayout.CENTER, new Label("Would open another page"));
                Button ok = new Button("ok");
                ok.addActionListener((ee)-> promp .dispose());
                option.add(ok);
                promp.addComponent(BorderLayout.SOUTH, option);
                promp.show(100, 100, 100, 100);
            }
        };

        inventoryButton.addActionListener(selectInventory);
        packageButton.addActionListener(selectPackage);
        transactionButton.addActionListener(selectTransaction);

        Form display = new Form("C2M UI Prototype", new BorderLayout());
        display.add(BorderLayout.CENTER,LayeredLayout.encloseIn(UI, FlowLayout.encloseCenterBottom(buttons)));
        display.show();
        
    }


}
