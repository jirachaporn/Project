import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BoomBoomCheck {
    
    // ถ้ามีชื่อ User นั้นแล้วจะใช้ชื่อนั้นไม่ได้
    public static boolean isCheckNameBoom(String user) {
        final File filename = new File("DataUserAndPassword.txt");  //อ่านจากไฟล์ DataUserAndPassword.txt
        FileReader fr = null;
        BufferedReader br = null;
        String line;
        String originalUser[];

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                originalUser = line.split(",");  // แยกข้อมูลในบรรทัดเป็นคีย์และค่า โดยใช้เครื่องหมาย ','
                if (originalUser[0].equals(user)) {    // ถ้า originalUser ตรงกับ user           
                    br.close(); fr.close();
                    return true; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
