package MTGApplication;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

public class Card {
	public String name;
	public int power;
	public int toughness;
	public String text;
	public String type1;
	public String type2;
	protected String type3;
	public int owned;
	public String color;
	public ImageIcon img = null;
	public String imgURL;
	public String rarity;

	
	public Card() {}
	
	public Card(String s) {
		name = s;
	}

	//certain creatures can have up to three types (permanent, creature, tribal identifier e.g. "elf")
	public Card(String s, String s2, int i, int i2, String t, String ty, String ty2, String r, String url) {
		name = s;
		color = s2;
		power = i;
		toughness = i2;
		text = t;
		type1 = ty;
		type2 = ty2;
		//type3 = ty3;
		owned = 0;
		imgURL = url;
		rarity = r;
		img = null;
	}
	
	public void setOwned(int i) {
		owned = i;
	}
	
	public int getOwned() {
		return owned;
	}
	
	public String getRarity () {
		return rarity;
	}
	
	public void setImg(ImageIcon i) {
		img = i;
	}
	
	public ImageIcon getImg() throws MalformedURLException {
		if(img == null)
		img = new ImageIcon(new URL(imgURL));
		return img;
	}
}
