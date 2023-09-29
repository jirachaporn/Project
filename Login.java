import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;

//หน้า Login

public class Login extends JFrame implements ActionListener {

    private Container cp;
    private BufferedImage Image1;
    private JLabel M, l1, l2, account, User, pass;
    private JButton login, create, help, fogot;
    private JPanel p;
    private JTextField textUse;
    private JPasswordField textPass;
    private JDialog Load;
    private JCheckBox show;
    final private File file = new File("DataUserAndPassword.txt");
    private FileReader fr;
    private BufferedReader br;
    public Dictionary<String, String> key = new Hashtable<>();

    public Login(Dictionary<String, String> loginOriginal) {
        super("Account");
        key = loginOriginal;
        Initial();
        setComponent();
        Finally();
        ImageIcon img = new ImageIcon("Image/Icon.png"); // กำหนด Icon ของโปรแกรม
        this.setIconImage(img.getImage());
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
        account = new JLabel("Account");
        account.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 30));
        account.setBounds(140, 20, 170, 35);
        account.setForeground(Color.black);

        // Username
        User = new JLabel("Username");
        User.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        User.setBounds(50, 70, 150, 35);
        User.setForeground(Color.black);

        // text Username
        textUse = new JTextField("", 100);
        textUse.setBounds(50, 100, 300, 25);
        textUse.setBackground(Color.white);

        // Password
        pass = new JLabel("Password");
        pass.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        pass.setBounds(50, 130, 150, 35);
        pass.setForeground(Color.black);

        // text Password
        textPass = new JPasswordField("", 100);
        textPass.setBounds(50, 160, 300, 25);
        textPass.setBackground(Color.white);

        // ดูรหัสผ่าน
        show = new JCheckBox("Show Password", false); 
        show.setBounds(50, 190, 120, 20);
        show.setActionCommand("Show"); 
        show.addActionListener(this);

        // ปุ่ม Login
        login = new JButton("Login");
        login.setBounds(140, 220, 100, 30);
        login.setForeground(Color.white);
        login.setBackground(new Color(100, 150, 200));
        login.setActionCommand("Login");
        login.addActionListener(this);

        // ปุ่ม fogot password
        fogot = new JButton("Forgot password ?");
        fogot.setBounds(120, 255, 140, 30);
        fogot.setForeground(new Color(100, 150, 200));
        fogot.setBorderPainted(false);
        fogot.setContentAreaFilled(false);
        fogot.setActionCommand("Forgot");
        fogot.addActionListener(this);

        // ปุ่ม create account
        create = new JButton("Create account");
        create.setBounds(20, 460, 120, 30);
        create.setForeground(new Color(100, 150, 200, 200));
        create.setBorderPainted(false);
        create.setContentAreaFilled(false);
        create.setActionCommand("create");
        create.addActionListener(this);

        // ปุ่ม help
        help = new JButton("Help");
        help.setBounds(320, 460, 70, 30);
        help.setForeground(new Color(100, 150, 200, 200));
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setActionCommand("help");
        help.addActionListener(this);

        p.add(account); // Account
        p.add(User); // Username
        p.add(textUse); // text Username
        p.add(pass); // Password
        p.add(textPass); // text Password
        p.add(login); // ปุ่ม Login
        p.add(fogot); // ปุ๋ม ลืมรหัสผ่าน
        p.add(create); // ปุ๋ม สร้างบัญชี
        p.add(help); // ปุ่ม help
        p.add(show); // ปุ่ม show password
        cp.add(p);

        
    }

    public void checkCredentials() {

        // สร้าง string มาเก็บข้อความใน textUse และ textPass
        String enteredUsername = textUse.getText();
        String enteredPassword = textPass.getText();

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            boolean found = false; 

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // แยกข้อมูลในบรรทัดเป็นคีย์และค่า โดยใช้เครื่องหมาย ','
                if (parts.length == 2) {
                    String username = parts[0]; // เก็บตำแหนงแรกไว้ใน username
                    String password = parts[1]; // เก็บตำแหนงที่สองไว้ใน password

                    // เปรียบเทียบรหัสผ่านและชื่อผู้ใช้ ถ้า ข้อความที่กรอกไว้ตรงกับ username และ
                    if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                        found = true; // found จะเป็นจริง
                        break; // หากพบข้อมูลตรงกัน ออกจากลูป
                    }
                }
            }
            br.close();
            fr.close();

            // ถ้า found เป็นจริงให้ แสดงหน้า loading และไปหน้า ToDoList
            if (found) {
                System.out.println("Loading....");
                Loading();
                Timer timer = new Timer(1500, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Load.dispose(); // ปิดหน้าจอโหลด
                        // ไปหน้า ToDoList
                        new Todo();
                        dispose();
                    }
                });
                timer.setRepeats(false); // ตั้งค่าให้ Timer ไม่ทำงานซ้ำ
                timer.start(); // เริ่มการทำงานของ Timer
            } else if (((Hashtable<String, String>) key).containsKey(enteredUsername)) { //หาค่า User ใน Dictionary ว่าตรงกับที่รับมาหรือไม่
                if ( key.get(enteredUsername).equals(enteredPassword) ) {  // ถ้า kry enteredUsername ตรงกับ enteredPassword 
                    System.out.println("Loading....");
                    Loading(); // เรียก เมทตอด Loading();
                    Timer timer = new Timer(1500, new ActionListener() { // ใช้ Timer ทำให้หน้า Loading ทำงาน 1.5 วิ แล้วปิดหน้า Loading ลงไป
                    public void actionPerformed(ActionEvent e) {
                        Load.dispose(); // ปิดหน้าจอโหลด
                        // ไปหน้า ToDoList
                        new Todo();
                        isVisible();
                        dispose();
                    }
                });
                    timer.setRepeats(false); // ตั้งค่าให้ Timer ไม่ทำงานซ้ำ
                    timer.start(); // เริ่มการทำงานของ Timer
                }

                    // ถ้า enteredUsername ว่างให้แสดงข้อความ Fill in the Username field !!!
                } else  if(enteredUsername.isEmpty()){
                        JOptionPane.showMessageDialog(cp, "Fill in the Username field !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    // ถ้า enteredPassword ว่างให้แสดงข้อความ Fill in the Password field !!!
                } else if(enteredPassword.isEmpty()){
                        JOptionPane.showMessageDialog(cp, "Fill in the Password field !!!", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    // ถ้าไม่ตรงกับเงื่อนไขไหนเลยให้แสดงข้อความ Password or username is incorrect. 
                    } else  
                        JOptionPane.showMessageDialog(cp, "Password or username is incorrect.","Error", JOptionPane.ERROR_MESSAGE);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // กดปุ่มแล้วไปหน้าอื่น            
    public void actionPerformed(ActionEvent e) {

        // ไปหน้า To do
        if ("Login".equals(e.getActionCommand())) {
            checkCredentials();
        }

        // ไปหน้าลืมรหัสผ่าน
        if ("Forgot".equals(e.getActionCommand())) {
            new Forgot();
            dispose();
        }

        // ไปหน้าสร้างบัญชี
        if ("create".equals(e.getActionCommand())) {
            new Create();
            dispose();
        }

        // ไปหน้าช่วยเหลือ
        if ("help".equals(e.getActionCommand())) {
            new Help();
            dispose();
        }

        // CheckBox Show Password
        if ("Show".equals(e.getActionCommand())) { // ถ้ากด show 
            if (show.isSelected()) {  // ตรวจสอบว่าปุ่ม show ถูกเลือกอยู่หรือไม่
                textPass.setEchoChar((char) 0); // ถ้าาปุ่ม show ถูกเลือกอยู่ให้แสดงข้อความ
            } else
                textPass.setEchoChar('•'); // ถ้าาปุ่ม show ไม่ถูกเลือกให้แสดงเป็น ••• 
        }
    }

    public void Loading() {

        // สร้าง JDialog  
        Load = new JDialog();
        Icon Image2 = new ImageIcon(this.getClass().getResource("Image/l1 copy.gif")); // นำ gif มาใส่
        l1 = new JLabel(Image2);
        l1.setBounds(190, 60, 100, 100); 

        // กำหนด Icon ของโปรแกรม
        ImageIcon img = new ImageIcon("Image/Icon.png");
        Load.setIconImage(img.getImage());

        // ใส่ข้อความ Loading . . .
        l2 = new JLabel("Loading . . .");
        l2.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 20));
        l2.setBounds(190, 140, 100, 100);

        Load.add(l1);
        Load.add(l2);
        Load.setLayout(null); 
        Load.setVisible(true); 
        Load.setSize(500, 300); // ตั้งขนาด 500*300
        Load.setLocationRelativeTo(null); // ทำให้ปรากฎกลางจอ
    }

    public void Finally() {

        // เพิ่มรูปพื้นหลัง
        try {
                Image1 = ImageIO.read(new File("Image/3.png"));
                M = new JLabel(new ImageIcon(Image1));
                M.setBounds(0, 0, 1000, 600);
                cp.add(M);
            } catch (IOException e) {
        }

        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
