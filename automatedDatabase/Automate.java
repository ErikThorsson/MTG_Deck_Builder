package automatedDatabase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
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

public class Automate {
	protected ArrayList<Card> arr = new ArrayList<Card>();
	protected ArrayList<Card> arr2 = new ArrayList<Card>();

	public static void main(String[] args) throws Exception {
		Automate bot = new Automate();
		bot.scanFile();
		bot.sieveForSet("DGM");
		bot.formatCards();
	}
	
	public void sieveForSet(String s) throws MalformedURLException, InterruptedException, AWTException, IOException {
		Card card = new Card();
		int count = 0;
		for(int i = 0; i < arr.size(); i++) {
			//if(arr.get(i).rarity.contains(s)) {
				card = arr.get(i);
				card.imgURL = getImgURL(card.name);
				arr2.add(card);
				count++;
				if(count % 50 == 0) {
					this.formatCards();
				}
			//}
		}

	}
	
	public String nameQuery(Card c) {
		Card card = c;
		String data = "";
		data += card.name + "\n";
		data += card.color + "\n";
		data += card.type1 + "\n";
		data += card.type2 + "\n";
		data += card.power + "\n";
		data += card.toughness + "\n";
		data += card.text + "\n";
		data += card.rarity + "\n";
		data += card.imgURL + "\n";
		data += "#Owned = " + card .owned + "\n";
		return data;
	}

	public String getImgURL(String s) throws InterruptedException, AWTException, MalformedURLException, IOException {
		//removes logger error statements
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		Logger.getAnonymousLogger().getParent().setLevel(Level.WARNING);
		//Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		driver.get("http://gatherer.wizards.com/Pages/Default.aspx");
		//WebElement searchBox = driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_CardSearchBoxParent"));
		WebElement searchBox = driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_CardSearchBoxParent_CardSearchBox"));
		searchBox.sendKeys(s);
	    searchBox.sendKeys(Keys.RETURN);
	    if(s.contains("//")) {
	    	Robot bot = new Robot();
	    	bot.mouseMove(240,650);
	    	Thread.sleep(2000);
	    	bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(1000);
			bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(1000);
	 	    s = s.replace("//", "");
	    }
	    String ID = driver.getCurrentUrl();
	    String cURL = "http://gatherer.wizards.com/Handlers/Image.ashx";
	    String[] splits = new String[1];
	    splits = ID.split("aspx");
	    cURL+= splits[1] + "&type=card";
		BufferedImage img = ImageIO.read(new URL(cURL));
		try {
	    File outputfile = new File("/Users/eorndahl/Desktop/VCO/ISD/" + s + ".jpg");
	    ImageIO.write((RenderedImage) img, "jpg", outputfile);
		} catch (Exception ex) {
			System.out.println("Unable to retrive " + s + "'s image." );
		}
	    driver.close();
	    return cURL;
	}
	
	public void saveImg(String s) throws MalformedURLException, IOException {
		BufferedImage img = ImageIO.read(new URL(s));
	    File outputfile = new File("/Users/eorndahl/Desktop/VCO/DGM/" + s + ".jpg");
	    ImageIO.write((RenderedImage) img, "jpg", outputfile);
	}
	
