import java.awt.Point;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class ReglerDriver {
    public static void main(String[] args) {
        Regler hej = new Regler(7);

        // System.out.println(hej.startmoves(4, 6, 3)+" = false");
        // System.out.println(hej.startmoves(1, 6, 3)+" = false");
        // System.out.println(hej.startmoves(0, 4, 3)+" = false");

        System.out.println(hej.startmoves(1, 3, 3) + " = true");
        System.out.println(hej.startmoves(1, 4, 3) + " = true");
        System.out.println(hej.startmoves(2, 4, 4) + " = true");
        System.out.println(hej.startmoves(2, 3, 4) + " = true");

        // hej.standardmovead(1, 3, 2);
        // hej.standardmovead(2, 2, 4);
        // hej.standardmovead(1, 2, 3);
        // hej.standardmovead(1, 5, 4);
        // hej.standardmovead(1, 6, 4);
        // System.out.println(hej.startmoves(1, 4, 3)+ " = false");
        // System.out.println(hej.startmoves(1, 3, 3)+ " = false");

        move(hej, 1, 4, 5);
        move(hej, 2, 5, 2);
        move(hej, 1, 4, 2);
        move(hej, 2, 3, 2);
        move(hej, 1, 2, 3);
        move(hej, 2, 4, 6);
        move(hej, 1, 6, 1);
        move(hej, 2, 1, 2);
        move(hej, 1, 4, 7);
        move(hej, 2, 7, 0);
        move(hej, 1, 0, 1);
        move(hej, 2, 1, 3);
        move(hej, 1, 3, 1);
        move(hej, 2, 5, 6);
        move(hej, 1, 6, 7);
        move(hej, 2, 1, 1);
        move(hej, 1, 5, 3);
        move(hej, 2, 6, 3);
        move(hej, 1, 6, 2);
        move(hej, 2, 4, 1);
        move(hej, 1, 0, 3);
        move(hej, 2, 3, 5);
        move(hej, 1, 2, 1);
        move(hej, 2, 7, 1);
        move(hej, 1, 7, 2);
        move(hej, 2, 3, 0);
        move(hej, 1, 2, 6);
        move(hej, 2, 5, 4);
        move(hej, 1, 6, 5);
        move(hej, 2, 3, 6);
        move(hej, 1, 3, 7);
        move(hej, 2, 2,5);
        move(hej, 1, 5,1);
        move(hej, 2, 5,0);
        move(hej, 1, 7,3);
        move(hej, 2, 1,0);
        move(hej, 1, 5,5);
        move(hej, 2, 6,4);
        move(hej, 1, 6,0);
        move(hej, 2, 7,4);
        move(hej, 1, 1,6);
        move(hej, 2, 2,0);
        move(hej, 1, 2,2);
        move(hej, 2, 4,0);
        move(hej, 1, 0,0);
        move(hej, 2, 0,7);
        move(hej, 1, 7,5);
        move(hej, 2, 6,6);
        move(hej, 1, 7,7);
        move(hej, 2, 2,4);
        move(hej, 1, 2,7);
        move(hej, 2, 1,4);
        move(hej, 1, 1,5);
        move(hej, 2, 0,5);
        move(hej, 1, 5,7);
        move(hej, 2, 7,6);
        move(hej, 1, 1,7);
        move(hej, 2, 0,2);
        move(hej, 1, 0,4);
        move(hej, 2, 0,6);
        System.out.println(hej.winner());
        

        // System.out.println(hej.legalmove(2));

        //

        // printgame(hej);

        // printMap(hej.legalmove(1));

        // hej.standardmove(1, 4, 5);
        // printgame(hej);
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

    public static void move(Regler hej, int f, int x, int y) {

        printMap(hej.legalmove(f));
        hej.standardmove(f, x, y);
        if (f == 1) {
            f = 2;
        } else {
            f = 1;
        }

        System.out.println("ny");
        printMap(hej.legalmove(f));
        printgame(hej);
    }

}