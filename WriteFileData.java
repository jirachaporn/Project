import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteFileData implements WritefileInterface{

    public boolean WriteFileBoom(String user, String pass, String conpass) {
        final File f = new File("DataUserAndPassword.txt"); // เขียนไฟล์ DataUserAndPassword.txt
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {

            fw = new FileWriter(f, true); // (f, true) ไม่ให้เขียนทับ
            bw = new BufferedWriter(fw); // เขียนไฟล์
            bw.write( user + "," + pass ); // เขียน ไฟล์โดยให้ user , pass
            bw.newLine(); // เขียนข้อมูลในบรรทัดใหม่
            bw.close(); fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }   
}

