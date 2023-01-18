import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class ReversiMenu {
    // Iniciater variabler
    public Stage stage;
    VBox root;
    private Scene scene;
     

    public ReversiMenu(Stage stage){
        this.stage = stage;
        buildMenu();
    }

    //building menu
    private void buildMenu()  {
        root = new VBox();
        scene = new Scene(root,800,500);
        //sets the background color
        root.setStyle("-fx-background-color: green;");

        //creating 3 buttons
        Button start = new Button("Start");
        Button options = new Button("Options");
        Button exit = new Button("Exit");

        //sets minimum width on buttons
        start.setMinWidth(200);
        options.setMinWidth(200);
        exit.setMinWidth(200);


        //adding style to the buttons.
        start.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");
        options.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");     
        exit.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");

        //setting default and cancel buttons.
        start.setDefaultButton(true);
        exit.setCancelButton(true);

        //adding the buttons to Vbox that we are using for the menu. 
        root.getChildren().addAll(start, options, exit);
        root.setAlignment(Pos.CENTER);

        //sets spacing between buttons
        root.setSpacing(60);
        
        //sets setonaction on start button
        start.setOnAction(event -> {
            try {
                start(this.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //sets setonaction on option button
        options.setOnAction(event -> {
            try {
                options(this.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //sets setonaction on exit button
        exit.setOnAction(event -> {
            exit();
        });

        
        //puts a tittle for the program. 
        stage.setTitle("Reversi menu");
        stage.setScene(scene);
    }

    //start method create a GameDriver instance and starts it.
    private void start(Stage stage) throws Exception{
        GameDriver_v2 gd = new GameDriver_v2();
        gd.start(stage);
    }

    //start method create a options instance and starts it.
    private void options(Stage stage){
        Options options = new Options();
        options.OptionsMenu(stage);
    }

    //exit method closes the program. 
    private void exit(){
        Platform.exit();
        System.exit(0);
    }

    public void show() {
        this.stage.show();
    }
}