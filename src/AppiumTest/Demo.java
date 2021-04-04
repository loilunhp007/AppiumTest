package AppiumTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Demo {
  public AppiumDriver driver;
	@BeforeTest
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android" );
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0" );
		caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung Galaxy J6" );
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2" );
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		
		URL url = new URL("http://0.0.0.0:4723/wd/hub"); 
		driver = new AppiumDriver(url,caps);
		driver.get("https://google.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertEquals(1, 1);
	}
}
