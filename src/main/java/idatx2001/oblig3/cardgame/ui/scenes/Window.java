package idatx2001.oblig3.cardgame.ui.scenes;

import idatx2001.oblig3.cardgame.DeckOfCards;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;


/**
 * This class represents the window to be shown to user.
 *
 * @author Torstein Eide
 * @version 0.1
 */
public class Window extends Application
{
    private DeckOfCards deck = new DeckOfCards();
    private HBox cardPane = new HBox(); // Create HBox to display cards

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane(); // Create the root where menus will be placed on top

        // Borderpanes
        GridPane bottomGrid = createBottomGrid();
        GridPane leftGrid = createLeftGrid();
        HBox mainCardDisplay = createMainHBox();

        root.setCenter(mainCardDisplay);
        root.setBottom(bottomGrid);
        root.setLeft(leftGrid);

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setTitle("CARD GAME");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Creates the left grid to be placed in the left side of the borderpane
     * returns GridPane to be placed in left side of the borderpane
     * @return GridPane to be placed in left side of the borderpane
     */
    private GridPane createLeftGrid()
    {
        // Settings for left grid
        Button dealHandButton = new Button("Deal hand");

        // Creates choice box
        ChoiceBox<Integer> numberOfCardsChoice = new ChoiceBox();
        numberOfCardsChoice.setValue(1);
        numberOfCardsChoice.getItems().add(1);
        numberOfCardsChoice.getItems().add(2);
        numberOfCardsChoice.getItems().add(3);
        numberOfCardsChoice.getItems().add(4);
        numberOfCardsChoice.getItems().add(5);

        // Action handler for the dealHand button
        dealHandButton.setOnAction(event -> {
            doDealHandForWindow(numberOfCardsChoice.getValue());
            doUpdateMainHbox();
        });


        // Places items in left pane
        GridPane.setConstraints(dealHandButton, 1, 45);
        GridPane.setConstraints(numberOfCardsChoice,1,43);

        GridPane leftGrid = new GridPane();

        // Grid Settings
        leftGrid.setPadding(new Insets(10,10,10,10));
        leftGrid.setVgap(8);
        leftGrid.setHgap(10);

        leftGrid.getChildren().addAll(dealHandButton, numberOfCardsChoice);

        return leftGrid;
    }

    /**
     * Creates the bottom grid to be placed in the bottom of the borderpane
     * returns GridPane to be placed in the bottom of the borderpane
     * @return GridPane to be placed in the bottom of the borderpane
     */
    private GridPane createBottomGrid()
    {
        // Create labels
        Label sumOfFacesLabel = new Label("Sum of faces: ");
        Label flushLabel = new Label("Flush: ");
        Label queenOfSpadesLabel = new Label("Queen of spades: ");
        Label cardsOfHeartsLabel = new Label("Cards of hearts: ");
        Button checkHandButton = new Button("Check hand");

        // Create Button
        TextField TextFieldQueenOfSpades = new TextField();
        TextField TextFieldFlush = new TextField();

        TextFieldFlush.setOnAction(event -> doFlushCheckForWindow());
        TextFieldFlush.setPrefWidth(80);
        TextFieldFlush.setMaxWidth(80);
        TextFieldQueenOfSpades.setOnAction(event -> doCheckForQueenOfSpadesForWindow());
        TextFieldQueenOfSpades.setPrefWidth(80);
        TextFieldQueenOfSpades.setMaxWidth(80);

        // Create text fields
        TextField sumOfFacesTextField = new TextField();
        TextField cardsOfHeartsTextField = new TextField();

        // Buttons for updating text fields
        checkHandButton.setOnAction(actionEvent -> {
            doCheckHandForWindow();
            sumOfFacesTextField.setText(String.valueOf(deck.getHandSum()));
            cardsOfHeartsTextField.setText(deck.getHeartCards());
            TextFieldFlush.setText(doFlushCheckForWindow());
            TextFieldQueenOfSpades.setText(doCheckForQueenOfSpadesForWindow());
        });

        // Place labels
        GridPane.setConstraints(checkHandButton,0,0);

        GridPane.setConstraints(sumOfFacesLabel, 5, 5);
        GridPane.setConstraints(sumOfFacesTextField, 6, 5);

        GridPane.setConstraints(flushLabel, 0,5);
        GridPane.setConstraints(TextFieldFlush,1,5);

        GridPane.setConstraints(queenOfSpadesLabel, 0, 10);
        GridPane.setConstraints(TextFieldQueenOfSpades,1,10);

        GridPane.setConstraints(cardsOfHeartsLabel, 5, 10);
        GridPane.setConstraints(cardsOfHeartsTextField,6,10);

        // Create the bottomgrid to hold all the items
        GridPane bottomGrid = new GridPane();

        bottomGrid.setPadding(new Insets(5,5,20,22));
        bottomGrid.setVgap(4);
        bottomGrid.setHgap(5);

        bottomGrid.getChildren().addAll(sumOfFacesLabel,flushLabel,queenOfSpadesLabel,cardsOfHeartsLabel,
                TextFieldQueenOfSpades,TextFieldFlush, sumOfFacesTextField,
                cardsOfHeartsTextField, checkHandButton);

        return bottomGrid;
    }

