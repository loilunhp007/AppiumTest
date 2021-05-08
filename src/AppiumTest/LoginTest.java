package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import utility.Constant;
import utility.ExcelUtils;
public class LoginTest {
	ExcelUtils ulti;
	int i=1;
  public AppiumDriver<MobileElement> driver;
	@BeforeMethod()
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android" );
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0" );
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung Galaxy J6" );
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2" );
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url,caps);
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test(priority = 1)
	public void verifyLoginPage() {


		WebElement usernameField=driver.findElement(By.xpath("//input[@id='txtUsername']"));
		WebElement passwordField=driver.findElement(By.xpath("//input[@id='txtPassword']"));
		WebElement button=driver.findElement(By.xpath("//*[@id=\"btnLogin\"]"));
		WebElement logo =driver.findElement(By.xpath("//*[@id=\"divLoginImage\"]"));
		Assert.assertTrue(usernameField.isDisplayed(),"Username Field is not displayed");
		Assert.assertTrue(passwordField.isDisplayed(),"Password Field is not Displayed");
		Assert.assertTrue(button.isDisplayed(),"Button is not Displayed");
		Assert.assertTrue(logo.isDisplayed(),"Password Field is not Displayed");
	}
	@SuppressWarnings("deprecation")
	@Test(priority = 2)
	public void loginSuccess() throws InterruptedException{
		//check login vs URL
		String expectedURL="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		String username="Admin";
		String password="admin123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, expectedURL);
	}
	@Test(priority = 3)
	public void loginWithUppercaseUsername() throws InterruptedException {
		String expectedURL="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		String username="ADMIN";
		String password="admin123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url, expectedURL);
			
	}
	@Test(priority = 4)
	public void loginWithUppercasePassword() throws InterruptedException {
		String expectedMessage="Invalid credentials";
		String username="Admin";
		String password="ADMIN123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String message = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Assert.assertEquals(message, expectedMessage);
		
		
	}
	@Test(priority = 5)
	public void loginWithSpecialCharacter() throws InterruptedException {
		String expectedMessage="Invalid credentials";
		String username=" Admin ";
		String password="admin123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String message = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Assert.assertEquals(message, expectedMessage);
	}
	@Test(priority = 6)
	public void loginWithIncorrectUsername() throws InterruptedException {
		String expectedMessage="Invalid credentials";
		String username="Admin123";
		String password="admin123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String message = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Assert.assertEquals(message, expectedMessage);
	}
	@Test(priority = 7)
	public void loginWithIncorrectPassword() throws InterruptedException {
		String expectedMessage="Invalid credentials";
		String username="Admin";
		String password="admin123456";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String message = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Assert.assertEquals(message, expectedMessage);
	}
	@Test(priority = 8)
	public void loginWithNullUsername() throws InterruptedException {
		String expectedMessage="Username cannot be empty";
		String username="";
		String password="admin123456";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String message = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Assert.assertEquals(message, expectedMessage);
	}
	@Test(priority = 9)
	public void loginWithPassword() throws InterruptedException {
		String expectedMessage="Password cannot be empty";
		String username="Admin";
		String password="";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String message = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Assert.assertEquals(message, expectedMessage);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
