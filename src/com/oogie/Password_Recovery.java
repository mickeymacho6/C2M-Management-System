package com.oogie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Object;

public class Password_Recovery extends JFrame {
    private JTextField enterCodeHereTextField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel codeSentLabel;
    private JLabel incorrectCodeLabel;
    private JLabel imageLabel;

    public Password_Recovery() {
        setContentPane(mainPanel);
        setTitle("Account Recovery");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        incorrectCodeLabel.setVisible(false);
        ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("card2cart_logo.jpg")));
        Image image = imageIcon.getImage();
        Image modifyImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon nuImageIcon = new ImageIcon(modifyImage);
        imageLabel.setIcon(nuImageIcon);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incorrectCodeLabel.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Password_Recovery passwordRecovery = new Password_Recovery();
    }
}
