package Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nick
 */

public class Game {

    //Variables to store the games info and state 
    private final String name;
    private final ArrayList<Player> players;
    private final Dealer dealer;
    private boolean continueGame = true;
    private final Scanner input = new Scanner(System.in);
    private boolean betsPlaced = false;
    
    /**
     * 
     * @param name
     * @param players
     * @param dealer
     */
    //Creates the game with the given name, player arrayList, and dealer
    public Game(String name, ArrayList<Player> players, Dealer dealer) {
        this.name = name;
        this.players = players;
        this.dealer = dealer;
    }

    //Starts the game
    public void startGame() {
        System.out.println("Welcome to " + name + "!");
        
        //Continues playing the game while continueGame is true
        while (continueGame) {
            
            //Shuffles the cards before each round
            dealer.shuffleGroupOfCards();
            
            //Starts a new round
            playRound();
        }
    }
    
    //Plays the round
    private void playRound() {
        placeBets();
        dealToAll();
        checkNatural();  
        playerTurn();
        dealerTurn();
        determineWinners();
        endGame();
    }
    
    //Takes the players bet if bets haven't already been placed
    private void placeBets() {
        if (!betsPlaced) {
            //Start of round - players check their pot and place their betss
            for (Player player: players) {

                player.checkPot();
                System.out.println("Place your bet " + player.getName());
                float playerBet = input.nextFloat();
                player.placeBet(playerBet);
            }
            
            betsPlaced = true;
        }
    }
    
    //Deals cards to all the players and the dealer
    private void dealToAll() {
        //The deal - players and dealer are dealt 1 card at a time. The dealer takes their 2nd card face down.
        while (dealer.getDealerHand().getHandSize() < 2) {
            
            for (Player player: players) {
                dealCardToPlayer(player);
            }
            
            dealCardToDealer();
        }    
    }
    
    /**
     * 
     * @param player 
     */
    //Helper to handle dealing cards to a player
    private void dealCardToPlayer(Player player) {
        Card dealtCard = dealer.dealCard(player.getHand());
        System.out.println(player.getName() + " is dealt a " + dealtCard);
    }
    
    //Helper to handle dealing cards to the dealer
    private void dealCardToDealer() {
        Card dealtCard = dealer.dealCard(dealer.getDealerHand());
        if (dealer.getDealerHand().getHandSize() == 1) {
                System.out.println("Dealer draws a " + dealtCard);
            }
            
        else {
            System.out.println("Dealer draws a card face down");
        }
    }
    
    //Checks if any player has a blackjack right after the deal and pays them out accordingly
    private void checkNatural() {
        
        //Iterates through the players
        for (Player player: players) {
            
            //Checks if the player and the dealer both have blackjack
            if (player.getHand().isBlackjack() && dealer.getDealerHand().isBlackjack()) {
                System.out.println("Dealer and " + player.getName() + " have blackjack. No bet collected");
                player.addBetToPot(1);
                player.setHasNatural(true);
            }
            
            //Checks if the player has blackjack
            else if (player.getHand().isBlackjack()) {
                player.addBetToPot(3);
                System.out.println(player.getName() + " has blackjack! and wins " + player.getBet() * 1.5 + "$!");
                player.setHasNatural(true);
            }
            
            //Checks if the dealer has blackjack
            else if (dealer.getDealerHand().isBlackjack()) {
                System.out.println("The dealers face down card was " + dealer.getDealerHand().getCards().getLast());
                System.out.println("Dealer has blackjack and collects your bet!");
                endGame();
            }
        }
    }
    
    //Handles the players turn
    private void playerTurn() {
        
        //Iterates through each player so they can all play
        for (Player player: players) {
            
            //Allows the player to play their turn if they haven't busted, got a natural blackjack, or stood 
            while (!player.getHand().isBust() && !player.getHand().isBlackjack() && player.hasStood() == false) {
                player.playTurn(dealer);
            }
            
            if (player.getHand().isBust()) {
                System.out.println(player.getName() + " busts and the dealer collects their bet!");
            }
            
            if (player.getHand().isBlackjack() && !player.hasNatural()) {
                System.out.println(player.getName() + " has blackjack!");
            }
        }
    }
    
    //Uses the dealers method to handle their turn
    private void dealerTurn() {
        dealer.dealerPlay();
    }
    
    //Determines the winners of the game
    private void determineWinners() {
        
        //Iterates through the players
        for (Player player: players) {
            
            //Ignores players who bust or had a natural blackjack
            if (player.getHand().isBust() || player.hasNatural()) {
                continue;
            }
            
            //Handles the dealer busting
            if (dealer.getDealerHand().isBust()) {
                System.out.println("The dealer has busted and " + player.getName() + " wins their bet!");
                player.addBetToPot(2);
            }
            
            else if (dealer.getDealerHand().isBlackjack() && !player.getHand().isBlackjack()) {
                System.out.println("The dealer has blackjack and collects " + player.getName() + "'s bet!");
            }
            
            //Makes sure the dealer has a valid hand to determine the winners
            else if (!dealer.getDealerHand().isBust() && !dealer.getDealerHand().isBlackjack()) {
                //Handles the players bet if they beat the dealer but didn't get a blackjack
                if (player.getHand().calculateHandValue() > dealer.getDealerHand().calculateHandValue()) {
                    player.addBetToPot(2);
                    System.out.println(player.getName() + " has a better hand than the dealer and collects their bet");
                }

                //Handles the players bet if they got a blackjack that wasn't a natural
                else if (player.getHand().isBlackjack()) {
                    System.out.println(player.getName() + " has blackjack and wins 1.5x their bet!");
                    player.addBetToPot(3);
                }

                //The dealer already has the players bet so they keep it if the player has a worse hand
                else if (player.getHand().calculateHandValue() < dealer.getDealerHand().calculateHandValue() && !dealer.getDealerHand().isBust()) {
                    System.out.println(player.getName() + " has less than the dealer and their bet is collected");    
                }

                //The player gets their bet back if they tie the dealer
                else {
                    System.out.println(player.getName() + " ties with the dealer.");
                    player.addBetToPot(1);
                }
            }
        }
    }
    
    //Handles the ending of the game 
    private void endGame() {
        
        //Takes user input for ending the game
        System.out.println("Do you want to end the game?");
        String playerAnswer = input.nextLine();
        
        //Ends the game if the player chooses yes
        if (playerAnswer.equalsIgnoreCase("yes")) {
            continueGame = false;
            System.out.println("The game has ended!");
        }
        
        //Continues and resets the game for the next round if the player chooses no
        else if (playerAnswer.equalsIgnoreCase("no")) {
            System.out.println("The game continues!");
            
            for (Player player: players) {
                player.resetHand();
            }

            dealer.resetDealerHand();
            dealer.resetGroupOfCards();
            
            betsPlaced = false;
        }
    }
}