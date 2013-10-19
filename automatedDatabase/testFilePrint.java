package automatedDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import MTGApplication.Card;

public class testFilePrint {
	protected ArrayList<Card> arr = new ArrayList<Card>();
	protected ArrayList<Card> arr2 = new ArrayList<Card>();

	public static void main(String[] args) throws Exception {
		testFilePrint test = new testFilePrint();
		test.scanFile();
		//test.formatCards();
	}

	public void printData() {

	}
	
	public void sieveForSet(String s) throws MalformedURLException, InterruptedException, AWTException, IOException {
		Card card = new Card();
		int count = 0;
		for(int i = 0; i < arr.size(); i++) {
				card = arr.get(i);
				card.imgURL = getImgURL(card.name);
				arr2.add(card);
				count++;
				if(count % 50 == 0) {
					this.formatCards();
				}
		}

	}

	public String nameQuery(Card c) {
		Card card = c;
		String data = "";
		data += card.name + "\n";
		data += card.color + "\n";
		data += card.CMC + "\n";
		data += card.type1 + "\n";
		data += card.type2 + "\n";
		data += card.type3 + "\n";
		data += card.power + "\n";
		data += card.toughness + "\n";
		data += card.text + "\n";
		data += card.rarity + "\n";
		data += card.imgURL + "\n";
		data += "#Owned = " + card .owned + "\n";
		return data;
	}

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
		robot.mouseMove(630, 375);
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

	public void scanFile() throws IOException, InterruptedException, AWTException {
		StringBuilder file = new StringBuilder();
		String name = "";
		String color = "";
		String CMC = "";
		String type1 = "";
		String type2 = "";
		String type3 = "";
		String powerS = "";
		String toughnessS = "";
		String text = "";
		String picURL;
		String rarity = "";
		Boolean isLand = false;

		BufferedReader br = new BufferedReader(new FileReader("/Users/eorndahl/Desktop/Theros Block-2013-09-29.txt"));
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
				}else if( color.contains("U") || color.contains("u")) {
						color = "blue";
				}else if( color.contains("B") || color.contains("b")) {
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
					try {
					String[] s = sCurrentLine.split("-- ");
					type3 = s[1];
					} catch (Exception e) {
						type3 = null;
					}
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
					if(sCurrentLine.contains("Planeswalker") || sCurrentLine.contains("Creature") || sCurrentLine.contains("Enchantment") || isLand == true) {
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
						} else if(powerS.contains("+*") || toughnessS.contains("+*")) {
								String[] split = powerS.split("\\+");
								powerS = split[0];
								toughnessS = split[0];
							}
						powerS = pT[0];
						toughnessS = pT[1];
						} else {
						powerS = "0";
						toughnessS = "0";
						text = sCurrentLine;
						counter++;
					}
				}
				if(counter == 4) {
					if(sCurrentLine.contains("-L") && sCurrentLine.length() < 6) {
					counter++;
					text = null;
					} else {
						text = "";
						String temp = sCurrentLine;
						while(!sCurrentLine.contains("-R") || !sCurrentLine.contains("-U") || !sCurrentLine.contains("-C") || !sCurrentLine.contains("-M")) {
							text += sCurrentLine;
							sCurrentLine = br.readLine();
							if(sCurrentLine == null) {
								text = null;
								sCurrentLine = temp;
								counter++;
								break;
							}
							if(sCurrentLine != null) {
								if(sCurrentLine.contains("-R") || sCurrentLine.contains("-U") || sCurrentLine.contains("-C") || sCurrentLine.contains("-M")){
									if(sCurrentLine.length() < 6 ) {
									counter++;
									break; }
									if(sCurrentLine.contains(",") & sCurrentLine.length() < 30 ) {
									counter++;
									break; }
								text+= ". " + sCurrentLine;	
								}
							}
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
					} else if (powerS.contains("+*") || toughnessS.contains("+*")) {
						System.out.println(powerS);
						String[] split = powerS.split("\\+");
						powerS = split[0];
						toughnessS = split[0];
					} else {
					power = Integer.parseInt(powerS);
					toughness = Integer.parseInt(toughnessS);
					}
					Card card = new Card(name, color, CMC, power, toughness, text, type1, type2, type3, rarity, null);
					arr.add(card);
					RefinedImageGrab bot = new RefinedImageGrab();
					bot.getImgURL(card.name);
					System.out.print(nameQuery(card) + "\n");
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
		for(int i = 0; i < arr.size(); i++) {
			Card card = (arr.get(i));
			formatted += card.name + "::";
			formatted += card.color + "::";
			formatted += card.CMC + "::";
			formatted += card.type1 + "::";
			formatted += card.type2 + "::";
			formatted += card.type3 + "::";
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
