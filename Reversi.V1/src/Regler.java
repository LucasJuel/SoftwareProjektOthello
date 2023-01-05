import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.awt.Point;

public class Regler {
    private int[][] braet;
    private int size; // 8*8 size=7
    private int startplacements = 0;
    private Map<Integer, List<Integer>> legalMap = new HashMap<>();

    /**
     * Intiation of Regler
     * 
     * @param size hvis 8*8 size = 7
     */
    public Regler(int size) {
        this.size = size;
        this.braet = new int[size + 1][size + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                braet[i][j] = 0;
            }
        }
    }

    // gøres 4 gange for at lave de første brikker
    /**
     * Må kun køres de første 4 gange
     * Vi starte øverst i venstre hjørne, som i 4 kvadrant
     * 
     * @param farve      1 for hvid og 2 for sort
     * @param placementx fra 0 til 7 hvis size = 7
     * @param placementy fra 0 til 7 hvis size = 7
     * @return Hvis det kan lade sig gøre så er den true
     */
    public boolean startmoves(int farve, int placementx, int placementy) {
        if ((placementx == size / 2 || placementx == (size / 2 + 1))
                && (placementy == size / 2 || placementy == (size / 2 + 1))
                && braet[placementx][placementy] == 0
                && startplacements < 4) {

            braet[placementx][placementy] = farve;
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
    public Map<Integer, List<Integer>> legalmove(int farve) {
        this.legalMap = new HashMap<>();
        int mod;
        if (farve == 1) {
            mod = 2;
        } else {
            mod = 1;
        }

        // Gennemgår hele brættet
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Finder alle spillerens brikker
                if (braet[i][j] == farve) {
                    // Kigger i en 3*3 rundt om spilleren brik
                    for (int i2 = i - 1; i2 <= i + 1; i2++) {
                        for (int j2 = j - 1; j2 <= j + 1; j2++) {
                            // Finder modstanderbrikkerne omkring spillerens brik
                            if (i2 >= 0 && i2 <= size && j2 >= 0 && j2 <= size && braet[i2][j2] == mod) {
                                List<Integer> muligvej = new ArrayList<Integer>();
                                int placeholder = i2 * 10 + j2;
                                muligvej.add(placeholder);
                                int relx = i2 - i;
                                int rely = j2 - j;
                                int j3 = j2;
                                int i3 = i2;
                                // Følger vejen af brikker der skal vendes, uden nødvendigvis at have en tom
                                // brik for enden
                                while ((i3 + relx) >= 0 && (i3 + relx) <= size && (j3 + rely) >= 0
                                        && (j3 + rely) <= size
                                        && braet[i3 + relx][j3 + rely] == mod) {
                                            
                                    placeholder = (i3 + relx) * 10 + j3 + rely;
                                    muligvej.add(placeholder);

                                    i3 += relx;
                                    j3 += rely;

                                }
                                // Finder om der er en tom brik for enden af vejen og tilføjer hvis der er
                                if ((i3 + relx) >= 0 && (i3 + relx) <= size && (j3 + rely) >= 0
                                && (j3 + rely) <= size && braet[i3 + relx][j3 + rely] == 0) {
                                    placeholder = (i3 + relx) * 10 + j3 + rely;
                                    if (legalMap.containsKey(placeholder)) {
                                        List<Integer> oldway = legalMap.get(placeholder);
                                        oldway.addAll(muligvej);
                                    } else {
                                        legalMap.put(placeholder, muligvej);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        return legalMap;
    }

    public int[][] gameboard() {
        return braet;
    }

    /**
     * Skal kun modtage lovlige træk.
     * Vi starte øverst i venstre hjørne, som i 4 kvadrant.
     * 
     * @param farve      1 for hvid og 2 for sort
     * @param placementx fra 0 til 7 hvis size = 7
     * @param placementy fra 0 til 7 hvis size = 7
     */
    public void standardmove (int farve, int placementx, int placementy){
        this.braet[placementx][placementy] = farve;
        List<Integer> flip = legalMap.get(placementx);
        for (int i = 0; i < flip.size(); i++) {
            this.braet[flip.get(i)][flip.get(i)] = farve;
        }
    }




    public int winner (){
        int hvid = 0;
        int sort = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(braet[i][j] == 1){
                    hvid++;
                } else if(braet[i][j] == 2){
                    sort++;
                }
            }
        }
        if(hvid>sort){
            return 1;
        }
        return 2;
    }
}