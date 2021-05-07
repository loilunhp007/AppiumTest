package AppiumTest;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class AdminSearchTest {
	AppiumDriver driver=null;
	@BeforeMethod()
	public void setUp() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android" );
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0" );
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung Galaxy J6" );
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2" );
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url,caps);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String username="Admin";
		String password="admin123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
	
	@Test(priority = 1)
	public void searchWithUsername() throws InterruptedException {
		String expURL="https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("Admin");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement search = driver.findElement(By.xpath("//*[@id=\"searchBtn\"]"));
		js.executeScript("arguments[0].scrollIntoView();", search);
		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table")));
		String expectedUsername = "Admin";
		String username = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]")).getText();
		assertEquals(username,expectedUsername );
		
	}
	
	@Test(priority = 2)
	public void searchWithRole() throws InterruptedException {
		String expURL="https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(2000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='searchSystemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='1']")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement search = driver.findElement(By.xpath("//*[@id=\"searchBtn\"]"));
		js.executeScript("arguments[0].scrollIntoView();", search);
		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table")));
		String expectedRole = "Admin";
		Thread.sleep(1000);
		String role = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[3]")).getText();
		assertEquals(role,expectedRole );
		Thread.sleep(2000);
	}
	
	@Test(priority = 3)
	public void searchWithEmployeeName() throws InterruptedException {
		String expURL="https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='searchSystemUser_employeeName_empName']")).sendKeys("Alice");
		driver.findElement(By.xpath("//ul/li[@class='ac_even ac_over']")).click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement username = driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']"));
		js.executeScript("arguments[0].scrollIntoView();", username);
		Thread.sleep(1000);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,300)).perform();
		Thread.sleep(2000);
		String expectedName = "Alice Duval";
		String name = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[4]")).getText();
		assertEquals(name,expectedName );


}
	
	@Test(priority = 4)
	public void searchWithStatus() throws InterruptedException {
		String expURL="https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(2000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='searchSystemUser_status']"));
		dropdown.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"searchSystemUser_status\"]/option[3]")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement search = driver.findElement(By.xpath("//*[@id=\"searchBtn\"]"));
		js.executeScript("arguments[0].scrollIntoView();", search);
		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table")));
		String expectedStatus = "Disabled";//status disabled
		String status = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[5]")).getText();
		assertEquals(status,expectedStatus );
		
		
	}
	@AfterMethod()
	public void tearDown() {
		driver.close();
	}
}
