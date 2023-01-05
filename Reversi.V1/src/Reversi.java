import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Reversi extends Application {
    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reversi demo");

        Label nul = new Label("0");

        nul.setFont(new Font(50));

        GridPane gp = new GridPane();
        gp.setGridLinesVisible(true);

        Scene scene = new Scene(gp, 500, 500);


        gp.setVgap(50);
        gp.setHgap(60);
        
  



        for (int i = 0; i < 8; i++) {

            for (int j = 0; j <7;j++) {
                nul = new Label("0");
                gp.addColumn(j, nul);
            }
            nul = new Label("0");
            gp.addRow(i, nul);
        }

    

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
