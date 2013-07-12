package MTGApplication;

public class Deck {
	private String[][] cardList;
	
	public Deck (String[][] s) {
		cardList = s;
	}
	
	public String[][] getDeck() {
		return cardList;
	}
	
	public void setDeck(String[][] s) {
		cardList = s;
	}
}
