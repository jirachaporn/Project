import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    final private File f = new File("DataUserAndPassword.txt");
    private FileWriter fw = null;
    private BufferedWriter bw = null;

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

        // วิธีตูน

        try {
            Image1 = ImageIO.read(new File("Image/3.png"));
            M = new JLabel(new ImageIcon(Image1));
            M.setBounds(0, 0, 1000, 600);
            cp.add(M);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void WriteFile() {
        try {
            fw = new FileWriter(f,true); // (f, true) ไม่เขียนทับ
            bw = new BufferedWriter(fw);
            bw.write(FieldUser.getText() + "," + FieldPass.getText() + "\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public Dictionary<String, String> readDataFromFile() {
    //     Dictionary<String, String> data = new Hashtable<>();
    //     File file = new File("DataUserAndPassword.txt");

    //     try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             String[] parts = line.split(",");
    //             if (parts.length == 2) {
    //                 data.put(parts[0], parts[1]);
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     return data;
    // }

                WriteFileData write = new WriteFileData();
            UserPassDefault userpass = new UserPassDefault();
    public void actionPerformed(ActionEvent e) {
        if ("create".equals(e.getActionCommand())) {
            String user = FieldUser.getText();
            String pass = FieldPass.getText();
            String conpass = FieldConPass.getText();
            WriteFileData write = new WriteFileData();
            UserPassDefault userpass = new UserPassDefault();
            if ( !user.isEmpty() && !user.contains(" ") ) {
                if ( !pass.isEmpty() && !pass.contains(" ") ) {
                    if ( !conpass.isEmpty() && !conpass.contains(" ") ) {
                        if ( pass.equals(conpass) ) {
                            if (!BoomBoomCheck.isCheckNameBoom(user)) {
                                write.WriteFileBoom(user, pass, conpass);
                                newFrameComplete();
                                new Login(userpass.GetValueOfDic());
                                dispose();
                            } else System.out.println(">>> This username is already taken. Please create a new one. <<<");                          
                        } else System.out.println(">>>>> Passwords do not match. Please enter again. <<<<<");
                    } else newFrameConfirmPass();
                } else newFrameWrongPass();
            } else newFrameWrong();
        }

        if("help".equals(e.getActionCommand())) {
            Help H = new Help();
            H.setVisible(true);
            dispose();
        }
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
        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
