import java.util.HashMap;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveNContinue {
    private int[][] data;
    JSONArray moves = new JSONArray();
    JSONObject brik = new JSONObject();
    JSONObject moveArr = new JSONObject();
    HashMap<String, int[]> intArrToHM = new HashMap<String, int[]>();
    int colorAtTurn;

    public SaveNContinue(int[][] data, int colorAtTurn) {
        this.data = data;
        this.colorAtTurn = colorAtTurn;
    }
    /**
     * Funktion der skriver boardet og tur til en save.json og saveColor.json.
     */
    public void writeToFile() {

        //Skriver et 2D array til en string
        String dataToString = Arrays.deepToString(data);
        //nyt Json objekt og sætter farven ind i det.
        JSONObject colorJson = new JSONObject();
        colorJson.put("Color", colorAtTurn);

        //Fileskriver funktioner kræver exception-håndtering
        try {
            //Skriver vores board til fil og derefter lukker strømmen.
            BufferedWriter file = new BufferedWriter(new FileWriter("./save.json"));
            file.write(dataToString);
            file.close();

            //Skriver vores farve til fil og derefter lukker strømmen.
            BufferedWriter fileColor = new BufferedWriter(new FileWriter("./saveColor.json"));
            fileColor.write(colorJson.toJSONString());
            fileColor.close();
        } catch (IOException e) {
            System.err.println("Der skete en fejl i skrivning til fil... :" + e);
        }

    }

    /**
     * Henter farven fra saveColor.json.
     * @return ColorAtTurn - farve fra saveColor.json
     */
    public int getColor() {
        try {
            //Bruger RegEx til at kun få tal.
            String numberOnly = readFileAsString("./saveColor.json").replaceAll("[^0-9]", "");
            colorAtTurn = Integer.parseInt(numberOnly);
        } catch (Exception e) {
            System.err.println("Der skete en fejl i læsning af fejl... :" + e);
        }
        return colorAtTurn;
    }

    /**
     * Læser en fil og laver den til en string.
     * [Kun for små mængder data]
     * @param file fil-navnet.
     * @return String fra fil.
     * @throws Exception
     */
    public static String readFileAsString(String file) throws Exception {
        //readAllBytes returnere et byte array, som vi laver til en ny string.
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    /**
     * Konvertere vores string til et nyt 2D array.
     * @param boardStr - Streng fra deepToString funktion.
     * @return et String array med tal som små Strings("Chars")
     */
    private static String[][] convertStringToDeep(String boardStr) {
        int row = 0, col = 0;
        //Baseret på strukturen, laver vi en ny kolonne for hver "[", men siden der altid er 2 til at starte med skal vi fjerne én til sidst.
        for (int i = 0; i < boardStr.length(); i++) {
            if (boardStr.charAt(i) == '[') {
                col++;
            }
        }
        col--;

        //Vi tjekker for kommaer og "]" og laver nye rækker ved dem.
        for (int i = 0; i < boardStr.length(); i++) {
            if (boardStr.charAt(i) == ',') {
                row++;
            }
            if (boardStr.charAt(i) == ']') {
                row++;
                break;
            }
        }

        //Laver string arrays, til at store hvad der står i kolonner og rows.
        String[][] charArr = new String[col][row];
        // fjerner "[, ]" skal bruges til at indsætte værdier
        boardStr = boardStr.replaceAll("\\[", "").replaceAll("\\]", "");
        // et placeholder array.
        String[] placeholder = boardStr.split(", ");

        //indsætter værdier.
        int x = -1;
        for (int i = 0; i < placeholder.length; i++) {
            //Bruger modulus til at se hvornår vi skifter kolonner.
            if (i % row == 0) {
                x++;
            }
            charArr[x][i % row] = placeholder[i];
        }
        return charArr;
    }

    /**
     * Hent det gemte board. Som 2D array.
     * @return response fra convertStringToDeep.
     */
    public String[][] getSavedBoard() {

        String file = "./save.json";
        String json = null;
        try {
            json = readFileAsString(file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertStringToDeep(json);
    }

}
