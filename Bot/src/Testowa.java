//http://selenium-release.storage.googleapis.com/index.html?path=2.45/
//https://code.google.com/p/selenium/wiki/GettingStarted
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;


public class Testowa {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\workspace2\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME); //tak robimy jezeli nie chcemy podgladac
		driver.get("http://www.google.com");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Java");
		element.submit();
		
        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
		System.out.println("koniec");
	}

}
