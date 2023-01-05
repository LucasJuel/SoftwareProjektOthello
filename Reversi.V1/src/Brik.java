import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.awt.Point;


public class Brik{

    static int color = 1;
    ArrayList<Circle> circles = new ArrayList<Circle>();
    Circle circle;

    public Brik(){

    }

    public Circle setBrik(Point p){
        circle = new Circle();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);
        setColor();
        circles.add(circle);

        return circle;
        // if(circle.getCenterX() <= 800){
        //     System.out.println(color + ", " + p.x + ", " + p.y);
        //     circles.add(circle);
        //     root.getChildren().add(circle);
        // }

        
    }

    public int getColorRep(){
        return color;
    }

    public void setColor(){
        if(color == 1){
            circle.setFill(Paint.valueOf("white"));
            color = 2;
        } else if(color == 2){
            circle.setFill(Paint.valueOf("black"));
            color = 1;
        }
    }
}