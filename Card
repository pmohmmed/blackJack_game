package blackjack;

public class Card {

    private final int suit; // (0) Clubs, (1) Diamonds, (2) Hearts, (3) Spades
	private final int rank; // 0->Ace, 1->2, 2->3, ... , 10->Jack, 11->Queen, 12->King
	private final int value;// 1 for Ace, 2 for 2, ..., 10 for {10, jack, queen, king}
	
	/*
	 * 
	 * 
	 */
	
//	Card(){
//		suit = 0;
//		rank = 0;
//		value = 0;
//	}
	
	//Parameterize constructor
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
		
		if((rank + 1) >= 10) {
			this.value = 10;
		}
		else
			this.value = (rank + 1);
		
	}
	 
	// copy constructor
	public Card(Card cop){
		this.suit = cop.suit;
		this.rank = cop.rank;
		this.value = cop.value;
	}

	 
	
	
	// getters for attributes
	int getSuit() {
		return suit;
	}
	
	int getRank() {
		return rank;
	}
	
	int getValue() {
		return value;
	}
	
	void info() {
		System.out.printf(
		"%s { %s %d, %s %d, %s %d }\n",
		"about card", "suit:", suit, "rank:", rank, "value:", value
		);
		
	}
	
}
