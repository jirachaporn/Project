import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.*;

// หน้าลืมรหัสผ่าน

public class Forgot extends JFrame implements ActionListener  {

    private Container cp;
    private BufferedImage Image1; 
    private JLabel M , Forgot , User;
    private JButton help , confirm , back;
    private JPanel p;
    private JTextField textUse;
    final private File file = new File("DataUserAndPassword.txt");
    private FileReader fr;
    private BufferedReader br;

    public Forgot(){
        super("Forgot password");
        Initial();
        setComponent();
        Finally() ;
        ImageIcon img = new ImageIcon("Image/Icon.png");  // กำหนด Icon ของโปรแกรม
        this.setIconImage(img.getImage());
    }
    public void Initial(){
        cp = getContentPane();
        cp.setLayout(null);
    }

    public void setComponent(){

        p = new JPanel();
        p.setLayout(null);
        p.setBounds(300, 35, 400, 500); 
        p.setBackground(new Color(255,255,255,200));

        // หัวข้อ Forgot password
        Forgot = new JLabel("Forgot password");
        Forgot.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 28));
        Forgot.setBounds(90, 20, 300, 35);
        Forgot.setForeground(Color.black);

        // Username
        User = new JLabel("Username");
        User.setFont(new Font("Harlow Solid Itailc", Font.PLAIN , 16));
        User.setBounds(50, 70, 150, 35);
        User.setForeground(Color.black);

        // text Username
        textUse = new JTextField("",100);
        textUse.setBounds(50, 105, 300, 25);
        textUse.setBackground(Color.white);

        // ปุ่มยืนยัน
        confirm = new JButton("Confirm");
        confirm.setBounds(140, 140, 100, 30);
        confirm.setForeground(Color.white);
        confirm.setBackground(new Color(100,150,200));
        confirm.setActionCommand("confirm");
        confirm.addActionListener(this);

        // ปุ่มขอความช่วยเหลือ 
        help = new JButton("Help");
        help.setBounds(320, 460, 70, 30);
        help.setForeground(new Color(100,150,200));
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setActionCommand("help");
        help.addActionListener(this);

        // ปุ่มกลับไปหน้า loging
        back = new JButton("Back to login");
        back.setBounds(10, 460, 120, 30);
        back.setForeground(new Color(100,150,200));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setActionCommand("back");
        back.addActionListener(this);

        p.add(Forgot);
        p.add(User);
        p.add(textUse);
        p.add(confirm);
        p.add(help);
        p.add(back);
        cp.add(p);

    }

    // กดเรียกดูรหัสผ่าน
    public void ShowPasssWord(){
        String usernameForgot = textUse.getText();  // สร้าง string usernameForgot มาเก็บข้อความใน textUse
        boolean userFound = false; // ให้ userFound เป็นเท็จ
        
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // แยกข้อมูลในบรรทัดเป็นคีย์และค่า โดยใช้เครื่องหมาย ','
                if (parts.length == 2) {
                    
                    String user = parts[0];    // ให้ ตำแหน่งแรกคือ user
                    String Textpassword = parts[1];   // ให้ตำปหน่งที่ 2 คือ รหัสผ่าน

                    if (usernameForgot.equals(parts[0])) { //ถ้าชื่อ user ตรงกับข้อมูลในตำแหน่งที่ 0
                        System.out.println("User is : "+user); // ปริ้น user ออกมาโชว์
                        System.out.println("Your password is : "+Textpassword); // ปริ้น password ออกมาโชว์
                        JOptionPane.showMessageDialog(null,"Your password is : " + Textpassword); // เด้ง Dialog แสดง password
                        new Login(userpass.GetValueOfDic()); // เรียกหน้า Login
                        dispose();
                        userFound = true; // ให้ userFound เป็นจริง
                        break;
                    }
                }
             } 
             if(!userFound){ // ถ้า userFound เป็นเท็จ ให้แสดงข้อความ User not found :( 
                JOptionPane.showMessageDialog(null,"User not found :( ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    UserPassDefault userpass = new UserPassDefault();

    public void actionPerformed(ActionEvent e) {
        if ("confirm".equals(e.getActionCommand())) {
            String usernameForgot = textUse.getText();  // รับ string ใน textUse 
            if (usernameForgot.isBlank()) {   //  ตรวจสอบว่า usernameForgot ว่างหรือไม่ ถ้าว่างให้ ปริ้น Empty text field.
                System.out.println("Empty text field.");
            } else { // ถ้าไม่ว่างให้เรียกเมทตอด ShowPasssWord();
                    ShowPasssWord();
                }
            }

            // ไปหน้า help
        if("help".equals(e.getActionCommand())) {
            new Help();
            dispose();
            }

            // ไปหน้า Login
        if("back".equals(e.getActionCommand())) {
            new Login(userpass.GetValueOfDic());
            dispose();
            }
    }

    public void Finally(){

         // เพิ่มรูปพื้นหลัง
        try {
            Image1 = ImageIO.read(new File("Image/3.png"));
            M = new JLabel(new ImageIcon(Image1));
            M.setBounds(0, 0, 1000, 600);
            cp.add(M);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
