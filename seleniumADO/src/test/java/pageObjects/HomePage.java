package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public WebDriver driver;
    private By buscador = By.id("buscadorHome");
    private By bannerCookies = By.id("cookiescript_injected");
    private By acceptCookiesButton = By.id("cookiescript_accept");
    private By autocompleteList = By.id("ui-id-1");
    private By firstSuggestion = By.xpath("//ul[@id='ui-id-1']/li[1]//a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setBuscador(String texto) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        int intentos = 0;
        int maxIntentos = 2;
        while (intentos < maxIntentos) {
            try {
                WebElement buscadorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(buscador));

                wait.until(ExpectedConditions.elementToBeClickable(buscador));
                buscadorElement.clear();
                buscadorElement.sendKeys(texto);
                return;
            } catch (StaleElementReferenceException e) {
                intentos++;
                System.out.println("StaleElementReferenceException: El elemento del buscador ya no es válido. Reintentando... intento " + intentos);
            }
        }
        throw new RuntimeException("No se pudo interactuar con el buscador después de " + maxIntentos + " intentos debido a un StaleElementReferenceException.");
    }

    public void aceptarCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement banner = wait.until(ExpectedConditions.presenceOfElementLocated(bannerCookies));
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
            acceptButton.click();
            System.out.println("Banner de cookies aceptado.");
        } catch (Exception e) {
            System.out.println("No se encontró el banner de cookies o no se pudo aceptar.");
        }
    }

    public boolean validarTextoAutocompletado(String textoBuscado, int reintentos) {
        int intentos = 0;
        while (intentos < reintentos) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
                WebElement firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstSuggestion));
                String textoSugerido = firstItem.getText();
                return textoSugerido.contains(textoBuscado);
            } catch (TimeoutException | NoSuchElementException e) {
                intentos++;
                if (intentos < reintentos) {
                    System.out.println("Reintentando... intento " + intentos);
                    try {
                        setBuscador(textoBuscado);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                } else {
                    System.out.println("No se encontró la sugerencia después de " + reintentos + " intentos.");
                    return false;
                }
            }
        }
        return false;
    }
}