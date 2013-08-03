package automatedDatabase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleImageRobot {
	public String getImgURL(String s) throws InterruptedException, AWTException {
		//removes logger error statements
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		Logger.getAnonymousLogger().getParent().setLevel(Level.WARNING);
		
		//Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com/imghp?hl=en&tab=wi");
		
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("gatherer MTG card " + s);
	    searchBox.sendKeys(Keys.RETURN);
	    
	   // Point coordinates = searchBox.getLocation();
	    Robot robot = new Robot();
	    robot.mouseMove(75, 300);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    Thread.sleep(1000);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    Thread.sleep(1000);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    Thread.sleep(1000);
	    robot.mouseMove(650, 375);
	    Thread.sleep(1000);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    Thread.sleep(1000);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    
	    String cURL = driver.getCurrentUrl();
	    driver.close();
		return cURL;
	}
}
