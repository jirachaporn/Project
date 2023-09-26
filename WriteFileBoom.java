import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteFileData {

    public boolean WriteFileBoom(String user, String pass, String conpass) {
        final File f = new File("DataUserAndPassword.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(f, true); // (f, true) ไม่เขียนทับ
            bw = new BufferedWriter(fw);
            if (!user.isEmpty() && !user.contains(" ")) {
                if (!pass.isEmpty() && !pass.contains(" ")) {
                    if (!conpass.isEmpty() && !conpass.contains(" ")) {
                        if (pass.equals(conpass)) {
                            bw.write(user + "," + pass + "\n");
                        }
                    }
                }
            }
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
// เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง เหงาจัง
