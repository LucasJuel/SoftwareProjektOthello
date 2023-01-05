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

        hej.standardmove(1, 3, 2);
        hej.standardmove(2, 2, 4);
        hej.standardmove(1, 2, 3);
        // System.out.println(hej.startmoves(1, 4, 3)+ " = false");
        // System.out.println(hej.startmoves(1, 3, 3)+ " = false");

        System.out.println(hej.legalmove(2));

        int f = 3;
        int g = -1;
        f += g;
        System.out.println(f);
    

        
    }
}
