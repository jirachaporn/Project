import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// หน้าสร้างบัญชี

public class Create extends JFrame implements ActionListener {

    private Container cp;
    private BufferedImage Image1; 
    private JLabel M , TextCreate , TextUse , TextPass , TextConPass ;
    private JPanel p;
    private JTextField FieldUser , FieldPass , FieldConPass;
    private JButton b , help , back;

    public Create(){
        super("Create account");
        Initial();
        setComponent();
        Finally() ;
        ImageIcon img = new ImageIcon("Image/Icon.png");
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

        // หัวข้อ Create a To Do Account
        TextCreate = new JLabel("Create a To Do Account");
        TextCreate.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 26));
        TextCreate.setBounds(60, 20, 300, 35);
        TextCreate.setForeground(Color.black);

        // Username
        TextUse = new JLabel("Username");
        TextUse.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        TextUse.setBounds(50, 70, 150, 35);
        TextUse.setForeground(Color.black);

        // text Username
        FieldUser = new JTextField("", 00);
        FieldUser.setBounds(50, 100, 300, 25);
        FieldUser.setBackground(Color.white);

        // Password
        TextPass = new JLabel("Password");
        TextPass.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        TextPass.setBounds(50, 130, 150, 35);
        TextPass.setForeground(Color.black);

        // text Password
        FieldPass = new JTextField("", 00);
        FieldPass.setBounds(50, 160, 300, 25);
        FieldPass.setBackground(Color.white);

        // Confirm
        TextConPass = new JLabel("Confirm Password");
        TextConPass.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        TextConPass.setBounds(50, 190, 150, 35);
        TextConPass.setForeground(Color.black);

        // text Confirm
        FieldConPass = new JTextField("", 00);
        FieldConPass.setBounds(50, 220, 300, 25);
        FieldConPass.setBackground(Color.white);

        // ปุ่ม Create
        b = new JButton("Create");
        b.setBounds(140, 260, 100, 30);
        b.setForeground(Color.white);
        b.setBackground(new Color(100, 150, 200));
        b.setActionCommand("create");
        b.addActionListener(this);

        // // ปุ่มขอความช่วยเหลือ 
        help = new JButton("Help");
        help.setBounds(320, 460, 70, 30);
        help.setForeground(new Color(100,150,200));
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setActionCommand("help");
        help.addActionListener(this);

        // ปุ่ม Back to login
        back = new JButton("Back to login");
        back.setBounds(10, 460, 120, 30);
        back.setForeground(new Color(100,150,200));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setActionCommand("back");
        back.addActionListener(this);

        p.add(TextCreate);
        p.add(TextUse);
        p.add(FieldUser);
        p.add(TextPass);
        p.add(FieldPass);
        p.add(TextConPass);
        p.add(FieldConPass);
        p.add(b);
        p.add(help);
        p.add(back);
        
        cp.add(p);
    }

            WriteFileData write = new WriteFileData();
            UserPassDefault userpass = new UserPassDefault();

    public void actionPerformed(ActionEvent e) {
        if ("create".equals(e.getActionCommand())) {
            String user = FieldUser.getText(); // สร้าง string user มาเก็บข้อความใน FieldUser (Username)
            String pass = FieldPass.getText(); // สร้าง string pass มาเก็บข้อความใน FieldPass  (Password)
            String conpass = FieldConPass.getText();   // สร้าง string conpass มาเก็บข้อความใน FieldConPass (Confirm Password) 
            if ( !user.isEmpty() && !user.contains(" ") ) { // ถ้า FieldUser ไม่ว่าง
                if ( !pass.isEmpty() && !pass.contains(" ") ) { // ถ้า FieldPass ไม่ว่าง
                    if ( !conpass.isEmpty() && !conpass.contains(" ") ) { // ถ้า FieldConPass ไม่ว่าง
                        if ( pass.equals(conpass) ) { // ถ้า FieldPass ตรงกับ FieldConPass
                            if (!BoomBoomCheck.isCheckNameBoom(user)) { //  ให้เรียกใช้ เมทตอด isCheckNameBoom เพื่อเช็คว่ามี user นั้น หรือยัง
                                write.WriteFileBoom(user, pass, conpass); //  ส่งข้อมูลไปให้ WriteFileBoom เพื่อสร้างบัญชี 
                                newFrameComplete(); // แสดงข้อความ Create New Account Successfully ✓
                                new Login(userpass.GetValueOfDic()); // เปิดหน้า Login
                                dispose();
                            } else System.out.println(">>> This username is already taken. Please create a new one. <<<");  //  ถ้า user ซ้ำ ให่แสดงข้อความ This username is already taken. Please create a new one.                   
                        } else System.out.println(">>>>> Passwords do not match. Please enter again. <<<<<"); // ถ้า FieldPass ไม่ตรงกับ FieldConPass ให้แสดงข้อความ Passwords do not match. Please enter again.
                    } else newFrameConfirmPass(); // ถ้า FieldConPass ว่างให้แสดงข้อความ Fill in the Confirm password field.
                } else newFrameWrongPass(); // ถ้า FieldPass ว่างให้แสดงข้อความ Fill in the Password field.
            } else newFrameWrong(); // ถ้า FieldUser ว่างให้แสดงข้อความ FieldPass ว่าง Fill in the Username field.
        }

        // ไปหน้า help
        if("help".equals(e.getActionCommand())) {
            Help H = new Help();
            H.setVisible(true);
            dispose();
        }

        // ไปหน้า Login
        if ("back".equals(e.getActionCommand())) {
            new Login(userpass.GetValueOfDic());
            dispose();
            }
    }

    public void newFrameComplete() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Create New Account Successfully ✓");
    }

    public void newFrameWrong() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Fill in the Username field.!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void newFrameWrongPass() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Fill in the Password field.!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void newFrameConfirmPass() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Fill in the Confirm password field.!!!", "Error", JOptionPane.ERROR_MESSAGE);
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
