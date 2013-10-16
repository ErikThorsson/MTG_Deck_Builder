package MTGApplication;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import automatedDatabase.GetPrice;
import automatedDatabase.GoogleImageRobot;

import dataStructures.BasicTree;
import dataStructures.HashTableMap;
import dataStructures.TreeNode;

public class CollectionMethods extends BasicTree {
	public BasicTree tree = new BasicTree();
	private String saveFile = "";
	private String home = System.getProperty("user.home");

	public CollectionMethods() throws InvalidKeyException, IOException {		
		String sFile = "";
		String home = System.getProperty("user.home"); 
		saveFile = (home + "/Desktop/VCO/VCOSave.txt");
		this.loadCompleteDatabase();
		try {
			this.load();
			this.addCard("card_back", null, null, 0, 0, null, null, null, null, null, null);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
		loadPrices();
	}

	public static void main(String[] args) throws InvalidKeyException, IOException, InterruptedException, AWTException {
		CollectionMethods test = new CollectionMethods();
				test.loadCompleteDatabase();
				test.printNameAndPrice();
//				String[] s = test.getCategory("cD");
				
//				Card card = test.getCard("Howlpack Alpha");
//				System.out.print(test.cmcCount(card.CMC));
//				System.out.println(card.CMC);
//				//String[] t = test.query("n", "green");
//				String[] j = test.query(s, "n", -1, -1, -1, "n", "n", "n", "n", "n", "n", -1, "n");
//				for (int i = 0; i < j.length; i++) {
//					System.out.println(j[i]);
//				}
				
//				ArrayList<Card> redL = test.red.cardEntries();
//				for (int i = 0; i < redL.size(); i++) {
//					System.out.println(redL.get(i).name);
//				}
//				for(int i = 0; i < s.length; i++) { //tests for card picture
//				GoogleImageRobot bot = new GoogleImageRobot();
//				bot.getImgURL(s[i]);	
//				}
					
//check for missing images					
//					BufferedImage image = null;
//					String home = System.getProperty("user.home");
//					ArrayList<String> notFound = new ArrayList<String>();
//					for(int i = 0; i < s.length; i++) {
//					Card card = test.getCard(s[i]);
//					try {
//						String cut = card.name;
//						if(card.name.contains("//"))
//							cut = card.name.replace("//", "");
//						image = ImageIO.read(new File(home + "/Desktop/VCO/Pictures Try 2/" + cut + ".jpg"));
//					} catch (Exception ex) {
//						notFound.add(card.name);
//						System.out.println(card.name);
//						}
//					}
//					String names = "";
//					for(int i = 0; i< notFound.size(); i++) {
//					names += notFound.get(i) + "\n";
//					PrintWriter out = new PrintWriter(home + "/Desktop/Missing Pictures.txt");
//					out.print(names);
//					out.close();
//					}
//					test.getDecks();
	}
	
	/**
	 * Set JCombo list to current saved decks
	 */
	public void getDecks() {
	String filepath = "/Users/eorndahl/Desktop/VCO/Decks";
	File directory = new File(filepath);
	String[] files = directory.list();
	String[] names = new String[files.length];
	for (int i = 0; i < names.length; i++){
		try{
		String[] splits = new String[2];
		splits = files[i].split("\\.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	/**
	 * returns a String[] of the card names in a given hashtable
	 * @param s
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] getCategory(String s) {
		HashTableMap h = (HashTableMap)((TreeNode) treeNodes.get(s)).getReference();
		ArrayList<String> arr = new ArrayList();
		ArrayList<Card> list = h.cardEntries();
		Card card = new Card();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) != null) {
				card = list.get(i);
				String s2 = card.name;
				arr.add(s2);
			}
		}
		String[] arr2= new String[arr.size()];
		arr.toArray(arr2);
		java.util.Arrays.sort(arr2);
		return  arr2;
	}

	/**
	 * returns a String[] of all owned cards
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String[] getAllArray() {
		HashTableMap h = (HashTableMap)((TreeNode) treeNodes.get("root")).getReference();
		ArrayList<String> arr = new ArrayList();
		ArrayList<Card> list = h.cardEntries();
		Card card = new Card();
		for(int i = 0; i < list.size(); i++) {
			card = list.get(i);
			if(list.get(i) != null) {
				String s2 = card.name;
				arr.add(s2);
			}
		}
		String[] arr2= new String[arr.size()];
		arr.toArray(arr2);
		java.util.Arrays.sort(arr2);
		return  arr2;
	}

	/**
	 * Returns a String[] of all the card names within a set.
	 * @param s
	 * @return
	 * @throws InvalidKeyException
	 */
	public String[] getSet(String s) throws InvalidKeyException {
		String[] all = getCategory("cD");
		ArrayList<String> set = new ArrayList<String>();
		for(int i = 0; i < all.length; i++) {
			if(getCard(all[i]).rarity !=  null)
				if(getCard(all[i]).rarity.contains(s)) //rarity contains set info too
					set.add(all[i]);

		}
		String[] setArr = new String[set.size()];
		set.toArray(setArr);
		java.util.Arrays.sort(setArr);
		return setArr;
	}

/**
 * Returns a String[] of the number of cards owned for every card you own
 * @return
 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer[] getOwned() {
		HashTableMap h = (HashTableMap)((TreeNode) treeNodes.get("root")).getReference();
		ArrayList<Integer> arr = new ArrayList();
		ArrayList<Card> list = h.cardEntries();
		Card card = new Card();
		for(int i = 0; i < list.size(); i++) {
			card = list.get(i);
			if(list.get(i) != null) {
				int s2 = card.owned;
				arr.add(s2);
			}
		}
		Integer[] arr2= new Integer[arr.size()];
		arr.toArray(arr2);
		return  arr2;
	}


	/**
	 * Adds a card to the deck
	 */
	public void addCardToDeck(String s, boolean b) {
	Card card = (Card) CompleteDatabase.get(s);
	if(b == true)
		card.inSB = true;
	if(b == false)
		card.inSB = false;
	if(deck.get(s) != null && card.inSB != true) { //increases owned by one if not owned 
		card.cardsInDeck++;
			return;
	} else if(deck.get(s) != null && card.inSB == true){
		card.numSB++;
		return;
	}
	deck.put(card.name, card);
	if(b == true)
		card.numSB++;
	else
		card.cardsInDeck++;
	}
	
	/**
	 * removes cards
	 */
	public void removeCardFromDeck(String s, boolean b) {
	Card card = (Card) deck.get(s);
	if(b == true)
		card.inSB = true;
	if(b == false)
		card.inSB = false;
	if(b == false) { //decreases owned by one if owned 
		card.cardsInDeck--;
	}
	if(b == true) { //decreases owned by one if owned 
		card.numSB--;
	}
	if(card.numSB == 0 & card.cardsInDeck == 0) {
	card.cardsInDeck = 0;
	card.numSB = 0;
	card.inSB = false;
	deck.remove(card.name);
	}
	}
	
	/**
	 * grabs deck card
	 */
	public Card grabDeckCard(String s) {
		return (Card) deck.get(s);
	}
	
	/**
	 * Reset deck
	 */
	public void resetDeck(){
		ArrayList<Card> cards = deck.cardEntries();
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if(card != null) {
				card.cardsInDeck = 0;
				card.numSB = 0;
				deck.remove(card.name);
			}
		}
	}
	
