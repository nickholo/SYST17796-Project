package Blackjack;

import java.util.ArrayList;

/**
 *
 * @author Nick
 */

public class Hand {

    //Stores the hand as an arrayList
    private final ArrayList<Card> cards;

    //Creates an empty hand
    public Hand() {
        cards = new ArrayList<>();
    }
    
    /**
     * 
     * @param card 
     */
    //Adds a given card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    //Returns the hand as an arrayList of cards
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    //Returns the size of the hand
    public int getHandSize() {
        return cards.size();
    }
    
    //Calculates and returns the hands numeric value
    public int calculateHandValue() {
        
        //The hand value and ace count start at 0 because there are no cards calculated yet
        int handValue = 0;
        int aceCount = 0;
        
        //Iterates through the cards in the hand and adds its value
        for (Card card: cards) {
            handValue += card.getNumericValue();
            
            //Counts if there is an ace in the hand
            if (card.getValue() == Value.ACE) {
                aceCount += 1;
            }
        }
        
        //If the hand has an ace while the hand value is less than enough to bust, the ace counts as 11
        //Otherwise the ace counts as 1
        while (aceCount > 0 && handValue <= 11) {
            handValue += 10;
            aceCount -= 1;
        }
        
        return handValue;
    }
    
    //Checks if the hand has a total of 21 
    public boolean isBlackjack() {
        return calculateHandValue() == 21;
    }
    
    //Checks if the hand is over 21 
    public boolean isBust() {
        return calculateHandValue() > 21;
    }
    
    //Clears the cards out of the hand
    public void resetHand() {
        cards.clear();
    }
    
    //Overrides the toString method for better output
    @Override
    public String toString() {
        return cards.toString() + " (Value: " + calculateHandValue() + ")";
    }
}