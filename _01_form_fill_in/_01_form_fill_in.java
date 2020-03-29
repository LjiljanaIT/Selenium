package _workbook_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class _01_form_fill_in {
	public static void main(String[] args) {
		/*
		 * By using Selenium Web driver open a website:
		 * https://www.techlistic.com/p/selenium-practice-form.html Fill out the form by
		 * using various web element locators. (task made by itbootcamp.com)
		 */

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
		waitFOR(1000);

		driver.manage().window().maximize();

		// declaration of all elements needed:
		WebElement firstN, lastN, gender, yearsOFe, date, prof, tool, cont, selCom, photo, down, btn;

		firstN = driver.findElement(By.name("firstname"));
		firstN.click();
		firstN.sendKeys("Ljilja");

		lastN = driver.findElement(By.name("lastname"));
		lastN.click();
		lastN.sendKeys("Ljiljana");
		waitFOR(1000);

		gender = driver.findElement(By.id("sex-1"));
		gender.click();
		waitFOR(1000);

		yearsOFe = driver.findElement(By.id("exp-6"));
		yearsOFe.click();
		waitFOR(1000);

		date = driver.findElement(By.id("datepicker"));
		date.click();
		date.sendKeys("07.07.2007.");
		waitFOR(1000);

		prof = driver.findElement(By.id("profession-1"));
		prof.click();
		waitFOR(1000);

		tool = driver.findElement(By.id("tool-2"));
		tool.click();
		waitFOR(1000);

		cont = driver.findElement(By.xpath("//option[contains(text(),'Europe')]"));
		cont.click();
		waitFOR(2000);

		selCom = driver.findElement(By.xpath("//option[contains(text(),'Wait Commands')]"));
		selCom.click();
		waitFOR(2000);

		photo = driver.findElement(By.name("photo"));
		photo.sendKeys("C:\\Users\\ljvka\\OneDrive\\Pictures\\Screenshots\\photo_za_domaci_0327.png");
		waitFOR(3000);
		// unsuccessful attempt to upload photo with relative path
		// photo.sendKeys("photo_za_domaci_0327.PNG");

		down = driver.findElement(By.linkText("Click here to Download File"));
		down.click();
		driver.navigate().back();
		waitFOR(3000);

		btn = driver.findElement(By.id("submit"));
		btn.click();

		waitFOR(2000);

		driver.close();

	}

	public static void waitFOR(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
