public class Regler {
    private int[][] braet;
    private int size; //8*8 size=7
    private int startplacements = 0;

    public Regler (int size) { 
        this.size = size;
        this.braet = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                braet[i][j] = 0;
            }
        }
    }

    //gøres 4 gange for at lave de første brikker
    public boolean startmoves (int farve, int placementx, int placementy){
        if ((placementx == size/2 || placementx == (size/2+1)) 
            && (placementy == size/2 || placementy == (size/2+1))
            && braet[placementx][placementy] == 0 
            && startplacements < 4
            ) {

            braet[placementx][placementy] = farve;
            return true;
        }
        return false;
    }




}
