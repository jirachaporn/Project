import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// หน้าช่วยเหลือ

public class Help extends JFrame implements ActionListener {

    private Container cp;
    private BufferedImage Image1; 
    private JLabel M , t1 , t2 , t3, t4 ,t5, t6 , f, b;
    private JPanel p;
    private JButton back;

    public Help(){
        super("Help");
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
        UserPassDefault userpass = new UserPassDefault();
        p = new JPanel();
        p.setLayout(null);
        p.setBounds(300, 35, 400, 500); 
        p.setBackground(new Color(255,255,255,100));

        t1 = new JLabel("Help");
        t1.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 30));
        t1.setBounds(170, 20, 170, 35);
        t1.setForeground(Color.black);

        t2 = new JLabel("Contact the developer");
        t2.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 16));
        t2.setBounds(50, 70, 230, 35);
        t2.setForeground(Color.black);

        f = new JLabel("Front-end");
        f.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 15));
        f.setBounds(50, 95, 230, 35);
        f.setForeground(Color.black);

        t3 = new JLabel("Name : Jirachaporn Pinpan 6521600176");
        t3.setFont(new Font("Harlow Solid Itailc", Font.PLAIN , 15));
        t3.setBounds(50, 115, 300, 35);
        t3.setForeground(Color.black);

        t4 = new JLabel("Email : Jirachaporn.p@ku.th");
        t4.setFont(new Font("Harlow Solid Itailc", Font.PLAIN , 15));
        t4.setBounds(50, 135, 300, 35);
        t4.setForeground(Color.black);

        b = new JLabel("Back-end");
        b.setFont(new Font("Harlow Solid Itailc", Font.BOLD , 15));
        b.setBounds(50, 165, 230, 35);
        b.setForeground(Color.black);

        t5 = new JLabel("Name : Ittiphon Kongkaew 6521601954");
        t5.setFont(new Font("Harlow Solid Itailc", Font.PLAIN , 15));
        t5.setBounds(50, 185, 300, 35);
        t5.setForeground(Color.black);

        t6 = new JLabel("Email : Ittiphon.k@ku.th");
        t6.setFont(new Font("Harlow Solid Itailc", Font.PLAIN , 15));
        t6.setBounds(50, 205, 300, 35);
        t6.setForeground(Color.black);

        back = new JButton("Back to login");
        back.setBounds(10, 460, 120, 30);
        back.setForeground(new Color(100,150,200));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setActionCommand("back");
        back.addActionListener(this);

        p.add(t1);
        p.add(t2);
        p.add(f);
        p.add(t3);
        p.add(t4);
        p.add(b);
        p.add(t5);
        p.add(t6);
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
    UserPassDefault userpass = new UserPassDefault();
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            new Login(userpass.GetValueOfDic());
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
