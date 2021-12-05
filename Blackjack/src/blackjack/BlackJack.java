package blackjack;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BlackJack {
	static boolean userGui; 
	static boolean enterName;// to let players enter their names( or make it automatic ) 
	static GUI gui;
	static Game gameObject;
	
	
	
	public static void main(String[] args) {
		
		// start (GUI) game   --> call --> guiGame()   $
		// start console game --> call --> blackJack() $ feature -> (you can play as many times as you like)

//		blackJack();
		guiGame();
	
	}

	
	
	
	
	
	static void guiGame() { // play with GUI
		userGui = true; 
		enterName = true;
		
		
		gameObject = new Game();
		
		gameObject.generateCards(); // generate card deck
		gameObject.setPlayersInfo(enterName); // Initial cards
		
		gui = new GUI();
		gui.runGUI( gameObject.getCardDeck(), gameObject.getPlayers()[0].getPlayerCards(), gameObject.getPlayers()[1].getPlayerCards(), gameObject.getPlayers()[2].getPlayerCards(), gameObject.getPlayers()[3].getPlayerCards() );
		
		startGame();
		endMessage();
//		System.exit(0);
	}
	
	static void blackJack() // play without GUI
	{
		 	
		Scanner replay = new Scanner(System.in);
		String newGame = "yes";
		userGui = false;
		enterName = true; 
		
		
		
		while(newGame.equals("yes")) { //  Play Again
			gameObject = new Game();
			gameObject.generateCards();
			gameObject.setHighScore(0);

		
			 
			gameObject.setPlayersInfo(enterName); // Initial cards
			startGame();
			
			sleep(2);

			System.out.print("\n\n(Start New Game \"yes\")\n: ");
			newGame = replay.next(); // to start new gameObject
			
		}
	endMessage();
	}// end 

	
	
    static void winner() {
		int count = 0; // to check push statement
		int endWinner = -1;
		for(int i=0;i<3;i++) {
			if(gameObject.getPlayers()[i].getScore() == gameObject.getHighScore()) {
				
				count++;
				endWinner = i;
				
			}
		}
		
		// to handle blackJack with player and dealer
		if(gameObject.getPlayers()[3].getScore() == gameObject.getHighScore() && count> 0) {

			count++;
			
		}
		
		if(count > 1) {
			System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! P U S H !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		else if(count == 1) {
			System.out.println("\n\n\n                                      $ Congratulations( Player" + (endWinner + 1) + " )\n"       );
			System.out.printf("-------------------------------- %s { %s , %s } --------------------------------","winner info","name: " + gameObject.getPlayers()[endWinner].getName(),"score: " + gameObject.getPlayers()[endWinner].getScore() );
		}
		else 
			System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!All players lost!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! \n"
					+              "                                 $Dealer Won$                                 ");

		

	}//end
	
    
    static void dealerTurn() {
	   Card temp; // store the drawn card for the dealer
	   boolean mess = true;
	   
	  //compare dealer score to game high score
	   while(gameObject.getPlayers()[3].getScore() <= gameObject.getHighScore() && gameObject.getPlayers()[3].getScore() != 21) {
			if(mess) {
				System.out.println("--------------------------------Dealer Turn--------------------------------\n");
				mess = false;
			}
		   System.out.println("dealer score: "+gameObject.getPlayers()[3].getScore());

			temp = gameObject.draw();
			gameObject.getPlayers()[3].setCard(temp);
			
			if(userGui) {
				gui.updateDealerHand(temp, gameObject.getCardDeck());
			}
			
//			wait two seconds
			 sleep(2);
			
	   } // end while
		System.out.println("dealer score: "+gameObject.getPlayers()[3].getScore());
		gameObject.checkHighScore(3);

   }//end class
  
	static boolean playerDone(int turn) { // Handle case [black jack or busted] 
		
		boolean happened = false;
		if(gameObject.getPlayers()[turn].busted()) 
		{
			scoreMessage(turn);
			System.out.println("\nplayer"+(turn+1)+" !!! BUSTED !!!\n");
		
//			turnMessage(turn);
			happened = true;
			
		}
		
		else if(gameObject.getPlayers()[turn].gotBlackJack()) {
			
			scoreMessage(turn);
			System.out.println("\nplayer"+(turn+1)+" !!! blackJack !!!\n");
//			turnMessage(turn);
			happened = true;

			
			
		}
		return happened;
	}
	
	
	static void startGame( ) {
		String choose; // store player choice (stand or hit)
		Card temp;	// temporarily store the new card 
		int turn = 0; // player turn

		Scanner playerChoice = new Scanner(System.in);
		
		System.out.println("\n=====================================================================================================");
		System.out.println("\n\n                                        G O O D   L O O K \n\n");
		turnMessage(turn);

		while(turn < 3) 
		{
			
			if(playerDone(turn)) { // in case got (blackjack or busted)
				gameObject.checkHighScore(turn);
				turn++;
				//wait one second
				 sleep(1);
				turnMessage(turn);
				continue;
			}
			
			
			scoreMessage(turn);
			
			System.out.printf("\n\n%s:\n",gameObject.getPlayers()[turn].getName()+" ( Stand or Hit )");
			choose = playerChoice.nextLine(); 
			
			if(choose.equals("hit") || choose.equals("Hit") || choose.equals("h") || choose.equals("HIT"))
			{
				temp = gameObject.draw();
				gameObject.getPlayers()[turn].setCard(temp);
				
				
				if(userGui) {
					gui.updatePlayerHand(temp, turn);

				}
			
				temp.info(); // About the drawn card
				

			}
			else if(choose.equals("stand") || choose.equals("Stand") || choose.equals("s") || choose.equals("STAND")) 
			{
				gameObject.checkHighScore(turn);
				turn++;
				turnMessage(turn);
				continue;
				
			}else {
				System.out.println("invalid choice");

			}
			
		}
		
		dealerTurn();
		winner();
		

		
	}
	
	// Messages methods
		static void turnMessage(int turn) {
			if(turn<3)
			System.out.println("\n\n---------------------------------Player["+(turn+1)+"] Turn---------------------------------");

		}//end
		
		
		static void scoreMessage(int turn) {
			
				System.out.println("your score = " + gameObject.getPlayers()[turn].getScore());


		}//end
		
		static void endMessage() {
			System.out.println("\n\n\n                                   Thanks for playing               ");

		}
	
		static void sleep(int seconds) {
			try {
				TimeUnit.SECONDS.sleep(seconds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    

}
