package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestBase {

    public WebDriver driver;

    public WebDriver WebDriverManager() throws IOException {
        String url = "";
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//seleniumADO//src//test//resources//global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String env = prop.getProperty("env");

        // Usamos FileManager para obtener las credenciales del archivo
        FileManager fileManager = new FileManager();
        String[] credentials = fileManager.getCredentials();
        String username = credentials[0];  // username desde Folio.txt
        String password = credentials[1];  // password desde Folio.txt

        if (env.contains("Drp")) {
            url = prop.getProperty("DrpUrl");
        } else if (env.contains("QA")) {
            url = prop.getProperty("QAUrl");
        } else {
            url = prop.getProperty("QAUrl");
        }

        String browser_properties = prop.getProperty("browser");
        String browser_maven = System.getProperty("browser");
        Boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
        Boolean max = Boolean.parseBoolean(prop.getProperty("max"));
        int implicit = Integer.parseInt(prop.getProperty("implicitWait"));

        String browser = browser_maven != null ? browser_maven : browser_properties;

        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.setAcceptInsecureCerts(true);

                if (headless) {
                    options.addArguments("--headless=new");
                    driver = new ChromeDriver(options);
                }
                driver = new ChromeDriver(options);  // Chrome driver

            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(options);

            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.setAcceptInsecureCerts(true);
                if (headless) {
                    options.addArguments("--headless=new");
                }
                driver = new EdgeDriver(options);
            }
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicit));
            driver.get("https://" + username + ":" + password + "@" + url);  // URL con las credenciales
        }
        if (max) driver.manage().window().maximize();
        return driver;
    }
}