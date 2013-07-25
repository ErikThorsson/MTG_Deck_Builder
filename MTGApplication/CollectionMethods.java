package MTGApplication;

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
//		try {
			readFromFile(home + "/Desktop/VCO/VCOSave.txt");
			sFile = (home + "/Desktop/VCO/VCOSave.txt");
//		} catch (Exception ex) {
//			sFile = "/Volumes/NIGEL/VCO/VCOSave.txt";
//		}
		saveFile = sFile;
		this.loadCompleteDatabase();
		try {
			this.load();
			this.addCard("card_back", null, null, 0, 0, null, null, null, null, null, null);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
		loadPrices();
	}

	public static void main(String[] args) throws InvalidKeyException, IOException {
		CollectionMethods test = new CollectionMethods();
				test.loadCompleteDatabase();
				//test.printNameAndPrice();
				String[] s = test.getCategory("cD");
//				Card card = test.getCard("Azor's Elocutors");
//				System.out.println(card.CMC);
				//String[] t = test.query("n", "green");
						//test.query(s, "n", -1, -1, -1, "n", "n", "n", "n", "n", "Centaur's Herald");
				for(int i = 0; i < s.length; i++) { //tests for card picture
					Card card = test.getCard(s[i]);
					//System.out.println(card.name);
					BufferedImage image = null;
					String home = System.getProperty("user.home");
					try {
						String cut = card.name;
						if(card.name.contains("//"))
							cut = card.name.replace("//", "");
						image = ImageIO.read(new File(home + "/Desktop/VCO/Pictures Try 2/" + cut + ".jpg"));
					} catch (Exception ex) {
						System.out.println(card.name);
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
			try {
				if(getCard(all[i]).rarity.contains(s)) //rarity contains set info too
					set.add(all[i]);
			} catch (Exception ex) {
				ex.printStackTrace(); //Not quite sure what is going wrong here buuut its small as far as I can tell
			}
		}
		String[] setArr = new String[set.size()];
		set.toArray(setArr);
		java.util.Arrays.sort(setArr);
		return setArr;
	}
	/**
	 * Selection sort O(n^2)....currently not usable due to slowness... >5 sec to load. A very informative display of algorithm runtime importance.
	 * @param s
	 * @return
	 */
	public String[] sortByName(String[] s) {
		String[] s2 = new String[s.length];
		for(int i = 0; i < s.length; i++) {
			String smallest = "";
			int in = 0;
			int j;
			for(j = 0; j < s.length; j++) {
				if(s[j] != null) {
					smallest = s[j];
					in = j;
					break;
				}}
			for(int k = 0 ; k < s.length; k++) {
				String n = s[k];
				if(s[k] != null) {
					if(smallest.compareTo(n) > 0) {
						smallest = s[k];
						in = k;
					}
				}}
			s2[i] = smallest;
			s[in] = null;
			j = 0;
		}
		return s2;
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
	public void addCardToDeck(String s) {
	Card card = (Card) CompleteDatabase.get(s); 
	if(deck.get(s) != null) { //increases owned by one if not owned 
		card.cardsInDeck++;
			return;}
	deck.put(card.name, card);
	card.cardsInDeck++;
	}
	
	/**
	 * removes cards
	 */
	public void removeCardFromDeck(String s) {
	Card card = (Card) deck.get(s);
	if(card.cardsInDeck > 0) { //decreases owned by one if owned 
		card.cardsInDeck--;
			return;}
	deck.remove(card.name);
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
			int h = card.cardsInDeck;
			for(int j = 0; j < h; j++) {
				card.cardsInDeck--;
				if(card.cardsInDeck == 0)
					deck.remove(card.name);
				//System.out.println(card.name + " " + card.cardsInDeck);
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
//		if(MTG.get(s) != null) {
//			setOwned(s, ((Card)MTG.get(s)).owned + 1, MTG);
//			return;
//		}
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
		if(owned > 1) {
			setOwned(s, owned - 1, MTG);
		} else {
			((HashTableMap) start.getReference()).remove(s); }
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
	 * Dynamic query attempt
	 */
//	public String[] query(String t3, String s2) throws InvalidKeyException {
//		int count = 0;
//		boolean nameON = false;
//		boolean colorON = false;
//		String v1 = "";
//		String name = t3.toLowerCase();
//		String color = s2;
//			if (!name.equals("n")) {
//				count++;
//				nameON = true;
//			}
//			if (!color.equals("n")) {
//				count++;
//				colorON = true;
//			}
//			
//		if (nameON == true && v1.equals("")) {
//			v1 = "name";
//		}
//		if(colorON == true && v1.equals(""))
//			v1 = "color";
//		String[] li = getAllArray();
//		ArrayList<String> arr = new ArrayList();
//		ArrayList<Card> list = new ArrayList<Card>();
//		for(int z = 0; z < li.length; z++)
//			list.add(getCard(li[z]));
//		
//			
//		if(count == 1) {
//			Card card = new Card();
//			for(int j = 0; j < list.size(); j++) {
//				card = list.get(j);
//					if(card.v1 != null)
//						if(card.getValue(v1).toLowerCase().contains(v1))
//							arr.add(card.getValue(v1));
//			}
//			String[] arr2= new String[arr.size()];
//			arr.toArray(arr2);
//			return  arr2;
//		}
//		return null;
//	}

	//Query for cards based on multiple characteristics. To omit a value use -1 for integers or "" for Strings. Most combinations
	//should work
	/**
	 * I NEED to make this less procedural and make a new method that creates the query based on what input I get in here...Otherwise
	 * I'll end up with factorial operations...with 7 parameters that's 5040 combinations...sooo yeah...
	 * @param lis
	 * @param s
	 * @param i
	 * @param i2
	 * @param i3
	 * @param s2
	 * @param s3
	 * @param s4
	 * @param t
	 * @return
	 * @throws InvalidKeyException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] query(String[] lis, String s, int i, int i2, int i3, String s2, String s3, String s4, String t, String t2, String t3) throws InvalidKeyException {
		String color = s;
		String[] li = lis;
		int power = i;
		int toughness = i2;
		int owned = i3;
		String type1 = s2;
		String type2 = s3;
		String rarity = s4;
		String tribal = t;
		String text = t2;
		String name = t3.toLowerCase();

		ArrayList<String> arr = new ArrayList();
		ArrayList<Card> list = new ArrayList<Card>();

		for(int z = 0; z < li.length; z++) {
			list.add(getCard(li[z]));
		}
		if(rarity.equals("common"))
			rarity = "-C";
		if(rarity.equals("uncommon"))
			rarity = "-U";
		if(rarity.equals("rare"))
			rarity = "-R";
		if(rarity.equals("mythic"))
			rarity = "-M";
		
		if(!name.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.name != null)
						if(card.name.toLowerCase().contains(name))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		
		if(!text.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null && text != null)
					if(card.text != null)
						if(card.text.contains(text))
							arr.add(card.name);

			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(power != -1 && toughness != -1 && !color.equals("n") && owned != -1 && !type1.equals("n") && !type2.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(!card.color.equals(null) && !card.type1.equals(null) && !card.type2.equals(null))
						if(card.power == power && card.toughness == toughness && card.color.equals(color) && card.owned == owned
						&& card.type1.equals(type1) && card.type2.equals(type2))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(power != -1 && toughness != -1 && !color.equals("n") && owned != -1 && !type1.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)	
					if(!card.color.equals(null) && !card.type1.equals(null))
						if(card.power == power && card.toughness == toughness && card.color.equals(color) && card.owned == owned
						&& card.type1.equals(type1))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(power != -1 && toughness != -1 && !color.equals("n") && owned != -1) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(!card.color.equals(null))
						if(card.power == power && card.toughness == toughness && card.color.equals(color) && card.owned == owned)
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(power != -1 && toughness != -1 && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.power == power && card.toughness == toughness && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(owned != -1 && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.owned == owned && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(power != -1 && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.power == power && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(toughness != -1 && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.toughness == toughness && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(power != -1 && toughness != -1 && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.power == power && card.toughness == toughness && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !type2.equals("n") && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !type2.equals("n") && !color.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !type2.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !color.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type2.equals("n") && !color.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !type2.equals("n") && !color.equals("n") && !rarity.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.color.equals(color) && card.rarity.contains(rarity) 
								&& card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !type2.equals("n") && !color.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.color.equals(color) 
								&& card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !type2.equals("n") && !rarity.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.rarity.contains(rarity) 
								&& card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !color.equals("n") && !rarity.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.color.equals(color) && card.rarity.contains(rarity) 
								&& card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type2.equals("n") && !color.equals("n") && !rarity.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.color.equals(color) && card.rarity.contains(rarity) 
								&& card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type1, Type2, Tribal
		if(!type1.equals("n") && !type2.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type2.equals(type2) && card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type2, Tribal, Rarity
		if(!type2.equals("n") && !tribal.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.type3.equals(tribal) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type1, Tribal, Rarity
		if(!type1.equals("n") && !tribal.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type3.equals(tribal) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Rarity, Color, Tribal
		if(!color.equals("n") && !tribal.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.color.equals(color) && card.type3.contains(tribal) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type1, Color, Rarity
		if(!type1.equals("n") && !color.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type2, Color, Rarity
		if(!type2.equals("n") && !color.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type2, Color, Tribal
		if(!type2.equals("n") && !color.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		//Type1, Color, Tribal
		if(!type1.equals("n") && !color.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!color.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.color.equals(color) && card.type3.contains(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.type3.contains(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}


		if(!color.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.color.equals(color) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type2.equals("n") && !rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.rarity.contains(rarity))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		
		if(!type2.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.type3.equals(tribal))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!rarity.equals("n") && !tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null) {
						if(card.rarity.contains(rarity) && card.type3.contains(tribal))
							arr.add(card.name);
					}
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(power != -1 && toughness != -1) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.power == power && card.toughness == toughness)
						arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		
		

		if(owned != -1 && !type1.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.owned == owned && card.type1.equals(type1))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(owned != -1 && !type2.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.owned == owned && card.type2.equals(type2))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n") && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type1.equals(type1) && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type2.equals("n") && !color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.type2.equals(type2) && card.color.equals(color))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		
		if(!(type1).equals("n") && !type2.equals("n"))  {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if((card.type1) != null && card.type2 != null) 
						if((card.type1).equals(type1) && (card.type2).equals(type2))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(power != -1) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.power == power)
						arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(toughness != -1) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.toughness == toughness)
						arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}

		if(!type1.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.type1 != null)
						if((card.type1).equals(type1))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(!type2.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.type2 != null)
						if((card.type2).equals(type2))
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(owned != -1) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.owned == owned)
						arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(!color.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.color != null)
						if(card.color.equals(color)) 
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		
		if(!rarity.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.rarity != null)
						if(card.rarity.contains(rarity)) 
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		if(!tribal.equals("n")) {
			Card card = new Card();
			for(int j = 0; j < list.size(); j++) {
				card = list.get(j);
				if(card != null)
					if(card.type3 != null)
						if(card.type3.contains(tribal)) 
							arr.add(card.name);
			}
			String[] arr2= new String[arr.size()];
			arr.toArray(arr2);
			return  arr2;
		}
		String[]arrr = new String[1];
		arrr[0] = "None Found";
		return arrr;
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
			try {
			price = value.getPriceL(names[i]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			prices[i] = price;
			nameAndPrice += names[i] + ":" + price + "\n";
			PrintWriter out = new PrintWriter(home + "/Desktop/Card Prices.txt");
			out.print(nameAndPrice);
			out.close();
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
				//e.printStackTrace();
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

			int power = Integer.parseInt(powerS);
			int toughness = Integer.parseInt(toughnessS);

			CompleteDatabase.put(name, new Card(name, color, CMC, power, toughness, text, type1, type2, tribal, rarity, picURL));
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
