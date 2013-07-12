package automatedDatabase;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sun.jna.platform.unix.X11.Display;

public class GetPrice {
		private static URL URLObj;
	    private static URLConnection connect;
	    
	public static void main(String[] args) throws InterruptedException, AWTException, MalformedURLException, IOException {
		GetPrice bot = new GetPrice();
		System.out.print(bot.getPriceL("Abrupt Decay"));
	}
	public String getPriceL(String s) throws IOException {
		//removes logger error statements
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		Logger.getAnonymousLogger().getParent().setLevel(Level.WARNING);
		

				
		//Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.mtggoldfish.com");
		
		//WebElement searchBox = driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_CardSearchBoxParent"));
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys(s);
	    searchBox.sendKeys(Keys.RETURN);	
	    String URL = driver.getCurrentUrl();
	    String[] priceArr = readURL(URL);
	    String price = priceArr[0];
	    driver.close();
	    return price;
	}
	
	public String[] readURL(String s) throws IOException {
				//System.out.println(s);
		        try {
		            // Establish a URL and open a connection to it. Set it to output mode.
		            URLObj = new URL(s);
		            connect = URLObj.openConnection();
		            connect.setDoOutput(true);	
		        }
		        catch (MalformedURLException ex) {
		        	System.out.println("The URL specified was unable to be parsed or uses an invalid protocol. Please try again.");
		        	System.exit(1); 
		        }
		        catch (Exception ex) {
		        	System.out.println("An exception occurred. " + ex.getMessage());
		        	System.exit(1);
		        }

		        BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));

		        String lineRead = "";
		        String price[] = null;
		        // Read all available lines of data from the URL and print them to screen.
		        while ((lineRead = reader.readLine()) != null) {
		        	if(lineRead.contains("font-size:31.5px;\">")) {
		        		price = lineRead.split("font-size:31.5px;\">");
		        		break;
		        	}
		        }
		        String fP[] = null;
		        price = price[1].split("</span>");
				return price;
	}
}
