public class ReglerDriver {
    public static void main(String[] args) {
        Regler hej = new Regler(7);

        System.out.println(hej.startmoves(4, 6, 3)+" = false");
        System.out.println(hej.startmoves(1, 6, 3)+" = false");
        System.out.println(hej.startmoves(0, 4, 3)+" = false");

        System.out.println(hej.startmoves(1, 4, 3));
        System.out.println(hej.startmoves(1, 3, 3));
        
    }
}
