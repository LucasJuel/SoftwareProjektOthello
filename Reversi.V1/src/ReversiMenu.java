import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class ReversiMenu {

    public Stage stage;
    VBox root;
    private Scene scene;
    static int size = 8;

    public static int getSize() {
        return size;
    }

    Label lbsize;
     
    public ReversiMenu(Stage stage){
        this.stage = stage;
        buildMenu();
    }


    private void buildMenu()  {
        root = new VBox();
        scene = new Scene(root,800,500);
        root.setStyle("-fx-background-color: green;");

        Button start = new Button("Start");
        lbsize = new Label("Size");
        Button toggleSize = new Button("Toggle Size");
        Button exit = new Button("Exit");

        start.setMinWidth(200);
        
        toggleSize.setMinWidth(200);

        exit.setMinWidth(200);

        lbsize.setText("Medium, 8");

        start.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");
        toggleSize.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");
        exit.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 30px; -fx-cursor: hand");
        lbsize.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");


        start.setDefaultButton(true);
        exit.setCancelButton(true);

        root.getChildren().addAll(start,lbsize, toggleSize, exit);
        root.setAlignment(Pos.CENTER);

        root.setSpacing(60);
        

        start.setOnAction(event -> {
            try {
                start(this.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        toggleSize.setOnAction(event -> {
            toggleSize();
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

    private void toggleSize(){
        if(size == 8){
            size = 16;
            lbsize.setText("Large, 16");
        }else if(size == 16){
            size = 4;
            lbsize.setText("Small, 4");
        }else if(size == 4){
            size = 8;
            lbsize.setText("Medium, 8");
        }
    }

    private void exit(){
        Platform.exit();
        System.exit(0);
    }

    public void show() {
        this.stage.show();
    }
}
