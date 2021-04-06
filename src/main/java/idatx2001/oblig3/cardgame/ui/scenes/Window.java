package idatx2001.oblig3.cardgame.ui.scenes;

import java.net.URL;
import java.util.logging.Logger;
import idatx2001.oblig3.cardgame.model.DeckOfCards;
import idatx2001.oblig3.cardgame.model.PlayingCard;
import idatx2001.oblig3.cardgame.ui.controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class represents the window to be shown to user.
 */
public class Window extends Application
{
    private Controller controller;
    private DeckOfCards deck = new DeckOfCards();

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
     * Temp method because controller wont work.. //TODO: fix this shit again..
     * @param n
     */
    public void doDealHandForWindow(int n) {
        try {
            deck.dealHand(n);
            System.out.println(n + " drawn..");
            System.out.println("");
            deck.showHand();
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument..");
        }
    }

    /**
     * Temp method because controller wont work.. // TODO: fix this as well..
     */
    public void doFlushCheckForWindow()
    {
        if(deck.getCollection().size() > 0) {
            deck.doFlushCheck();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Flush");
            alert.setHeaderText(deck.doFlushCheck());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Flush");
            alert.setHeaderText("It looks like you don't have any cards in your hand");
            alert.setContentText("You should draw some cards and then check for a flush.");
            alert.showAndWait();
        }
    }

    /**
     * Temp method because controller wont work.. // TODO: fix this as well..
     */
    public void doCheckForQueenOfSpadesForWindow()
    {
        if(deck.checkForQueenOfSpades())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("QUEEN OF SPADES");
            alert.setHeaderText("You have the queen of spades in hand!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Queen of spades");
            alert.setHeaderText("You do not have the queen of spades in hand");
            alert.setContentText("You should keep playing and maybe you'll find it..");
            alert.showAndWait();
        }
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
        Button checkHandButton = new Button("Check hand");

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
        });

        // Places items in left pane
        GridPane.setConstraints(dealHandButton, 1, 43);
        GridPane.setConstraints(checkHandButton,1,39);
        GridPane.setConstraints(numberOfCardsChoice,1,45);

        GridPane leftGrid = new GridPane();

        // Grid Settings
        leftGrid.setPadding(new Insets(10,10,10,10));
        leftGrid.setVgap(8);
        leftGrid.setHgap(10);

        leftGrid.getChildren().addAll(dealHandButton, checkHandButton, numberOfCardsChoice);

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
        Button sumOfFacesButton = new Button("Sum of faces: ");
        Label flushLabel = new Label("Flush: ");
        Label queenOfSpadesLabel = new Label("Queen of spades: ");
        Button cardsOfHeartsButton = new Button("Cards of hearts: ");

        // Create Button
        Button yesOrNoButtonQueenOfSpades = new Button("Yes/no");
        Button yesOrNoButtonFlush = new Button("Yes/no");

        yesOrNoButtonFlush.setOnAction(event -> doFlushCheckForWindow());
        yesOrNoButtonQueenOfSpades.setOnAction(event -> doCheckForQueenOfSpadesForWindow());

        // Create text fields
        TextField sumOfFacesTextField = new TextField();
        TextField cardsOfHeartsTextField = new TextField();

        // Buttons for updating text fields
        sumOfFacesButton.setOnAction(event -> sumOfFacesTextField.setText(String.valueOf(deck.getHandSum())));
        cardsOfHeartsButton.setOnAction(event -> cardsOfHeartsTextField.setText(deck.getHeartCards()));

        // Place labels
        GridPane.setConstraints(sumOfFacesButton, 5, 5);
        GridPane.setConstraints(sumOfFacesTextField, 6, 5);

        GridPane.setConstraints(flushLabel, 0,5);
        GridPane.setConstraints(yesOrNoButtonFlush,1,5);

        GridPane.setConstraints(queenOfSpadesLabel, 0, 10);
        GridPane.setConstraints(yesOrNoButtonQueenOfSpades,1,10);

        GridPane.setConstraints(cardsOfHeartsButton, 5, 10);
        GridPane.setConstraints(cardsOfHeartsTextField,6,10);

        // Create the bottomgrid to hold all the items
        GridPane bottomGrid = new GridPane();

        bottomGrid.setPadding(new Insets(5,5,20,22));
        bottomGrid.setVgap(4);
        bottomGrid.setHgap(5);

        bottomGrid.getChildren().addAll(sumOfFacesButton,flushLabel,queenOfSpadesLabel,cardsOfHeartsButton,
                                        yesOrNoButtonQueenOfSpades,yesOrNoButtonFlush, sumOfFacesTextField,
                                        cardsOfHeartsTextField);

        return bottomGrid;
    }

    /**
     * Creates the main display of cards
     * returns a HBox where cards are to be displayed for the user
     * @return a HBox where cards are to be displayed for the user
     */
    private HBox createMainHBox()
    {
        HBox cardPane = new HBox(); // Create HBox to display cards
        cardPane.setSpacing(50);
        cardPane.setAlignment(Pos.CENTER); // Place HBox correctly

        // TODO: make labels show corresponding card String values.
        // Create labels to display cards
        DeckOfCards deck = new DeckOfCards();

        if(deck.getCollection().size() == 0){
            Label firstCardLabel = new Label("");
            Label secondCardLabel = new Label("");
            Label thirdCardLabel = new Label("no cards drawn");
            Label fourthCardLabel = new Label("");
            Label fifthCardLabel = new Label("");

            cardPane.getChildren().addAll(firstCardLabel, secondCardLabel, thirdCardLabel, fourthCardLabel,
                    fifthCardLabel);
        } else {
            Label firstCardLabel = new Label(deck.getHandCard(0));
            Label secondCardLabel = new Label(deck.getHandCard(1));
            Label thirdCardLabel = new Label(deck.getHandCard(2));
            Label fourthCardLabel = new Label(deck.getHandCard(3));
            Label fifthCardLabel = new Label(deck.getHandCard(4));

            cardPane.getChildren().addAll(firstCardLabel, secondCardLabel, thirdCardLabel, fourthCardLabel,
                    fifthCardLabel);
        }

        return cardPane;
    }
}
