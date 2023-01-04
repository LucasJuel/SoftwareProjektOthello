import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Regler {
    private int[][] braet;
    private int size; // 8*8 size=7
    private int startplacements = 0;

    /**
     * Intiation of Regler 
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
     * @param farve 1 for hvid og 2 for sort
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

    public Map<Integer, List<Integer>> Legalmove() {
        Map<Integer, List<Integer>> legalMap = new HashMap<>();
        

        return legalMap;
    }



    public int[][] gameboard () {
        return braet;
    }

}
