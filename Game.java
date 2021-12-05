package blackjack;
import java.util.Random;
import java.util.Scanner;


public class Game {
	
	 private Player[] players = new Player[4];
	 private Card[] cardDeck = new Card[52];
	 private int highScore;
	 private int index; // index of random cards from -> card deck
	 private Random rand = new Random();
	
	 
	//default constructor
	 Game(){
//		 generate();
		 highScore = 0;
		 
		 
	 }

	 
	 
	//setters
	void setHighScore(int score) {
		highScore = score;
	}
	
	 boolean validName(int index,String name) {
		 boolean valid = true;
		 for(int i=0;i<index;i++) {
			 if(players[i].getName().equals(name)) {
				 valid = false;
				 break;
			 }
		 }
		 if(name.equals("Dealer") || name.equals("dealer")|| name.equals("DEALER")) {
			 valid = false;
		 }
		 return valid;
	 }
	 void setPlayersInfo(boolean enterName) { // Initialize the game
		String inputName = new String();
		System.out.println("\n----------------------------");

		for(int i=0;i<3;i++) {
			
			if(enterName) {
				do {
				Scanner input = new Scanner(System.in);
				System.out.print("$player"+ (i+1) +" name: ");
				inputName = input.nextLine();
				if(inputName.equals("")){
					System.out.println("\n--- invalid name ---\n");
				}
				if(!validName(i,inputName)) {
					System.out.println("\n--- already taken ---\n");
				}
				}while(inputName.equals("")|| !validName(i,inputName));
				
			}
			else {	
				
				inputName = "player" + (i+1);
					
			}
			players[i] = new Player(inputName);
			players[i].setCard(draw());
			players[i].setCard(draw());

		}
		setDealerInfo();
		
		

	}

	 
	 
	//getters
	
	 Player[] getPlayers() {
		 return players;
	 }
	
	 Card[] getCardDeck() {
		
		return cardDeck;
		
	}
	
	 int getHighScore() {
		return highScore;
	}

	
	 
	//other methods
	void generateCards() {
		
		int count = 0;
		for(int suit = 0; suit<4; suit++) 
		{
			 
			 for(int rank = 0; rank<13; rank++)
			 {
				 
				 cardDeck[count] = new Card(suit,rank);
				 
				 count++; // number of cards
			 }
		 }
		
	}
	
	Card draw() {
		
		Card card;
		while(true) {
			
		 index = rand.nextInt(52);
		 if(cardDeck[index] != null) {
			 card =new Card(cardDeck[index]);
			 cardDeck[index] = null;
			 break;
		 }
//		 System.out.println("here");
		
	}
		return card;
	
	}
	
	void checkHighScore(int Index) {// update the game maximum score 
		 
		 if(players[Index].getScore() > highScore && players[Index].getScore() <= 21) {
			 highScore = players[Index].getScore();
		 }
	 }
	

	void PlayersInfo() { // to get all players with all cards info
		for(int i=0;i<3;i++) {
			players[i].cardsIfnoPlayers();
		}
		players[3].cardsIfnoDealer();
	}
	
	
	 private void setDealerInfo() { // it's called by default
			players[3] = new Player("Dealer");
			players[3].setCard(draw());
			players[3].setCard(draw());
			
	}
	
	
	
	
}

