package automatedDatabase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import MTGApplication.Card;
import MTGApplication.CollectionMethods;

import com.sun.jna.platform.unix.X11.Display;

public class GetPrice {
		private static URL URLObj;
	    private static URLConnection connect;
	    
	public static void main(String[] args) throws InterruptedException, AWTException, MalformedURLException, IOException, InvalidKeyException {
		GetPrice bot = new GetPrice();
		//System.out.print(bot.getPriceL("Abundance"));
		//bot.getCorrectPrices();
		bot.getMissingPrices();
	}
	public String getPriceL(String s) throws IOException, AWTException, InterruptedException {
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
	    String[] priceArr = null;
	    try{
	    priceArr = readURL(URL);
	    } catch (Exception ex) {
		    	Robot robot = new Robot();
			    robot.mouseMove(100, 260);
			    robot.mousePress(InputEvent.BUTTON1_MASK);
			    Thread.sleep(1000);
			    robot.mousePress(InputEvent.BUTTON1_MASK);
			    robot.mouseRelease(InputEvent.BUTTON1_MASK);
			    Thread.sleep(1000);
			    robot.mousePress(InputEvent.BUTTON1_MASK);
			    robot.mouseRelease(InputEvent.BUTTON1_MASK);
			    Thread.sleep(1000);
			    URL = driver.getCurrentUrl();
			    try{
				    priceArr = readURL(URL);
				    } catch (Exception e2) {
				    	e2.printStackTrace();
					    driver.close();
				    }
	    		}
	    String price = null;
	    try {
	    price = priceArr[0]; 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    driver.close();
	    return price;
	}
	
	public String[] readURL(String s) throws IOException {
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
		        String price[] = new String[3];
		        // Read all available lines of data from the URL and print them to screen.
		        while ((lineRead = reader.readLine()) != null) {
		        	if(lineRead.contains("return false;\">")) {
		        		price = lineRead.split("return false;\">");
		        		break;
		        	}
		        }
		        price = price[1].split("</a>");
				return price;
	}
	
	public String getCorrectPrices() throws IOException {
		File price = new File("/Users/eorndahl/Desktop/Card Values.txt");
		BufferedReader br = new BufferedReader(new FileReader(price));
		String[] split = null;
        String lineRead = "";
        String temp = "";
        String correct = "";
		 while ((lineRead = br.readLine()) != null) {
				split = lineRead.split(":");
				try {
				if(!split[1].equals(temp)) {
					correct += lineRead;
					System.out.println(lineRead);
				}
				temp = split[1];
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 return correct;
	}
	
	public void getMissingPrices() throws InvalidKeyException, IOException {
		String home = System.getProperty("user.home");
		ArrayList<String> notFound = new ArrayList<String>();
		CollectionMethods c = new CollectionMethods();
		String[] s = c.getCategory("cD");
		String price = "";
		String nameAndPrice = "";
		for(int i = 0; i < s.length; i++) {
		Card card = c.getCard(s[i]);
		if(card.rarity != null)
			if(card.price.equals("0.00") && card.rarity.contains("-R") || card.rarity.contains("-M")) {
				notFound.add(card.name);
				//System.out.println(card.name);
				}
		}
		for(int i = 0; i< notFound.size(); i++) {
			price = null;
			try {
			price = getPriceL(notFound.get(i));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if(price != null) {
				nameAndPrice += notFound.get(i) + ":" + price + "\n";
				PrintWriter out = new PrintWriter(home + "/Desktop/Card Prices.txt");
				System.out.println(nameAndPrice);
				out.print(nameAndPrice);
				out.close();
				}
		}
	}
}
