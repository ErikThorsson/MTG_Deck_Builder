package automatedDatabase;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import MTGApplication.Card;
import MTGApplication.CollectionMethods;

public class imgGrab {
	private static boolean haveFile = true;
	public static void main(String[] args) throws IOException, InvalidKeyException, InterruptedException {
		CollectionMethods test = new CollectionMethods();
		test.loadCompleteDatabase();
		String[] all = test.getCategory("cD");
		//get every card into an array
		Card[] card = new Card[all.length];
		for(int i = 0; i < all.length; i++) {
			card[i] = test.getCard(all[i]);
		}
//		for(int i = 0; i <all.length; i++ ) {
//			System.out.println(card[i].name);
//		}
//		for(int i = 0; i <all.length; i++ ) {
//			System.out.println(all[i]);
//		}

		//now get its url link download the image and save the image with its name	
		for(int i = 0; i < card.length; i++) {
		Thread.sleep(1000);
		try {
		    // retrieve image
			BufferedImage img = ImageIO.read(new URL(card[i].imgURL));
//			try{
//			String f = new File("/Users/eorndahl/Desktop/VCO/Pictures/" + card[i].name + ".jpg").getName();
//			}catch (Exception ex) {
//				haveFile = false;
//			}
			//if(haveFile == false) {
		    File outputfile = new File("/Users/eorndahl/Desktop/VCO/Pictures Try 2/" + card[i].name + ".jpg");
		    ImageIO.write((RenderedImage) img, "jpg", outputfile);
		   // haveFile = true;
		} catch (IOException e) {
		  //
		}
		}
	}
}
