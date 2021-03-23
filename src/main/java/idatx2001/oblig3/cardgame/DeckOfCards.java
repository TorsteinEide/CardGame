package idatx2001.oblig3.cardgame;
import java.util.ArrayList;

/**
 * This class represents a deck of cards
 * where cards are "stored"
 */
public class DeckOfCards
{
    private final char[] suit = {'S', 'H', 'D', 'C' };
    private final int[] rank = {1,2,3,4,5,6,7,8,9,10,11,12,13}; //1=ace, 11=jack, 12=queen, 13=king
    private int[] deck = new int[52]; //Array that represents the full deck at 52 cards

    /**
     * Constructor initializes a object of class DeckOfCards
     * Creates a deck of cards with 52 cards
     */
    public DeckOfCards()
    {
        for(int index = 0; index < deck.length; index++)
        {
            deck[index] = index;
            System.out.println(index);
        }
    }

    public void dealHand(int n)
    {

    }
}
