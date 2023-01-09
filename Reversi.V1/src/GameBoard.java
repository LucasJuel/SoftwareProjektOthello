import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Point;

public class GameBoard {

    private int size;
    private Group root;
    public Scene scene;
    public Button genstart;
    int height;
    private Text turText;
    private Text vinderText;

    public GameBoard(int size) {
        this.size = size;
        this.turText = new Text();
        this.vinderText = new Text();
        this.genstart= new Button();
        root = new Group();
        scene = new Scene(root);


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
            Line Xgridline = new Line(i, 0, i, height);
            Xgridline.setFill(Paint.valueOf("black"));
            root.getChildren().add(Xgridline);

            Line Ygridline = new Line(0, i, height, i);
            Ygridline.setFill(Paint.valueOf("black"));
            root.getChildren().add(Ygridline);
        }
        setText(turText, 825, 200, 30);
        setText(vinderText, 825, 400, 30);
        setButton(genstart, 825, 600, 150, 75, 25, "66, 135, 245", "Spil igen");
        
        
        return primStage;
    }

    public void setTurText(int farve) {
        if (farve == 1) {
            turText.setFill(Color.WHITE);
            turText.setText("Hvid");
        } else {
            turText.setFill(Color.BLACK);
            turText.setText("Sort");
        }
    }

    public void setVinderText(int farve) {
        turText.setText(null);
        if (farve == 1) {
            vinderText.setFill(Color.WHITE);
            vinderText.setText("HVID\nVINDER!!");
        } else if (farve == 2) {
            vinderText.setFill(Color.BLACK);
            vinderText.setText("SORT\nVINDER!!");
        } else {
            vinderText.setFill(Color.RED);
            vinderText.setText("UAFGJORT");

        }
    }

    public void setText(Text text, int x, int y, int fSize) {
        root.getChildren().add(text);
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFont(new Font(fSize));
    }

    public void setButton(Button but, int x, int y, int xSize, int ySize, int fSize, String rgbString, String text) {
        root.getChildren().add(but);
        but.setPrefSize(xSize,ySize);
        but.setLayoutX(x);
        but.setLayoutY(y);
        but.setStyle("-fx-background-color: rgb("+rgbString+"); -fx-font-size: "+fSize+"px; -fx-cursor: hand");
        but.setTextFill(Color.WHITE);
        but.setText(text);
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

    public Boolean knapIsPressed (Point p) {
        if (genstart.getLayoutX() < p.x && p.x < genstart.getLayoutX()+150 &&genstart.getLayoutY() < p.y && p.y < genstart.getLayoutY()+75) {
            return true;
        } else {
            return false;
        }
    }

    
}
