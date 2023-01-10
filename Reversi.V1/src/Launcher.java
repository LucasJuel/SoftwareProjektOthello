import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {
        ReversiMenu reversiMenu = new ReversiMenu(stage);
        reversiMenu.show();
    }

    public static void main(String[] args){
        launch(args);
    }
    
}
