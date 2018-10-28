package com.revature.test.Accounts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class SigAccounts {
	public static WebDriver driver;
	public final String url = "http://52.14.64.242:8085/SIG/";

	@Test
	public void smokeTest() {
		System.out.println("SMOKE TEST");
		assertEquals(driver.getTitle(), "SIG");
		String url = driver.getCurrentUrl();
		assertTrue(url.endsWith("/accounts"));
	}
	
	@Test
	public void addAccount() throws InterruptedException {
		System.out.println("ADD ACCOUNT TEST");
		waitForElem(By.cssSelector("div[class=col-md-2] button")).click();
		
		waitForElem(By.cssSelector("input[formcontrolname=accountName]")).sendKeys("test");
		waitForElem(By.cssSelector("option[value=IRA]")).click();
		String url = driver.getCurrentUrl();
		assertTrue(url.endsWith("/add-accounts"));
		//waitForElem(By.cssSelector("form button")).click();
		//Thread.sleep(500);
		//String url = driver.getCurrentUrl();
		//assertTrue(url.endsWith("/accounts"));
	}
	
	@Test
	public void viewAccount() {
		String title = waitForElem(By.cssSelector("body > app-root > div.container.card.mt-2 > div > app-accounts > div:nth-child(3) > div > div > div:nth-child(2) > div > div > h5")).getText();
		String balance = waitForElem(By.cssSelector("body > app-root > div.container.card.mt-2 > div > app-accounts > div:nth-child(3) > div > div > div:nth-child(2) > div > div > p")).getText();

		assertEquals(title, "test");
		assertEquals(balance, "Current Balance: 0");
	}
	
	@Test
	public void goToAccountDetails() throws InterruptedException {
		waitForElem(By.cssSelector("body > app-root > div.container.card.mt-2 > div > app-accounts > div:nth-child(3) > div > div > div:nth-child(2) > div > div > form > button")).click();
		Thread.sleep(500);
		String url = driver.getCurrentUrl();
		assertTrue(url.contains("/account-details"));
		waitForElem(By.cssSelector("a[routerlink=accounts]")).click();
	}

	@BeforeMethod
	public void beforeClass() throws InterruptedException {
		System.out.println("BEFORE METHOD");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.id("dropdownMenuButton")).click();

		driver.findElement(By.cssSelector("input[formcontrolname=username]")).sendKeys("test");
		driver.findElement(By.cssSelector("input[formcontrolname=password]")).sendKeys("pass");

		waitForElem(By.cssSelector("div[aria-labelledby=dropdownMenuButton] button")).click();
		Thread.sleep(1000);
	}

	@AfterMethod
	public void afterClass() {
		System.out.println("AFTER METHOD");
		driver.findElement(By.tagName("h2")).click();
		waitForElem(By.id("dropdownMenuButton")).click();
		waitForElem(By.cssSelector("div[aria-labelledby=dropdownMenuButton] a")).click();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("SETTING UP TEST");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		System.out.println("TEARING DOWN TEST");

		// driver.findElement(By.cssSelector("div[aria-labelledby=dropdownMenuButton]
		// a")).click();

		driver.quit();
	}

	public WebElement waitForElem(By locator) {
		return (new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator)));
	}

}
