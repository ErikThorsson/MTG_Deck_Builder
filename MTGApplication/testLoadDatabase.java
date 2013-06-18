package MTGApplication;

import java.io.IOException;
import java.security.InvalidKeyException;

public class testLoadDatabase {
	public static void main (String[] args) throws InvalidKeyException, IOException {
		CollectionMethods test = new CollectionMethods();
		test.loadCompleteDatabase();
		String[] cardNames = test.getCategory("CompleteDatabase");
//		for (int i = 0; i < cardNames.length; i++)  {
//			if (cardNames != null)
//			System.out.println(cardNames[i]);
//		}
	}
}
