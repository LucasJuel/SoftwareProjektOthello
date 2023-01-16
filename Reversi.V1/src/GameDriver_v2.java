import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Point;
import java.text.RuleBasedCollator;
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
    Brik_v2 brikobj;
    private int pass = 0;
    private int winner = 0;
    private int color = 2;
    private boolean isLoaded = false;
    Point q;
    SaveNContinue save = new SaveNContinue(null, color);


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
            q = new Point((int) event.getX(), (int) event.getY());
            // Tjekker om brugeren trykker inde på spillebrættet

            if (gm.isOk(p)) {

                // ----- De første 4 moves -----
                // Tjekker om der man har lagt brikken i midten og tilføjer den i Regler
                if (ruleBoard.startMoves(p.x, p.y) && isLoaded == false) {

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
                    for (Map.Entry<Point, List<Point>> entry : legalMovesMap.entrySet()) {
                        circleBoard.get(entry.getKey().x).get(entry.getKey().y).setMuligColor(4);
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
                    pass++;
                    addPosCir();
                    // Kalder en static metode
                    Brik_v2.setPassColor();
                }
            } else if (gm.genstartIsPressed(q)) {
                restartGame();
            } else if (gm.saveIsPressed(q)){
                saveGame();
            } else if (gm.loadIsPressed(q)){
                loadGame();                
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
        pass = 0;

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

    private void saveGame(){
        System.out.println("hej");
        brikobj = new Brik_v2(ruleBoard, gm, q);
        save = new SaveNContinue(ruleBoard.getGameboard(), brikobj.getColorAtTurn());
        save.writeToFile();
    }

    private void loadGame(){
        restartGame();

        save = new SaveNContinue(ruleBoard.getGameboard(), color);

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.print("plads: " + j + "" + i + "farve: " + save.getSavedBoard()[j][i] + " | ");
                ruleBoard.standardMoveDev(Integer.parseInt(save.getSavedBoard()[j][i]), j, i);
            }
            System.out.println();
        }
        ruleBoard.printgame();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                circleBoard.get(i).get(j).setMuligColor(Integer.parseInt(save.getSavedBoard()[i][j]));
                Brik_v2.setColorAtTurn(Integer.parseInt(save.getSavedBoard()[i][j]));
            }
        }
        legalMovesMap = ruleBoard.legalMove(save.getColor());
        gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);


        // ruleBoard.skipStart();
        // for (int i = 0; i <= 7; i++) {
        //      circleBoardRække = new ArrayList<Brik_v2>(size);

        //      for (int j = 0; j <= 7; j++) {
        //         Point p = new Point(i, j);

        //         System.out.print("plads: " + j + "" + i + "farve: " + save.getSavedBoard()[j][i] + " | ");

        //         Brik_v2 brik = new Brik_v2(ruleBoard, gm, p, save.getSavedBoard()[i][j], save.getColor());
        //         circleBoardRække.add(brik);
        //      }
        //      System.out.println();
        //  }
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
            }
            // Ændre farve på tur teksten
            gm.setTurText(color);
        }
    }
}