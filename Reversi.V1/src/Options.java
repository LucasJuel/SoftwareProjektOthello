import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Options {

    // Iniciater variabler
    public Stage stage;
    Group root;
    private Scene scene;
    static int size= 4;
    Label lSize;
    Label Colorpieces;
    static boolean single = true;
    static boolean muligeTræk;
    static boolean pieceHover;
    CheckBox singleplayer;
    CheckBox possibleMove;
    CheckBox hoverPiece;
    static RadioButton selectedTema;
    ToggleGroup groupTema;
    Label saveConfirm;
    Label labelSize;
    Label selectColorTema;

    RadioButton Tema1 = new RadioButton();
    RadioButton Tema2 = new RadioButton();
    RadioButton Tema3 = new RadioButton();
    private static ArrayList<Color> player1 = new ArrayList<Color>(
            Arrays.asList(Color.WHITE, Color.rgb(142, 202, 230)));
    private static ArrayList<Color> player2 = new ArrayList<Color>(Arrays.asList(Color.BLACK, Color.rgb(251, 133, 0)));
    private static ArrayList<String> player1farveStrings = new ArrayList<String>(Arrays.asList("Hvid", "Blå"));
    private static ArrayList<String> player2farveStrings = new ArrayList<String>(Arrays.asList("Sort", "Orange"));
    private static ArrayList<Color> baggrundList = new ArrayList<Color>(
            Arrays.asList(Color.GREEN, Color.rgb(2, 48, 71)));
    private static ArrayList<String> baggrundListHex = new ArrayList<String>(Arrays.asList("008000", "023047"));
    private static ArrayList<Color> flipList = new ArrayList<Color>(Arrays.asList(Color.BLUE, Color.rgb(230, 57, 70)));
    private static ArrayList<Color> tekstFarveColor = new ArrayList<Color>(
            Arrays.asList(Color.BLACK, Color.rgb(230, 57, 70)));

    private static int colorInt = 1;

    // implements getters for import the values to the main game driver. to adjust
    // the settings set for the game.
    public static int getSize() {
        return 4 * 2;
    }

    public static boolean makeModstander() {
        return single;
    }

    public static boolean showPossibleMoves() {
        return muligeTræk;
    }

    public static boolean showFlipHover() {
        return pieceHover;
    }

    public static Color getPlayer2Color() {
        return player2.get(colorInt);
    }

    public static String getPlayer2String() {

        return player2farveStrings.get(colorInt);
    }

    public static Color getPlayer1Color() {
        return player1.get(colorInt);
    }

    public static String getPlayer1String() {

        return player1farveStrings.get(colorInt);
    }

    public static Color getBaggrundsColor() {
        return baggrundList.get(colorInt);
    }

    public static String getBaggrundString() {
        return baggrundListHex.get(colorInt);
    }

    public static Color getFlipColor() {
        return flipList.get(colorInt);
    }

    public void OptionsMenu(Stage stage) {
        this.stage = stage;
        buildOptions();
    }

    // builds the options menu window and all the visuals inside.
    private void buildOptions() {
        root = new Group();
        scene = new Scene(root, 800, 500);
        scene.setFill(Options.getBaggrundsColor());
        // Select Size of the board.
        labelSize = new Label("Select your size...");
        labelSize.setTextFill(tekstFarveColor.get(colorInt));

        lSize = new Label(" ");
        lSize.setTextFill(tekstFarveColor.get(colorInt));

        // creating a slider for selecting the size
        Slider sliderSize = new Slider();

        // set min and max values with a default value.
        sliderSize.setMin(2);
        sliderSize.setMax(8);
        sliderSize.setValue(4);
        size = 8;
        lSize.setText("8");
        sliderSize.setMinorTickCount(2);
        sliderSize.setBlockIncrement(10);

        // Create a visual on the slider so it's easier to see what value you are
        // selecting.
        // sliderSize.setShowTickLabels(true);
        // sliderSize.setShowTickMarks(true);
        sliderSize.setMajorTickUnit(2);
        sliderSize.setMinorTickCount(2);
        sliderSize.setBlockIncrement(2);
        sliderSize.setSnapToTicks(true);

        // creating a listener on the slider to create a label to show the value.
        sliderSize.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                            Number newValue) {
                        lSize.setText("" + newValue.intValue() * 2);
                    }
                });

        // adds the slider to the group so we can show them. and setting coordinates.
        root.getChildren().addAll(labelSize, sliderSize, lSize);
        labelSize.setLayoutX(50);
        labelSize.setLayoutY(30);
        sliderSize.setLayoutX(50);
        sliderSize.setLayoutY(50);
        lSize.setLayoutX(50);
        lSize.setLayoutY(80);

        // Creating RadioButton for pieces color

        Tema1.setText("Classic");
        Tema1.setTextFill(tekstFarveColor.get(colorInt));
        Tema1.setSelected(true);
        Tema2.setText("Sunset"); // Canva.com Triadic color Combination #df2120
        Tema2.setTextFill(tekstFarveColor.get(colorInt));
        Tema3.setText("Light Blue/Pink"); // #afe31c
        Tema3.setTextFill(tekstFarveColor.get(colorInt));

        // creating a Togglegroup so only one of these can be selected at a time
        groupTema = new ToggleGroup();
        Tema1.setToggleGroup(groupTema);
        Tema2.setToggleGroup(groupTema);
        Tema3.setToggleGroup(groupTema);

        // setting up the select color with radio buttons where it's going to be
        // displayed on the screen.
        selectColorTema = new Label("Select color of your Pieces");
        selectColorTema.setTextFill(tekstFarveColor.get(colorInt));
        root.getChildren().addAll(Tema1, Tema2, Tema3, selectColorTema);
        selectColorTema.setLayoutX(50);
        selectColorTema.setLayoutY(200);
        Tema1.setLayoutX(50);
        Tema1.setLayoutY(220);
        Tema2.setLayoutX(50);
        Tema2.setLayoutY(240);
        Tema3.setLayoutX(50);
        Tema3.setLayoutY(260);

        // Checkboxes with descriptions.

        singleplayer = new CheckBox("Singleplayer / play against the computer");
        possibleMove = new CheckBox("Show possible moves");
        hoverPiece = new CheckBox("See turned pieces by hovering");
        singleplayer.setTextFill(tekstFarveColor.get(colorInt));
        possibleMove.setTextFill(tekstFarveColor.get(colorInt));
        hoverPiece.setTextFill(tekstFarveColor.get(colorInt));

        if(possibleMove.isSelected()) {
            hoverPiece.setDisable(false);
        } else {
            hoverPiece.setDisable(true);
        }

        possibleMove.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed (ObservableValue <? extends Boolean> arg0, Boolean gammBoolean, Boolean nyBoolean) {
                if(nyBoolean) {
                    hoverPiece.setDisable(false);
                } else {
                    hoverPiece.setDisable(true);
                    hoverPiece.setSelected(false);
                }
            }
        });

        // adding the checkboxes to the group and adding coordinates to the group.
        root.getChildren().addAll(singleplayer, possibleMove, hoverPiece);
        singleplayer.setLayoutX(400);
        singleplayer.setLayoutY(50);
        possibleMove.setLayoutX(400);
        possibleMove.setLayoutY(90);
        hoverPiece.setLayoutX(400);
        hoverPiece.setLayoutY(130);

        if (single) {
            singleplayer.setSelected(true);
        } else {
            singleplayer.setSelected(false);
        }

        if (muligeTræk) {
            possibleMove.setSelected(true);
        } else {
            possibleMove.setSelected(false);
        }

        if (pieceHover) {
            hoverPiece.setSelected(true);
        } else {
            hoverPiece.setSelected(false);
        }

        if (colorInt == 0) {
            Tema1.setSelected(true);
        } else if (colorInt == 1) {
            Tema2.setSelected(true);
        } else if (colorInt == 2) {
            Tema3.setSelected(true);
        }

        // creating a label to give a confirmation message on when the settings are
        // saved.
        saveConfirm = new Label("");
        root.getChildren().add(saveConfirm);
        saveConfirm.setLayoutX(400);
        saveConfirm.setLayoutY(380);

        if (single) {
            singleplayer.setSelected(true);
        } else {
            singleplayer.setSelected(false);
        }

        if (muligeTræk) {
            possibleMove.setSelected(true);
        } else {
            possibleMove.setSelected(false);
        }

        if (pieceHover) {
            hoverPiece.setSelected(true);
        } else {
            hoverPiece.setSelected(false);
        }

        // Save button

        Button Save = new Button("Save");

        setButton(Save, 400, 400, 150, 50, 30, "0,0,0", "save");

        // back button
        Button back = new Button("Back");

        setButton(back, 600, 400, 150, 50, 30, "0,0,0", "back");

        // set action on the back button to call the method back();
        back.setOnAction(event -> {
            back();
        });

        // set action on the save button to call the method save();
        Save.setOnAction(event -> {
            save();
        });

        // sets the title and setting the scene.
        stage.setTitle("Reversi Options");
        stage.setScene(scene);
    }

    // creating the save method to check all the variables and puts them into other
    // variables that can be used from other classes.
    private void save() {
        size = Integer.parseInt(lSize.getText());
        selectedTema = (RadioButton) groupTema.getSelectedToggle();
        single = singleplayer.isSelected();
        muligeTræk = possibleMove.isSelected();
        pieceHover = hoverPiece.isSelected();

        if (Tema1.isSelected()) {
            colorInt = 0;
        } else if (Tema2.isSelected()) {
            colorInt = 1;
        } else if (Tema3.isSelected()) {
            colorInt = 2;
        }
        saveConfirm.setText("Options are saved");
        saveConfirm.setTextFill(tekstFarveColor.get(colorInt));
        updateColor();
    }

    // creating the back method to start the menu again and shows it on the screen.
    private void back() {
        ReversiMenu rm = new ReversiMenu(stage);
        rm.show();
    }

    // method to create a button, with all the variables to setup a button.
    public void setButton(Button but, int x, int y, int xSize, int ySize, int fSize, String rgbString, String text) {
        root.getChildren().add(but);
        but.setPrefSize(xSize, ySize);
        but.setLayoutX(x);
        but.setLayoutY(y);
        but.setStyle("-fx-background-color: rgb(" + rgbString + "); -fx-font-size: " + fSize + "px; -fx-cursor: hand");
        but.setTextFill(Color.WHITE);
        but.setText(text);
    }

    void show() {
        this.stage.show();
    }

    private void updateColor() {
        labelSize.setTextFill(tekstFarveColor.get(colorInt));
        lSize.setTextFill(tekstFarveColor.get(colorInt));
        Tema1.setTextFill(tekstFarveColor.get(colorInt));
        Tema2.setTextFill(tekstFarveColor.get(colorInt));
        Tema3.setTextFill(tekstFarveColor.get(colorInt));
        selectColorTema.setTextFill(tekstFarveColor.get(colorInt));
        singleplayer.setTextFill(tekstFarveColor.get(colorInt));
        possibleMove.setTextFill(tekstFarveColor.get(colorInt));
        hoverPiece.setTextFill(tekstFarveColor.get(colorInt));
        scene.setFill(baggrundList.get(colorInt));
    }
}
