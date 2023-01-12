import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReglerDriver {
    public static void main(String[] args) {
        Regler hej = new Regler(7);

        // System.out.println(hej.startmoves(4, 6, 3)+" = false");
        // System.out.println(hej.startmoves(1, 6, 3)+" = false");
        // System.out.println(hej.startmoves(0, 4, 3)+" = false");

        System.out.println(hej.startMoves(3, 3) + " = true");
        System.out.println(hej.startMoves(4, 3) + " = true");
        System.out.println(hej.startMoves(4, 4) + " = true");
        System.out.println(hej.startMoves(3, 4) + " = true");

      hej.standardMoveDev(1, 3, 2);
        hej.standardMoveDev(2, 2, 4);
        hej.standardMoveDev(1, 2, 3);
        hej.standardMoveDev(1, 5, 4);
        hej.standardMoveDev(1, 6, 4);
        // System.out.println(hej.startmoves(1, 4, 3)+ " = false");
        // System.out.println(hej.startmoves(1, 3, 3)+ " = false");

       printgame(hej);

        // System.out.println(hej.legalMove(2));
        printMap(hej.legalMove(2));

        hej.standardMove(2, 7, 4);

        //printgame(hej);
        System.out.println("hej");
       // printMap(hej.legalMove(1));

       // hej.standardMove(1, 4, 5);
       // move(hej, 2, 0, 2);
      //  move(hej, 1, 0, 4);
      //  move(hej, 2, 0, 6);

        printMap(hej.bestMove());
        //System.out.println(hej.winner());

        // System.out.println(hej.legalmove(2));

        //

        // printgame(hej);

        // printMap(hej.legalmove(1));

        // hej.standardmove(1, 4, 5);
        // printgame(hej);
    }

    /**
     * Printer hele spillet
     * 
     * @param hej objektet Regler som er i brug
     */
    public static void printgame(Regler hej) {
        int[][] brat = hej.getGameboard();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                System.out.print(brat[j][i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Printer et hashmap af hvilke positioner der er mulige at stille en brik på
     * efter reglerne
     * 
     * @param map Hashmappet med alle Point objekterne
     */
    public static void printMap(Map<Point, List<Point>> map) {
        for (Map.Entry<Point, List<Point>> entry : map.entrySet()) {
            Point key = entry.getKey();
            List<Point> value = entry.getValue();
            System.out.print("[x=" + (int) key.getX() + ", y=" + (int) key.getY() + "] : ");
            for (int i = 0; i < value.size(); i++) {
                System.out.print("[x=" + (int) value.get(i).getX() + ", y=" + (int) value.get(i).getY() + "], ");
            }
            System.out.println();
        }
    }
   
    /**
     * Laver et move ud fra farve på spillebrættet og kalder legalMove igen
     * 
     * @param hej Objektet Regler
     * @param f Farven af spilleren der skal flytte
     * @param x x positionen
     * @param y y positionen
     */
    public static void move(Regler hej, int f, int x, int y) {
        printMap(hej.legalMove(f));
        hej.standardMove(f, x, y);
        if (f == 1) {
            f = 2;
        } else {
            f = 1;
        }

        System.out.println("ny");
        printMap(hej.legalMove(f));
        printgame(hej);
    }

}