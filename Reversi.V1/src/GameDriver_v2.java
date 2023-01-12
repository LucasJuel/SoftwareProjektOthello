import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Point;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameDriver_v2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private int size = 8;
    GameBoard gm = new GameBoard(size);
    Regler ruleBoard = new Regler(size - 1);
    Button genstartSpilKnap;
    Stage primStage;

    private Map<Point, List<Point>> legalMovesMap = new HashMap<>();
    ArrayList<Circle> posCircles = new ArrayList<Circle>();
    ArrayList<ArrayList<Brik_v2>> circleBoard;
    ArrayList<Brik_v2> circleBoardRække;
    private int pass = 0;
    private int winner = 0;
    private int color = 2;

    @Override
    public void start(Stage primStage) throws Exception {
        this.primStage = primStage;
        restartGame();
        // gm.draw(primStage).show();
        // gm.setTurText(2);
        // gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    /**
     * Når der bliver trykket med hånden så bliver denne metode kaldt
     * 
     * @param event
     */
    private void handleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Point p = new Point((int) event.getX() / 100, (int) event.getY() / 100);
            Point q = new Point((int) event.getX(), (int) event.getY());
            if (gm.isOk(p)) {

                // De første 4 moves
                if (ruleBoard.startMoves(p.x, p.y)) {
                    circleBoard.get(p.x).get(p.y).setColor();
                    changeColor();
                    if (ruleBoard.getStartPlacement() < 2) {
                        gm.setTurText(2);
                    } else {
                        gm.setTurText(1);
                    }
                    legalMovesMap = ruleBoard.legalMove(color);
                    if (ruleBoard.getStartPlacement() == 4) {
                        // Da den allerede har ændret farve til den næste brik.
                        //brik.setColorRes();
                        Brik brikPos2 = new Brik(ruleBoard);
                        brikPos2.possibleCircle(legalMovesMap, gm);
                        gm.setTurText(color);
                    }
                } else if (ruleBoard.start() == false && legalMovesMap.containsKey(p)) {
                    // En almindelig tur i spillet
                    pass = 0;
                    Brik brikPos = new Brik(ruleBoard);
                    brikPos.deletePossibleCircle(gm);

                    ruleBoard.standardMove(color, p.x, p.y);
                    List<Point> brikVendes = legalMovesMap.get(p);
                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));
                    for (int i = 0; i < brikVendes.size(); i++) {
                        Brik brikFlip = new Brik(ruleBoard);
                        gm.getRoot().getChildren().add(brikFlip.flipBrik(brikVendes.get(i)));
                    }

                    // Da den allerede har ændret farve til den næste brik.
                    addPosCir();
                }

                while (ruleBoard.start() == false && legalMovesMap.isEmpty()) {
                    if (ruleBoard.start() == false && (pass == 2)) {
                        // Spillet er færdig
                        winner = ruleBoard.winner();
                        System.out.println("winner er " + winner);
                        gm.setVinderText(winner);
                        break;
                    }
                    // Betyder at der skal meldes pas
                    System.out.println("pass");
                    pass++;
                    addPosCir();
                    Brik brik = new Brik(ruleBoard);
                    brik.setColorInt();
                }
            } else if (gm.knapIsPressed(q)) {
                restartGame();
            }
        }
    }

    /**
     * Ændre hvilken farve tur det er
     */
    private void changeColor() {
        if (color == 1) {
            color = 2;
        } else {
            color = 1;
        }
    }

    /**
     * Den genstarter brættet og sætter et nyt spillebræt op
     */
    private void restartGame() {
        gm = new GameBoard(size);
        ruleBoard = new Regler(size - 1);
        gm.draw(primStage).show();
        gm.setTurText(2);
        color = 2;

        circleBoard = new ArrayList<ArrayList<Brik_v2>>(size);
        
        // x koordinattet
        for (int i = 0; i < size; i++) {
            circleBoardRække = new ArrayList<Brik_v2>(size);
            // y koordinattet
            for (int j = 0; j < size; j++) {
                Point p = new Point(i,j);

                Brik_v2 brik = new Brik_v2(ruleBoard, gm, p);
                circleBoardRække.add(brik);
                
            }
            circleBoard.add(circleBoardRække);
        }

        gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    /**
     * Tilføjer cirkler hvor der er mulighed for at ligge en cirkel
     */
    private void addPosCir() {
        changeColor();
        legalMovesMap = ruleBoard.legalMove(color);
        Brik brikPos = new Brik(ruleBoard);
        brikPos.possibleCircle(legalMovesMap, gm);
        gm.setTurText(color);
    }

    public static void printgame(Regler hej) {
        int[][] brat = hej.getGameboard();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                System.out.print(brat[j][i] + " ");
            }
            System.out.println();
        }
    }

}
