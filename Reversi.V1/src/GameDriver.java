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

public class GameDriver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // Iniciater variabler
    private int size = 8;
    GameBoard gm = new GameBoard(size);
    Regler ruleBoard = new Regler(size - 1);
    Button genstartSpilKnap;
    Stage primStage;
    private Map<Point, List<Point>> legalMovesMap = new HashMap<>();
    ArrayList<Circle> posCircles = new ArrayList<Circle>();
    private int pass = 0;
    private int winner = 0;
    private int color = 2;

    @Override
    public void start(Stage primStage) throws Exception {
        // Opretter Stage og tegner scenen, og gør så der sker noget når man klikker
        this.primStage = primStage;
        gm.draw(primStage).show();
        gm.setTurText(2);
        gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    /**
     * Når der bliver trykket med mussen så bliver denne metode kaldt
     * 
     * @param event
     */
    private void handleClick(MouseEvent event) {
        // Tjekker om det er venstre knappen der bliver trykket
        if (event.getButton() == MouseButton.PRIMARY) {
            // Laver to point for hvor på brættet der trykkes eller hvor på skærmen
            Point p = new Point((int) event.getX() / 100, (int) event.getY() / 100);
            Point q = new Point((int) event.getX(), (int) event.getY());

            // Tjekker om brugeren trykker inde på spillebrættet
            if (gm.isOk(p)) {
                // ----- De første 4 moves -----
                // Tjekker om der man har lagt brikken i midten og tilføjer den i Regler
                if (ruleBoard.startMoves(p.x, p.y)) {

                    // Laver en brik med farve og viser den og derefter holder styr på hvilken farve
                    // der skal være den næste
                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));
                    changeColor();
                    if (ruleBoard.getStartPlacement() < 2) {
                        gm.setTurText(2);
                    } else {
                        gm.setTurText(1);
                    }

                    // Tjekker for legalmoves og sender et hashmap af legalMove tilbage ud fra en
                    // farve
                    legalMovesMap = ruleBoard.legalMove(color);
                    if (ruleBoard.getStartPlacement() == 4) {

                        // Laver en brik for alle steder det er muligt at ligge en ud fra hashmappet
                        brik.setColorRes();
                        Brik brikPos2 = new Brik(ruleBoard);
                        brikPos2.possibleCircle(legalMovesMap, gm);

                        // Ændre farve på tur teksten
                        gm.setTurText(color);
                    }

                    // Ser om der er blevet lagt de 4 første brikker og derefter ser om det er et
                    // legalmove.
                } else if (ruleBoard.start() == false && legalMovesMap.containsKey(p)) {
                    // ----- En tur ------

                    // Sætter antal pass til 0 og sletter alle mulige træk man kan lave
                    pass = 0;
                    Brik brikPos = new Brik(ruleBoard);
                    brikPos.deletePossibleCircle(gm);

                    // Opdatere Regler med det træk der bliver lavet
                    ruleBoard.standardMove(color, p.x, p.y);

                    // Laver en brik hvor der er blevet trykket
                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));

                    // Vender brikkerne der skal flippes, får Value listen fra den bestemte key fra
                    // hashmappet, og derefter laver en ny brik så den nye brik er vendt om
                    List<Point> brikVendes = legalMovesMap.get(p);
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
}
