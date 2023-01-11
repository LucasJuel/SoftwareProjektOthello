import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

import java.awt.Point;

public class Brik_v2 {

    static int color = 1;
    ArrayList<Circle> circles = new ArrayList<Circle>();
    static ArrayList<Circle> delPossiblePosCircle;
    Circle circle;
    Regler r;
    boolean checkStart;

    /**
     * initialize Brik med objektet regler fra det spil der er i gang
     * 
     * @param r Objektet Regler
     */
    public Brik_v2(Regler r, GameBoard gm, Point p) {
        this.r = r;
        checkStart = r.start();
        circle = new Circle();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);
        circles.add(circle);
        circle.setFill(null);
        gm.getRoot().getChildren().add(circle);

    }

    /**
     * Sætter en brik på spille brættet
     * 
     * @param p et Point objekt med positionen på brættet, fra 0 til 7 hvis
     *          størrelsen er 8
     * @return retunere et Circle objekt
     */
    public Circle setBrik(Point p) {
        circle = new Circle();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);
        circles.add(circle);
        circle.setFill(null);
        return circle;
    }

    // gør vi kan ændre farven af spillerene i menyen
    public void setColor(int farve) {
        if (farve == 1){
            circle.setFill(Color.rgb(2, 3, 4));
        } else if (farve == 2) {
            circle.setFill(Color.rgb(2, 3, 4));
            
        } else if (farve == 3) {
            //Flipfarve
            circle.setFill(Color.rgb(2, 3, 4));
        }

        if (r.getStartPlacement() <= 4) {
            if (r.getStartPlacement() > 2) {
                circle.setFill(Paint.valueOf("white"));
            } else {
                circle.setFill(Paint.valueOf("black"));
            }

        } else {
            // setColor();
        }

    }


}
