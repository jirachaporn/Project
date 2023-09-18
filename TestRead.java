import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestRead {
    
    public static void main(String[] args) {
    ReadFile();
    }
    public static void ReadFile() {
        File f = new File("DataUserAndPassword.txt");
        FileReader fw = null;
        BufferedReader br = null;
        String line;
        try {
            fw = new FileReader(f); // (f, true) ไม่เขียนทับ
            br = new BufferedReader(fw);

            while ( (line=br.readLine()) != null ) {
                
                System.out.println(line);
            
            
        }fw.close();    
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
