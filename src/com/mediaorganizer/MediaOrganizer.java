package com.mediaorganizer;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;


public class MediaOrganizer{

    private static void filesChecking(String filesLocation) {
        String path = "C:\\Foto\\temp";
        String picDir;

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File files : listOfFiles) {
            if (files.isFile()) {
                File fileName = new File(path + "\\" + files.getName());
                //Extracting the taken date
                try {
                    Metadata metadata = ImageMetadataReader.readMetadata(fileName);
                    picDir = picTakenDate(metadata);
                    //Creating Folder picDir
                    File newDir = new File(path + "\\" + picDir);
                    if (!newDir.exists()) {
                        newDir.mkdir();
                        System.out.println("directory " + picDir + " created.");
                    }
                    //Move the file to the directory
                    System.out.println("the file " + fileName + " is moving to " + path + "\\" + picDir);
                    fileMove(fileName, newDir);
                } catch (ImageProcessingException e) {
                    System.out.println("no date taken in the name: " + files.getName());
                    // handle exception
                } catch (IOException e) {
                    // handle exception
                }
            }
        }
    }

    //Getting taken date of the picture
    private static String picTakenDate(Metadata metadata)
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
    private static void fileMove(File fileName, File newDir) {
        if(fileName.renameTo(new File(newDir + "\\" + fileName.getName()))){
            System.out.println("File " + fileName.getName() + " is moved successful!");
        }else{
            System.out.println("File " + fileName.getName() + " is failed to move!");
        }
    }

}
