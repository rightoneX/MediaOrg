package com.mediaorganizer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindow implements ActionListener {
    JFrame frame = new JFrame();
    JButton folderButton = new JButton("Source");
    JButton processButton = new JButton("Process");
    JTextField sourceText = new JTextField();
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem menuItem = new JMenuItem("About");

    DialogWindow(){
        menuBar.add(menu);
        menu.add(menuItem);

        folderButton.setBounds(265,50, 90, 30);
        folderButton.setFocusable(false);
        folderButton.addActionListener(this);

        processButton.setBounds(265,200, 90, 30);
        processButton.setFocusable(false);
        processButton.addActionListener(this);

        sourceText.setBounds(10, 50, 250, 30);
        sourceText.setEditable(true);
        sourceText.setText(System.getProperty("user.dir"));

        frame.setJMenuBar(menuBar);
        frame.add(folderButton);
        frame.add(processButton);
        frame.add(sourceText);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getIconImage()
        frame.setResizable(false);
        frame.setSize(375,320);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == folderButton){
//            frame.dispose();
//           open explore window to navigat to the required folder
        }

        if(e.getSource() == processButton){
            frame.dispose();
//           open explore window to navigat to the required folder
        }

    }
}
