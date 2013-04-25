package MTGApplication;

import java.io.IOException;
import java.security.InvalidKeyException;

public class Hello {
	public static void main (String[] args) throws InvalidKeyException, IOException {
		CollectionMethods test = new CollectionMethods();
		test.loadCompleteDatabase();
		String[] all = test.getCategory("cD");
		//get every card into an array
		Card[] card = new Card[all.length];
		for(int i = 0; i < all.length; i++) {
			card[i] = test.getCard(all[i]);
		}
		System.out.print(card.length);
//		for(int i = 0; i <all.length; i++ ) {
//			System.out.println(card[i].name);
//		}
//		for(int i = 0; i <all.length; i++ ) {
//			System.out.println(all[i]);
//		}
	}
}
