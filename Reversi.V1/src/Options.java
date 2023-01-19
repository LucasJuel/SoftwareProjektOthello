import javafx.beans.value.ObservableValue;
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
    static int size;
    Label lSize;
    Label Colorpieces;
    static boolean single = true;
    static boolean muligeTræk;
    static boolean pieceHover;
    static boolean showTime;
    CheckBox singleplayer;
    CheckBox possibleMove;
    CheckBox hoverPiece;
    CheckBox showtimeCheck;
    static RadioButton selectedTema;
    ToggleGroup groupTema;
    Label saveConfirm;
    //private ArrayList<Color> player1 = ;

    private int player1Int = 1;


    // implements getters for import the values to the main game driver. to adjust
    // the settings set for the game.
    public static int getSize() {
        return size;
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

    public static boolean isshowTime() {
        return showTime;
    }

    public static Color getPlayer2Color() {
        return Color.BLACK;
    }

    public static String getPlayer2String() {

        return "White";
    }

    public static Color getPlayer1Color() {
        return Color.WHITE;
    }

    public static String getPlayer1String() {

        return "Black";
    }

    public static Color getBaggrundsColor() {
        return Color.GREEN;
    }

    public static String getBaggrundString() {
        return "008000";
    }

    public static Color getFlipColor() {
        return Color.BLUE;
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
        Label labelSize = new Label("Select your size...");
        lSize = new Label(" ");
        lSize.setTextFill(Color.BLACK);

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
        sliderSize.setShowTickLabels(true);
        sliderSize.setShowTickMarks(true);
        sliderSize.setMajorTickUnit(2);
        sliderSize.setMinorTickCount(2);

        // creating a listener on the slider to create a label to show the value.
        sliderSize.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                            Number newValue) {
                        lSize.setText("" + newValue.intValue()*2);
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
        RadioButton Tema1 = new RadioButton();
        Tema1.setText("Black/white");
        Tema1.setSelected(true);
        RadioButton Tema2 = new RadioButton();
        Tema2.setText("Green/Blue"); // Canva.com Triadic color Combination #df2120
        RadioButton Tema3 = new RadioButton();
        Tema3.setText("Light Blue/Pink"); // #afe31c

        // creating a Togglegroup so only one of these can be selected at a time
        groupTema = new ToggleGroup();
        Tema1.setToggleGroup(groupTema);
        Tema2.setToggleGroup(groupTema);
        Tema3.setToggleGroup(groupTema);

        // setting up the select color with radio buttons where it's going to be
        // displayed on the screen.
        Label selectColorTema = new Label("Select color of your Pieces");
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
        showtimeCheck = new CheckBox("show the time used for each player");

        // adding the checkboxes to the group and adding coordinates to the group.
        root.getChildren().addAll(singleplayer, possibleMove, hoverPiece, showtimeCheck);
        singleplayer.setLayoutX(400);
        singleplayer.setLayoutY(50);
        possibleMove.setLayoutX(400);
        possibleMove.setLayoutY(90);
        hoverPiece.setLayoutX(400);
        hoverPiece.setLayoutY(130);
        showtimeCheck.setLayoutX(400);
        showtimeCheck.setLayoutY(170);

        // creating a label to give a confirmation message on when the settings are
        // saved.
        saveConfirm = new Label("");
        root.getChildren().add(saveConfirm);
        saveConfirm.setLayoutX(400);
        saveConfirm.setLayoutY(380);

        if(single){
            singleplayer.setSelected(true);
         }else{
             singleplayer.setSelected(false);
         }
 
         if(muligeTræk){
             possibleMove.setSelected(true);
          }else{
             possibleMove.setSelected(false);
          }
 
          if(pieceHover){
             hoverPiece.setSelected(true);
          }else{
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
        showTime = showtimeCheck.isSelected();

        saveConfirm.setText("Options are saved");
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
}
