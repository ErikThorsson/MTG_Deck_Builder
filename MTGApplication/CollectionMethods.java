package MTGApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dataStructures.BasicTree;
import dataStructures.HashTableMap;
import dataStructures.TreeNode;

public class CollectionMethods extends BasicTree {
	public BasicTree tree = new BasicTree();
	private String saveFile = "";

	public CollectionMethods() throws InvalidKeyException, IOException {		

//----> comment this out if you dont want to play with text files and save() / load()
		
		String sFile;
		String home = System.getProperty("user.home");
		sFile = (home + "/Desktop/VCO/VCOSave.txt");
		saveFile = sFile;
		this.loadCompleteDatabase();
		try {
		this.load();
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
// <------
//				String[] cards = this.getCategory("cD");
//				for(int i = 0; i < cards.length; i++) {
//					this.addCard(cards[i]);
//				}
	}
	
	public static void main(String[] args) throws InvalidKeyException, IOException {
		CollectionMethods test = new CollectionMethods();
		test.loadCompleteDatabase();
//		String[] all = test.getCategory("cD");
//		for(int i = 0; i <all.length; i++ ) {
		//System.out.println(java.util.Arrays.toString(test.query("green", -1, -1, -1, "n", "n")));
		//}
		System.out.print(test.getCard("Cancel").color);
	}

	//returns a String[] of the cards and # owned in a given hashtable
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
		return  arr2;
	}
	
	//returns a String[] of all owned cards
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
		return  arr2;
	}
	
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


	//Adds non land cards not held in database.
	@SuppressWarnings("unchecked")
	public void addCard(String s, String s2, int i, int i2, String t, String ty, String ty2, String ty3, String url) throws InvalidKeyException {
		Card card = new Card(s, s2, i, i2, t, ty, ty2, ty3, url);
		if(MTG.get(s) != null) {
			setOwned(s, ((Card)MTG.get(s)).owned + 1);
			return;
		}
		MTG.put(card.name, card);
		CompleteDatabase.put(card.name, card);
		spells.put(card.name, card);
		if(card.color == "red") {
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
		card.owned =+ 1;
	}


	//Adds card if in database w/ String. Tis' the only way to add land.
	@SuppressWarnings("unchecked")
	public void addCard(String s) throws InvalidKeyException {
		Card card = (Card) CompleteDatabase.get(s);

		if(MTG.get(s) != null) {
			setOwned(s, ((Card)MTG.get(s)).owned + 1);
			return;
		}
		
		try{ 
			MTG.put(card.name, card);
		} catch (NullPointerException n) {
			InvalidKeyException ex = new InvalidKeyException();
			throw ex;
		}
		 
		if(card.color == null) {
			land.put(card.name, card);
			card.owned =+ 1;
			return;
		}
		
		spells.put(card.name, card);
		if(card.color == "red") {
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
		card.owned =+ 1;
	}

	//quickly sets the number of owned card copies. 

	public void setOwned(String s, int i) {
		((Card) MTG.get(s)).setOwned(i);
	}

	//Removes from appropriate hashtables w/ tree traversal.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeCard(String s) {
		String color = "Iwonder";
		int owned = ((Card)MTG.get(s)).owned;
		if(land.get(s) != null) {
			if(owned > 1) {
				setOwned(s, owned - 1);
			} else {
				land.remove(s);
				MTG.remove(s);
			}
			return;
		} else {
			color = ((Card) MTG.get(s)).color;
		}
		if(owned > 1) {
			setOwned(s, owned - 1);
		} else {
			MTG.remove(s);
			spells.remove(s);
		}
		TreeNode start = ((TreeNode) treeNodes.get(color));
		//remove it from its color
		if(owned > 1) {
			setOwned(s, owned - 1);
		} else {
			((HashTableMap) start.getReference()).remove(s); }
		//then remove from every other possible subtree
		List<TreeNode> children = start.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			TreeNode child = (TreeNode) iter.next();
			if(owned > 1) {
				setOwned(s, owned - 1);
			} else {
				((HashTableMap) child.getReference()).remove(s);  } 
			List<TreeNode> grandkids = child.getChildren();
			for (Iterator iter2 = grandkids.iterator(); iter2.hasNext();) {
				TreeNode child2 = (TreeNode) iter2.next(); 
				if(owned > 1) {
					setOwned(s, owned - 1);
				} else {
					((HashTableMap) child2.getReference()).remove(s); }
			}
		}
	}


	//returns a list of entries given a hashtable
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> entries(HashTableMap h) {
		ArrayList<Card> list = h.cardEntries();
		return list;
	}

	//Query for cards based on multiple characteristics. To omit a value use -1 for integers or "" for Strings. Most combinations
	//should work
	
