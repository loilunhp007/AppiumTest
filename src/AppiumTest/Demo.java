package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Demo {
  public AppiumDriver driver;
	@BeforeMethod()
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android" );
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0" );
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung Galaxy J6" );
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2" );
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		URL url = new URL("http://0.0.0.0:4723/wd/hub"); 
		driver = new AppiumDriver(url,caps);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	@Test(priority = 1)
	public void assertUsernameAndPasswordField() {

		String ExpectedUsernamefield="txtUsername";
		String ExpectedPasswordfield="txtPassword";
		String usernameField=driver.findElement(By.xpath("//div[@id='divUsername']/input[@id='txtUsername']")).getText();
		String passwordField=driver.findElement(By.xpath("//div[@id='divPassword']/input[@id='txtPassword']")).getText();
		System.out.println(usernameField+"aa:"+passwordField);
		//Assert.assertEquals(usernameField, ExpectedUsernamefield);
		//Assert.assertEquals(passwordField, ExpectedPasswordfield);
		driver.close();
	}
	
	@Test(priority = 2)
	public void loginSuccess() throws InterruptedException{
		//check login vs URL
		String expectedURL="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		String username="Admin";
		String password="admin123";
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, expectedURL);
		driver.close();
	}
	@Test(priority = 3)
	public void loginWithUppercaseUsername() throws InterruptedException {
		String expectedMessage="Csrf token validation failed";
		String username="AdMiN";
		String password="admin123";
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(message, expectedMessage);
		driver.close();
		
		
	}
	@Test(priority = 4)
	public void loginWithUppercasPassword() throws InterruptedException {
		String expectedMessage="Csrf token validation failed";
		String username="Admin";
		String password="Admin123";
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(message, expectedMessage);
		driver.close();
		
		
	}
	@Test(priority = 5)
	public void loginWithSpecialCharacter() throws InterruptedException {
		String expectedMessage=" Invalid credentials";
		String username=" */Admin";
		String password="admin123";
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(message, expectedMessage);
		driver.close();
	}
	@Test(priority = 6)
	public void loginWithIncorrectUsername() throws InterruptedException {
		String expectedMessage=" Invalid credentials";
		String username="Admin123";
		String password="admin123";
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(message, expectedMessage);
		driver.close();
	}
	@Test(priority = 7)
	public void loginWithIncorrectPassword() throws InterruptedException {
		String expectedMessage=" Invalid credentials";
		String username="Admin";
		String password="admin123456";
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(message, expectedMessage);
		driver.close();
		
	}
	
}
