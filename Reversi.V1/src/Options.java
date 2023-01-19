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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Options {

    // Iniciater variabler
    public Stage stage;
    Group root;
    private Scene scene;
    static int size = 8;
    Label lSize;
    Label Colorpieces;
    static boolean single = false;
    static boolean automatically = true;
    static boolean pieceHover = true;
    static boolean showTime = true;
    CheckBox singleplayer;
    CheckBox automaticallyPiece;
    CheckBox hoverPiece;
    CheckBox showtimeCheck;
    RadioButton Tema1;
    RadioButton Tema2;
    RadioButton Tema3;
    static RadioButton selectedTema;
    ToggleGroup groupTema;
    Label saveConfirm;

    //implements getters for import the values to the main game driver. to adjust the settings set for the game.
    public static int getSize() {
        return size;
    }

    public static boolean isSingle() {
        return single;
    }

    public static boolean isautomatically() {
        return automatically;
    }

    public static boolean ispieceHover() {
        return pieceHover;
    }

    public static boolean isshowTime() {
        return showTime;
    }

    public static RadioButton getSelectedTema() {
        return selectedTema;
    }




    public void OptionsMenu(Stage stage){
        this.stage = stage;
        buildOptions();
    }
    

    //builds the options menu window and all the visuals inside. 
    private void buildOptions()  {
        root = new Group();
        scene = new Scene(root,800,500);
        scene.setFill(Paint.valueOf("Green"));
    

        //Select Size of the board.
        Label labelSize = new Label("Select your size...");
        lSize = new Label(" ");
        lSize.setTextFill(Color.BLACK);

        //creating a slider for selecting the size
        Slider sliderSize = new Slider();

        //set min and max values with a default value. 
        sliderSize.setMin(2);
        sliderSize.setMax(8);
        sliderSize.setValue(4);
        size = 8;
        lSize.setText("8");

        //Create a visual on the slider so it's easier to see what value you are selecting.
        sliderSize.setShowTickMarks(true);



        //creating a listener on the slider to create a label to show the value.
        sliderSize.valueProperty().addListener(
            new ChangeListener<Number>() {
                
                public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue)
                {
                    lSize.setText(""+newValue.intValue()*2);
                }
            }
        );


        //adds the slider to the group so we can show them. and setting coordinates. 
        root.getChildren().addAll(labelSize, sliderSize, lSize);
        labelSize.setLayoutX(50);
        labelSize.setLayoutY(30);
        sliderSize.setLayoutX(50);
        sliderSize.setLayoutY(50);
        lSize.setLayoutX(200);
        lSize.setLayoutY(50);

        

        //Creating RadioButton for pieces color
        Tema1 = new RadioButton();
        Tema1.setText("Black/white");
        Tema2 = new RadioButton();
        Tema2.setText("Green/Blue"); //Canva.com Triadic color Combination #df2120
        Tema3 = new RadioButton();
        Tema3.setText("Light Blue/Pink"); // #afe31c

        //creating a Togglegroup so only one of these can be selected at a time
        groupTema = new ToggleGroup();
        Tema1.setToggleGroup(groupTema);
        Tema2.setToggleGroup(groupTema);
        Tema3.setToggleGroup(groupTema);
        Tema1.setSelected(true);


        //setting up the select color with radio buttons where it's going to be displayed on the screen.
        Label selectColorTema = new Label("Select color of your Pieces");
        root.getChildren().addAll(Tema1,Tema2,Tema3, selectColorTema);
        selectColorTema.setLayoutX(50);
        selectColorTema.setLayoutY(200);
        Tema1.setLayoutX(50);
        Tema1.setLayoutY(220);
        Tema2.setLayoutX(50);
        Tema2.setLayoutY(240);
        Tema3.setLayoutX(50);
        Tema3.setLayoutY(260);

        

        
        //Checkboxes with descriptions. 

        singleplayer = new CheckBox("Singleplayer / play against the computer");
        automaticallyPiece = new CheckBox("turn pieces automatically");
        hoverPiece = new CheckBox("see tuned pieces by hovering");
        showtimeCheck = new CheckBox("show the time used for each player");
        
        //adding the checkboxes to the group and adding coordinates to the group. 
        root.getChildren().addAll(singleplayer, automaticallyPiece, hoverPiece, showtimeCheck);
        singleplayer.setLayoutX(400);
        singleplayer.setLayoutY(50);
        automaticallyPiece.setLayoutX(400);
        automaticallyPiece.setLayoutY(90);
        hoverPiece.setLayoutX(400);
        hoverPiece.setLayoutY(130);
        showtimeCheck.setLayoutX(400);
        showtimeCheck.setLayoutY(170);

        if(single = true){
           singleplayer.setSelected(true);
        }else{
            singleplayer.setSelected(false);
        }

        if(automatically = true){
            automaticallyPiece.setSelected(true);
         }else{
            automaticallyPiece.setSelected(false);
         }

         if(pieceHover = true){
            hoverPiece.setSelected(true);
         }else{
            hoverPiece.setSelected(false);
         }

         if(showTime = true){
            showtimeCheck.setSelected(true);
         }else{
            showtimeCheck.setSelected(false);
         }


        //creating a label to give a confirmation message on when the settings are saved. 
        saveConfirm = new Label("");
        root.getChildren().add(saveConfirm);
        saveConfirm.setLayoutX(400);
        saveConfirm.setLayoutY(380);
        


        //Save button
        
        Button Save = new Button("Save");

        setButton(Save, 400, 400, 150, 50, 30, "0,0,0", "save");

        //back button
        Button back = new Button("Back");

        setButton(back, 600, 400, 150, 50, 30, 	"0,0,0", "back");


        //set action on the back button to call the method back();
        back.setOnAction(event -> {
            back();
        });

        //set action on the save button to call the method save();
        Save.setOnAction(event -> {
            save();
        });

    
        //sets the title and setting the scene. 
        stage.setTitle("Reversi Options");
        stage.setScene(scene);
    }


    //creating the save method to check all the variables and puts them into other variables that can be used from other classes.
    private void save() {
        size = Integer.parseInt(lSize.getText())/2;
        selectedTema = (RadioButton) groupTema.getSelectedToggle();
        single = singleplayer.isSelected();
        automatically = automaticallyPiece.isSelected();
        pieceHover = hoverPiece.isSelected();
        showTime = showtimeCheck.isSelected();

        saveConfirm.setText("Options are saved");
    }

    //creating the back method to start the menu again and shows it on the screen. 
    private void back(){
        ReversiMenu rm = new ReversiMenu(stage);
        rm.show();
    }

    // method to create a button, with all the variables to setup a button. 
    public void setButton(Button but, int x, int y, int xSize, int ySize, int fSize, String rgbString, String text) {
        root.getChildren().add(but);
        but.setPrefSize(xSize,ySize);
        but.setLayoutX(x);
        but.setLayoutY(y);
        but.setStyle("-fx-background-color: rgb("+rgbString+"); -fx-font-size: "+fSize+"px; -fx-cursor: hand");
        but.setTextFill(Color.WHITE);
        but.setText(text);
    }

    void show(){
        this.stage.show();
    }
}
