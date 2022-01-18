package com.mediaorganizer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Settings extends JFrame implements ActionListener {

    ImageIcon iconImage;

    Settings(){

        iconImage = new ImageIcon("src\\img\\settings.png");


        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setTitle("About");
        this.setIconImage(iconImage.getImage());
        this.setResizable(false);
        this.setSize(350,200);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