	/**
	 * Adds non land cards not held in database.
	 * Looks like this is no longer needed due to the query method being so efficient. 
	 * Might be useful in the future for optimization I suppose...
	 */
	@SuppressWarnings("unchecked")
	public void addCard(String s, String s2, String s3, int i, int i2, String t, String ty, String ty2, String ty3, String r, String url) throws InvalidKeyException {
		Card card = new Card(s, s2, s3, i, i2, t, ty, ty2, ty3, r, url);
		try {
			if(card.type2 == "land") {
				land.put(card.name, card);
				card.owned =+ 1;
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		MTG.put(card.name, card);
		CompleteDatabase.put(card.name, card);
		spells.put(card.name, card);
		
		if(card.color!= null) {
		if(card.color.equals("red")) {
			red.put(card.name, card);
			if (card.type1.equals("permanent")) 
				redPermanents.put(card.name, card);
			if(card.type2.equals("creature"))
				redCreatures.put(card.name, card);
			if(card.type2.equals("planeswalker"))
				redPlaneswalkers.put(card.name, card);
			if(card.type2.equals("enchantment"))
				redEnchantments.put(card.name, card);
			else
				redNonPermanents.put(card.name, card);
			if(card.type2.equals("instant"))
				redInstants.put(card.name, card);
			if(card.type2.equals("sorcery"))
				redSorcery.put(card.name, card);
			}
		}
		if(card.color == "white") {
			white.put(card.name, card);
			if (card.type1 == "permanent") 
				whitePermanents.put(card.name, card);
			if(card.type2 == "creature")
				whiteCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				whitePlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				whiteEnchantments.put(card.name, card);
			else
				whiteNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				whiteInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				whiteSorcery.put(card.name, card);
		}
		if(card.color == "blue") {
			blue.put(card.name, card);
			if (card.type1 == "permanent") 
				bluePermanents.put(card.name, card);
			if(card.type2 == "creature")
				blueCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				bluePlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				blueEnchantments.put(card.name, card);
			else
				blueNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				blueInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				blueSorcery.put(card.name, card);
		}
		if(card.color == "black") {
			black.put(card.name, card);
			if (card.type1 == "permanent") 
				blackPermanents.put(card.name, card);
			if(card.type2 == "creature")
				blackCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				blackPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				blackEnchantments.put(card.name, card);
			else
				blackNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				blackInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				blackSorcery.put(card.name, card);
		}
		if(card.color == "green") {
			green.put(card.name, card);
			if (card.type1 == "permanent") 
				greenPermanents.put(card.name, card);
			if(card.type2 == "creature")
				greenCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				greenPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				greenEnchantments.put(card.name, card);
			else
				greenNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				greenInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				greenSorcery.put(card.name, card);
		}
		if(card.color == "multi") {
			multi.put(card.name, card);
			if (card.type1 == "permanent") 
				multiPermanents.put(card.name, card);
			if(card.type2 == "creature")
				multiCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				multiPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				multiEnchantments.put(card.name, card);
			else
				multiNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				multiInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				multiSorcery.put(card.name, card);
		}
		if(card.color == "colorless") {
			colorless.put(card.name, card);
			colorlessPermanents.put(card.name, card);
			if(card.type2 == "creature")
				colorlessCreatures.put(card.name, card);
			if(card.type2 == "equipment")
				colorlessEquipment.put(card.name, card);
			if(card.type2 == "artifact")
				colorlessArtifacts.put(card.name, card);
		}	
		card.owned =+ 1;
	}


	//Adds card if in database w/ String. Tis' the only way to add land.
	@SuppressWarnings("unchecked")
	public void addCard(String s) throws InvalidKeyException {
		Card card = (Card) CompleteDatabase.get(s);
		if(MTG.get(s) != null) { //increases owned by one if not owned 
			setOwned(s, ((Card)MTG.get(s)).owned + 1, MTG);
			return;
		}

		try{ 
			MTG.put(card.name, card);
		} catch (NullPointerException n) {
			n.printStackTrace();
		}
		try {
			if(card.color == null) {
				land.put(card.name, card);
				card.owned =+ 1;
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}

		spells.put(card.name, card);
		
		if(card.color != null) {
		if(card.color.equals("red")) {
			red.put(card.name, card);
			if (card.type1 == "permanent") 
				redPermanents.put(card.name, card);
			if(card.type2 == "creature")
				redCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				redPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				redEnchantments.put(card.name, card);
			else
				redNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				redInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				redSorcery.put(card.name, card);
			}

		if(card.color == "white") {
			white.put(card.name, card);
			if (card.type1 == "permanent") 
				whitePermanents.put(card.name, card);
			if(card.type2 == "creature")
				whiteCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				whitePlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				whiteEnchantments.put(card.name, card);
			else
				whiteNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				whiteInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				whiteSorcery.put(card.name, card);
		}

		if(card.color == "blue") {
			blue.put(card.name, card);
			if (card.type1 == "permanent") 
				bluePermanents.put(card.name, card);
			if(card.type2 == "creature")
				blueCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				bluePlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				blueEnchantments.put(card.name, card);
			else
				blueNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				blueInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				blueSorcery.put(card.name, card);
		}
		if(card.color == "black") {
			black.put(card.name, card);
			if (card.type1 == "permanent") 
				blackPermanents.put(card.name, card);
			if(card.type2 == "creature")
				blackCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				blackPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				blackEnchantments.put(card.name, card);
			else
				blackNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				blackInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				blackSorcery.put(card.name, card);
		}
		if(card.color == "green") {
			green.put(card.name, card);
			if (card.type1 == "permanent") 
				greenPermanents.put(card.name, card);
			if(card.type2 == "creature")
				greenCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				greenPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				greenEnchantments.put(card.name, card);
			else
				greenNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				greenInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				greenSorcery.put(card.name, card);
		}
		if(card.color == "multi") {
			multi.put(card.name, card);
			if (card.type1 == "permanent") 
				multiPermanents.put(card.name, card);
			if(card.type2 == "creature")
				multiCreatures.put(card.name, card);
			if(card.type2 == "planeswalker")
				multiPlaneswalkers.put(card.name, card);
			if(card.type2 == "enchantment")
				multiEnchantments.put(card.name, card);
			else
				multiNonPermanents.put(card.name, card);
			if(card.type2 == "instant")
				multiInstants.put(card.name, card);
			if(card.type2 == "sorcery")
				multiSorcery.put(card.name, card);
		}
		if(card.color == "colorless") {
			colorless.put(card.name, card);
			colorlessPermanents.put(card.name, card);
			if(card.type2 == "creature")
				colorlessCreatures.put(card.name, card);
			if(card.type2 == "equipment")
				colorlessEquipment.put(card.name, card);
			if(card.type2 == "artifact")
				colorlessArtifacts.put(card.name, card);
			}
		}
		card.owned =+ 1;
	}

	//quickly sets the number of owned card copies. 

	@SuppressWarnings("rawtypes")
	public void setOwned(String s, int i, HashTableMap h) {
		((Card) h.get(s)).setOwned(i);
	}

	/**
	 * Removes from appropriate hashtables w/ tree traversal.
	 * Will keep in case it leads to efficiency later down the line.
	 * @param s
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeCard(String s) {
		String color = "Iwonder";
		int owned = ((Card)MTG.get(s)).owned;
		if(land.get(s) != null) {
			if(owned > 1) {
				setOwned(s, owned - 1, MTG);
			} else {
				land.remove(s);
				MTG.remove(s);
			}
			return;
		} else {
			color = ((Card) MTG.get(s)).color;
		}
		if(owned > 1) {
			setOwned(s, owned - 1, MTG);
		} else {
			MTG.remove(s);
			spells.remove(s);
		}
		TreeNode start = ((TreeNode) treeNodes.get(color));
		//remove it from its color
		((HashTableMap) start.getReference()).remove(s); 
		//then remove from every other possible subtree
		List<TreeNode> children = start.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			TreeNode child = (TreeNode) iter.next();
			if(owned > 1) {
				setOwned(s, owned - 1, MTG);
			} else {
				((HashTableMap) child.getReference()).remove(s);  } 
			List<TreeNode> grandkids = child.getChildren();
			for (Iterator iter2 = grandkids.iterator(); iter2.hasNext();) {
				TreeNode child2 = (TreeNode) iter2.next(); 
				if(owned > 1) {
					setOwned(s, owned - 1, MTG);
				} else {
					((HashTableMap) child2.getReference()).remove(s); }
			}
		}
	}
	/**
	 * Let's make this remove from the complete database and then my card hashtable can scan this. Much easier than
	 * managing both at once...
	 * @param s
	 * @param h
	 */
	@SuppressWarnings("rawtypes")
	public void removeCard(String s, String h) {
		@SuppressWarnings("unused")
		String color = "Iwonder";
		HashTableMap ht = (HashTableMap)((TreeNode) treeNodes.get(h)).getReference();
		int owned = ((Card)ht.get(s)).owned;
		if(owned >= 1) {
			setOwned(s, owned - 1, ht);
		}
		if(owned == 1 && h.equals("root"))
			MTG.remove(s);
	}
	//returns a list of entries given a hashtable
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> entries(String s) {
		HashTableMap h = (HashTableMap)((TreeNode) treeNodes.get(s)).getReference();
		ArrayList<Card> list = h.cardEntries();
		return list;
	}

	/**
	 * New query (sieve method)
	 */
	public String[] query(String[] lis, String s, int i, int i2, int i3, String s2, 
			String s3, String s4, String t, String t2, String t3, int i4, String s5) throws InvalidKeyException {
		String color = s;
		String[] li = lis;
		int power = i;
		int toughness = i2;
		int owned = i3;
		int cManaC = i4;
		String type1 = s2;
		String type2 = s3;
		String rarity = s4;
		String text = t2;
		String colors = s5;
		
		ArrayList<Card> filtered = new ArrayList<Card>();
		ArrayList<Card> temp = new ArrayList<Card>();
		ArrayList<Card> list = new ArrayList<Card>();

		for(int z = 0; z < li.length; z++) {
			list.add(getCard(li[z]));
		}
		filtered = (ArrayList<Card>) list.clone();
//		if (color.equals("red")) {
//			filtered = red.cardEntries();
//		}
		if(rarity.equals("common"))
			rarity = "-C";
		if(rarity.equals("uncommon"))
			rarity = "-U";
		if(rarity.equals("rare"))
			rarity = "-R";
		if(rarity.equals("mythic"))
			rarity = "-M";

		if(power != -1) {
			filtered.clear();
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.power == power)
						filtered.add(card);
			}
		}

		if(toughness != -1) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				}
				if(card != null)
					if(card.toughness == toughness) {
						temp.add(card);
					}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(!type1.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				}
				if(card != null)
					if(card.type1 != null)
						if((card.type1).equals(type1)) {
							temp.add(card);
						}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(!type2.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				}			if(card != null)
					if(card.type2 != null)
						if((card.type2).equals(type2)) {
							temp.add(card);
						}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(owned != -1) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				}	
				if(card != null)
					if(card.owned == owned) {
						temp.add(card);
					}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(!color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				} 
				if(card != null)
					if(card.color != null)
						if(card.color.equals(color)) { 
							temp.add(card);
						}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(!rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				} 
				if(card != null)
					if(card.rarity != null)
						if(card.rarity.contains(rarity)) {
							temp.add(card);
						}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}
		if(!text.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				}if(card != null && text != null)
					if(card.text != null)
						if(card.name.toLowerCase().contains(text) || card.type2.toLowerCase().contains(text) 
								|| card.text.toLowerCase().contains(text) ) {
							temp.add(card);
						}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(cManaC != -1) {
			Card card = new Card();
			int cmcCount = 0;
			for(int j = 0; j < filtered.size(); j++) {
				try{
					card = filtered.get(j);
				} catch (Exception e) {
					card = list.get(j);
				} 
				if(card != null)
					if(card.CMC != null) {
						cmcCount = cmcCount(card.CMC);
						if(cManaC == 9) {
							if(cmcCount >= 9)
								temp.add(card);
						} else if(cmcCount == cManaC) 
							temp.add(card);
					}
			}
			filtered = (ArrayList<Card>) temp.clone();
			temp.clear();
		}

		if(!colors.equals("")) {
			Card card = new Card();
			for(int z = 0; z < colors.length(); z++) {
				if(colors.charAt(z) == 85) {
					for(int j = 0; j < filtered.size(); j++) {
						try{
							card = filtered.get(j);
						} catch (Exception e) {
							card = list.get(j);
						} 
						if(card != null)
							if(card.CMC != null)
								if(card.CMC.contains("U")) {
									temp.add(card);
								}
					}
					filtered = (ArrayList<Card>) temp.clone();
					temp.clear();
				}
				if(colors.charAt(z) == 82) {
					for(int j = 0; j < filtered.size(); j++) {
						try{
							card = filtered.get(j);
						} catch (Exception e) {
							card = list.get(j);
						} 
						if(card != null)
							if(card.CMC != null)
								if(card.CMC.contains("R")) {
									temp.add(card);
								}
					}
					filtered = (ArrayList<Card>) temp.clone();
					temp.clear();
				}
				if(colors.charAt(z) == 87) {
					for(int j = 0; j < filtered.size(); j++) {
						try{
							card = filtered.get(j);
						} catch (Exception e) {
							card = list.get(j);
						} 
						if(card != null)
							if(card.CMC != null)
								if(card.CMC.contains("W")) {
									temp.add(card);
								}
					}
					filtered = (ArrayList<Card>) temp.clone();
					temp.clear();
				}
				if(colors.charAt(z) == 71) {
					for(int j = 0; j < filtered.size(); j++) {
						try{
							card = filtered.get(j);
						} catch (Exception e) {
							card = list.get(j);
						} 
						if(card != null)
							if(card.CMC != null)
								if(card.CMC.contains("G")) {
									temp.add(card);
								}
					}
					filtered = (ArrayList<Card>) temp.clone();
					temp.clear();
				}
				if(colors.charAt(z) == 66) {
					for(int j = 0; j < filtered.size(); j++) {
						try{
							card = filtered.get(j);
						} catch (Exception e) {
							card = list.get(j);
						} 
						if(card != null)
							if(card.CMC != null)
								if(card.CMC.contains("B")) {
									temp.add(card);
								}
					}
					filtered = (ArrayList<Card>) temp.clone();
					temp.clear();
				}
			}
		}

		String[] namesList= new String[filtered.size()];
		for(int j = 0; j < filtered.size(); j++) {
			namesList[j] = filtered.get(j).name;
		}
		return  namesList;
	}

	/**
	 * Count the CMC
	 */
	
	public int cmcCount(String s) {
		int count = 0;
		int nextInt = -1;
		if(!s.contains("Land")) {
		for(int i = 0; i < s.length(); i++) {
				int current = Character.getNumericValue(s.charAt(i));
				try {
				nextInt = Character.getNumericValue(s.charAt(i + 1)); 
				if(nextInt < 10 && nextInt > 0 && current < 10 && current > 0)
					i++;
				} catch (Exception e) {
					//e.printStackTrace();
				}
				if(nextInt != -1 && current != -1 && nextInt < 10 && current < 10) {
					System.out.println(nextInt);
					System.out.println(current);
					char charOne = 0;
					char charTwo = 0;
					try {
						charOne = s.charAt(i);
						charTwo = s.charAt(i + 1);
						char[] array = {charOne,charTwo};
						String number = new String(array);
						count += Integer.parseInt(number);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (current < 10 && current > 0)
					count += current;
				else if (current == -1) {
					i++;i++;
					i++;i++;
					count++;
				} else
					count++;
		}
		}
		return count;
	}
	
	


	//SOP the card's data
	public String nameQuery(String s) {
		Card card = (Card) MTG.get(s);
		String data = "";
		data += card.name + "\n";
		data += card.color + "\n";
		data += card.CMC + "\n";
		data += card.type1 + "\n";
		data += card.type2 + "\n";
		data += card.type3 + "\n";
		data += card.text + "\n";
		data += card.rarity + "\n";
		data += "#Owned = " + card .owned + "\n";
		return data;
	}


	//SOP a Card object's data
	public String nameQuery(Card c) {
		Card card = c;
		String data = "";
		data += card.name + "\n";
		data += card.color + "\n";
		data += card.CMC + "\n";
		data += card.type1 + "\n";
		data += card.type2 + "\n";
		data += card.type3 + "\n";
		data += card.text + "\n";
		data += card.rarity + "\n";
		data += "#Owned = " + card .owned + "\n";
		return data;
	}

	public Card getCard(String s) throws InvalidKeyException {
		Card card = new Card();
		if(s != null) {
		try {
			card = (Card) CompleteDatabase.get(s);
		} catch (NullPointerException n) {
			InvalidKeyException ex = new InvalidKeyException();
			throw ex;
			}
		}
		return card;
	}

	/**
	 * saves to a specific text file on my computer
	 * @throws FileNotFoundException
	 * @throws InvalidKeyException
	 * @throws MalformedURLException
	 */
	public void save() throws FileNotFoundException, InvalidKeyException, MalformedURLException {
		PrintWriter out = new PrintWriter(saveFile);
		try {
			this.MTG.remove("card_back");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for(int i = 0; i < this.getAllArray().length; i++) {
			out.print(this.getAllArray()[i]);
			out.print(":");
			out.print(getCard(this.getAllArray()[i]).getOwned());
			out.print(":");
		}
		out.close();
	}

	/**
	 * Saves a backup of the save file under a randomized file name.
	 * @throws IOException
	 */
	public void backup() throws IOException {
		StringBuilder sFile = new StringBuilder();
		sFile = readFromFile(saveFile);
		Random r = new Random();
		int i = r.nextInt(100000);
		File f = new File(home + "/Desktop/VCO/VCOBackupSave" + i + ".txt");
		f.createNewFile();
		PrintWriter out = new PrintWriter(home + "/Desktop/VCO/VCOBackupSave" + i + ".txt");
		out.print(sFile);
		out.close();
	}

	/**
	 * Reads the save text file and adds the correct number of cards to your database
	 * @throws IOException
	 * @throws InvalidKeyException
	 */
	public void load() throws IOException, InvalidKeyException  {
		StringBuilder sFile = new StringBuilder();
		sFile = readFromFile(saveFile);
		String[] lines = sFile.toString().split(":");
		ArrayList<String> cardNames = new ArrayList<String>();
		ArrayList<Integer> numOwned = new ArrayList<Integer>();
		//add names and #Owned to separate ArrayLists
		for(int i = 0; i < lines.length; i++) {
			if(i == 0 || i % 2 == 0) {
				cardNames.add(lines[i]);
			} else {
				int num = Integer.parseInt(lines[i]);
				numOwned.add(num);
			}
		}
		//convert ArrayLists to Arrays
		Integer[] arr2= new Integer[numOwned.size()];
		numOwned.toArray(arr2);
		String[] arr = new String[cardNames.size()];
		cardNames.toArray(arr);
		//add correct number of cards to database
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr2[i]; j++)
				this.addCard(arr[i]);
		}
	}
	/**
	 * returns the name of each rare/ mythic card as a String[]
	 * @throws FileNotFoundException 
	 */
	public String[] rareAndMythicNames() throws FileNotFoundException {
		StringBuilder names = new StringBuilder();
		HashTableMap h = (HashTableMap)((TreeNode) treeNodes.get("cD")).getReference();
		ArrayList<String> arr = new ArrayList();
		ArrayList<Card> list = h.cardEntries();
		Card card = new Card();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) != null) {
				card = list.get(i);
				String s2 = card.name;
				if(card.rarity != null)
					if(card.rarity.contains("-M") || card.rarity.contains("-R"))
						arr.add(s2);
			}
		}
		String[] arr2= new String[arr.size()];
		arr.toArray(arr2);
		java.util.Arrays.sort(arr2);
		return arr2;
	}
	
	/**
	 * Prints the name and price of each rare and mythic card
	 * @throws IOException 
	 */
	public void printNameAndPrice() throws IOException {
		GetPrice value = new GetPrice();
		String[] names = rareAndMythicNames();
		String[] prices = new String[names.length];
		String nameAndPrice = "";
		String price = "";
		for(int i = 0; i< names.length; i++) {
			price = null;
			try {
			price = value.getPriceL(names[i]);
			prices[i] = price;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if(price != null) {
			nameAndPrice += names[i] + ":" + price + "\n";
			PrintWriter out = new PrintWriter(home + "/Desktop/Card Prices.txt");
			out.print(nameAndPrice);
			out.close();
			}
		}
	}
	/**
	 * Load the prices
	 * @throws IOException 
	 * @throws InvalidKeyException 
	 */
	public void loadPrices() throws IOException, InvalidKeyException {
		File price = new File(home + "/Desktop/Card Values.txt");
		BufferedReader br = new BufferedReader(new FileReader(price));
		String[] split = null;
        String lineRead = "";
        while ((lineRead = br.readLine()) != null) {
			split = lineRead.split(":");
			Card card = new Card();
			card = getCard(split[0]);
			try {
			card.setPrice(split[1]);
			}catch (Exception e) {
				//card.setPrice(null);
			}
        }
	}

	/**
	 * Loads every card from the complete database's text file into the program.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadCompleteDatabase() throws IOException {
		StringBuilder sFile = new StringBuilder();
		String name;
		String color;
		String CMC;
		String type1;
		String type2;
		String tribal;
		String rarity;
		String powerS;
		String toughnessS;
		String text;
		String picURL;
		try {
			sFile = readFromFile(home + "/Desktop/VCO/readFormattedCards.txt");
		} catch (Exception ex) {
			sFile = readFromFile("/Volumes/NIGEL/VCO/readFormattedCards.txt");
		}
		String[] lines = sFile.toString().split("::");
		int cur = 0;
		//for the string array containing all data
		for(int i = 0; i < lines.length; i++) {
			cur = 0;
			name = lines[i + cur]; cur++;
			color = lines[i + cur]; cur++;
			CMC = lines[i + cur]; cur++;
			type1 = lines[i + cur]; cur++;
			type2 = lines[i + cur]; cur++;
			tribal = lines[i + cur]; cur++;
			rarity = lines[i + cur]; cur++;
			powerS = lines[i + cur]; cur++;
			toughnessS = lines[i + cur]; cur++;
			text = lines[i + cur]; cur++;
			picURL = lines[i + cur]; cur++;
			i += 10;
			//System.out.println(name);
			int power = Integer.parseInt(powerS);
			int toughness = Integer.parseInt(toughnessS);
//			try {
//				addCard(name, color, CMC, power, toughness, text, type1, type2, tribal, rarity, picURL);
//			} catch (InvalidKeyException e1) {
//				e1.printStackTrace();
//			}
			CompleteDatabase.put(name, new Card(name, color, CMC, power, toughness, text, type1, type2, tribal, rarity, picURL));
			Card card;
		}
	}

	/**
	 * Reads text from a file line by line
	 */
	public static StringBuilder readFromFile(String filename) throws IOException {
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader(filename));
		StringBuilder line2 = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			line2.append(line);
		}
		return line2;
	}

}
