package constant;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static WebDriver driver;
	public static Properties config;
	@BeforeSuite                                    //precondition
	public static void setUp() throws Throwable
	{ 

		config = new Properties();
		config.load(new FileInputStream("C:\\Selenium_9.30 am batch\\Hybrid_FrameWork\\PropertyFiles\\Environment.Properties"));
		driver = new ChromeDriver();
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(config.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
		else if(config.getProperty("Browser").equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
			//driver.manage().window().maximize();
			driver.get(config.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
		else {
			Reporter.log("Browse value is not matching",true);
		}
	}
	@AfterSuite
	public static void tearDown()
	{
		driver.close();
	}
}


