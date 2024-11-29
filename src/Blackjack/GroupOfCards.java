package Blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nick
 */

public class GroupOfCards {

    //Stores the group of cards as an arrayList and the initial group of cards size
    private final ArrayList<Card> groupOfCards;
    private final int groupOfCardsTotalSize;
    
    /**
     * 
     * @param numberOfDecks 
     */
    //Constructs the group of cards made of x amount of decks
    public GroupOfCards(int numberOfDecks) {
        
        groupOfCards = new ArrayList<>();
        
        for (int i = 0; i < numberOfDecks; i++) {
            groupOfCards.addAll(new Deck().getCards());
        }
        
        groupOfCardsTotalSize = groupOfCards.size();
    }
    
    //Shuffles the arraylist of cards
    public void shuffle() {
        Collections.shuffle(groupOfCards);
    }
    
    //Draws the top card from the group of cards
    public Card drawCard() {
        return groupOfCards.remove(groupOfCards.size() - 1);
    }
    
    //Returns the initial size of the created group of cards
    public int getGroupOfCardsSize() {
        return groupOfCardsTotalSize;
    }
    
    //Returns the size of the group of cards
    public int getRemainingCards() {
        return groupOfCards.size();
    }
}