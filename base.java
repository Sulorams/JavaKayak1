package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base  {

	public static WebDriver driver;
	public Properties prop;
	public base() 
	{
		prop= new Properties();
		String path=System.getProperty("user.dir");
		FileInputStream fis;
		try {
			fis = new FileInputStream("C:\\Sulochana\\Selenium Udemy\\airlineticketing\\src\\main\\java\\resources\\data.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
public WebDriver initializeDriver() throws IOException
{
	String browserName=prop.getProperty("browser");
	System.out.println(browserName);
//execute in chrome driver 
if(browserName.equals("chrome"))
{ 
		System.setProperty("webdriver.chrome.driver", "C:\\Sulochana\\Selenium Udemy\\chromedriver.exe");
	 	ChromeOptions o = new ChromeOptions();
		o.addArguments("--disable-notifications");
		o.addArguments("disable-infobars");
		driver = new ChromeDriver(o);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
}
else if (browserName.equals("firefox"))
{
	System.setProperty("webdriver.gecko.driver", "C:\\Sulochana\\Selenium Udemy\\geckodriver\\geckodriver.exe");
	FirefoxOptions  o = new FirefoxOptions ();
	o.addArguments("--disable-notifications");
	o.addArguments("disable-infobars");
	 driver= new FirefoxDriver(o);
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
}
else if (browserName.equals("IE"))
{
//	IE code
}

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
return driver;


}


public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
{
	TakesScreenshot ts=(TakesScreenshot) driver; 
	File source =ts.getScreenshotAs(OutputType.FILE);
	String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	FileUtils.copyFile(source,new File(destinationFile));
	return destinationFile;

}

}
