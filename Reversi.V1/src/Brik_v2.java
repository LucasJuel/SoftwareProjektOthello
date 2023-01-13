import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.awt.Point;

public class Brik_v2 {

    private static int color = 1;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private Circle circle;
    private Regler_v2 r;

    /**
     * Initialize Brik_v2 og sætter en brik på Point p på spille brættet
     * 
     * @param r  Objektet Regler
     * @param gm Objektet GameBoard
     * @param p  et Point objekt med positionen på brættet, fra 0 til 7 hvis
     *           størrelsen er 8
     */
    public Brik_v2(Regler_v2 r, GameBoard gm, Point p) {
        this.r = r;
        circle = new Circle();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);
        circles.add(circle);
        circle.setFill(null);
        gm.getRoot().getChildren().add(circle);

    }

    /**
     * Ændre farven af circle objektet efter parameter farve
     * 
     * @param farve farve af brik, 1=hvid, 2=sort, 3=muligbrik, 4=fjern mulig brik
     */
    public void setMuligColor(int farve) {
        if (farve == 1) {
            circle.setFill(Color.rgb(255, 255, 255));
        } else if (farve == 2) {
            circle.setFill(Color.rgb(0, 0, 0));
        } else if (farve == 3) {
            // Flipfarve
            circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(2);
        } else if (farve == 4) {
            circle.setStroke(null);
            circle.setStrokeWidth(1);
        }
    }

    /**
     * Ændre farven af circle uden et argument, den bruger static color for at ændre
     * farven
     */
    public void setColor() {
        if (r.getStartPlacement() <= 4) {
            if (r.getStartPlacement() > 2) {
                circle.setFill(Paint.valueOf("white"));
                color = 2;
            } else {
                circle.setFill(Paint.valueOf("black"));
                color = 1;
            }
        } else {
            if (color == 1) {
                circle.setFill(Color.rgb(255, 255, 255));
                color = 2;
            } else if (color == 2) {
                circle.setFill(Color.rgb(0, 0, 0));
                color = 1;
            }
        }
    }

    /**
     * Til at ændre static int variablen color uden brug af et objekt, derfor
     * static, når der er static og det er den næstes tur
     */
    public static void setPassColor() {
        if (color == 1) {
            color = 2;
        } else {
            color = 1;
        }
    }

}
