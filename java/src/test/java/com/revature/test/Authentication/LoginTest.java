package com.revature.test.Authentication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	ChromeDriver driver;
	String url;

	public LoginTest() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
		url = "http://18.191.230.71:8085/SIG";
	}

	@BeforeTest
	public void setUp() {
		driver.get(url);
	}

	@Test(priority = 1)
	public void Smoke() {
		assertEquals(driver.getTitle(), "SIG");
	}

	@Test(priority = 2)
	public void loginScreenAvailable() {
		try {
			WebElement dropdown = waitForElem(By.id("dropdownMenuButton"));
			dropdown.click();
			assertTrue(dropdown.isDisplayed(), "The login menu should be displayed but it wasn't");
		} catch (Exception e) {
			fail("Failed due to an exception", e);
		}
	}

	@Test(priority = 3)
	public void failedLogin() {
		try {
			waitForElem(By.cssSelector("app-login input[formcontrolname=username]")).sendKeys("mussab");
			driver.findElement(By.cssSelector("app-login input[formcontrolname=password]")).sendKeys("password");
			driver.findElement(By.cssSelector("app-login button")).click();
			WebElement elem = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-login button")));
			assertTrue(elem.isEnabled(),
					"Login Should've been not successful so the login button should be enabled but it wasn't");
			assertTrue(driver.findElement(By.cssSelector("app-login")).isDisplayed(),
					"Login Should've been not successful so the app-login tag should be visible but it wasn't");
		} catch (Exception e) {
			fail("Failed due to an exception", e);
		}
	}

	@Test(priority = 4)
	public void successfulLogin() {
		try {
			WebElement username = waitForElem(By.cssSelector("app-login input[formcontrolname=username]"));
			username.clear();
			username.sendKeys("mussab");
			WebElement pass = driver.findElement(By.cssSelector("app-login input[formcontrolname=password]"));
			pass.clear();
			pass.sendKeys("pass");
			driver.findElement(By.cssSelector("app-login button")).click();
			assertFalse(driver.findElement(By.cssSelector("app-login button")).isEnabled(),
					"Login Should've been successful so the login button should be disabled");
			assertTrue(waitForElem(By.tagName("app-accounts")).isDisplayed(),
					"Login wasn't successful for the user mussab with the password pass");
		} catch (Exception e) {
			fail("Failed due to an exception", e);
		}
	}

	@Test(priority = 5)
	public void logoutAvalableAndHides() {
		try {
			WebElement logout = driver.findElement(By.cssSelector("div[aria-labelledby=dropdownMenuButton] a"));
			if (logout.isDisplayed()) {
				WebElement dropdown = waitForElem(By.id("dropdownMenuButton"));
				dropdown.click();
			}
			assertFalse(logout.isDisplayed());
		} catch (Exception e) {
			fail("Failed due to an exception", e);
		}
	}

	@Test(priority = 6)
	public void Logout() {
		try {
			WebElement logout = driver.findElement(By.cssSelector("div[aria-labelledby=dropdownMenuButton] a"));
			if (logout.isDisplayed()) {
				logout.click();
			} else {
				driver.findElement(By.id("dropdownMenuButton")).click();
				logout = waitForElem(By.cssSelector("div[aria-labelledby=dropdownMenuButton] a"));
				logout.click();
			}
			assertTrue(waitForElem(By.tagName("app-home")).isDisplayed(),
					"Once logged out should be redirected to the home page but that wasn't the case");
		} catch (Exception e) {
			fail("Failed due to an exception", e);
		}
	}

	@AfterTest
	public void tearDownClass() {
		driver.quit();
	}

	public WebElement waitForElem(By locator) {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
