public class ReglerDriver {
    public static void main(String[] args) {
        Regler hej = new Regler(7);

        // System.out.println(hej.startmoves(4, 6, 3)+" = false");
        // System.out.println(hej.startmoves(1, 6, 3)+" = false");
        // System.out.println(hej.startmoves(0, 4, 3)+" = false");

        System.out.println(hej.startmoves(1, 3, 3)+ " = true");
        System.out.println(hej.startmoves(1, 4, 3)+ " = true");
        System.out.println(hej.startmoves(2, 4, 4)+ " = true");
        System.out.println(hej.startmoves(2, 3, 4)+ " = true");

        hej.standardMove(1, 3, 2);
        hej.standardMove(2, 2, 4);
        hej.standardMove(1, 2, 3);
        hej.standardMove(1, 5, 4);
        hej.standardMove(1, 6, 4);
        // System.out.println(hej.startmoves(1, 4, 3)+ " = false");
        // System.out.println(hej.startmoves(1, 3, 3)+ " = false");
        int[][] brat = hej.getGameboard();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                System.out.print(brat[j][i] + " ");
            }
            System.out.println();
        }
       System.out.println(hej.legalMove(2));

        int f = 3;
        int g = -1;
        f += g;
        System.out.println(f);
    

        
    }
}