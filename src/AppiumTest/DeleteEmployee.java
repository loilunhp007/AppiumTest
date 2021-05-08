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

public class DeleteEmployee {
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
	public void testAddEmployee() throws InterruptedException{
		
		WebElement pim = driver.findElement(By.xpath("//li[@class='main-menu-first-level-list-item']//a[@id='menu_pim_viewPimModule']"));
		pim.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		WebElement btnadd = driver.findElement(By.xpath("//div[@class='top']//input[@id='btnAdd']"));
		btnadd.click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
		WebElement lastName = driver.findElement(By.xpath("//ol//li//ol[@class='fieldsInLine']//li[3]//input[@id='lastName']"));
		lastName.click();
		lastName.sendKeys("Smith");
		
		WebElement firstName = driver.findElement(By.xpath("//ol//li//ol[@class='fieldsInLine']//li[1]//input[@id='firstName']"));
		firstName.click();
		firstName.sendKeys("Kryth");
		
		WebElement id = driver.findElement(By.xpath("//ol//li[2]//input[@id='employeeId']"));
		id.click();
		id.clear();
		id.sendKeys("0118");
		
		WebElement a = driver.findElement(By.xpath("//div[@id='wrapper']//div[@id='branding']//a//img"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",a );
		
		new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(500,1350)).release().perform();
		
		Thread.sleep(3000);

		WebElement user_name = driver.findElement(By.xpath("//ol//li//input[@id='user_name']"));
		user_name.click();
		user_name.sendKeys("krythsmith0118");
		
		
		WebElement user_password = driver.findElement(By.xpath("//ol//li//input[@id='user_password']"));
		user_password.click();
		user_password.sendKeys("jkpwdkry.19@3");
		
		
		WebElement re_password = driver.findElement(By.xpath("//ol//li//input[@id='re_password']"));
		re_password.click();
		re_password.sendKeys("jkpwdkry.19@3");
		
		WebElement status = driver.findElement(By.xpath("//ol//li//select[@id='status']"));
		status.click();
		status.sendKeys("Enabled");
		
		WebElement text = driver.findElement(By.xpath("//ol//li//label[contains(text(),'Status')]"));
		js.executeScript("arguments[0].scrollIntoView()", text);
		
		new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(2,500)).release().perform();
		
		Thread.sleep(5000);
		
		String firstnameExpected = "Kryth";
		String firstnameReality = driver.findElement(By.xpath("//ol//li[@class='line nameContainer']//ol//li//input[@id='personal_txtEmpFirstName']")).getAttribute("value");
		Assert.assertEquals(firstnameReality, firstnameExpected);
		
	}
	
	@Test
	public void testDeleteEmployee() throws InterruptedException{
		
		WebElement pim = driver.findElement(By.xpath("//li[@class='main-menu-first-level-list-item']//a[@id='menu_pim_viewPimModule']"));
		pim.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		WebElement id = driver.findElement(By.xpath("//ol//li//input[@id='empsearch_id']"));
		id.click();
		id.sendKeys("0118");
		
		Thread.sleep(1000);
		
		WebElement a = driver.findElement(By.xpath("//div[@id='wrapper']//div[@id='branding']//a//img"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",a );
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement btnadd = driver.findElement(By.xpath("//form//fieldset//p//input[@id='searchBtn']"));
		btnadd.click();
		Thread.sleep(6000);
		
		WebElement clickchk = driver.findElement(By.xpath("//tr//td[1]"));
		clickchk.click();
		Thread.sleep(3000);
		
		WebElement clickdelete = driver.findElement(By.xpath("//div//input[@id='btnDelete']"));
		clickdelete.click();
		Thread.sleep(3000);
		
		WebElement clickok = driver.findElement(By.xpath("//div//input[@id='dialogDeleteBtn']"));
		clickok.click();
		Thread.sleep(3000);
		
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
