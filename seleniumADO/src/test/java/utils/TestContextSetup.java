package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectManager;

public class TestContextSetup {

	public WebDriver driver;
	public String landingPageProductName;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtils genericUtils;

	public String reportText;
	public boolean addScreenShot = false;

	
	public TestContextSetup() throws IOException
	{
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.WebDriverManager()); //Genera el driver y se asigna al pageobject manager
		genericUtils = new GenericUtils(testBase.WebDriverManager());

	}

	public void initReportVariables(){
		this.reportText = "";
		this.addScreenShot = false;
	}

	public void addTextToReport(String text){
		this.reportText += "<i>"+text + "</i></br>";
		System.out.println(text);
	}

	public void takeSS(){
		this.addScreenShot = true;
	}
	
}
