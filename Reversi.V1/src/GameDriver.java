import javafx.application.Application;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.Point;

public class GameDriver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    GameBoard gm = new GameBoard(8);

    @Override
    public void start(Stage primStage) throws Exception {
        gm.draw(primStage).show();
        gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    private void handleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Point p = new Point((int) event.getX() / 100, (int) event.getY() / 100);
            if (gm.isOK(p)) {
                Brik brik = new Brik();
                gm.getRoot().getChildren().add(brik.setBrik(p));
            }
        } else {
            System.out.println(event.getTarget());
            gm.getRoot().getChildren().remove(event.getTarget());
        }

    }
}
