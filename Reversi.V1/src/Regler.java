import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.Point;

public class Regler {
    private int[][] braet;
    private int size; // 8*8 size=7
    private int startplacements;
    private Map<Point, List<Point>> legalMap = new HashMap<>();
    private int color;

    /**
     * Intiation of Regler, sætter bræt til 0
     * 
     * @param size hvis 8*8 size = 7
     */
    public Regler(int size) {
        color = 2;
        startplacements = 0;
        this.size = size;
        this.braet = new int[size + 1][size + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                braet[i][j] = 0;
            }
        }
    }

    /**
     * Kan kun køres de første 4 gange
     * Vi starte øverst i venstre hjørne, som i 4 kvadrant
     * 
     * @param farve      1 for hvid og 2 for sort
     * @param placementx fra 0 til 7 hvis size = 7
     * @param placementy fra 0 til 7 hvis size = 7
     * @return Hvis det kan lade sig gøre så er den true
     */
    public boolean startMoves(int placementx, int placementy) {
        // Den skal være placeret i de 4 midderste brikker, der må ikke allerede være en
        // brik og det skal være et af de 4 første ryk
        if ((placementx == size / 2 || placementx == (size / 2 + 1))
                && (placementy == size / 2 || placementy == (size / 2 + 1))
                && braet[placementx][placementy] == 0
                && startplacements < 4) {

            if (startplacements < 2) {
                color = 2;
            } else if (startplacements >= 2) {
                color = 1;
            }
            braet[placementx][placementy] = color;
            startplacements++;
            return true;
        }
        return false;
    }

    /**
     * Den tjekker om der er og hvilke legalmoves der så er med.
     * Hvis HashMap er Null, så er der ingen legalmoves
     * 
     * @param farve Det er hvis tur det er, 1 for hvid og 2 for sort
     * @return Et HashMap startende med en integer bestående af koordinator fx.
     *         (3,4) = 34, efterfølgende af en Integer List, som består af lignende
     *         koordinator, som repræsentere alle brikker der skal vendes
     */
    public Map<Point, List<Point>> legalMove(int farve) {
        this.legalMap = new HashMap<>();
        int mod;
        if (farve == 1) {
            mod = 2;
        } else {
            mod = 1;
        }

        // Gennemgår hele brættet
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                // Finder alle spillerens brikker
                if (braet[i][j] == farve) {
                    // Kigger i en 3*3 rundt om spilleren brik
                    for (int i2 = i - 1; i2 <= i + 1; i2++) {
                        for (int j2 = j - 1; j2 <= j + 1; j2++) {
                            indsaetMuligvej(i2, j2, mod, i, j);
                        }
                    }
                }
            }
        }

        return legalMap;
    }

    /**
     * Tjekker om det er mugligt for den modstander der er blevet fundet om der er
     * flere og om der er en tom plads så det bliver en mulig position, og bliver
     * tilføjet til hashmappet
     * 
     * @param i2  x koordinatet til modstanderen
     * @param j2  y koordinatet til modstanderen
     * @param mod farve af modstander
     * @param i   x koordinatet til spillerens brik
     * @param j   y koordinatet til spillerens brik
     */
    private void indsaetMuligvej(int i2, int j2, int mod, int i, int j) {
        // Finder modstanderbrikkerne omkring spillerens brik
        if (i2 >= 0 && i2 <= size && j2 >= 0 && j2 <= size && braet[i2][j2] == mod) {
            List<Point> muligvej = new ArrayList<Point>();
            Point placeholder = new Point(i2, j2);
            muligvej.add(placeholder);
            int relx = i2 - i;
            int rely = j2 - j;
            // Følger vejen af brikker der skal vendes, uden nødvendigvis at have en tom
            // brik for enden
            while ((i2 + relx) >= 0 && (i2 + relx) <= size && (j2 + rely) >= 0
                    && (j2 + rely) <= size
                    && braet[i2 + relx][j2 + rely] == mod) {

                placeholder = new Point((i2 + relx), (j2 + rely));
                muligvej.add(placeholder);

                i2 += relx;
                j2 += rely;

            }
            // Finder om der er en tom brik for enden af vejen og tilføjer hvis der er
            if ((i2 + relx) >= 0 && (i2 + relx) <= size && (j2 + rely) >= 0
                    && (j2 + rely) <= size && braet[i2 + relx][j2 + rely] == 0) {
                placeholder = new Point((i2 + relx), (j2 + rely));
                if (legalMap.containsKey(placeholder)) {
                    List<Point> oldway = legalMap.get(placeholder);
                    oldway.addAll(muligvej);
                } else {
                    legalMap.put(placeholder, muligvej);
                }
            }
        }
    }

    /**
     * For at få gameboard
     * 
     * @return retunere spillebrættet som int[][], hvor 1 er hvid og 2 er sort
     */
    public int[][] getGameboard() {
        return braet;
    }

    /**
     * Skal kun modtage lovlige træk. Den vender og ligger også en brik
     * Vi starte øverst i venstre hjørne, som i 4 kvadrant.
     * 
     * @param farve      1 for hvid og 2 for sort
     * @param placementx fra 0 til 7 hvis size = 7
     * @param placementy fra 0 til 7 hvis size = 7
     */
    public void standardMove(int farve, int placementx, int placementy) {
        startplacements++;
        this.braet[placementx][placementy] = farve;
        Point move = new Point(placementx, placementy);
        List<Point> flip = legalMap.get(move);
        for (int i = 0; i < flip.size(); i++) {
            this.braet[(int) flip.get(i).getX()][(int) flip.get(i).getY()] = farve;
        }
    }

    /**
     * Det behøver ikke at være et lovligt træk for at ændre farven
     * Vi starte øverst i venstre hjørne, som i 4 kvadrant.
     * 
     * @param farve      1 for hvid og 2 for sort
     * @param placementx fra 0 til 7 hvis size = 7
     * @param placementy fra 0 til 7 hvis size = 7
     */
    public void standardMoveDev(int farve, int placementx, int placementy) {
        this.braet[placementx][placementy] = farve;
    }

    /**
     * Finder ud af hvem der er vinder,
     * tjekker ikke om der burde være en vinder
     * 
     * @return tal, 0 = uafgjort, 1 hvid vinder og 2 sort vinder
     */
    public int winner() {
        int hvid = 0;
        int sort = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (braet[i][j] == 1) {
                    hvid++;
                } else if (braet[i][j] == 2) {
                    sort++;
                }
            }
        }
        if (hvid > sort) {
            return 1;
        } else if (sort > hvid) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Tjekker om det stadig er en del af de 4 første moves
     * hvor der skal først ligges 2 af en farve og 2 af den anden
     * 
     * @return om det er en af de første 4 træk : boolean
     */
    public boolean start() {
        if (startplacements < 4) {
            // System.out.println(startplacements);
            return true;
        } else {
            // System.out.println(startplacements);
            return false;
        }
    }

    /**
     * Hvilken tur er vi på
     * 
     * @return et tal fra 0 til 4, hvis 4 så er det ikke længere de første 4 moves
     */
    public int getStartPlacement() {
        return startplacements;
    }

    /**
     * Finder det træk hvor der skal flippes flest af modstanderens brikker.
     * 
     * @return det valgte træk i form af et punkt opbjekt.
     */
    public Point oppMove() {
        Entry<Point, List<Point>> possibleMove = null;

        for (Entry<Point, List<Point>> mostFlips : legalMap.entrySet()) {
            if (possibleMove == null || mostFlips.getValue().size() > possibleMove.getValue().size()) {
                possibleMove = mostFlips;
            }
        }

        Point move = possibleMove.getKey();
        return move;
    }

    /**
     * Finder et tomtfelt i de 4 inderste felter
     * 
     * @return det tomme felt på punkt form
     */
    public Point oppStart() {
        for (int i = 3; i <= 4; i++) {
            for (int j = 3; j <= 4; j++) {
                if (braet[i][j] == 0) {
                    Point move = new Point(i, j);
                    return move;
                }
            }
        }
        Point no = new Point(0, 0);
        return no;
    }

}