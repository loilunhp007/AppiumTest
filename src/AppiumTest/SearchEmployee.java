package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class SearchEmployee {
	WebDriver driver;
	DesiredCapabilities caps = new DesiredCapabilities();
	
	@BeforeMethod
	public void InitConfig() throws MalformedURLException {
		
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android" );
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.0" );
		//Android Emulator - Pixel_3a_API_26:5554
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2" );
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		caps.setCapability("unicodeKeyboard", "true");                                     
		caps.setCapability("resetKeyboard", "true");
		
		URL url = new URL("http://0.0.0.0:4723/wd/hub"); 
		driver = new AppiumDriver(url,caps);
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		WebElement uname = driver.findElement(By.xpath("//div[@id='divUsername']//input[@id='txtUsername']"));
		uname.click();
		uname.sendKeys("admin");
		
		WebElement upass = driver.findElement(By.xpath("//div[@id='divPassword']//input[@id='txtPassword']"));
		upass.click();
		upass.sendKeys("admin123");
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void testSearchEmployeeNameExist() throws InterruptedException{
		
		WebElement pim = driver.findElement(By.xpath("//li[@class='main-menu-first-level-list-item']//a[@id='menu_pim_viewPimModule']"));
		pim.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		WebElement name = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_employee_name_empName']"));
		name.click();
		name.sendKeys("John");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement btnadd = driver.findElement(By.xpath("//form//fieldset//p//input[@id='searchBtn']"));
		btnadd.click();
		
		Thread.sleep(8000);

		String nameExpected = "John";
		String nameReality = driver.findElement(By.xpath("//tr//td[3]//a")).getText();
		Assert.assertEquals(nameReality, nameExpected);
	}
	
	@Test
	public void testSearchEmployeeNameAndIDExist() throws InterruptedException{
		
		WebElement pim = driver.findElement(By.xpath("//li[@class='main-menu-first-level-list-item']//a[@id='menu_pim_viewPimModule']"));
		pim.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		WebElement id = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_id']"));
		id.click();
		id.sendKeys("0011");
		
		WebElement name = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_employee_name_empName']"));
		name.click();
		name.sendKeys("John");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement btnadd = driver.findElement(By.xpath("//form//fieldset//p//input[@id='searchBtn']"));
		btnadd.click();
		
		Thread.sleep(8000);

		String idExpected = "0011";
		String idReality = driver.findElement(By.xpath("//tr//td[2]//a")).getText();
		
		Assert.assertEquals(idExpected, idReality);
	}
	
	@Test
	public void testSearchEmployeeNameNotExist() throws InterruptedException{
		
		WebElement pim = driver.findElement(By.xpath("//li[@class='main-menu-first-level-list-item']//a[@id='menu_pim_viewPimModule']"));
		pim.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		WebElement name = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_employee_name_empName']"));
		name.click();
		name.sendKeys("Johnathan");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement btnadd = driver.findElement(By.xpath("//form//fieldset//p//input[@id='searchBtn']"));
		btnadd.click();
		
		Thread.sleep(8000);

		String resultExpected = "No Records Found";
		String resultReality = driver.findElement(By.xpath("//tr//td")).getText();
		Assert.assertEquals(resultReality, resultExpected);
	}
	
	@Test
	public void testSearchEmployeeNameAndIDNotExist() throws InterruptedException{
		
		WebElement pim = driver.findElement(By.xpath("//li[@class='main-menu-first-level-list-item']//a[@id='menu_pim_viewPimModule']"));
		pim.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		WebElement id = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_id']"));
		id.click();
		id.sendKeys("1001");
		
		WebElement name = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_employee_name_empName']"));
		name.click();
		name.sendKeys("Stella");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement btnadd = driver.findElement(By.xpath("//form//fieldset//p//input[@id='searchBtn']"));
		btnadd.click();
		
		Thread.sleep(8000);

		String resultExpected = "No Records Found";
		String resultReality = driver.findElement(By.xpath("//tr//td")).getText();
		Assert.assertEquals(resultReality, resultExpected);
	}
	
	
	@AfterMethod
	  public void closeBrowser() {
		  System.out.println("Closing the browser session");
		  driver.quit();
	  }

}
