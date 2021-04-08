package idatx2001.oblig3.cardgame.model;

import idatx2001.oblig3.cardgame.DeckOfCards;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class responsible for testing deckOfCards method
 *
 * @author Torstein Eide
 * @version 0.1
 */
public class DeckOfCardsTest
{

    DeckOfCards deck = new DeckOfCards();

    /**
     * Tests if a hand can be dealt properly
     * drawing a number of cards
     */
    @Test
    public void testPositiveDealHand()
    {
        try {
            deck.dealHand(5);
            assertTrue(true);
        } catch (IllegalArgumentException e)
        {
            fail("You can not draw more cards than there are in the deck..");
        }
    }

    /**
     * Tests if a hand can be dealt not properly
     * drawing more cards than there are in the deck
     */
    @Test
    public void testNegativeDealHand()
    {
        try {
            deck.dealHand(100);
            fail("You cannot draw more cards than there are in the deck..");
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    /**
     * Tests if a card can be gotten from the hand through an index properly
     */
    @Test
    public void testPositiveGetHandCard()
    {
        try{
            deck.dealHand(5);
            deck.getHandCard(4);
            assertTrue(true);
        } catch (IllegalArgumentException e){
            fail("There are no cards with this index..");
        }
    }

    /**
     * Tests if a card can be gotten from a hand through an index
     * that does not hold on to any object
     */
    @Test
    public void testNegativeGetHandCard()
    {
        try{
            deck.dealHand(0);
            deck.getHandCard(1);
            fail("There are no cards with this index..");
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
}