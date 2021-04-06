package idatx2001.oblig3.cardgame.ui.controller;


import idatx2001.oblig3.cardgame.model.DeckOfCards;
import idatx2001.oblig3.cardgame.model.PlayingCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

public class Controller
{

    DeckOfCards deck = new DeckOfCards();

    /**
     * Constructor initializes a object of class controller
     */
    public Controller()
    {

    }

    /**
     * Deals a hand to be used by the main window, shown to the user
     * @param n number of cards to be drawn by the user
     */
    // TODO: Fix this shit..
    public void doDealHand(int n) {
        try {
            deck.dealHand(n);
            System.out.println(n + " drawn..");
            System.out.println("");
            deck.showHand();
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument..");
        }
    }







}
