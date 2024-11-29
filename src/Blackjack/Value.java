package Blackjack;

/**
 *
 * @author Nick
 */

//Stores the value of the card
//A card can be an ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, or king
public enum Value {
    
    //Each constant has the value and the numeric equivalent of that value
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);
    
    //Defines a variable to store the equivalent numeric value
    private final int numericValue;
    
    
    /**
     * 
     * @param numericValue 
     */    
    //Defines a constructor that takes the equivalent numeric value allowing the enum to store the two value types
    Value(int numericValue) {
        this.numericValue = numericValue;
    }
    
    //Method that returns the numeric value equivalent
    public int getNumericValue() {
        return numericValue;
    }
}