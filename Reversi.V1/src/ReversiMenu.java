import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class ReversiMenu {

    public Stage stage;
    VBox root;
    private Scene scene;
     
    public ReversiMenu(Stage stage){
        this.stage = stage;
        buildMenu();
    }


    private void buildMenu()  {
        root = new VBox();
        scene = new Scene(root,800,500);
        root.setStyle("-fx-background-color: green;");

        Button start = new Button("Start");
        Button options = new Button("Options");
        Button exit = new Button("Exit");

        start.setMinWidth(200);
        


        exit.setMinWidth(200);



        start.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");
        options.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");     
        exit.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");


        start.setDefaultButton(true);
        exit.setCancelButton(true);

        root.getChildren().addAll(start, options, exit);
        root.setAlignment(Pos.CENTER);

        root.setSpacing(60);
        

        start.setOnAction(event -> {
            try {
                start(this.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        options.setOnAction(event -> {
            try {
                options(this.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        exit.setOnAction(event -> {
            exit();
        });

        
        
        stage.setTitle("Reversi menu");
        stage.setScene(scene);
    }

    private void start(Stage stage) throws Exception{
        GameDriver gd = new GameDriver();
        gd.start(stage);
    }

    private void options(Stage stage){
        Options options = new Options();
        options.OptionsMenu(stage);
    }


    private void exit(){
        Platform.exit();
        System.exit(0);
    }

    public void show() {
        this.stage.show();
    }
}
