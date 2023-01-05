import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.awt.Point;


public class Brik extends Application{
    public static void main(String[] args) {
        launch(args);
    }

        
    Group root = new Group();
    int color = 1;
    ArrayList<Circle> circles = new ArrayList<Circle>();
    Text player = new Text();

    
    @Override
    public void start(Stage primStage){
        primStage.setTitle("Den bedste Reversi");
        Scene scene = new Scene(root, 1000, 800);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, this::handleClick);
        scene.setFill(Paint.valueOf("Green"));
        primStage.setResizable(false);

        player.setX(850);
        player.setY(200);
        root.getChildren().add(player);

        primStage.getIcons().add(new Image("file:./img/IconReversi.png"));

        for(int i = 0; i < scene.getHeight()+100; i+=100){
            Line Xgridline = new Line(i, 0, i, scene.getHeight());
            Xgridline.setFill(Paint.valueOf("black"));
            root.getChildren().add(Xgridline);

            Line Ygridline = new Line(0, i, scene.getWidth()-200, i);
            Ygridline.setFill(Paint.valueOf("black"));
            root.getChildren().add(Ygridline);
        }
        
        primStage.setScene(scene);
        primStage.show();
    }

    /**
     * 
     * @param event
     */
    private void handleClick(MouseEvent event){        
        Point p = new Point(makeInt(event.getX()), (makeInt(event.getY())));
        setCircle(p);
    }

    private int makeInt(double dToI){
        int newInt = (int) Math.round(dToI);
        return newInt/100;
    }


    private void setCircle(Point p){
        Circle circle = new Circle();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);

        if(color == 1){
            circle.setFill(Paint.valueOf("white"));
            player.setText("Sort");
            player.setFill(Paint.valueOf("black"));
            color = 2;
        } else if(color == 2){
            player.setText("Hvid");
            circle.setFill(Paint.valueOf("black"));
            player.setFill(Paint.valueOf("white"));
            color = 1;
        }

        
        if(circle.getCenterX() <= 800){
            System.out.println(color + ", " + p.x + ", " + p.y);
            circles.add(circle);
            root.getChildren().add(circle);
        }

    }
}