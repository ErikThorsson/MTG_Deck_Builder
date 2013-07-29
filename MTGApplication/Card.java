package MTGApplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Card {
	public String name;
	public int power;
	public int toughness;
	public String text;
	public String type1;
	public String type2;
	public String type3;
	public int owned;
	public String color;
	public ImageIcon img = null;
	public String imgURL;
	public String rarity;
	private String home = System.getProperty("user.home");
	public String CMC;
	public String price;
	public int cardsInDeck;
	public String notes;
	public boolean inSB;
	
	public Card() {}
	
	public Card(String s) {
		name = s;
	}
	
	public Card(String s, int i) {
		name = s;
		owned = i;
	}

	//certain creatures can have up to three types (permanent, creature, tribal identifier e.g. "elf")
	public Card(String s, String s2, String s3, int i, int i2, String t, String ty, String ty2, String ty3, String r, String url) {
		name = s;
		color = s2;
		CMC = s3;
		power = i;
		toughness = i2;
		text = t;
		type1 = ty;
		type2 = ty2;
		if(ty3 != null)
		type3 = ty3.toLowerCase();
		owned = 0;
		imgURL = url;
		rarity = r;
		img = null;
		price = "0.0";
		cardsInDeck = 0;
		notes = "";
	}
	
	public void setOwned(int i) {
		owned = i;
	}
	
	public int getOwned() {
		return owned;
	}
	
	public String getValue(String s) {
		if (s.equals("name")) {
			return name;
		}
		if(s.equals("color")){
			return color;
		}
		return null;
	}
	
	public String getRarity () {
		return rarity;
	}
	
	public void setImg(ImageIcon i) {
		img = i;
	}
	
	public void setPrice(String s) {
		price = s;
	}

	public ImageIcon getImg() throws IOException {
		if(img == null) {
			BufferedImage image = null;
			try {
				String cut = this.name;
				if(this.name.contains("//"))
					cut = this.name.replace("//", "");
				image = ImageIO.read(new File(home + "/Desktop/VCO/Pictures Try 2/" + cut + ".jpg"));
				img = new ImageIcon(image);
				return img;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return img;
	}
}
