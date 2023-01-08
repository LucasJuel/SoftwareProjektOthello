import javafx.application.Application;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.Point;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class GameDriver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    GameBoard gm = new GameBoard(8);
    Regler ruleBoard = new Regler(8 - 1);
    private Map<Point, List<Point>> legalMovesMap = new HashMap<>();
    private int pass = 0;
    private int winner = 0;

    @Override
    public void start(Stage primStage) throws Exception {
        gm.draw(primStage).show();
        gm.getGMScene().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleClick);
    }

    /**
     * Når der bliver trykket med hånden så bliver denne metode kaldt
     * @param event
     */
    private void handleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Point p = new Point((int) event.getX() / 100, (int) event.getY() / 100);
            if (gm.isOk(p)) {
                if(ruleBoard.startMoves(p.x, p.y)){
                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));

                    //Da den allerede har ændret farve til den næste brik.
                    legalMovesMap = ruleBoard.legalMove(brik.getColorRep());
                } else if(ruleBoard.start() == false && legalMovesMap == null){
                    //Betyder at der skal meldes pas
                    pass++;
                } else if(ruleBoard.start() == false && legalMovesMap.containsKey(p)) {
                    //En almindelig tur i spillet
                    List<Point> brikVendes = legalMovesMap.get(p);
                    Brik brik = new Brik(ruleBoard);
                    gm.getRoot().getChildren().add(brik.setBrik(p));
                    for (int i = 0; i < brikVendes.size(); i++) {
                        Brik brikFlip = new Brik(ruleBoard);
                        gm.getRoot().getChildren().add(brikFlip.flipBrik(brikVendes.get(i)));
                    }
                    pass = 0;
                    //Da den allerede har ændret farve til den næste brik.
                    legalMovesMap = ruleBoard.legalMove(brik.getColorRep());
                } else if (ruleBoard.start() == false && legalMovesMap == null && pass == 2) {
                    //Spillet er færdig
                    winner = ruleBoard.winner();
                }
            }
        } else {
            System.out.println(event.getTarget());
            gm.getRoot().getChildren().remove(event.getTarget());
        }
    }

}
