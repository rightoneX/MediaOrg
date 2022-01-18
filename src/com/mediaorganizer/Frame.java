package com.mediaorganizer;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener {

        JMenuBar menuBar;
        JMenu fileMenu;
        JMenu helpMenu;
        JMenuItem settingsItem;
        JMenuItem exitItem;
        JMenuItem helpItem;
        JMenuItem aboutItem;

        JButton folderButton;
        JButton processButton;
        JTextField folderText;
        JTextArea logText;
        JScrollPane scrollArea;
        ImageIcon iconImage;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

    Frame(){

            folderButton = new JButton("Folder");
            processButton = new JButton("Process");
            folderText = new JTextField();
            logText = new JTextArea();
            scrollArea = new JScrollPane(logText);

            menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
            helpMenu = new JMenu("Help");

            settingsItem = new JMenuItem("Settings");
            exitItem = new JMenuItem("Exit");
            helpItem = new JMenuItem("Help");
            aboutItem = new JMenuItem("About");
            iconImage = new ImageIcon("src\\img\\icon.png");

            menuBar.add(fileMenu);
            menuBar.add(helpMenu);
            fileMenu.add(settingsItem);
            fileMenu.add(exitItem);
            fileMenu.setMnemonic(KeyEvent.VK_F);
            helpMenu.add(helpItem);
            helpMenu.add(aboutItem);
            helpMenu.setMnemonic(KeyEvent.VK_H);

            settingsItem.addActionListener(this);
            settingsItem.setMnemonic(KeyEvent.VK_S);
            exitItem.addActionListener(this);
            exitItem.setMnemonic(KeyEvent.VK_E);
            helpItem.addActionListener(this);
            helpItem.setMnemonic(KeyEvent.VK_H);
            aboutItem.addActionListener(this);
            aboutItem.setMnemonic(KeyEvent.VK_A);

            folderText.setBounds(5, 10, 250, 30);
            folderText.setEditable(true);
            folderText.setText("C:/Foto");

            folderButton.setBounds(265,10, 90, 30);
            folderButton.setFocusable(false);
            folderButton.addActionListener(this);

            scrollArea.setBounds(5,70, 350, 120);
            processButton.setBounds(265,200, 90, 30);
            processButton.setFocusable(false);
            processButton.addActionListener(this);

            this.setJMenuBar(menuBar);
            this.add(folderButton);
            this.add(processButton);
            this.add(folderText);
            this.add(scrollArea);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Media Organizer");
            this.setIconImage(iconImage.getImage());
            this.setResizable(false);
            this.setSize(370,290);
            this.setLayout(new BorderLayout());
            this.setVisible(true);
        }


    public void setLogText(String text)
        {
            logText.append(text + "\n");
        }


    public void folderScan(String sourceFolder, String destinationFolder) {

        String picDir;
        File folder = new File(sourceFolder);
        File[] listOfFiles = folder.listFiles();

        for (File files : listOfFiles) {
            if (files.isFile()) {
                File fileName = new File(sourceFolder + "\\" + files.getName());

                try {  //Extracting the taken date
                    Metadata metadata = ImageMetadataReader.readMetadata(fileName);
                    picDir = picTakenDate(metadata);
                    File newDir = new File(destinationFolder + "\\" + picDir);
                    if (!newDir.exists()) {
                        newDir.mkdir();
                        setLogText("directory " + picDir + " created.");
                    }
                    fileMove(fileName, newDir);  //Move the file to the directory
                    setLogText("the file " + fileName + " is moving to " + destinationFolder + "\\" + picDir);
                } catch (ImageProcessingException e) {
//                    System.out.println("no date set in " + files.getName() + " error >" +e);
                    setLogText("no date set in " + files.getName() + " error >" +e);
//                    frame.logText.setText("no date set in " + files.getName() + " error >" +e);
                    // no data had been  found, check the file name and create folder
                    String dirName = files.getName().substring(0,4) + "." + files.getName().substring(4,6) +
                            "." + files.getName().substring(6,8);
                    File newDir = new File(destinationFolder + "\\" + dirName);
                    if (!newDir.exists()) {
                        newDir.mkdir();
                        setLogText("directory " + dirName + " created.");
                    }
                    fileMove(fileName, newDir );  //Move the file to the directory
                    setLogText("the file " + fileName + " is moving to " + destinationFolder + "\\" + dirName );

                } catch (IOException e) {
                    // handle exception
                }
            }
        }
    }




    //Getting taken date of the picture
    static String picTakenDate(Metadata metadata)
    {
        String picTakenDate = null;
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (tag.getTagName() == "Date/Time Original"){
                    picTakenDate = tag.getDescription();
                    picTakenDate = picTakenDate.substring(0, 10);
                    picTakenDate = picTakenDate.replace(":", ".");
                }
            }
        }
        return picTakenDate;
    }

    //Moving files to correct folder
    public void fileMove(File fileName, File newDir) {
        if(fileName.renameTo(new File(newDir + "\\" + fileName.getName()))){
            setLogText("File " + fileName.getName() + " is moved successful!");
//            System.out.println("File " + fileName.getName() + " is moved successful!");
        }else{
            setLogText("File " + fileName.getName() + " is failed to move!");
//            System.out.println("File " + fileName.getName() + " is failed to move!");
        }
    }



    @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == folderButton){
//            frame.dispose();
//           open explore window to navigat to the required folder
            }

            if(e.getSource() == processButton){
                logText.append("log file " + dtf.format(now) + "\n");
//          set the source folder
                String sourceFolder  = folderText.getText();
//            set the destination folder
                String destinationFolder  = folderText.getText();
                folderScan(sourceFolder, destinationFolder);
            }
            if(e.getSource() == settingsItem){
//               to do
                new Settings();
            }
            if(e.getSource() == exitItem){
                this.dispose();
            }
            if(e.getSource() == helpItem){
//                to do
            }
            if(e.getSource() == aboutItem){
//           About Information
                new About();
            }
        }
}