    /**
     * Creates the main display of cards
     * returns a HBox where cards are to be displayed for the user
     * @return a HBox where cards are to be displayed for the user
     */
    private HBox createMainHBox()
    {
        //HBox cardPane = new HBox(); // Create HBox to display cards
        cardPane.setSpacing(50);
        cardPane.setAlignment(Pos.CENTER); // Place HBox correctly

        // Create labels to display cards
        Label firstCardLabel = new Label();

        firstCardLabel.setText("no cards in hand");

        cardPane.getChildren().addAll(firstCardLabel);

        return cardPane;
    }

    /**
     * Updates the main HBox when hand is being dealt
     */
    public void doUpdateMainHbox()
    {
        cardPane.getChildren().clear();
        int i = 0;
        while(i < deck.getCollection().size()) {
            Label cardLabel = new Label(doGetCardsOnHand(i));
            cardPane.getChildren().add(cardLabel);
            i++;
        }

    }

    /**
     * Method for dealing a hand used in class Window
     * @param n number of cards to be drawn
     */
    public void doDealHandForWindow(int n) {
        try {
            deck.resetCardsOnHand();
            deck.dealHand(n);
            System.out.println(n + " drawn..");
            System.out.println("");
            deck.showHand();
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument..");
        }
    }

    /**
     * Method for checking for a flush used in class Window
     */
    public String doFlushCheckForWindow()
    {
        String flushString = "";
        if(deck.getCollection().size() > 0) {
            deck.doFlushCheck();
            flushString = deck.doFlushCheck();
        } else {
            System.out.println("No cards in hand");
        }
        return flushString;
    }

    /**
     * Method for checking for queen of spades used in class Window
     */
    public String doCheckForQueenOfSpadesForWindow()
    {
        String queenOfSpades = "";
        if(deck.checkForQueenOfSpades())
        {
            queenOfSpades = "In Hand";
        } else {
            queenOfSpades = "Not in hand..";
        }
        return queenOfSpades;
    }

    /**
     * checks if the hand is empty, if so alerts user
     */
    public void doCheckHandForWindow() {
        if(deck.getCollection().size() <= 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO CARDS IN HAND");
            alert.setHeaderText("It looks like you don't have any cards in your hand");
            alert.setContentText("You should draw some.");
            alert.showAndWait();
        }
    }

    /**
     * @param index the index of the card to be gotten
     * returns the card to be gotten
     * @return the card to be gotten
     */
    public String doGetCardsOnHand(int index)
    {
        String cardToGet = deck.getHandCard(index);

        return cardToGet;
    }

    /**
     * Creates a ImageView object that contains an entire deck of cards,
     * crops the image for a specific card
     * @param card Card to be cropped
     * returns the cropped image of the given card
     * @return the cropped image of the given card
     *
    private ImageView getCardImage(PlayingCard card)
    {
        ImageView image = new ImageView();

        URL path = getClass().getClassLoader().getResource("deckOfCards.png");

        Image fullImage = new Image(String.valueOf(path));
        image.setImage(fullImage);

        Rectangle2D croppedImage = this.doCropImage(card);
        image.setViewport(croppedImage);

        return image;
    }

    private Rectangle2D doCropImage()
    {

    }
    **/
}