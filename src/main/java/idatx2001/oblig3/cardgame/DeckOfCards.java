package idatx2001.oblig3.cardgame;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a deck of cards
 * where cards are "stored"
 */
public class DeckOfCards
{
    private final char[] suit = {'S', 'H', 'D', 'C' };
    private final int[] rank = {1,2,3,4,5,6,7,8,9,10,11,12,13}; //1=ace, 11=jack, 12=queen, 13=king
    private int[] deck = new int[52]; //Array that represents the full deck at 52 cards
    private ArrayList<PlayingCard> deckOfCards = new ArrayList();
    private Random random = new Random();

    /**
     * Constructor initializes a object of class DeckOfCards
     * Creates a deck of cards with 52 cards with all faces and suits
     */
    public DeckOfCards()
    {
        boolean initializing = true;
        int suitIndex = 0;
        while(initializing) {
            for (int index = 1; index <= rank.length; index++) {
                deckOfCards.add(new PlayingCard(suit[suitIndex], index));
            }
            if(deckOfCards.size() == 52)
            {
                initializing = false;
            }
            suitIndex++;
        }

    }

    /**
     * Prints the entire deck of cards.
     */
    public void showDeck()
    {
        for(PlayingCard pc : deckOfCards)
        {
            System.out.println(pc.getAsString());
        }
    }

    /**
     * Picks a random card from the deck of cards to be added to a collection of random cards
     * Does not allow duplicate cards to be picked
     * @param n number of cards to be picked
     * returns the collection of random cards
     * @return the collection of random cards
     */
    public ArrayList<PlayingCard> dealHand(int n)
    {
        ArrayList<PlayingCard> collection = new ArrayList<>(); // Collection where random cards are to be added

        if(n <= deckOfCards.size())
        {
            for (int index = 0; index < n; index++) {
                PlayingCard card = deckOfCards.get(random.nextInt(deckOfCards.size()));
                collection.add(card); // Adds the random card to collection
                deckOfCards.remove(card); // Removes the random card from the deck
            }
        } else {
            throw new IllegalArgumentException("Cannot draw more cards than the deck holds..");
        }
        return collection;
    }

    public static void main(String[] args) {
        DeckOfCards dc = new DeckOfCards();
        dc.dealHand(5);
    }
}
