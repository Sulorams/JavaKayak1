package pageObjects;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;
public class HomePage extends base {
	
	public static Logger log =LogManager.getLogger(base.class.getName());
	@FindBy(xpath="//input[@aria-label='Flight origin input']")
	WebElement clearcache;
	
	@FindBy(xpath="//input[@aria-label='Flight origin input']")
	WebElement from;	
	
	@FindBy(xpath="//div[@class='yWDF yWDF-mod-spacing-base']//ul//li//div[@class='JyN0-checkbox']")
	List<WebElement> flightOptionsList;
	
	@FindBy(xpath="//input[@placeholder='To?']")
	WebElement to;
	
	@FindBy(xpath= "//div[@class='sR_k-input sR_k-mod-responsive']//span[@aria-label='Start date calendar input']")
	WebElement dpDate;
	
	@FindBy(xpath="//div[@class='wHSr-monthName']")
	List<WebElement> dateCalen;
	
	
	@FindBy(xpath="//div[@class='sR_k-input sR_k-mod-responsive']//span[@aria-label='End date calendar input']")
	WebElement rDate;
	
	@FindBy(xpath="//button[@aria-label='Search']")
	WebElement search;
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	public String validatePageTitle()
	{
		return driver.getTitle();
	}
	
	public String findFlightDetails(String src,String des,String sDate,String reDate,int sourceN,int destN,int N) throws InterruptedException, IOException
	{
		clearcache.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(clearcache));
		clearcache.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);
		clearcache.sendKeys(Keys.BACK_SPACE);

		from.sendKeys("LAX");
		for (int i = 0; i < flightOptionsList.size(); i++)
		{
		if(i==sourceN)
		{
		log.info("Selecting source Nth flight"+N);
		flightOptionsList.get(i).click();
		break;
		}
		}
		Thread.sleep(5000);
		to.sendKeys("ORD");
		Thread.sleep(4000);
		for (int j = 0; j < flightOptionsList.size(); j++) 
		{
		if(j==destN)
		{
		log.info("Selecting Nth destination flight"+N);
		flightOptionsList.get(j).click();
		break;
		}
		}
		Thread.sleep(3000);
		//departure date selection
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(dpDate));
		dpDate.click();
				String d[]=sDate.split(" ");
				for (int x = 0; x < dateCalen.size(); x++) 
				{
					String month = dateCalen.get(x).getText();
					if (month.contains(d[0])) 
					{
						String s = "(//div[@aria-label='"+sDate+"'])[1]";
						System.out.println(s); //
						WebElement welement = driver.findElement(By.xpath(s));
						WebDriverWait wait2= new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.elementToBeClickable(welement));
						welement.click();
					}
				}
				Thread.sleep(3000);
				
				//closing popup
				String mainWindow=driver.getWindowHandle();	
			    Set<String> set=driver.getWindowHandles();
			    Iterator<String> itr=set.iterator();
			   while(itr.hasNext())
			   {
				   String childWindow=itr.next();
				   if(!mainWindow.equals(childWindow))
				   {
				   driver.switchTo().window(childWindow);
			       driver.close();
			       }
			   } 
			   driver.switchTo().window(mainWindow);
				
				//return date
				/*Actions contactClickAction = new Actions(driver);	
				rDate.click();
				String r[]=reDate.split(" ");
				for (int x = 0; x < dateCalen.size(); x++) 
				{
					String month = dateCalen.get(x).getText();
					if (month.contains(r[0])) 
					{
						String s = "(//div[@aria-label='"+reDate+"'])[1]";
						System.out.println(s); //
						WebElement welement = driver.findElement(By.xpath(s));
						WebDriverWait wait2= new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.elementToBeClickable(welement));
						welement.click();
					}
				}*/
				 
				// clicking search button
				log.info("clicking the search button");
				search.click();
				Thread.sleep(7000);
				String expectedOrigin = null;
				String expectedDestination = null;
				//(//div[@class='col-info result-column']//ol)[1]//label
				//(//div[@class='col-info result-column']//div[@class='mainInfo'])[2]//li[1]//label
				List<WebElement> flightList=driver.findElements(By.xpath("//div[@class='col-info result-column']//ol"));
				for(int i=0;i<flightList.size();i++)
				{
					if(i==N)
					{
						List<WebElement> flights=flightList.get(i).findElements(By.xpath(".//label//parent::div"));
						for(int j=0;j<flights.size();j++)
						{
							System.out.println(flights.get(j));
							flights.get(j).click();
						}
						List<WebElement> airportNameList=flightList.get(i).findElements(By.xpath(".//span[@class='airport-name']"));
						expectedOrigin=airportNameList.get(0).getText();
						expectedDestination=airportNameList.get(0).getText();
						break;
					}
				}
				//comparing origin and destination
				
				getScreenShot();
				return expectedOrigin+" "+expectedDestination;
	
}
	public  static void getScreenShot() throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = "C:\\Sulochana\\Selenium Udemy\\airlineticketing\\"+"ScreenShot"+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
	}		
}
