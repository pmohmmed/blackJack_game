package blackjack;

public class Player {
	private String name;
	private int score;
	private Card[] cards = new Card[11];
	private int cardNum; // number of cards in player's hand
	
	private boolean blackJack; // got a black jack ( optional )
	private boolean bust; // exceed 21 ( optional )
	
	/*
	 * 
	 * 
	 */
	
	Player(String name){
		setName(name);
		score = 0;
		cardNum=0;
		blackJack = false;
		bust = false;
		
	}
	
	
	
	
	
	//setters
	
	void setName(String name) {
		this.name = name;
		
	}
	
	void setCard(Card newCard) {
		cards[cardNum] = new Card(newCard); // Card's copy constructor
		
		updatePlayerScore();
		cardNum++;
	}
	
	
	// getters
	String getName() {
		return name;
	}
	int getScore() {
		return score;
	}
	Card[] getPlayerCards() {
		return cards;
	}
	boolean gotBlackJack() {
		return blackJack;
	}
	boolean busted() {
		return bust;
	}
	
	
	
	//player's cards info
		void cardsIfnoPlayers() {
			System.out.println("\n#### (" +name+"'s cards) #####");
			for(int i=0;i<cardNum;i++) {
				System.out.printf("%s %d { ","Card:",i+1);
				System.out.printf("%s%d | ","Card Suit: ",cards[i].getSuit());
				System.out.printf("%s%d | ","Card Rank: ",cards[i].getRank());
				System.out.printf("%s%d ","Card Value: ",cards[i].getValue());
				System.out.println("}");
				
			}
			System.out.println("score: "+ score);
		}
		
	//dealer's cards info
		void cardsIfnoDealer() {
			System.out.println("\n#### (" +name+"'s cards) #####");
			
				System.out.printf("%s %d { ","Card:",1);
				System.out.printf("%s%d | ","Card Suit: ",cards[0].getSuit());
				System.out.printf("%s%d | ","Card Rank: ",cards[0].getRank());
				System.out.printf("%s%d ","Card Value: ",cards[0].getValue());
				System.out.println("}");
				
			System.out.println("card:2 { unkown }");
			System.out.println("score: unkown");
		}
		
		// UPDATE player's score and CHECK (blackJack or busted) 
		private void updatePlayerScore() 
		{ 
			
			score+=cards[cardNum].getValue(); // score
		
			if(score > 21) // status
			{
//				score = -1;
				bust = true;
				
			}	
			else if(score == 21) {blackJack = true;}
				
		
		}
		
}
