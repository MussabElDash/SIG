package com.revature.test.Authentication;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterTest {
	ChromeDriver driver;
//	HtmlUnitDriver driver;
	String url;
	ArrayList<WebElement> elements;

	public RegisterTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
//		driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		url = "http://52.14.64.242:8085/SIG";
	}

	@BeforeTest
	public void setUp() {
		driver.get(url);
		elements = new ArrayList<WebElement>();
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=username]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=password1]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=password2]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=fname]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=lname]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=address]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=city]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=state]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=zip]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=ssn]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=dob]")));
		elements.add(driver.findElement(By.cssSelector("input[formcontrolname=phone]")));
	}

	@Test
	public void registerFormAvailable() {
		WebElement form = driver.findElement(By.cssSelector("app-register form"));
		assertTrue(form.isDisplayed(), "The Registration Form should be visible");
	}

	@Test
	public void failedRegistrations() {
		int random = (int) (Math.random() * 10);
		while (random-- > 0) {
			List<WebElement> ign = getRandomElems();
			int i = 0;
//			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys(randString());
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("pass");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("pass");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("fname");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("lname");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("some address");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("some city");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("as");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("11111");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("111111111");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("11112018");
			}
			i++;
			elements.get(i).clear();
			if (!ign.contains(elements.get(i))) {
				elements.get(i).sendKeys("1111111111");
			}
			driver.findElement(By.cssSelector("app-register button")).click();
			assertFalse(driver.findElement(By.cssSelector("app-register button")).isEnabled());
			if (ign.size() == 0) {
				assertTrue(waitForElem(By.tagName("app-login")).isDisplayed(),
						"You should be registered successfuly so you be be redirected to the login page");
			} else {
				WebElement button = (new WebDriverWait(driver, 10))
						.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-register button")));
				assertTrue(button.isEnabled(), "The register button should be enabled after unsuccessful register");
			}
		}
	}

	@AfterTest
	public void tearDownClass() {
		driver.quit();
	}

	public List<WebElement> getRandomElems() {
		ArrayList<WebElement> elems = new ArrayList<WebElement>();
		Random rand = new Random();
		int ran = rand.nextInt(elements.size());
		while (ran-- > 0) {
			int index = rand.nextInt(elements.size());
			while (elems.contains(elements.get(index))) {
				index = rand.nextInt(elements.size());
			}
			elems.add(elements.get(index));
		}
		return elems;
	}

	public String randString() {
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
	}

	public WebElement waitForElem(By locator) {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
