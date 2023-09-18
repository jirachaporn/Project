import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// หน้าเริ่มต้น


public class Form1 extends JFrame implements ActionListener {

    public Container cp;
    public BufferedImage Image1; 
    public JLabel M , t1;
    public JButton B;
    public JPanel p;

    public Form1(){
        super("To Do List !!");
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

        t1 = new JLabel("Start Using To Do !!");
        t1.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 28));
        t1.setBounds(70, 150, 300, 35);
        t1.setForeground(Color.black);

        B = new JButton("Let's go");
        B.setBounds(150, 260, 100, 30);
        B.setForeground(Color.white);
        B.setBackground(new Color(100,150,200));
        B.setActionCommand("Let's go");
        B.addActionListener(this);

        p.add(t1);
        p.add(B);
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

        // วิธีกูเอง

        // M = new JLabel();
        // M.setIcon(new ImageIcon("Image/2.png"));
        // Dimension size = M.getPreferredSize();
        // M.setBounds(0, 0, size.width, size.height);
        // cp.add(M);
    }

    public void actionPerformed(ActionEvent e) {
        if ("Let's go".equals(e.getActionCommand())) {
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