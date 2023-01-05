import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Brik extends Application{
    public static void main(String[] args) {
        launch(args);
    }

        
    Group root = new Group();
    int color = 0;
    ArrayList<Circle> circles = new ArrayList<Circle>();

    @Override
    public void start(Stage primStage){
        primStage.setTitle("Du lugter");
        Scene scene = new Scene(root, 1000, 1000);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, this::handleClick);
        scene.setFill(Paint.valueOf("Green"));

        primStage.setScene(scene);
        primStage.show();

    }

    private void handleClick(MouseEvent event){
        Circle circle = new Circle();
        circle.setCenterX(event.getX());
        circle.setCenterY(event.getY());
        circle.setRadius(50);

        //Skifter farven for hvert click
        if(color == 0){
            circle.setFill(Paint.valueOf("black"));
            color = 1;
        } else if(color == 1){
            circle.setFill(Paint.valueOf("white"));
            color = 0;
        }
        circles.add(circle);
        root.getChildren().add(circle);
        System.out.println(circles.toString());

    }
}
