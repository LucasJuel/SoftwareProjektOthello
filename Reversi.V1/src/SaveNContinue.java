import java.util.HashMap;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    
    public SaveNContinue(int[][] data){
        this.data = data;
    }


    public void writeToFile(){

        String dataToString = Arrays.deepToString(data);
        try {
            FileWriter file = new FileWriter("./save.json");
            file.write(dataToString);
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String readFileAsString(String file) throws Exception{

        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private static String[][] stringToDeep(String str) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                row++;
            }
        }
        row--;
        for (int i = 0;; i++) {
            if (str.charAt(i) == ',') {
                col++;
            }
            if (str.charAt(i) == ']') {
                break;
            }
        }
        col++;
        String[][] out = new String[row][col];
        str = str.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] s1 = str.split(", ");
        int j = -1;
        for (int i = 0; i < s1.length; i++) {
            if (i % col == 0) {
                j++;
            }
            out[j][i % col] = s1[i];
        }
        return out;
        }
    
        public String[][] getSavedBoard(){

            String file = "./save.json";
            String json = null;
            try {
                json = readFileAsString(file);
    
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //String[][] brat = stringToDeep(json);

            // for (int i = 0; i <= 7; i++) {
            //     for (int j = 0; j <= 7; j++) {
    
            //         System.out.print(brat[j][i] + " ");
            //     }
            //     System.out.println();
            // }

            return stringToDeep(json);
        }

}

