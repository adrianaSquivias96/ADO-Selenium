package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class FileManager {


    public String resourcesPath = System.getProperty("user.dir") + "\\seleniumADO\\src\\test\\resources\\";


    public String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);  // Imprimir en consola (puedes eliminarlo si no lo necesitas)
                content.append(data).append(System.lineSeparator());
            }
            myReader.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }


    public String writeInFile(String fileName, String content) {

        String filePath = createFile(fileName);
        if (filePath == null) return null;

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

    public String createFile(String fileName) {
        try {
            String filePath = resourcesPath + fileName;
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists: " + filePath);
            }
            return filePath;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }


    public String[] getCredentials() {
        String username = "";
        String password = "";

        try {

            FileInputStream fis = new FileInputStream(resourcesPath + "Folio.txt");
            Properties prop = new Properties();
            prop.load(fis);


            username = prop.getProperty("username");
            password = prop.getProperty("password");

            return new String[] { username, password };
        } catch (IOException e) {
            System.out.println("An error occurred while loading credentials.");
            e.printStackTrace();
            return null;
        }
    }


    public void saveCredentialsToFile(String fileName) {
        String[] credentials = getCredentials();
        if (credentials != null) {
            String content = "username=" + credentials[0] + "\npassword=" + credentials[1];
            writeInFile(fileName, content);
        }
    }
}