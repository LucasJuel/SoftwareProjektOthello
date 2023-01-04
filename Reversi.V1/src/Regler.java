import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.braet = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                braet[i][j] = 0;
            }
        }
    }

    // gøres 4 gange for at lave de første brikker
    /**
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

    public Map<Integer, List<Integer>> legalmove(int farve) {
        this.legalMap = new HashMap<>();
        int mod;
        if (farve == 1) {
            mod = 2;
        } else {
            mod = 1;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (braet[i][j] == farve) {
                    for (int i2 = i - 1; i2 < i + 1; i2++) {
                        for (int j2 = j - 1; j2 < j + 1; j2++) {
                            if (i2 >= 0 && i2 <= size && j2 >= 0 && j2 <= size && braet[i2][j2] == mod) {
                                int relx = i2 - i;
                                int rely = j2 - j;
                                List<Integer> muligvej = new ArrayList<Integer>();
                                while (i2 >= 0 && i2 <= size && j2 >= 0 && j2 <= size
                                        && braet[i2 + relx][j2 + rely] == mod) {
                                    int placeholder = i2 * 10 + j2;
                                    muligvej.add(placeholder);
                                    i2 += relx;
                                    j2 += rely;
                                }
                                if (i2 >= 0 && i2 <= size && j2 >= 0 && j2 <= size && braet[i2][j2] == 0) {
                                    int placeholder = i2 * 10 + j2;
                                    legalMap.put(placeholder, muligvej);
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

}
