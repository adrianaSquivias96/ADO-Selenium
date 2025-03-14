package utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenericUtils {
	public WebDriver driver;

	By topFrame = By.id("topFrame");

	By mainFrame = By.id("mainFrame");

	By firmaDigital = By.id("firmaDigital");
	
	public GenericUtils(WebDriver driver)
	{
		this.driver = driver;
	}

	public void switchTotopFrame(){
		this.waitFor(5);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(topFrame));
	}

	public void switchToMainFrame(){
		this.waitFor(5);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(mainFrame));
	}

	public void switchToFirmaDigital(){
		this.waitFor(5);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(mainFrame));
		this.waitFor(5);
		driver.switchTo().frame(driver.findElement(firmaDigital));
	}
	

	public void SwitchWindowToChild()
	{
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1 =s1.iterator();
		String parentWindow = i1.next();
		String childWindow = i1.next();
		driver.switchTo().window(childWindow);
	}

	public void waitFor(int sec){
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
