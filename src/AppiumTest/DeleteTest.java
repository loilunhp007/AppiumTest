package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DeleteTest {
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
	//when we delete user the next user will be display in position that we delete 
	// check if username we have 
	@Test
	public void deleteUser() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[3]")).getText()!="Admin") {
			driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[1]")).click();
			String expectedUsername = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[2]/td[2]")).getText();
			driver.findElement(By.xpath("//*[@id=\"btnDelete\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"dialogDeleteBtn\"]")).click();
			Thread.sleep(1000);
			String username = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[2]")).getText();
			Assert.assertEquals(username, expectedUsername);
		}
	}
	@AfterMethod()
	public void tearDown() {
		driver.close();
	}
}
