package Blackjack;

/**
 *
 * @author Nick
 */

public class Card {
    
    //Variables to store the cards value and suit
    private final Value value;
    private final Suit suit;

    /**
     * 
     * @param value
     * @param suit
     */
    //Defines a card object that has a value and a suit
    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }
    
    //Returns the cards numeric value
    public int getNumericValue() {
        return value.getNumericValue();
    }
    
    //Returns the cards value
    public Value getValue() {
        return value;
    }
    
    //Returns the cards suit
    public Suit getSuit() {
        return suit;
    }

    //Overrides the toString method for better output
    @Override
    public String toString() {
        return value + " of " + suit;
    }
}