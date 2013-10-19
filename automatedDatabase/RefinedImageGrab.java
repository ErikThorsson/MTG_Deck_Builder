package automatedDatabase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RefinedImageGrab {
	public static void main(String[] args) throws InterruptedException, AWTException, MalformedURLException, IOException {
		RefinedImageGrab bot = new RefinedImageGrab();
		bot.getImgURL("Fire // Ice");
	}
	public String getImgURL(String s) throws InterruptedException, AWTException, MalformedURLException, IOException {
		//removes logger error statements
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		Logger.getAnonymousLogger().getParent().setLevel(Level.WARNING);
		
		//Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		driver.get("http://gatherer.wizards.com/pages/search/default.aspx?name=+[\"" + s + "\"]");
			    
	    String ID = driver.getCurrentUrl();
	    String cURL = "http://gatherer.wizards.com/Handlers/Image.ashx";
	    String[] splits = new String[1];
	    splits = ID.split("aspx");
	    cURL+= splits[1] + "&type=card";
		BufferedImage img = ImageIO.read(new URL(cURL));
		if(s.contains("//"))
			s = s.replace("//", "");
	    File outputfile = new File("/Users/eorndahl/Desktop/VCO/Theros/" + s + ".jpg");
	    try{
	    ImageIO.write(img, "jpg", outputfile);
	    } catch (Exception ex){
	    	try {
	    	Robot robot = new Robot();
		    robot.mouseMove(170, 650);
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    Thread.sleep(1000);
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(1000);
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(1000);
		    ID = driver.getCurrentUrl();
		    cURL = "http://gatherer.wizards.com/Handlers/Image.ashx";
		    splits = ID.split("aspx");
		    cURL+= splits[1] + "&type=card";
			img = ImageIO.read(new URL(cURL));
		    ImageIO.write(img, "jpg", outputfile);
	    	} catch ( Exception e) {
	    	System.out.println(s);
	    	}
	    }
	    driver.close();
	    return cURL;
	}
}
