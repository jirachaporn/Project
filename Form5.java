import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// หน้าสร้างบัญชี

public class Form5 extends JFrame implements ActionListener, NewFrame {

    public Container cp;
    public BufferedImage Image1;
    public JLabel M, TextCreate, TextEmail, TextUse, TextPass, TextConPass;
    public JPanel p;
    public JTextField FieldUser, FieldPass, FieldConPass;
    public JButton b;

    public Form5() {
        super("Create New Account");
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

        TextCreate = new JLabel("Create a To Do Account");
        TextCreate.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 26));
        TextCreate.setBounds(60, 20, 300, 35);
        TextCreate.setForeground(Color.black);

        // Username
        TextUse = new JLabel("Username");
        TextUse.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        TextUse.setBounds(50, 130, 150, 35);
        TextUse.setForeground(Color.black);

        // text Username
        FieldUser = new JTextField("", 00);
        FieldUser.setBounds(50, 160, 300, 25);
        FieldUser.setBackground(Color.white);

        // Password
        TextPass = new JLabel("Password");
        TextPass.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        TextPass.setBounds(50, 190, 150, 35);
        TextPass.setForeground(Color.black);

        // text Password
        FieldPass = new JTextField("", 00);
        FieldPass.setBounds(50, 220, 300, 25);
        FieldPass.setBackground(Color.white);

        // Confirm
        TextConPass = new JLabel("Confirm Password");
        TextConPass.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        TextConPass.setBounds(50, 250, 150, 35);
        TextConPass.setForeground(Color.black);

        // text Confirm
        FieldConPass = new JTextField("", 00);
        FieldConPass.setBounds(50, 280, 300, 25);
        FieldConPass.setBackground(Color.white);

        b = new JButton("Create");
        b.setBounds(140, 320, 100, 30);
        b.setForeground(Color.white);
        b.setBackground(new Color(100, 150, 200));
        b.setActionCommand("create");
        b.addActionListener(this);

        p.add(TextCreate);
        p.add(TextUse);
        p.add(FieldUser);
        p.add(TextPass);
        p.add(FieldPass);
        p.add(TextConPass);
        p.add(FieldConPass);
        p.add(b);

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
        File f = new File("DataUserAndPassword.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;

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

    public void actionPerformed(ActionEvent e) {
        if ("create".equals(e.getActionCommand())) {
            String user = FieldUser.getText();
            String pass = FieldPass.getText();
            String conpass = FieldConPass.getText();

            if ( !user.isEmpty() && !user.contains(" ") ) {
                if ( !pass.isEmpty() && !pass.contains(" ") ) {
                    if ( !conpass.isEmpty() && !conpass.contains(" ") ) {
                        if ( pass.equals(conpass) ) {
                            WriteFile();
                            newFrameComplete();
                            UserAndPassword userpass = new UserAndPassword();
                            new Form3(userpass.GetValueOfDic());
                            dispose();
                        } System.out.println(">>>>> Passwords do not match. Please enter again. <<<<<");
                    } else newFrameConfirmPass();
                } else newFrameWrongPass();
            } else newFrameWrong();
        }
    }

    public void Finally() {
        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void newFrameComplete() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Create New Account Successfully ✓");
    }

    @Override
    public void newFrameWrong() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Fill in the Username field.!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void newFrameWrongPass() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Fill in the Password field.!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void newFrameConfirmPass() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Fill in the Confirm password field.!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}