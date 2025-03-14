
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public String resourcesPath = System.getProperty("user.dir") + "\\src\\test\\resources\\";

    public String readFromFile(String filePath){
        String content = new String();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                content += data;
            }
            myReader.close();
            return content;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
    public String writeInFile(String fileName, String content){
        //Revisar si el archivo existe si no crearlo
        String filePath = createFile(fileName);

        if(filePath == null) return null;

        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            return filePath;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public String createFile(String fileName){
        try {
            String filePath = resourcesPath + fileName;
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists. " + filePath);
            }
            return filePath;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
}

