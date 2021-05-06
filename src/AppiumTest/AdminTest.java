package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class AdminTest {
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
		String username="Admin";
		String password="admin123";
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
	@Test(priority = 1)
	public void checkAdminUserPage() {
		String expURL="https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, expURL);
	}
	
	@Test(priority = 2)
	public void AddUserSuccess() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='1']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("minmin5");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("123456789");
		driver.hideKeyboard();
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(3000);
		String ExpectedUrl= "https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers";
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, ExpectedUrl);	
	}
	
	@Test(priority = 3)
	public void AddUserWithNotExistEmplyee() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Test123@!@");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("minmin");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("123456789");
		
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")));
		String expectedError="Employee does not exist";
		String currentError = null;
		currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[2]/span[@class='validation-error']")).getText();
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(5000);
		Assert.assertEquals(currentError, expectedError);	
	}
	
	@Test(priority = 4)
	public void AddUserWithNullEmployeeName() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).clear();
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("minmin");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("123456789");
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")));
		String expectedError="Required";
		String currentError = null;
		if(driver.findElement(By.xpath("//form/fieldset/ol/li[2]/span[@class='validation-error']")).getText()!=null) {
			 currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[2]/span[@class='validation-error']")).getText();
		}
		
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(5000);
		Assert.assertEquals(currentError, expectedError);	
	}
	
	@Test(priority = 5)
	public void AddUserWithInvalidUsername() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("min");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("123456789");
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")));
		String expectedError="Should have at least 5 characters";
		String currentError = null;
		if(driver.findElement(By.xpath("//form/fieldset/ol/li[3]/span[@class='validation-error']")).getText()!=null) {
			currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[3]/span[@class='validation-error']")).getText();
		}
		
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(5000);
		Assert.assertEquals(currentError, expectedError);	
	}
	@Test(priority = 6)
	public void AddUserWithNullUsername() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).clear();
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("123456789");
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")));
		String expectedError="Required";
		String currentError = null;
		if(driver.findElement(By.xpath("//form/fieldset/ol/li[3]/span[@class='validation-error']")).getText()!=null) {
			currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[3]/span[@class='validation-error']")).getText();
		}
		
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(5000);
		Assert.assertEquals(currentError, expectedError);	
	}
	
	@Test(priority = 7)
	public void AddUserWithExistUsername() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).clear();
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("123456789");
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")));
		String expectedError="Already exists";
		String currentError = null;
		if(driver.findElement(By.xpath("//form/fieldset/ol/li[3]/span[@class='validation-error']")).getText()!=null) {
			 currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[3]/span[@class='validation-error']")).getText();
		}
		Thread.sleep(2000);
		Assert.assertEquals(currentError, expectedError);	
	}
	
	@Test(priority = 8)
	public void AddUserWithInvalidPassword() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("minmin");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("1234");
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")));
		String expectedError="Should have at least 8 characters";
		String currentError = null;
		if(driver.findElement(By.xpath("//form/fieldset/ol/li[6]/span[@class='validation-error']")).getText()!=null) {
			 currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[6]/span[@class='validation-error']")).getText();
		}
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(5000);
		Assert.assertEquals(currentError, expectedError);	
	}
	
	@Test(priority = 9)
	public void AddUserWithNullPassword() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//input[@id='btnAdd']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
		dropdown.click();
		driver.findElement(By.xpath("//option[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("Alice Duval");
		driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("minmin123");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@id='systemUser_password']")).clear();
		driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).clear();
		driver.hideKeyboard();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//input[@id='systemUser_password']")));
		String expectedError="Required";
		String currentError = null;
		if(driver.findElement(By.xpath("//form/fieldset/ol/li[6]/span[@class='validation-error']")).getText()!=null) {
			currentError=driver.findElement(By.xpath("//form/fieldset/ol/li[6]/span[@class='validation-error']")).getText();
		}
		
		WebElement addbtn= driver.findElement(By.xpath("//input[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		TouchAction t = new TouchAction<>(driver);
		t.tap(new PointOption<>().withCoordinates(20,200)).perform();
		Thread.sleep(5000);
		Assert.assertEquals(currentError, expectedError);	
	}
	@AfterMethod()
	public void tearDown() {
		driver.close();
	}

}
