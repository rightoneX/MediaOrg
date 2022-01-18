package com.mediaorganizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class About extends JFrame implements ActionListener {

    JButton okButton;
    JLabel appLable;
    JLabel revLable;
    JLabel jvLable;
    JLabel vendorLable;
    JLabel contactLable;
    JTextArea aboutText;
    ImageIcon imageFile;
    JLabel imgLabel;
    ImageIcon iconImage;

    About(){

        okButton = new JButton("Ok");
        appLable = new JLabel("Media Organizer");
        revLable = new JLabel("revision 0.2112, built on 15.12.2021");
        jvLable = new JLabel("java version " +System.getProperty("java.runtime.version"));
        vendorLable = new JLabel("AUCO Ltd");
        contactLable = new JLabel("https://auco.co.nz");
        aboutText = new JTextArea(" program description program description progra\n " +
                                                    "program description program description progra\n " +
                                                    "program description program description progra" );
        imageFile = new ImageIcon("src\\img\\logo.png");
        imgLabel = new JLabel(imageFile);
        iconImage = new ImageIcon("src\\img\\icon.png");

        appLable.setVisible(true);
        appLable.setBounds(125,5, 150,20);

        revLable.setVisible(true);
        revLable.setBounds(80,25, 250,20);

        jvLable.setVisible(true);
        jvLable.setBounds(80,40, 250,20);

        vendorLable.setVisible(true);
        vendorLable.setBounds(80,55, 250,20);

        contactLable.setVisible(true);
        contactLable.setBounds(80,70, 250,20);

        imgLabel.setVisible(true);
        imgLabel.setBounds(20,20, 50,60);

        aboutText.setVisible(true);
        aboutText.setBounds(40,90, 270,50);
        aboutText.setEditable(false);
        aboutText.setBackground(new Color(0,0,0,0));

        okButton.setVisible(true);
        okButton.setBounds(135,145, 80,20);
        okButton.addActionListener(this);

        this.add(okButton);
        this.add(appLable);
        this.add(revLable);
        this.add(jvLable);
        this.add(vendorLable);
        this.add(contactLable);
        this.add(aboutText);
        this.add(imgLabel);

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
        if(e.getSource() == okButton){
            super.dispose();
        }
    }
}
