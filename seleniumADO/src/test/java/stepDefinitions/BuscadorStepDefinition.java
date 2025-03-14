package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.*;
import utils.TestContextSetup;

public class BuscadorStepDefinition {
    public WebDriver driver;
    TestContextSetup testContextSetup;
    public HomePage homePage;
    private String textoBuscado; // Variable para almacenar el número ingresado

    public BuscadorStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.homePage = testContextSetup.pageObjectManager.getHomePage();
    }

    @Given("El usuario abre la pagina")
    public void elUsuarioAbreLaPagina() {
        this.testContextSetup.addTextToReport("El usuario accedió al portal");
    }

    @When("Ingresa un caracter númerico {int}")
    public void ingresaUnCaracterNumerico(Integer numero) throws InterruptedException {
        homePage.aceptarCookies();
        homePage.setBuscador(numero.toString());
        this.textoBuscado = numero.toString(); // Guardamos el valor ingresado
    }

    @Then("Se muestra resultado coincidente con la busqueda")
    public void seMuestraResultadoCoincidenteConLaBusqueda() {
        int intentosMax = 2;
        boolean resultado = homePage.validarTextoAutocompletado(textoBuscado, intentosMax);
        Assert.assertTrue(resultado, "El texto sugerido no coincide con la búsqueda");
    }

    @When("Ingresar un número como primer carácter {string}")
    public void ingresarUnNúmeroComoPrimerCarácterNumero(String numero) throws InterruptedException {
        homePage.aceptarCookies();
        homePage.setBuscador(numero);
        this.textoBuscado = numero; // Guardamos el valor ingresado
    }
}