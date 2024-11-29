package Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nick
 */

public class Player {

    //Variables to store the players info and game state
    private final String name;
    private final Hand hand;
    private float pot;
    private float bet;
    private boolean hasStood;
    private boolean hasNatural;
    private final Scanner input = new Scanner(System.in);
        
    /**
     * 
     * @param name
     * @param pot 
     */
    //Creates a player with the given name and starting pot amount
    public Player(String name, float pot) {
        this.name = name;
        this.pot = pot;
        this.hand = new Hand();
        this.bet = 0;
        this.hasStood = false;
    }
    
    //Checks the players pot amount
    public void checkPot() {
        System.out.printf("%s, your pot has $%.2f%n", name, pot);
    }
    
    /**
     * 
     * @param dealer 
     */
    //Player chooses their turn action and uses the hit, stand, and double down methods to execute their action
    public void playTurn(Dealer dealer) {
        System.out.println("It's your turn " + name + " " + hand);
        System.out.println("Do you want to hit, stand, or double down");
        String userChoice = input.nextLine();
        
        switch (userChoice.toLowerCase()) {
            case "hit" -> hit(dealer);
            case "stand" -> stand();
            case "double down" -> doubleDown(dealer);
            default -> System.out.println("That is not a valid choice. Select hit, stand, or double down.");
        }
    }
    
    /**
     * 
     * @param amount 
     */
    //Places the players bet given the bet amount
    public void placeBet(float amount) {
        
        //Doesn't place the bet if the amount is 0 or less
        if (amount <= 0) {
            System.out.println("Bet must be greater than zero");
            return;
        }
        
        //Bets the players whole pot if the bet is greater than the pot
        if (amount > pot) {
            bet = pot;
            pot = 0;
            System.out.printf("%s goes all in with a bet of $%.2f%n", name, bet);
        }
        
        //Removes the bet amount from the players pot
        else {
            bet = amount;
            pot -= amount;
            System.out.printf("%s places a bet of $%.2f%n", name, bet);
        }        
    }
    
    /**
     * 
     * @param dealer 
     */
    //Tells the dealer to deal a card to the player
    private void hit(Dealer dealer) {
        Card drawnCard = dealer.dealCard(hand);
        System.out.println(name + " hits and draws " + drawnCard);
    }
    
    //Tells the game the player has stood and wants no more cards
    private void stand() {
        System.out.println(name + " stands");
        hasStood = true;
    }
    
    
    /**
     * 
     * @param dealer 
     */
    //Tells the game the player doubles down
    private void doubleDown(Dealer dealer) {
        
        //Only allows a double down if the hand value is correct
        if (hand.calculateHandValue() == 9 || hand.calculateHandValue() == 10 || hand.calculateHandValue() == 11) {
            System.out.println(name + " doubles down");
        
            //Only allows a double down if the player has enough money
            if (bet > pot) {
                System.out.println("Not enough money to double down " + name);
                return;
            }
        
            //Calculates the double down amount
            float additionalBet = Math.min(bet, pot);
            pot -= additionalBet;
            bet += additionalBet;
        
            //Deals the card
            Card drawnCard = dealer.dealCard(hand);
            System.out.println(name + " doubles down, draws " + drawnCard + ", and stands");
        
            //Player stands after doubling down
            stand();
        }
        
        else {
            System.out.println("Can't double down with that hand. Hand value must total 9, 10, or 11.");
            playTurn(dealer);
        }
        
    }
    
    /**
     * 
     * @param winningRatio 
     */
    //Takes the winning ratio of the hand and multiplys it by the bet to add to the hand
    public void addBetToPot(double winningRatio) {
        pot += bet * winningRatio;
    }
    
    //Resets the hand
    public void resetHand() {
        hand.resetHand();
    }
    
    /**
     * 
     * @param players
     * @param playerName
     * @return 
     */
    //Checks if the players name is unique and not null
    public static boolean checkNameUniqueAndNotNull(ArrayList<Player> players, String playerName) {
        for (Player player: players) {
            if (player.getName().equalsIgnoreCase(playerName) || player.getName() == null) {
                return false;
            }
        }
        return true; 
    }
    
    //Returns the players name
    public String getName() {
        return name;
    }
    
    //Returns the players hand
    public Hand getHand() {
        return hand;
    }
    
    //Returns the players pot
    public float getPot() {
        return pot;
    }
    
    //Returns the players bet
    public float getBet() {
        return bet;
    }
    
    //Returns the players stand status
    public boolean hasStood() {
        return hasStood;
    }
    
    //Returns the players natural status
    public boolean hasNatural() {
        return hasNatural;
    }
    
    /**
     * 
     * @param hasNatural 
     */
    //Sets the players natural status
    public void setHasNatural(boolean hasNatural) {
        this.hasNatural = hasNatural;
    }
    
    //Overrides the toString method for better output
    @Override
    public String toString() {
        return String.format("Player: %s, Pot: $%.2f, Hand: %s", name, pot, hand);
    }
}