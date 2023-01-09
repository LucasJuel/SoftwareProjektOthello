import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.Point;

public class Brik {

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
        // Laver en ny cirkel
        circle = new Circle();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);
        circles.add(circle);

        // Ændre farven fra starten så der er to af hver efter hinanden
        if (r.getStartPlacement() <= 4) {
            if (r.getStartPlacement() > 2) {
                circle.setFill(Paint.valueOf("white"));
            } else {
                circle.setFill(Paint.valueOf("black"));
            }

        } else {
            setColor();
        }
        // retunere Circle objektet
        return circle;
    }

    /**
     * Flipper en brik på spille brættet
     * 
     * @param p et Point objekt med positionen på brættet, fra 0 til 7 hvis
     *          størrelsen er 8
     * @return retunere et Circle objekt
     */
    public Circle flipBrik(Point p) {
        // Laver en ny Circle og flipper farven til den rigtige, indsætter cirklen i
        // midten af dens firkant
        circle = new Circle();
        setColorFlip();
        circle.setCenterX(p.x * 100 + 50);
        circle.setCenterY(p.y * 100 + 50);
        circle.setRadius(40);
        return circle;
    }

    /**
     * Laver en Cirkel hvor der er en mulighed for at ligge en brik
     * 
     * @param p et Point objekt med positionen på brættet, fra 0 til 7 hvis
     *          størrelsen er 8
     * @return retunere et Circle objekt
     */
    public void possibleCircle(Map<Point, List<Point>> legalMovesMap, GameBoard gm) {

        delPossiblePosCircle = new ArrayList<Circle>();

        // Den tager hele legalMovesmap og går i gennem alle keys og viser derved alle
        // de mulige stedder at sætte en brik, hvorefter den tilføjer det til
        // delPossiblePosCircle som indeholder alle cirkler som skal slettes
        for (Map.Entry<Point, List<Point>> entry : legalMovesMap.entrySet()) {
            Circle circle = new Circle();
            circle.setFill(null);
            circle.setStroke(Paint.valueOf("blue"));
            circle.setStrokeWidth(2);

            // Sætter den i midten af en firkant
            circle.setCenterX(entry.getKey().x * 100 + 50);
            circle.setCenterY(entry.getKey().y * 100 + 50);
            circle.setRadius(40);
            gm.getRoot().getChildren().add(circle);
            delPossiblePosCircle.add(circle);
        }

    }

    /**
     * Den fjerner de cirkler der er for at vise hvor man kan sætte en brik
     * 
     * @param gm GameBoard objekt
     */
    public void deletePossibleCircle(GameBoard gm) {
        // Går igennem delPossiblePosCircle, og sletter alle Circle objekter i den.
        if (delPossiblePosCircle != null) {
            for (int i = 0; i < delPossiblePosCircle.size(); i++) {
                gm.getRoot().getChildren().remove(delPossiblePosCircle.get(i));
            }
        }

    }

    /**
     * Reseter farven til sort
     */
    public void setColorRes() {
        color = 2;
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

    /**
     * Ændre farven af Cirklen til flip farve,
     */
    public void setColorFlip() {
        if (color == 1) {
            circle.setFill(Paint.valueOf("black"));
        } else if (color == 2) {
            circle.setFill(Paint.valueOf("white"));
        }
    }

    /**
     * Ændre farven af Cirklen uden at have lavet et cirkel objekt i forvejen
     */
    public void setColorInt() {
        if (color == 1) {
            color = 2;
        } else if (color == 2) {
            color = 1;
        }
    }

}