import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

// หน้าลืมรหัสผ่าน

public class Form4 extends JFrame implements ActionListener  {

    public Container cp;
    public BufferedImage Image1; 
    public JLabel M , t1 , t2;
    public JButton B , help , confirm , back;
    public JPanel p;
    public JTextField te;

    public Form4(){
        super("Forgot password");
        Initial();
        setComponent();
        Finally() ;
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

        t1 = new JLabel("Forgot password");
        t1.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 28));
        t1.setBounds(90, 20, 300, 35);
        t1.setForeground(Color.black);

        t2 = new JLabel("Email");
        t2.setFont(new Font("Harlow Solid Itailc", Font.PLAIN , 16));
        t2.setBounds(50, 70, 150, 35);
        t2.setForeground(Color.black);

        // text Username
        te = new JTextField("",100);
        te.setBounds(50, 105, 300, 25);
        te.setBackground(Color.white);

        confirm = new JButton("Confirm");
        confirm.setBounds(140, 140, 100, 30);
        confirm.setForeground(Color.white);
        confirm.setBackground(new Color(100,150,200));
        confirm.setActionCommand("confirm");
        confirm.addActionListener(this);

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


        p.add(t1);
        p.add(t2);
        p.add(te);
        p.add(confirm);
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

    public void actionPerformed(ActionEvent e) {
        if("help".equals(e.getActionCommand())) {
            Form6 Form6 = new Form6();
            Form6.setVisible(true);
            dispose();
            }
        if ("confirm".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(null, "Show password");
            }
        if("back".equals(e.getActionCommand())) {
            Form3 Form3 = new Form3(null);
            Form3.setVisible(true);
            dispose();
            }
    }

    public void Finally(){
        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}