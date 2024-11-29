package Blackjack;

import java.util.ArrayList;

/**
 *
 * @author Nick
 */

public class Deck {

    //Deck has an array of cards
    private final ArrayList<Card> cards;
    
    
    //Constructs the default deck with 52 cards, one of each suit for each card
    public Deck() {
        cards = new ArrayList<>();
        
        //Iterates through each suit
        for (Suit suit: Suit.values()) {
            
            //Iterates through each value
            for (Value value: Value.values()) {
                
                //Creates a card with the current iterations suit and value
                cards.add(new Card(value, suit));
            }
        }
    }

    //Returns an arraylist of the cards in the deck
    public ArrayList<Card> getCards() {
        return cards;
    }
}