package automatedDatabase;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import MTGApplication.Card;
import MTGApplication.CollectionMethods;

public class GetMissingPictures {
	public static void main (String[] args) throws IOException, InterruptedException, AWTException, InvalidKeyException {
		CollectionMethods test = new CollectionMethods();
		File cards = new File("/Users/eorndahl/Desktop/Missing Pictures.txt");
		BufferedReader br = new BufferedReader(new FileReader(cards));
        String lineRead = "";
        ArrayList<String> missingCards = new ArrayList<String>();
        while ((lineRead = br.readLine()) != null) {
        missingCards.add(lineRead);
        }
        RefinedImageGrab bot = new RefinedImageGrab();
        for(int i = 0; i < missingCards.size(); i++) {
        	Card card = test.getCard(missingCards.get(i));
				bot.getImgURL(missingCards.get(i));
        }
	}
}
