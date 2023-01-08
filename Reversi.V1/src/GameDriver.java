import javafx.application.Application;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Point;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameDriver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    GameBoard gm = new GameBoard(8);
    Regler ruleBoard = new Regler(8 - 1);

    private Map<Point, List<Point>> legalMovesMap = new HashMap<>();
    ArrayList<Circle> posCircles = new ArrayList<Circle>();
    private int pass = 0;
    private int winner = 0;
    private int color = 2;

    @Override
    public void start(Stage primStage) throws Exception {
        gm.draw(primStage).show();
        gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    /**
     * Når der bliver trykket med hånden så bliver denne metode kaldt
     * 
     * @param event
     */
    private void handleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Point p = new Point((int) event.getX() / 100, (int) event.getY() / 100);
            if (gm.isOk(p)) {

                if (ruleBoard.startMoves(p.x, p.y)) {
                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));
                    changeColor();
                    // System.out.println("farven er " + color);
                    legalMovesMap = ruleBoard.legalMove(color);
                    if (ruleBoard.getStartPlacement() == 4) {
                        // Da den allerede har ændret farve til den næste brik.

                    }
                    // System.out.println("De mulige stedder at sætte en brik er " + legalMovesMap);
                    // printgame(ruleBoard);
                } else if (ruleBoard.start() == false && legalMovesMap.isEmpty()) {
                    // Betyder at der skal meldes pas
                    System.out.println("hej");
                    pass++;
                    changeColor();
                    // System.out.println("farven er " + color);
                    legalMovesMap = ruleBoard.legalMove(color);
                } else if (ruleBoard.start() == false && legalMovesMap.containsKey(p)) {
                    // System.out.println(legalMovesMap);
                    // En almindelig tur i spillet
                    Brik brikPos = new Brik(ruleBoard);
                    brikPos.deletePossibleCircle(legalMovesMap, gm);
                    ruleBoard.standardMove(color, p.x, p.y);
                    List<Point> brikVendes = legalMovesMap.get(p);

                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));
                    for (int i = 0; i < brikVendes.size(); i++) {
                        // Man kunne tilføje noget her til at fjerne en cirkel der er under så der ikke
                        // bliver vist rigtig mange cirkler over hinanden.
                        // System.out.println(event.getTarget());
                        // gm.getRoot().getChildren().remove();
                        Brik brikFlip = new Brik(ruleBoard);
                        gm.getRoot().getChildren().add(brikFlip.flipBrik(brikVendes.get(i)));
                        ruleBoard.standardMoveDev(color, brikVendes.get(i).x, brikVendes.get(i).y);
                    }
                    

                    // Da den allerede har ændret farve til den næste brik.
                    changeColor();
                    // System.out.println("farven er " + color);
                    legalMovesMap = ruleBoard.legalMove(color);
                    //System.out.println("De mulige stedder at sætte en brik er " + legalMovesMap);
                    //printgame(ruleBoard);
                    Brik brikPos2 = new Brik(ruleBoard);
                    brikPos2.possibleCircle(legalMovesMap, gm);
                } else if (ruleBoard.start() == false && (legalMovesMap.isEmpty() || pass == 2)) {
                    // Spillet er færdig
                    winner = ruleBoard.winner();
                }
            }
        } else {
            System.out.println(event.getTarget());
            gm.getRoot().getChildren().remove(event.getTarget());
        }
    }

    private void changeColor() {
        if (color == 1) {
            color = 2;
        } else {
            color = 1;
        }
    }

    public static void printgame(Regler rule) {
        int[][] brat = rule.getGameboard();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                System.out.print(brat[j][i] + " ");
            }
            System.out.println();
        }
    }

}
