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
    public Stage stage;
    Group root;
    private Scene scene;
    static int size;
    Label lSize;
    Label Colorpieces;
    static boolean single;
    static boolean automatically;
    static boolean pieceHover;
    static boolean showTime;
    CheckBox singleplayer;
    CheckBox automaticallyPiece;
    CheckBox hoverPiece;
    CheckBox showtimeCheck;
    static RadioButton selectedTema;
    ToggleGroup groupTema;
    Label saveConfirm;


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

    private void buildOptions()  {
        root = new Group();
        scene = new Scene(root,800,500);
        scene.setFill(Paint.valueOf("Green"));
    

        //Select Size of the board.
        Label labelSize = new Label("Select your size...");
        lSize = new Label(" ");
        
        lSize.setTextFill(Color.BLACK);

        Slider sliderSize = new Slider();

        sliderSize.setMin(4);
        sliderSize.setMax(16);
        sliderSize.setValue(8);
        size = 8;
        lSize.setText("8");

        //sliderSize.setShowTickLabels(true);
        //sliderSize.setShowTickMarks(true);


        sliderSize.setBlockIncrement(2);
        sliderSize.setMajorTickUnit(2);
        sliderSize.setMinorTickCount(2);
        sliderSize.setSnapToTicks(true);


        sliderSize.valueProperty().addListener(
            new ChangeListener<Number>() {
                
                public void changed(ObservableValue <? extends Number > observable, Number oldValue, Number newValue)
                {
                    lSize.setText(""+newValue.intValue());
                }
            }
        );

        root.getChildren().addAll(labelSize, sliderSize, lSize);
        labelSize.setLayoutX(50);
        labelSize.setLayoutY(30);
        sliderSize.setLayoutX(50);
        sliderSize.setLayoutY(50);
        lSize.setLayoutX(50);
        lSize.setLayoutY(80);

        

        //Creating RadioButton for pieces color
        RadioButton Tema1 = new RadioButton();
        Tema1.setText("Black/white");
        RadioButton Tema2 = new RadioButton();
        Tema2.setText("Green/Blue"); //Canva.com Triadic color Combination #df2120
        RadioButton Tema3 = new RadioButton();
        Tema3.setText("Light Blue/Pink"); // #afe31c

        groupTema = new ToggleGroup();
        Tema1.setToggleGroup(groupTema);
        Tema2.setToggleGroup(groupTema);
        Tema3.setToggleGroup(groupTema);


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

        

        
        //Checkboxes

        singleplayer = new CheckBox("Singleplayer / play against the computer");
        automaticallyPiece = new CheckBox("turn pieces automatically");
        hoverPiece = new CheckBox("see tuned pieces by hovering");
        showtimeCheck = new CheckBox("show the time used for each player");
        
        root.getChildren().addAll(singleplayer, automaticallyPiece, hoverPiece, showtimeCheck);

        singleplayer.setLayoutX(400);
        singleplayer.setLayoutY(50);
        automaticallyPiece.setLayoutX(400);
        automaticallyPiece.setLayoutY(90);
        hoverPiece.setLayoutX(400);
        hoverPiece.setLayoutY(130);
        showtimeCheck.setLayoutX(400);
        showtimeCheck.setLayoutY(170);


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



        back.setOnAction(event -> {
            back();
        });

        Save.setOnAction(event -> {
            save();
        });

    
        stage.setTitle("Reversi Options");
        stage.setScene(scene);
    }


    private void save() {
        size = Integer.parseInt(lSize.getText());
        selectedTema = (RadioButton) groupTema.getSelectedToggle();
        single = singleplayer.isSelected();
        automatically = automaticallyPiece.isSelected();
        pieceHover = hoverPiece.isSelected();
        showTime = showtimeCheck.isSelected();

        saveConfirm.setText("Options are saved");
    }

    private void back(){
        ReversiMenu rm = new ReversiMenu(stage);
        rm.show();
    }

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
