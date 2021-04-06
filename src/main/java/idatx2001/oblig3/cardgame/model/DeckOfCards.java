package idatx2001.oblig3.cardgame.model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class represents a deck of cards
 * where cards are "stored"
 */
public class DeckOfCards
{
    private final char[] suit = {'S', 'H', 'D', 'C' };
    private final int[] rank = {1,2,3,4,5,6,7,8,9,10,11,12,13}; //1=ace, 11=jack, 12=queen, 13=king
    private ArrayList<PlayingCard> deckOfCards = new ArrayList();
    private ArrayList<PlayingCard> collection = new ArrayList<>(); // Collection where random cards are to be added
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
     * returns the deck of cards
     * @return the deck of cards
     */
    public ArrayList<PlayingCard> getDeckOfCards()
    {
        return deckOfCards;
    }

    /**
     * Creates a string with information about the queen of spades
     * returns a string with information regarding queen of spades
     * @return a string with information regarding queen of spades
     */
    public boolean checkForQueenOfSpades()
    {
        boolean queenOfSpades = false;
        boolean searching = true;
        Iterator<PlayingCard> it = collection.iterator();

        while(it.hasNext() && searching)
        {
            PlayingCard card = it.next();
            if(card.getAsString().equalsIgnoreCase("S11"))
            {
                queenOfSpades = true;
                searching = false;
            } else {
                queenOfSpades = false;
            }
        }

        return queenOfSpades;
    }

    /**
     * Creates a new String with all the cards of suit hearts
     * returns a new String of heart cards
     * @return a new String of heart cards
     */
    public String getHeartCards()
    {
        String hearts = "";
        for(PlayingCard card : collection)
        {
            if(card.getSuit() == 'H')
            {
                hearts += card.getAsString() + " ";
            }
        }

        if(hearts.isBlank()) // Checks if the hearts string is empty after creation, if so gives new value
        {
            hearts = "No hearts";
        }

        return hearts;
    }

    /**
     * Calculates the sum of a players hand
     * returns the sum of a players hand
     * @return the sum of a players hand
     */
    public int getHandSum()
    {
        int sum = 0;

        for(PlayingCard pc : collection)
        {
            sum += pc.getFace();
        }

        return sum;
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
     * Prints the cards on hand.
     */
    public void showHand()
    {
        for(PlayingCard pc : collection)
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

    /**
     * returns a given card in the collection of cards in a users hand
     * @return a given card in the collection of cards in a users hand
     */
    public String getHandCard(int index)
    {
        String handCard = collection.get(index).getAsString();
        return handCard;
    }

    /**
     * returns the collection of cards on a users hand
     * @return the collection of cards on a users hand
     */
    public ArrayList<PlayingCard> getCollection()
    {
        return collection;
    }

    /**
     * Checks if what a user has in hand qualifies for a flush
     */
    public String doFlushCheck() {
        String flushInfo = "";
        long HighestNumberOfSameSuit = collection
                .stream()
                .map((PlayingCard card) -> card.getSuit())
                .collect(Collectors.groupingBy((Character c) -> c.charValue(), Collectors.counting()))
                .values()
                .stream()
                .reduce(Math::max)
                .get();

        if (HighestNumberOfSameSuit >= 5) {

            flushInfo = "Flush!";
        } else {
            flushInfo = "Unfortunately no flush..";
        }
        return flushInfo;
    }

    public static void main(String[] args) {
        DeckOfCards dc = new DeckOfCards();
        dc.dealHand(5);
        dc.showHand();
        dc.checkForQueenOfSpades();

    }
}
