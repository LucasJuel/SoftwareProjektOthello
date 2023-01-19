import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Point;

public class GameBoard {

    private int sizePiece;
    private int size;
    private Group root;
    private Scene scene;
    private Button genstart;
    private Button save;
    private Button load;
    private Button back;

    int height;
    private Text turText;
    private Text vinderText;

    /**
     * initialize GameBoard objektet.
     * 
     * @param size Gameboards størrelse
     */
    public GameBoard(int size) {
        this.size = size;
        this.turText = new Text();
        this.vinderText = new Text();
        this.genstart = new Button();
        this.save = new Button();
        this.load = new Button();
        this.back = new Button();
        root = new Group();
        scene = new Scene(root);
        sizePiece = (int) 800/size;
    }

    /**
     * Laver et spillebræt som er grønt med alle firkanter
     * 
     * @param primStage Stage der bliver brugt
     * @return Stagen der er i gang med at blive brugt
     */
    public Stage draw(Stage primStage) {
        // sætter en variable height fra antal brikker til antal pixels.
        height = 800;
        primStage.setTitle("Reversi");

        // Sætter højde og bredde og gør plads til en menu til højre for spillet.(40 og
        // 17 er tilpassede værdier da der var nogle problemer med vores stage.)
        primStage.setHeight(height + 40);
        primStage.setWidth(height + 17 + 200);

        // tilføjer scenen og gør man ikke kan resize det.
        primStage.setScene(scene);
        primStage.setResizable(false);
        // Gør boardet grønt, og tilføjer et icon til vinduet.
        scene.setFill(Options.getBaggrundsColor());
        primStage.getIcons().add(new Image("file:img/IconReversi.png"));

        // Et for loop til at sætte stregener på boardet.
        for (int i = 0; i < height + sizePiece; i += sizePiece) {
            // Horisontale streger.
            Line xGridline = new Line(i, 0, i, height);
            xGridline.setStroke(Options.getLineColor());
            root.getChildren().add(xGridline);
            // Vertikale streger.
            Line yGridline = new Line(0, i, height, i);
            yGridline.setStroke(Options.getLineColor());
            root.getChildren().add(yGridline);
        }
        // Text og knap til at kunne spille igen.
        setText(turText, 825, 200, 30);
        setText(vinderText, 825, 300, 30);
        setButton(genstart, 825, 600, 150, 75, 25, "66, 135, 245", "Spil igen");
        setButton(save, 825, 500, 150, 75, 25, "66, 135, 245", "Gem spillet");
        setButton(load, 825, 400, 150, 75, 25, "66, 135, 245", "load spillet");
        setButton(back, 920, 10, 75, 25, 15, "66, 135, 245", "Tilbage");

        // Og så returnere vi vores stage.
        return primStage;
    }

    /**
     * Ændrer tekst og farve for hvem der har tur.
     * 
     * @param farve
     */
    public void setTurText(int farve) {
        if (farve == 1) {
            turText.setFill(Options.getPlayer1Color());
            turText.setText(Options.getPlayer1String());
        } else if (farve == 2) {
            turText.setFill(Options.getPlayer2Color());
            turText.setText(Options.getPlayer2String());
        } else if (farve == 3) {
            turText.setFill(Options.getPlayer1Color());
            turText.setText("Computer");
        }
    }

    /**
     * Sætter den passende tekst når spillet er afgjort.
     * 
     * @param farve 1 = hvid, 2 = sort, 3 = hvid pass, 4 = hvid pass, 5 = resetter
     *              text, andet = uafgjort
     * 
     */
    public void setVinderText(int farve) {
        turText.setText(null);
        
        if (farve == 1) {
            vinderText.setFill(Options.getPlayer1Color());
            vinderText.setText(""+Options.getPlayer1String()+"\nVINDER!!");
        } else if (farve == 2) {
            vinderText.setFill(Options.getPlayer2Color());
            vinderText.setText(""+Options.getPlayer2String()+"\nVINDER!!");
        } else if (farve == 3) {
            vinderText.setFill(Options.getPlayer1Color());
            vinderText.setText(""+Options.getPlayer1String()+" \nmelder \npass");
        } else if (farve == 4) {
            vinderText.setFill(Options.getPlayer2Color());
            vinderText.setText(""+Options.getPlayer2String()+" \nmelder \npass");
        } else if (farve == 5) {
            vinderText.setFill(null);
            vinderText.setText("");
        } else if (farve == 6) {
            vinderText.setFill(Options.getPlayer1Color());
            vinderText.setText("Computer \nVINDER!!");
        } else if (farve == 7) {
            vinderText.setFill(Options.getPlayer1Color());
            vinderText.setText("Computer \nmelder \npass");
        } else {
            vinderText.setFill(Color.RED);
            vinderText.setText("UAFGJORT");
        }
    }

    

    /**
     * Sætter en tekst baseret på givne parametre.
     * @param text  - hvad der skal stå.
     * @param x     - placering på x-aksen
     * @param y     - placering på y-aksen
     * @param fSize - font size.
     */
    public void setText(Text text, int x, int y, int fSize) {
        root.getChildren().add(text);
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFont(new Font(fSize));
    }

    /**
     * Laver en knap baseret på parametre.
     * 
     * @param but       - knap objekt.
     * @param x         - placering på x-aksen.
     * @param y         - placering på y-aksen.
     * @param xSize     - bredde af knap.
     * @param ySize     - højde af knap.
     * @param fSize     - font størrelse.
     * @param rgbString - tilføj farve i RGB.
     * @param text      - hvad der skal stå. 
     */
    public void setButton(Button but, int x, int y, int xSize, int ySize, int fSize, String rgbString, String text) {
        root.getChildren().add(but);
        but.setPrefSize(xSize, ySize);
        but.setLayoutX(x);
        but.setLayoutY(y);
        but.setStyle("-fx-background-color: rgb(" + rgbString + "); -fx-font-size: " + fSize + "px; -fx-cursor: hand");
        but.setTextFill(Color.WHITE);
        but.setText(text);
    }

    /**
     * Returnere et scene fra et GameBoard.
     * 
     * @return Scene
     */
    public Scene getGMScene() {
        return this.scene;
    }

    /**
     * Returnere root fra et stage.
     * 
     * @return root
     */
    public Group getRoot() {
        return this.root;
    }

    /**
     * Tjekker om et klik er inde for brættet(ex. menuen).
     * 
     * @param p - Point
     * @return boolean baseret på om det er inde eller ude.
     */
    public boolean isOk(Point p) {
        if (p.x * sizePiece < height) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tjekker om en knap er klikket.
     * 
     * @param p - Point
     * @return Boolean om knappen er klikket.
     */
    public Boolean genstartIsPressed(Point p) {
        if (genstart.getLayoutX() < p.x && p.x < genstart.getLayoutX() + 150 && genstart.getLayoutY() < p.y
                && p.y < genstart.getLayoutY() + 75) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean saveIsPressed(Point p) {
        if (save.getLayoutX() < p.x && p.x < save.getLayoutX() + 150 && save.getLayoutY() < p.y
                && p.y < save.getLayoutY() + 75) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean loadIsPressed(Point p) {
        if (load.getLayoutX() < p.x && p.x < load.getLayoutX() + 150 && load.getLayoutY() < p.y
                && p.y < load.getLayoutY() + 75) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean backIsPressed(Point p) {
        if (back.getLayoutX() < p.x && p.x < back.getLayoutX() + 150 && back.getLayoutY() < p.y
                && p.y < back.getLayoutY() + 75) {
            return true;
        } else {
            return false;
        }
    }
}
