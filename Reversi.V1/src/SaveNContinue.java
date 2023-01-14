import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import java.awt.Point;

public class SaveNContinue {    
    private int[][] data;
    static  JSONObject save = new JSONObject();

    
    public SaveNContinue(int[][] data){
        data = this.data;
    }


    public void writeToFile(){
        System.out.println(data);
    }

}
