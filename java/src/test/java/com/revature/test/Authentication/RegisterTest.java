package com.revature.test.Authentication;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
	ChromeDriver driver;
//	HtmlUnitDriver driver;
	String url;
	ArrayList<WebElement> elements;

	public RegisterTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions);
//		 url = "http://52.14.64.242:8085/SIG";
		url = "localhost:4200";
	}

	@BeforeMethod
	public void setUp() {
		driver.get(url);
		elements = new ArrayList<WebElement>();
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=username]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=password1]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=password2]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=fname]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=lname]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=address]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=city]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=state]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=zip]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=ssn]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=dob]")));
		elements.add(driver.findElement(By.cssSelector("app-register input[formcontrolname=phone]")));

		elements.get(0).sendKeys(randString());
		elements.get(1).sendKeys("pass");
		elements.get(2).sendKeys("pass");
		elements.get(3).sendKeys("fname");
		elements.get(4).sendKeys("lname");
		elements.get(5).sendKeys("some address");
		elements.get(6).sendKeys("some city");
		elements.get(7).sendKeys("as");
		elements.get(8).sendKeys("11111");
		elements.get(9).sendKeys("111111111");
		elements.get(10).sendKeys("11112018");
		elements.get(11).sendKeys("1111111111");
	}

	@Test
	public void registerFormAvailable() {
		WebElement form = driver.findElement(By.cssSelector("app-register form"));
		assertTrue(form.isDisplayed(), "The Registration Form should be visible");
	}

	@Test
	public void notMatchingPass() {
		elements.get(2).clear();
		elements.get(2).sendKeys("pass1");
		driver.findElement(By.cssSelector("app-register button")).click();
		wait(500);
		WebElement button = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-register button")));
		assertTrue(button.isEnabled(), "The register button should be enabled after unsuccessful register");
	}

	int wrong = 0;

	@Test(invocationCount = 5)
	public void wrongFieldLenght() {
		ArrayList<WebElement> wrngs = new ArrayList<WebElement>();
		wrngs.add(elements.get(7));
		wrngs.add(elements.get(8));
		wrngs.add(elements.get(9));
		wrngs.add(elements.get(10));
		wrngs.add(elements.get(11));
		WebElement elem = wrngs.get(wrong++);
		elem.clear();
		elem.sendKeys("11111111111");
		wait(500);
		driver.findElement(By.cssSelector("app-register button")).click();
		wait(500);
		WebElement button = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-register button")));
		assertTrue(button.isEnabled(), "The register button should be enabled after unsuccessful register");
	}

	@Test(invocationCount = 5)
	public void failedRegistrations() {
		List<WebElement> ign = getRandomElems();
		for (WebElement elem : ign) {
			elem.clear();
		}
		wait(500);
		driver.findElement(By.cssSelector("app-register button")).click();
		wait(500);
		WebElement button = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-register button")));
		assertTrue(button.isEnabled(), "The register button should be enabled after unsuccessful register");
	}

	@Test
	public void registerSuccessfuly() {
		driver.findElement(By.cssSelector("app-register button")).click();
		wait(500);
		WebElement login = waitForElem(By.cssSelector("router-outlet+app-login"));
		assertTrue(login.isDisplayed(), "The Login Form should be visible");
	}

	@AfterClass
	public void tearDownClass() {
		driver.quit();
	}

	public List<WebElement> getRandomElems() {
		ArrayList<WebElement> elems = new ArrayList<WebElement>();
		Random rand = new Random();
		int ran = rand.nextInt(elements.size());
		while (ran < 3) {
			ran = rand.nextInt(elements.size());
		}
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
		String res = "";
		for (int i = 0; i < 8; i++) {
			res += (char) (new Random().nextInt(58) + 65);
		}
		return res;
	}

	public WebElement waitForElem(By locator) {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	private void wait(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
		}
	}
}
