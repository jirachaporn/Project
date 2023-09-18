import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
// import java.util.Set;
// import java.util.Dictionary;
// import java.util.Enumeration;
// import java.util.Hashtable;

//หน้า Login

public class Form3 extends JFrame implements ActionListener {

    public Container cp;
    public BufferedImage Image1;
    public JLabel M, t1, t2, t3;
    public JButton login, fogot, create, help;
    public JPanel p;
    public JTextField tu, tp;

    public Dictionary<String, String> key = new Hashtable<>(); // เพ่ิ่ม

    public Form3(Dictionary<String, String> loginOriginal) {

        super("Account");
        key = loginOriginal;
        Initial();
        setComponent();
        Finally();
    }

    public void Initial() {
        cp = getContentPane();
        cp.setLayout(null);
    }

    public void setComponent() {
        p = new JPanel();
        p.setLayout(null);
        p.setBounds(300, 35, 400, 500);
        p.setBackground(new Color(255, 255, 255, 200));

        // Account
        t1 = new JLabel("Account");
        t1.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 30));
        t1.setBounds(140, 20, 170, 35);
        t1.setForeground(Color.black);

        // Username
        t2 = new JLabel("Username");
        t2.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        t2.setBounds(50, 70, 150, 35);
        t2.setForeground(Color.black);

        // text Username
        tu = new JTextField("", 100);
        tu.setBounds(50, 100, 300, 25);
        tu.setBackground(Color.white);

        // Password
        t3 = new JLabel("Password");
        t3.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        t3.setBounds(50, 130, 150, 35);
        t3.setForeground(Color.black);

        // text Password
        tp = new JTextField("", 00);
        tp.setBounds(50, 160, 300, 25);
        tp.setBackground(Color.white);

        // ปุ่ม Login
        login = new JButton("Login");
        login.setBounds(140, 210, 100, 30);
        login.setForeground(Color.white);
        login.setBackground(new Color(100, 150, 200));
        login.setActionCommand("Login");
        login.addActionListener(this);

        // ปุ่ม fogot password
        fogot = new JButton("Forgot password ?");
        fogot.setBounds(120, 245, 140, 30);
        fogot.setForeground(new Color(100, 150, 200));
        fogot.setBorderPainted(false);
        fogot.setContentAreaFilled(false);
        fogot.setActionCommand("Forgot");
        fogot.addActionListener(this);

        // ปุ่ม create account
        create = new JButton("Create account");
        create.setBounds(20, 460, 120, 30);
        create.setForeground(new Color(100, 150, 200));
        create.setBorderPainted(false);
        create.setContentAreaFilled(false);
        create.setActionCommand("create");
        create.addActionListener(this);

        // ปุ่ม help
        help = new JButton("Help");
        help.setBounds(320, 460, 70, 30);
        help.setForeground(new Color(100, 150, 200));
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setActionCommand("help");
        help.addActionListener(this);

        p.add(t1); // Account
        p.add(t2); // Username
        p.add(tu); // text Username
        p.add(t3); // Password
        p.add(tp); // text Password
        p.add(login); // ปุ่ม Login
        // p.add(fogot); // ปุ๋ม ลืมรหัสผ่าน
        p.add(create); // ปุ๋ม สร้างบัญชี
        p.add(help); // ปุ่ม help
        cp.add(p);

        // เพิ่มรูป

        try {
            Image1 = ImageIO.read(new File("Image/3.png"));
            M = new JLabel(new ImageIcon(Image1));
            M.setBounds(0, 0, 1000, 600);
            cp.add(M);
        } catch (IOException e) {

        }

    }

    public void ReadFile() {
        File f = new File("DataUserAndPassword.txt");
        FileReader fw = null;
        BufferedReader br = null;
        String line;

        try {
            fw = new FileReader(f); // (f, true) ไม่เขียนทับ
            br = new BufferedReader(fw);

            while ( (line=br.readLine()) != null ) {
                System.out.println(line);
            
        }   fw.close();    
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // กดปุ่มแล้วไปหน้าอื่น
    public void actionPerformed(ActionEvent e) {

        // ไปหน้าหลัก
        if ("Login".equals(e.getActionCommand())) {
            ReadFile();
            String GetUser = tu.getText(); // เอาค่าจาก TextFiled User ใส่ GetUser
            String Getpass = String.valueOf(tp.getText()); // เอาค่าจาก TextFiled Password แปลงเป็น String จากนั้นเอาใส่ Getpass

            System.out.println("Text User : " + GetUser);
            System.out.println("Text Password : " + Getpass);

            if (((Hashtable<String, String>) key).containsKey(GetUser)) {
                if (key.get(GetUser).equals(Getpass)) {
                    new Form2();
                    dispose();
                    System.out.println("Connect :)");
                } else
                    System.out.println("Wrong password :(");
            } else
                System.out.println("Username not found :(");

        }

        // ไปหน้าลืมรหัสผ่าน
        if ("Forgot".equals(e.getActionCommand())) {
            Form4 Form4 = new Form4();
            Form4.setVisible(true);
            dispose();
        }

        // ไปหน้าสร้างบัญชี
        if ("create".equals(e.getActionCommand())) {
            Form5 Form5 = new Form5();
            Form5.setVisible(true);
            dispose();
        }

        // ไปหน้าช่วยเหลือ
        if ("help".equals(e.getActionCommand())) {
            Form6 Form6 = new Form6();
            Form6.setVisible(true);
            dispose();
        }

    }

    public void Finally() {
        setSize(1000, 600);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

// Enumeration<String> k = d.keys();
// while (k.hasMoreElements()) {
// key = k.nextElement();
// }

// if (GetUser.equals(key)) {
// if (Getpass.equals(pass)) {
// Form2 Form2 = new Form2();
// Form2.setVisible(true);
// dispose();
// } else System.out.println("Disconnect Password :(");
// } else {
// System.out.println("Disconnect User:(");