@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] query(String s, int i, int i2, int i3, String s2, String s3) {
		String color = s;
		int power = i;
		int toughness = i2;
		int owned = i3;
		String type1 = s2;
		String type2 = s3;
		ArrayList<String> arr = new ArrayList();

		if(power != -1 && toughness != -1 && !color.equals("n") && owned != -1 && !type1.equals("n") && !type2.equals("n")) {
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
		
		if(owned != -1 && !type1.equals("n")) {
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
		
		if(!type1.equals("n") && !type2.equals("n") && !color.equals("n")) {
			ArrayList<Card> list = entries(MTG);
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
		
		if(power != -1 && toughness != -1) {
			ArrayList<Card> list = entries(MTG);
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
		
		if(power != -1) {
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
		
		if(!(type1).equals("n") && !type2.equals("n"))  {
			ArrayList<Card> list = entries(MTG);
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
		if(!type1.equals("n")) {
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
			ArrayList<Card> list = entries(MTG);
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
		data += card.type1 + "\n";
		data += card.type2 + "\n";
		data += card.text + "\n";
		data += "#Owned = " + card .owned + "\n";
		return data;
	}

	
	//SOP a Card object's data
	public String nameQuery(Card c) {
		Card card = c;
		String data = "";
		data += card.name + "\n";
		data += card.color + "\n";
		data += card.type1 + "\n";
		data += card.type2 + "\n";
		data += card.text + "\n";
		data += "#Owned = " + card .owned + "\n";
		return data;
	}

	public Card getCard(String s) throws InvalidKeyException {
		Card card = new Card();
		try {
		card = (Card) CompleteDatabase.get(s);
		} catch (NullPointerException n) {
			InvalidKeyException ex = new InvalidKeyException();
			throw ex;
		}
		return card;
	}
	
	//saves to a specific text file on my computer
	public void save() throws FileNotFoundException, InvalidKeyException, MalformedURLException {
		PrintWriter out = new PrintWriter(saveFile);	
		for(int i = 0; i < this.getAllArray().length; i++) {
			out.print(this.getAllArray()[i]);
			out.print(":");
			out.print(this.getOwned()[i]);
			out.print(":");
		}
		out.close();
	}
	
	public void setSaveFile(String s) throws FileNotFoundException {
		String home = System.getProperty("user.home");
		PrintWriter out = new PrintWriter(home + "/Desktop/VCO/VCOSaveLocation.txt");
		out.print(s);
		}
	
	//reads the save text file, and adds the correct number of cards to your database
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
	
public void loadCompleteDatabase() throws IOException {
	StringBuilder sFile = new StringBuilder();
	String name;
	String color;
	String type1;
	String type2;
	String rarity;
	String powerS;
	String toughnessS;
	String text;
	String picURL;
	sFile = readFromFile("/Users/eorndahl/Desktop/VCO/readFormattedCards.txt");
	//String[] currentData = sFile.toString().split("/////");
	String[] lines = sFile.toString().split("::");
	int cur = 0;
	//for the string array containing all data
	for(int i = 0; i < lines.length; i++) {
			cur = 0;
			name = lines[i + cur]; cur++;
			color = lines[i + cur]; cur++;
			type1 = lines[i + cur]; cur++;
			type2 = lines[i + cur]; cur++;
			rarity = lines[i + cur]; cur++;
			powerS = lines[i + cur]; cur++;
			toughnessS = lines[i + cur]; cur++;
			text = lines[i + cur]; cur++;
			picURL = lines[i + cur]; cur++;
			i += 8;
			
			int power = Integer.parseInt(powerS);
			int toughness = Integer.parseInt(toughnessS);

			CompleteDatabase.put(name, new Card(name, color, power, toughness, text, type1, type2, rarity, picURL));
	}
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
