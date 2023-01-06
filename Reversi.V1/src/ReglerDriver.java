import java.awt.Point;
import java.util.List;
import java.util.Map;

public class ReglerDriver {
    public static void main(String[] args) {
        Regler hej = new Regler(7);

        // System.out.println(hej.startmoves(4, 6, 3)+" = false");
        // System.out.println(hej.startmoves(1, 6, 3)+" = false");
        // System.out.println(hej.startmoves(0, 4, 3)+" = false");

        System.out.println(hej.startmoves( 3, 3) + " = true");
        System.out.println(hej.startmoves(4, 3) + " = true");
        System.out.println(hej.startmoves(4, 4) + " = true");
        System.out.println(hej.startmoves(3, 4) + " = true");

        hej.standardmovead(1, 3, 2);
        hej.standardmovead(2, 2, 4);
        hej.standardmovead(1, 2, 3);
        hej.standardmovead(1, 5, 4);
        hej.standardmovead(1, 6, 4);
        // System.out.println(hej.startmoves(1, 4, 3)+ " = false");
        // System.out.println(hej.startmoves(1, 3, 3)+ " = false");

        printgame(hej);

        // System.out.println(hej.legalmove(2));
        printMap(hej.legalmove(2));

        hej.standardmove(2, 7, 4);

        printgame(hej);
        System.out.println("hej");
        printMap(hej.legalmove(1));

        hej.standardmove(1, 4, 5);
        //printgame(hej);
    }

    public static void printgame(Regler hej) {
        int[][] brat = hej.gameboard();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                System.out.print(brat[j][i] + " ");
            }
            System.out.println();
        }
    }

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

}