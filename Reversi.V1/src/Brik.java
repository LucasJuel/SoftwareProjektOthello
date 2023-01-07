import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.awt.Point;

public class Brik {

    static int color = 1;
    ArrayList<Circle> circles = new ArrayList<Circle>();
    Circle circle;
    Regler r;
    boolean checkStart;

    /**
     * initialize Brik med objektet regler fra det spil der er i gang
     * 
     * @param r Objektet Regler
     */
    public Brik(Regler r) {
        this.r = r;
        checkStart = r.start();
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

        if (r.getStartPlacement() < 4) {
            if (r.getStartPlacement() > 2) {
                circle.setFill(Paint.valueOf("white"));
            } else {
                circle.setFill(Paint.valueOf("black"));
            }
        } else {
            setColor();
        }
        return circle;
    }

    /**
     * Retunere farven af brikken der bliver brugt
     * @return 1 for hvid og 2 for sort
     */
    public int getColorRep() {
        return color;
    }

    /**
     * Ændre farven af Cirklen til den modsatte af hvad den er
     */
    public void setColor() {
        if (color == 1) {
            circle.setFill(Paint.valueOf("white"));
            color = 2;
        } else if (color == 2) {
            circle.setFill(Paint.valueOf("black"));
            color = 1;
        }
    }
}