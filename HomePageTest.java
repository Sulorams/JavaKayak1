package airlineticketing;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import resources.base;

public class HomePageTest extends base{
public static Logger log =LogManager.getLogger(base.class.getName());
HomePage hpage;
public HomePageTest() throws IOException
	{
	super();
	}
@BeforeTest
	public void setUp() throws IOException
	{
		driver=initializeDriver();
		log.info("Driver intialized successfully");
		hpage=new HomePage();
	}
	@Test(dataProvider="getData")
		public void searchFlight(String source,String destination,String dDate,String rDate,int sN,int rN,int N) throws IOException, InterruptedException
		{
		    String title=hpage.validatePageTitle();
		    Assert.assertEquals(title, "Search Flights, Hotels & Rental Cars | KAYAK");
		    
			log.info(source,destination,dDate,rDate,sN,rN,N);
			String output=hpage.findFlightDetails(source,destination,dDate,rDate,sN,rN,N);
			String result[]=output.split(" ");
			log.info("Successfully found the flights for the given source and destination");
			log.info("Asserting source and destination");
			Assert.assertEquals(result[0], source);
			Assert.assertEquals(result[1], destination);
			
		} 
		@AfterTest
		public void teardown()
		{
			log.info("driveris getting closed");
			driver.close();
		}
		
		@DataProvider 
		public Object[][] getData()
		{
			Object[][] data=new Object[1][7];
			//0th row
			data[0][0]="LAX";
			data[0][1]="ORD";
			data[0][2]="July 23, 2022";
			data[0][3]="August 16, 2022";
			data[0][4]=1;
			data[0][5]=1;
			data[0][6]=1;
			
			return data;
			
	}
		
		
	}
