package automatedDatabase;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;

import MTGApplication.CollectionMethods;

public class GetMissingPictures {
	public static void main (String[] args) throws IOException, InterruptedException, AWTException, InvalidKeyException {
//		File missing = new File("/users/eorndahl/Desktop/Missing Pictures.txt");
//		BufferedReader br = new BufferedReader(new FileReader(missing));
//        String lineRead = "";
		CollectionMethods test = new CollectionMethods();
		test.loadCompleteDatabase();
		String[] s = test.getCategory("cD");
        RefinedImageGrab bot = new RefinedImageGrab();
        for(int i = 0; i < s.length; i++) {
        	if(s[i].contains("//")) 
        		bot.getImgURL(s[i]);
        }
	}
}
