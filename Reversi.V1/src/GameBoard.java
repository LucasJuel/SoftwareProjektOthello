import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.awt.Point;
    

public class GameBoard {

    private int size;
    private Group root = new Group();
    public Scene scene = new Scene(root);
    int height;

    public GameBoard(int size) {
        this.size = size;
    }

    /**
     * Laver et spillebræt som er grønt med alle firkanter
     * 
     * @param primStage Stage der bliver brugt
     * @return Stagen der er i gang med at blive brugt
     */
    public Stage draw(Stage primStage) {
        height = size * 100;
        primStage.setTitle("Den bedste Reversi");

        primStage.setHeight(height + 40);
        primStage.setWidth(height + 17 + 200);

        primStage.setScene(scene);
        primStage.setResizable(false);
        scene.setFill(Paint.valueOf("Green"));
        primStage.getIcons().add(new Image("file:./img/IconReversi.png"));

        for (int i = 0; i < height + 100; i += 100) {
            System.out.println(i);
            Line Xgridline = new Line(i, 0, i, height);
            Xgridline.setFill(Paint.valueOf("black"));
            root.getChildren().add(Xgridline);

            Line Ygridline = new Line(0, i, height, i);
            Ygridline.setFill(Paint.valueOf("black"));
            root.getChildren().add(Ygridline);
        }
        return primStage;
    }

    public void /* regler */ restart() {

    }

    public Scene getGMScene() {
        return this.scene;
    }

    public Group getRoot() {
        return this.root;
    }

    public boolean isOk(Point p) {
        if (p.x * 100 < height) {
            return true;
        } else {
            return false;
        }
    }
}
