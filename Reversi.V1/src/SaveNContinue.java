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

    public void writeToFile() {

        String dataToString = Arrays.deepToString(data);
        JSONObject colorJson = new JSONObject();
        colorJson.put("Color", colorAtTurn);
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("./save.json"));
            file.write(dataToString);
            file.close();

            BufferedWriter fileColor = new BufferedWriter(new FileWriter("./saveColor.json"));
            fileColor.write(colorJson.toJSONString());
            fileColor.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getColor() {
        try {
            
            String numberOnly= readFileAsString("./saveColor.json").replaceAll("[^0-9]", "");
            colorAtTurn = Integer.parseInt(numberOnly);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(colorAtTurn);
        return colorAtTurn;
    }

    public static String readFileAsString(String file) throws Exception {

        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private static String[][] convertStringToDeep(String boardStr) {
        int row = 0, col = 0;
        for (int i = 0; i < boardStr.length(); i++) {
            if (boardStr.charAt(i) == '[') {
                col++;
            }
        }
        col--;

        for (int i = 0; i < boardStr.length(); i++) {
            if (boardStr.charAt(i) == ',') {
                row++;
            }
            if (boardStr.charAt(i) == ']') {
                row++;
                break;
            }
        }

        String[][] charArr = new String[col][row];
        boardStr = boardStr.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] placeholder = boardStr.split(", ");

        int x = -1;
        for (int i = 0; i < placeholder.length; i++) {
            if (i % row == 0) {
                x++;
            }
            charArr[x][i % row] = placeholder[i];
        }
        return charArr;
    }

    public String[][] getSavedBoard() {

        String file = "./save.json";
        String json = null;
        try {
            json = readFileAsString(file);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // String[][] brat = stringToDeep(json);

        // for (int i = 0; i <= 7; i++) {
        // for (int j = 0; j <= 7; j++) {

        // System.out.print(brat[j][i] + " ");
        // }
        // System.out.println();
        // }

        return convertStringToDeep(json);
    }

}
