package Blackjack;

/**
 *
 * @author Nick
 */

public class Dealer {
    
    //Variables to store the dealers hand, and the info needed for the cards the dealer uses to manage the game
    private GroupOfCards groupOfCards;
    private final Hand dealerHand;
    private final int numberOfDecks;

    /**
     * 
     * @param numberOfDecks 
     */
    //Creates the dealer with a given number of decks
    public Dealer(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        
        //Dealer creates a new group of cards to play the game with
        this.groupOfCards = new GroupOfCards(numberOfDecks);
        
        //Dealer starts with an empty hand
        this.dealerHand = new Hand();
    }
    
    //Returns the dealers hand
    public Hand getDealerHand() {
        return dealerHand;
    }
    
    //Resets the dealers hand
    public void resetDealerHand() {
        dealerHand.resetHand();
    }
    
    //Shuffles the group of cards - used at start of every round
    public void shuffleGroupOfCards() {
        groupOfCards.shuffle();
    }
    
    /**
     * 
     * @param hand
     * @return 
     */
    //Deals a card and returns the dealt card - used when player hits or the dealer is below 21
    public Card dealCard(Hand hand) {
        
        //Checks if the group of cards is getting small and resets it
        if (groupOfCards.getRemainingCards() <= groupOfCards.getGroupOfCardsSize() / 2) {
            resetGroupOfCards();
        }
        
        Card drawnCard = groupOfCards.drawCard();
        hand.addCard(drawnCard);
        return drawnCard;
    } 
    
    //Handles the dealers play during the round
    public void dealerPlay() {
        
        //Dealer reveals their face down card at the start of their turn
        System.out.println("The dealer's face down card is a " + dealerHand.getCards().getLast());
        
        //Dealer keeps drawing cards until they have a hand value greater than or equal to 17
        while (dealerHand.calculateHandValue() < 17) {
            Card newCard = dealCard(dealerHand);
            System.out.println("The dealer drew a " + newCard);
        }
        
        //Dealer busts
        if (dealerHand.isBust()) {
            System.out.println("The dealer busts!");
        }
        
        //Dealer blackjack
        else if (dealerHand.isBlackjack()) {
            System.out.println("The dealer has blackjack!");
        }
        
        //Outputs the dealers hand
        else  {
            System.out.println("The dealer stands with a hand value of " + dealerHand.calculateHandValue());
        }
    }
    
    //Used when the size of the group of cards gets too low
    public void resetGroupOfCards() {
        this.groupOfCards = new GroupOfCards(numberOfDecks);
        shuffleGroupOfCards();
    }
}