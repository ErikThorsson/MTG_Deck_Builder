package MTGApplication;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
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
	
	public Card() {}
	
	public Card(String s) {
		name = s;
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
	
	public void setPrice(String s) {
		price = s;
	}
	
	public ImageIcon getImg() throws IOException {
		BufferedImage image = null;
		if(img == null) {
			try {
				if(this.name.contains("//")) {
					String cut = this.name.replace("//", "");
					image = ImageIO.read(new File(home + "/Desktop/VCO/Pictures Try 2/" + cut + ".jpg"));
				} else {
				image = ImageIO.read(new File(home + "/Desktop/VCO/Pictures Try 2/" + this.name + ".jpg"));
				}
			} catch (Exception ex) {
				if(this.name.contains("//")) {
					String cut = this.name.replace("//", "");
					System.out.println(cut);
					image = ImageIO.read(new File("/Volumes/NIGEL/VCO/Pictures Try 2/" + cut + ".jpg"));
				} else {
				image = ImageIO.read(new File("/Volumes/NIGEL/VCO/Pictures Try 2/" + this.name + ".jpg"));
				}
			}
		img = new ImageIcon(image);
		}
		return img;
	}
}
