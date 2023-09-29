import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

// หน้าหลัก Todo

public class Todo extends JFrame implements ActionListener, KeyListener{
    private Container cp;
    private JLabel t, t2, timet, M2 ,timet2;
    private JButton Add, Delete , Clear , back;
    private JPanel p1, p2;
    private JTextField Item;
    private JList<String>  toDoList;
    private DefaultListModel<String> defaultToDoList;
    private JComboBox<String> timeH, timeM;
    private BufferedImage Image2;

    public Todo() {
        super("To Do List !!");
        Initial();
        setComponent();
        JPanel();
        Time();
        Finally();
        ImageIcon img = new ImageIcon("Image/Icon.png"); // กำหนด Icon ของโปรแกรม
        this.setIconImage(img.getImage());
    }

    public void Initial() {
        cp = getContentPane();
        cp.setLayout(null);
    }

    // กำหนดค่าปุ่มต่างๆ
    public void setComponent() {
        
        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(20, 20, 950, 520);
        p2.setBackground(new Color(45, 45, 45, 200));

        // หัวข้อ To Do List
        t = new JLabel("To Do List");
        t.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 30));
        t.setBounds(230, 20, 400, 35);
        t.setForeground(new Color(255, 220, 0));

        // Your List 
        t2 = new JLabel("Your List : ");
        t2.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        t2.setBounds(20, 65, 80, 35);
        t2.setForeground(Color.white);

        // text Your List :
        Item = new JTextField("", 40);
        Item.setBounds(110, 70, 500, 25);
        Item.setBackground(Color.white);

        // ปุ่ม Add
        Add = new JButton("Add");
        Add.setBounds(110, 150, 80, 25);
        Add.setForeground(Color.WHITE);
        Add.setBackground(new Color(0, 49, 165));

        // ปุ่ม Delete
        Delete = new JButton("Delete");
        Delete.setBounds(200, 150, 80, 25);
        Delete.setForeground(Color.WHITE);
        Delete.setBackground(new Color(158, 41, 59));

        // ปุ่ม Clear
        Clear = new JButton("Clear");
        Clear.setBounds(290, 150, 80, 25);
        Clear.setForeground(Color.WHITE);
        Clear.setBackground(new Color(170, 150, 30));

        // ปุ่ม Sing out
        back = new JButton("Sing out");
        back.setBounds(0, 480, 100, 30);
        back.setForeground(Color.white);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);

        // กำหนด ActionCommand ของแต่ละปุ่ม
        back.setActionCommand("back");
        Add.setActionCommand("Add");
        Delete.setActionCommand("Delete");
        Clear.setActionCommand("Clear");

        // กำหนด ActionListener ของแต่ละปุ่ม
        back.addActionListener(this);
        Add.addActionListener(this);
        Add.addKeyListener(this);
        Delete.addActionListener(this);
        Clear.addActionListener(this);
        Item.addActionListener(this);
        Item.addKeyListener(this);

        p2.add(t);
        p2.add(t2);
        p2.add(Item);
        p2.add(Add);
        p2.add(Delete);
        p2.add(Clear);
        p2.add(back);
        cp.add(p2);

    }

    // หน้าต่างของ list
    public void JPanel() {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBounds(600, 35, 400, 500);
        p1.setBackground(new Color(255, 255, 255, 0));

        // เพิ่ม JList สำหรับแสดงรายการ To-Do
        toDoList = new JList<>(defaultToDoList); // toDoList โดยกำหนดให้เป็น DefaultListModel 
        JScrollPane scrollPane = new JScrollPane(toDoList); //  นำ toDoList มาใส่ใน scrollPane เพื่อให้เลื่อนได้
        scrollPane.setPreferredSize(new Dimension(300, 480));
        p1.add(scrollPane);
        cp.add(p1);
        cp.setComponentZOrder(p1, 0); // กำหนดลำดับการวางแสดง components โดยให้ p1 อยู่บนสุด
    }

    public void sortTimeList() {

        List<String> Newlist = new ArrayList<>(); // สร้าง string Newlist โดยกำหนดให้เป็น ArrayList<>
        for (int i = 0; i < defaultToDoList.getSize(); i++) { // วน for i < ขนาดของ defaultToDoList
            Newlist.add(defaultToDoList.getElementAt(i)); // เพิ่ม defaultToDoList เข้าไปใน Newlist ( เป็นการก็อปปี้ )
        }
        Collections.sort(Newlist, new Comparator<String>() { // เรียงลำดับข้อมูลที่อยู่ใน Newlist
            public int compare(String o1, String o2) { // เปรียบเทียบอ็อบเจกต์ 2 ตัว
                String time1 = getTimePart(o1); // ให้อ็อบเจกต์ o1 = time1
                System.out.println("time1: " + time1);
                String time2 = getTimePart(o2); // ให้อ็อบเจกต์ o2 = time2
                System.out.println("time2: " + time2);
                System.out.println("-------------------------------------------");
                return time1.compareTo(time2); // return time1 เปรียบเทียบกับ time2
            }
    
            String getTimePart(String todoItem) {
                String[] parts = todoItem.split(":"); //  แบ่งสตริงเป็นสองส่วนโดยใช้เครื่องหมาย (:)
                String hours = parts[0].trim(); // ให้ string ตำแหน่งที่ 1 เป็นชั่วโมง
                String minutes = parts[1].trim(); // ให้ string ตำแหน่งที่ 2 เป็นนาที
                return hours + ":" + minutes; 
            }
        });
    
        defaultToDoList.clear();  // ลบ defaultToDoList ออกทั้งหมด 
        for (String item : Newlist) { // เข้าถึงแต่ละรายการใน Newlist 
            defaultToDoList.addElement(item); // เพิ่ม item เข้าไปใน Newlist (เพื่ออัปเดตรายการงานที่มีการจัดเรียงเวลาแล้ว)
        }
    }

    // ComboBox ต่างๆ
    public void Time() {

        // Time :
        timet = new JLabel("Time : ");
        timet.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        timet.setBounds(50, 115, 100, 16);
        timet.setForeground(Color.white);

        timet2 = new JLabel(":");
        timet2.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 18));
        timet2.setBounds(168, 110, 100, 20);
        timet2.setForeground(Color.white);

        p2.add(timet);
        p2.add(timet2);

        // หน่วยชั่วโมง
        timeH = new JComboBox<>();
        for (int i = 0; i < 24; i++) { // วน for i = 0 ถึง 23
            if (i < 10) { // ถ้า i น้อยกว่า 10
                timeH.addItem("0" + i); // addItem 0+i จะได้ 01 02 03
            } else
                timeH.addItem("" + i); // add i จะได้ 10 11 12 
        }
        p2.add(timeH);
        timeH.setBounds(110, 110, 50, 25);
        timeH.setBackground(Color.white);

        // หน่วยนาที
        timeM = new JComboBox<>();
        for (int i = 0; i < 60; i++) { // วน for i = 0 ถึง 59
            if (i < 10) { // ถ้า i น้อยกว่า 10
                timeM.addItem("0" + i); // addItem 0+i จะได้ 01 02 03
            } else
                timeM.addItem("" + i); // add i จะได้ 10 11 12 
        }
        p2.add(timeM);
        timeM.setBounds(180, 110, 50, 25);
        timeM.setBackground(Color.white);
    }
    

    public void actionPerformed(ActionEvent e) {
        String newItem = Item.getText();

        // เพิ่มข้อความ
    if ("Add".equals(e.getActionCommand())) { // ถ้ากด Add
        if ( !newItem.isEmpty() ) { // ถ้า newItem ไม่ว่าง 
            String timeStringH = (String) timeH.getSelectedItem(); // สร้าง string timeStringH มาเก็บค่า timeH
            String timeStringM = (String) timeM.getSelectedItem(); // สร้าง string timeStringM มาเก็บค่า timeM
            defaultToDoList.addElement("Time " +timeStringH+" : "+timeStringM+" "+newItem); // add "Time " +timeStringH+" : "+timeStringM+" "+newItem เข้าไปใน defaultToDoList
            Item.setText(""); // Clear TextField ให้ว่าง 
            sortTimeList(); // เรียกใช้เมทตอด sortTimeList เพื่อเรียงลำดับเวลา
            System.out.println("Added: " + newItem); // ปริ้น Added: " + newItem ออกมาดู
            System.out.println("Item added successfully!!!");
            System.out.println("-------------------------------------------");
        } else {
            System.out.println("The list field is empty.");
        }
    }

        // ลบข้อความ
    if ("Delete".equals(e.getActionCommand())) { // ถ้ากด Delete
        if (!defaultToDoList.isEmpty()) { // ถ้า defaultToDoList ไม่ว่าง 
            int Line = toDoList.getSelectedIndex(); // สร้าง Line มาดูบรรทัดที่เลือกเริ่มตั้งแต่ 0
            String Value = toDoList.getSelectedValue(); // สร้าง Value ไว้ดูค่าใน Lineนั้นๆ
            if (Line != -1) { // Line ต้องไม่เป็น-1 เพราะต้องเริ่มที่ 0
                System.out.println("Delete Line : " + Line);  // ปริ้น line ออกมาดู
                defaultToDoList.remove(Line); // ลบ line ที่เลือกออก
                System.out.println("-------------------------------------------");
            }
        }
    }
        // Clear list
    if ("Clear".equals(e.getActionCommand())){
        if (!defaultToDoList.isEmpty()) { // ถ้า defaultToDoList ไม่ว่าง
            defaultToDoList.removeAllElements(); // ให้ลบทุกตัวใน defaultToDoList ทิ้ง
            System.out.println("Remove all elements");
            System.out.println("-------------------------------------------");
            JOptionPane.showMessageDialog(cp, "Clear complete !!!");
        } else { // ถ้า defaultToDoList ว่าง 
            JOptionPane.showMessageDialog(cp, "The list is already empty.","Error", JOptionPane.ERROR_MESSAGE); // แสดงข้อความ The list is already empty.
            System.out.println("-------------------------------------------");
        }
    }
}

    // คีย์บอร์ด
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) { // ถ้ากด ENTER จะ add
            String newItem = Item.getText(); // ลบช่องว่างหน้าและหลังข้อความ

            if (!newItem.isEmpty()) { // ถ้า newItem ไม่ว่าง 
                String timeStringH = (String) timeH.getSelectedItem(); // สร้าง string timeStringH มาเก็บค่า timeH
                String timeStringM = (String) timeM.getSelectedItem(); // สร้าง string timeStringM มาเก็บค่า timeM
                defaultToDoList.addElement("Time " +timeStringH+" : "+timeStringM+" "+newItem); // add "Time " +timeStringH+" : "+timeStringM+" "+newItem เข้าไปใน defaultToDoList
                Item.setText(""); // Clear TextField ให้ว่าง
                sortTimeList(); // เรียกใช้เมทตอด sortTimeList เพื่อเรียงลำดับเวลา
                System.out.println("Added: " + newItem); // ปริ้น Added: " + newItem ออกมาดู
                System.out.println("Item added successfully!!!");
                System.out.println("-------------------------------------------");
            } else {
                System.out.println("The list field is empty.");
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }

     // ตั้งค่าหน้าต่าง JFrame
    public void Finally() {

        // เพิ่มรูปพื้นหลัง
        try {
            Image2 = ImageIO.read(new File("Image/3.png"));
            M2 = new JLabel(new ImageIcon(Image2));
            M2.setBounds(0, 0, 1000, 600);
            cp.add(M2);
            } catch (IOException e) {
            e.printStackTrace();
        }

        cp.setBackground(new Color(45, 45, 45));
        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
