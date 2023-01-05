public class Dinmor {
    public static void main(String[] args) {
        int i2 = 3;
        int j2 = 3;
        int relx = 0;
        int rely = -1;
        int size = 7;

        while ((i2 + relx) >= 0 && (i2 + relx) <= size && (j2 + rely) >= 0
                                        && (j2 + rely) <= size
                                        /*&& braet[i2 + relx][j2 + rely] == mod*/) {
                                            System.out.println("j2 1 = " + j2);
                                    /*placeholder = (i2 + relx) * 10 + j2 + rely;
                                    muligvej.add(placeholder);*/
                                    System.out.println("hej");
                                    System.out.println("x = " + relx);
                                    System.out.println("y = " + rely);
                                    System.out.println("j2 = " + j2);
                                    
                                    i2 += relx;
                                    j2 += rely;
                                    System.out.println("j2 2 = " + j2);
                                    

                                }
    }
}
