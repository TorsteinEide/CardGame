package idatx2001.oblig3.cardgame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class represents a deck of cards
 * where cards are "stored"
 *
 * @author Torstein Eide
 * @version 0.1
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
     * returns a boolean value true if queen is in hand, false if not
     * @return a boolean value true if queen is in hand, false if not
     */
    public boolean checkForQueenOfSpades()
    {
        boolean queenOfSpades = false;
        List<PlayingCard> list = this.collection.stream()
                .filter(playingCard -> playingCard.getSuit() == 'S')
                .filter(playingCard -> playingCard.getFace() == 12)
                .collect(Collectors.toList());

        if(!list.isEmpty())
        {
            queenOfSpades = true;
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
        /**
        int sum = 0;

        for(PlayingCard pc : collection)
        {
            sum += pc.getFace();
        }

        return sum;
         **/


        Integer face = collection.stream()
                .map(card -> card.getFace())
                .reduce(0,(a,b) -> a + b);

        int sum = Integer.valueOf(face);

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
     * Gives cards on hand back to the deck
     */
    public void resetCardsOnHand()
    {
        while(collection.size() > 0){
            int index = 0;
            collection.remove(index);
            index++;
        }

        new DeckOfCards();
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

                if(!collection.contains(card)){
                    collection.add(card); // Adds the random card to collection
                }

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
        String handCard = "";
        if(collection.isEmpty()) {
            throw new IllegalArgumentException("There are no cards in the deck..");
        } else if(index >= 0 && index < collection.size()){
            handCard = collection.get(index).getAsString();
        } else {
            throw new IllegalArgumentException("There are no cards tied to this index..");
        }
        System.out.println(handCard);
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
        long SameSuit = collection
                .stream()
                .map((PlayingCard card) -> card.getSuit())
                .collect(Collectors.groupingBy((Character c) -> c.charValue(), Collectors.counting()))
                .values()
                .stream()
                .reduce(Math::max)
                .get();

        if (SameSuit >= 5) {

            flushInfo = "FLUSH!";
        } else {
            flushInfo = "No flush..";
        }
        return flushInfo;
    }

    public static void main(String[] args) {
        DeckOfCards dc = new DeckOfCards();
        dc.dealHand(3);
        dc.showHand();
        System.out.println("");
        System.out.println(dc.getHandSum());

    }
}