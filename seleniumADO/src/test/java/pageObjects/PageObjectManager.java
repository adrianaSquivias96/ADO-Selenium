package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    public WebDriver driver;

    public HomePage homePage;


    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }// Se crea el objeto de cada PageObject con el driver correspondiente al hilo definido en la clase Test Context Setup



    public HomePage getHomePage(){
        homePage = new HomePage(driver);
        return homePage;
    }


}
