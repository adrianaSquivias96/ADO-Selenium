package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String env = prop.getProperty("env");

        String username = "";
        String password = "";

        if(env.contains("Drp")){
            url = prop.getProperty("DrpUrl");
            username = "customer";
            password = "VnUyYSZoS9smK77RDXTknvTKdzgbi8";
        } else if(env.contains("QA")){
            url = prop.getProperty("QAUrl");
            username = "customer";
            password = "VnUyYSZoS9smK77RDXTknvTKdzgbi8";
        }else{
            url = prop.getProperty("QAUrl");
            username = "customer";
            password = "VnUyYSZoS9smK77RDXTknvTKdzgbi8";
        }
        String browser_properties = prop.getProperty("browser");
        String browser_maven = System.getProperty("browser");
        Boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
        Boolean max = Boolean.parseBoolean(prop.getProperty("max"));
        int implicit = Integer.parseInt(prop.getProperty("implicitWait"));
        // result = testCondition ? value1 : value2

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
               // options.setHeadless(headless);
                driver = new ChromeDriver(options);// driver gets the life
            }
            if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                //options.addArguments("--remote-allow-origins=*");
                options.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(options);
            }

            if(browser.equalsIgnoreCase("edge")){
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.setAcceptInsecureCerts(true);
                if (headless) {
                    options.addArguments("--headless=new");
                }
               // options.setHeadless(headless);
                driver = new EdgeDriver(options);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
            driver.get("https://" + username + ":" + password + "@" + url);


        }
        if (max) driver.manage().window().maximize();

        return driver;

    }
}