	public void scanFile() throws IOException, InterruptedException, AWTException {
		StringBuilder file = new StringBuilder();
		String name = "";
		String color = "";
		String CMC = "";
		String type1 = "";
		String type2 = "";
		String type3;
		String powerS = "";
		String toughnessS = "";
		String text = "";
		String picURL;
		String rarity = "";
		Boolean isLand = false;

		BufferedReader br = new BufferedReader(new FileReader("/Users/eorndahl/Desktop/Innistrad Data.txt"));
		String sCurrentLine;
		int counter = 0;
		while ((sCurrentLine = br.readLine()) != null) {
			if(!sCurrentLine.equals("")) {
				if(counter == 0) {
					name = sCurrentLine;
				}
				if(counter == 1) {
					color = sCurrentLine;
					CMC = sCurrentLine;
					if (color.contains("Land")) {
						color = "colorless";
						isLand = true;
					} else if(color.contains("R") && color.contains("W") || color.contains("r") && color.contains("w") 
							||color.contains("R") && color.contains("B") ||color.contains("r") && color.contains("b") 
							|| color.contains("R") && color.contains("U") ||color.contains("r") && color.contains("u") 
							|| color.contains("R") && color.contains("G") ||color.contains("r") && color.contains("g") 
							|| color.contains("B") && color.contains("W") ||color.contains("b") && color.contains("w") 
							|| color.contains("U") && color.contains("B") ||color.contains("u") && color.contains("b")
							|| color.contains("G") && color.contains("B") ||color.contains("g") && color.contains("b") 
							|| color.contains("G") && color.contains("W") ||color.contains("g") && color.contains("w") 
							||color.contains("G") && color.contains("U") ||color.contains("g") && color.contains("u") 
							|| color.contains("U") && color.contains("W") ||color.contains("u") && color.contains("w"))  {
						color = "multi";
				} else if( color.contains("R") || color.contains("r")) {
						color = "red";
				}else if( color.contains("W") || color.contains("w")) {
						color = "white";
				}else if( color.contains("B") || color.contains("b")) {
						color = "blue";
				}else if( color.contains("U") || color.contains("u")) {
						color = "black";
				}else if( color.contains("G") || color.contains("g")) {
							color = "green";
				}else if(!color.contains("R") || !color.contains("r") 
						|| !color.contains("W") || !color.contains("w")
					|| !color.contains("B") || !color.contains("b")
					|| !color.contains("U") || !color.contains("u")
					|| !color.contains("G") || color.contains("g")) {
						color = "colorless";
				}
				}
				if(counter == 2) {
					if(isLand == true) {
						type2 = "land";
						counter++;
					}
					if(sCurrentLine.contains("Instant") || sCurrentLine.contains("Sorcery"))
						type2 = sCurrentLine.toLowerCase();
					if(sCurrentLine.contains("Creature"))
						type2 = "creature";
					if(sCurrentLine.contains("Enchantment")) {
						type2 = "enchantment";
					}
					if(sCurrentLine.contains("Planeswalker")) {
						type2 = "planeswalker";
						br.readLine();
					}
					if(sCurrentLine.contains("Artifact"))
						type2 = "artifact";
					if(sCurrentLine.contains("Planeswalker") || sCurrentLine.contains("Creature") || sCurrentLine.contains("Enchantment") || sCurrentLine.contains("Artifact")) {
						type1 = "permanent";
					} else {
						type1 = "nonPermanent";
					}
				}
				if(counter == 3) {
					if(sCurrentLine.charAt(0) == '0' || sCurrentLine.charAt(0) == '1' ||sCurrentLine.charAt(0) == '2' ||
							sCurrentLine.charAt(0) == '3' || sCurrentLine.charAt(0) == '4' ||
							sCurrentLine.charAt(0) == '5' || sCurrentLine.charAt(0) == '6' || sCurrentLine.charAt(0) == '7'
							||sCurrentLine.charAt(0) == '8' || sCurrentLine.charAt(0) == '9' || sCurrentLine.charAt(0) == '*' 
							&& !type2.equals("planeswalker")) {
						String[] pT = sCurrentLine.toString().split("/");
						if(powerS.equals("*") || toughnessS.equals("*")) {
							powerS = "0";
							toughnessS = "0";
						} else {
						powerS = pT[0];
						toughnessS = pT[1];
						}
						} else {
						powerS = "0";
						toughnessS = "0";
						text = sCurrentLine;
						counter++;
					}
				}
				if(counter == 4) {
					if(sCurrentLine.contains("-L") ) {
					counter++;
					text = "I am a land";
					} else if(sCurrentLine.contains("-R") || sCurrentLine.contains("-U") || sCurrentLine.contains("-C") || sCurrentLine.contains("RTR-M") 
							|| sCurrentLine.contains("GTC-M") || sCurrentLine.contains("DGM-M")) {
						text = null;
						counter++;
					} else {
						text = sCurrentLine; 
						while(!sCurrentLine.contains("-R") || !sCurrentLine.contains("-U") || !sCurrentLine.contains("-C") || sCurrentLine.contains("RTR-M") 
								|| sCurrentLine.contains("GTC-M") || sCurrentLine.contains("DGM-M")) {
							sCurrentLine = br.readLine();
							if(sCurrentLine.contains("-R") || sCurrentLine.contains("-U") || sCurrentLine.contains("-C") || sCurrentLine.contains("RTR-M") 
									|| sCurrentLine.contains("GTC-M") || sCurrentLine.contains("DGM-M")) {
									counter++;
									break; }
								text+= ". " + sCurrentLine;										
						}
					}
				}
				if(counter == 5) {
					rarity = sCurrentLine;
					int power = 0;
					int toughness = 0;
					if(powerS.equals("*") || toughnessS.equals("*")) {
						powerS = "0";
						toughnessS = "0";
					} else {
					power = Integer.parseInt(powerS);
					toughness = Integer.parseInt(toughnessS);
					}
					Card card = new Card(name, color, CMC, power, toughness, text, type1, type2, rarity, null);
					arr.add(card);
					counter = 0;
					isLand = false;
					continue;
				}
				counter++;
			}
		}
	}

	
	public void formatCards() throws FileNotFoundException {
		String formatted = "";
		ArrayList<String> arrr = new ArrayList<String>();
		for(int i = 0; i < arr2.size(); i++) {
			Card card = (Card)(arr2.get(i));
			formatted += card.name + "::";
			formatted += card.color + "::";
			formatted += card.type1 + "::";
			formatted += card.type2 + "::";
			formatted += card.rarity + "::";
			formatted += card.power + "::";
			formatted += card.toughness + "::";
			formatted += card.text + "::";
			formatted += card.imgURL + "::";
			arrr.add(formatted);
			formatted = "";
		}
		PrintWriter out = new PrintWriter("/Users/eorndahl/Desktop/readFormattedTest.txt");
		for(int i = 0; i < arrr.size();i++)
			out.println(arrr.get(i));
		out.close();
	}
	
	//Reads text from a file line by line
	public static StringBuilder readFromFile(String filename) throws IOException {
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader(filename));
		StringBuilder line2 = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			line2.append(line);
			{
			}
		}
		return line2;
	}
}
