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
import javafx.event.EventHandler;

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
        // Opretter Stage og tegner scenen, og gør så der sker noget når man klikker
        this.primStage = primStage;
        restartGame();
    }

    /**
     * Når der bliver trykket med hånden så bliver denne metode kaldt
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
                // System.out.println(ruleBoard.getGameboard());
                // ----- De første 4 moves -----
                // Tjekker om der man har lagt brikken i midten og tilføjer den i Regler
                if (ruleBoard.startMoves(p.x, p.y)) {

                    // Laver en brik med farve og viser den og derefter holder styr på hvilken farve
                    // der skal være den næste
                    circleBoard.get(p.x).get(p.y).setColor();

                    if (ruleBoard.getStartPlacement() < 2) {
                        gm.setTurText(2);
                    } else {
                        gm.setTurText(1);
                    }
                    addPosCir();

                    // Ser om der er blevet lagt de 4 første brikker og derefter ser om det er et
                    // legalmove.
                } else if (ruleBoard.start() == false && legalMovesMap.containsKey(p)) {
                    // ----- En tur ------

                    // Sætter antal pass til 0 og sletter alle mulige træk man kan lave
                    pass = 0;
                    gm.setVinderText(5);
                    for (Map.Entry<Point, List<Point>> entry : legalMovesMap.entrySet()) {
                        circleBoard.get(entry.getKey().x).get(entry.getKey().y).setMuligColor(4);
                        delFlipCircles(entry.getKey().x, entry.getKey().y);
                    }

                    // Opdatere Regler med det træk der bliver lavet
                    ruleBoard.standardMove(color, p.x, p.y);

                    // Laver en brik hvor der er blevet trykket
                    circleBoard.get(p.x).get(p.y).setColor();

                    // Vender brikkerne der skal flippes, får Value listen fra den bestemte key fra
                    // hashmappet, og derefter ændre farven på brikken der er der
                    List<Point> brikVendes = legalMovesMap.get(p);
                    for (int i = 0; i < brikVendes.size(); i++) {
                        circleBoard.get(brikVendes.get(i).x).get(brikVendes.get(i).y).setMuligColor(color);
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
                    if (color == 1) {
                        gm.setVinderText(3);
                    } else if (color == 2) {
                        gm.setVinderText(4);

                    }

                    pass++;
                    addPosCir();
                    // Kalder en static metode
                    Brik_v2.setPassColor();
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
                Point p = new Point(i, j);

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
        // Tjekker for legalmoves og sender et hashmap af legalMove tilbage ud fra en
        // farve
        changeColor();
        legalMovesMap = ruleBoard.legalMove(color);
        if (ruleBoard.getStartPlacement() >= 4) {

            // Laver en brik for alle steder det er muligt at ligge en ud fra hashmappet
            for (Map.Entry<Point, List<Point>> entry : legalMovesMap.entrySet()) {
                circleBoard.get(entry.getKey().x).get(entry.getKey().y).setMuligColor(3);
                addFlipCircles(entry.getKey().x, entry.getKey().y, entry.getValue());

            }
            // Ændre farve på tur teksten
            gm.setTurText(color);
        }
    }

    /**
     * Tilføjer de cirkler der bliver vendt når der bliver trykket på en brik
     * 
     * @param x
     * @param y
     * @param pList
     */
    public void addFlipCircles(int x, int y, List<Point> pList) {
        circleBoard.get(x).get(y).getCircle().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int i = 0; i < pList.size(); i++) {
                    circleBoard.get(pList.get(i).x).get(pList.get(i).y).setMuligColor(5);
                }

            }
        });
        circleBoard.get(x).get(y).getCircle().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int i = 0; i < pList.size(); i++) {
                    circleBoard.get(pList.get(i).x).get(pList.get(i).y).setMuligColor(6);
                }
            }
        });
        circleBoard.get(x).get(y).getCircle().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int i = 0; i < pList.size(); i++) {
                    circleBoard.get(pList.get(i).x).get(pList.get(i).y).setMuligColor(6);
                }
            }
        });

    }

    public void delFlipCircles(int x, int y) {
        circleBoard.get(x).get(y).getCircle().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        circleBoard.get(x).get(y).getCircle().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }

}