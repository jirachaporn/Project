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
    private JList<String> toDo, toDoList;
    private DefaultListModel<String> defaultToDoList;
    private JComboBox<String> timeH, timeM;
    private BufferedImage Image2;
    // public Integer Time;

    public Todo() {
        super("To Do List !!");
        Initial();
        setComponent();
        JPanel();
        Time();
        Finally();
        ImageIcon img = new ImageIcon("Image/Icon.png");
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

        t = new JLabel("To Do List");
        t.setFont(new Font("Harlow Solid Itailc", Font.BOLD, 30));
        t.setBounds(230, 20, 400, 35);
        t.setForeground(new Color(255, 220, 0));

        defaultToDoList = new DefaultListModel<>();
        toDo = new JList<String>(defaultToDoList);
        toDo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        t2 = new JLabel("Your List : ");
        t2.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        t2.setBounds(20, 65, 80, 35);
        t2.setForeground(Color.white);

        Item = new JTextField("", 40);
        //Item.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 14));
        Item.setBounds(110, 70, 500, 25);
        Item.setBackground(Color.white);

        Add = new JButton("Add");
        Add.setBounds(110, 150, 80, 25);
        Add.setForeground(Color.WHITE);
        Add.setBackground(new Color(0, 49, 165));

        Delete = new JButton("Delete");
        Delete.setBounds(200, 150, 80, 25);
        Delete.setForeground(Color.WHITE);
        Delete.setBackground(new Color(158, 41, 59));

        Clear = new JButton("Clear");
        Clear.setBounds(290, 150, 80, 25);
        Clear.setForeground(Color.WHITE);
        Clear.setBackground(new Color(170, 150, 30));

        back = new JButton("Sing out");
        back.setBounds(0, 480, 100, 30);
        back.setForeground(Color.white);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setActionCommand("back");
        back.addActionListener(this);

        Add.setActionCommand("Add");
        Delete.setActionCommand("Delete");
        Clear.setActionCommand("Clear");

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
        toDoList = new JList<>(defaultToDoList);
        JScrollPane scrollPane = new JScrollPane(toDoList);
        scrollPane.setPreferredSize(new Dimension(300, 480));
        // scrollPane.setBackground(new Color(178, 178, 178));
        p1.add(scrollPane);
        cp.add(p1);
        cp.setComponentZOrder(p1, 0);
    }

    public void sortTimeList() {
        List<String> Newlist = new ArrayList<>();
        for (int i = 0; i < defaultToDoList.getSize(); i++) {
            Newlist.add(defaultToDoList.getElementAt(i));
        }
    
        Collections.sort(Newlist, new Comparator<String>() {
            public int compare(String o1, String o2) {
                // ดึงข้อมูลเวลาออกมา
                String time1 = getTimePart(o1);
                System.out.println("time1: " + time1);
                String time2 = getTimePart(o2);
                System.out.println("time2: " + time2);
                System.out.println("-------------------------------------------");
                return time1.compareTo(time2);
            }
    
            String getTimePart(String todoItem) {
                String[] parts = todoItem.split(":");
                String hours = parts[0].trim(); // ชั่วโมง
                String minutes = parts[1].trim(); // นาที
                
                return hours + ":" + minutes;
            }
        });

    
        // ลบ defaultToDoList ออกทั้งหมด แล้วเพิ่ม Newlist เข้าไปแทน (เพื่ออัปเดตรายการงานที่มีการจัดเรียงเวลาแล้ว)
        defaultToDoList.clear();
        for (String item : Newlist) {
            defaultToDoList.addElement(item);
        }
    }

    // ComboBox ต่างๆ
    public void Time() {

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
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                timeH.addItem("0" + i);
            } else
                timeH.addItem("" + i);
        }
        p2.add(timeH);
        timeH.setBounds(110, 110, 50, 25);
        timeH.setBackground(Color.white);

        // หน่วยนาที
        timeM = new JComboBox<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                timeM.addItem("0" + i);
            } else
                timeM.addItem("" + i);
        }
        p2.add(timeM);
        timeM.setBounds(180, 110, 50, 25);
        timeM.setBackground(Color.white);
    }
    
    // ตั้งค่าหน้าต่าง JFrame
    public void Finally() {

        try {
            Image2 = ImageIO.read(new File("Image/3.png"));
            M2 = new JLabel(new ImageIcon(Image2));
            M2.setBounds(0, 0, 1000, 600);
            cp.add(M2);
            } catch (IOException e) {
            e.printStackTrace();
        }

        cp.setBackground(new Color(45, 45, 45));
        // cp.setBackground(Color.white);
        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String newItem = Item.getText();

        // เพิ่มข้อความ
    if ("Add".equals(e.getActionCommand())) { // ถ้ากด Add
        if ( !newItem.isEmpty() ) {
        String timeStringH = (String) timeH.getSelectedItem();
        String timeStringM = (String) timeM.getSelectedItem();
            defaultToDoList.addElement("Time " +timeStringH+" : "+timeStringM+" "+newItem);
            Item.setText("");
            sortTimeList();
            System.out.println("Added: " + newItem);
            System.out.println("Item added successfully!!!");
            System.out.println("-------------------------------------------");
            // newFrameComplete();
        } else {
            System.out.println("The list field is empty.");
        }
    }

        // ลบข้อความ
    if ("Delete".equals(e.getActionCommand())) { // ถ้ากด Delete
        if (!defaultToDoList.isEmpty()) {
            int Line = toDoList.getSelectedIndex(); // สร้าง Line มาดูบรรทัดที่เลือกเริ่มตั้งแต่ 0
            String Value = toDoList.getSelectedValue(); // สร้าง Value ไว้ดูค่าใน Lineนั้นๆ
            if (Line != -1) { // Line ต้องไม่เป็น-1 เพราะต้อง
                System.out.println("Delete Line : " + Line);
                System.out.println("Line : " + Line + " ---> Value is : " + Value);
                defaultToDoList.remove(Line);
                System.out.println("The value inside is " + defaultToDoList); // ดู List ที่อยู่ข้างในในปัจจุบัน
                System.out.println("-------------------------------------------");
            }
        }
    }
        // Clear list
    if ("Clear".equals(e.getActionCommand())){
        if (!defaultToDoList.isEmpty()) { // ถ้า scrollPane TDL ไม่ว่าง
            defaultToDoList.removeAllElements(); // ให้ลบทุกตัวทิ้ง
            System.out.println("Remove all elements");
            System.out.println("-------------------------------------------");
            JOptionPane.showMessageDialog(cp, "Clear complete !!!");
        } else {
            System.out.println("The list is already empty .");
            System.out.println("-------------------------------------------");
        }
    }

}

    // คีย์บอร์ด
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) { // ถ้ากด ENTER จะ add
            String newItem = Item.getText(); // ลบช่องว่างหน้าและหลังข้อความ
            String timeStringH = (String) timeH.getSelectedItem();
            String timeStringM = (String) timeM.getSelectedItem();
        
            if (!newItem.isEmpty()) {
                defaultToDoList.addElement("Time " +timeStringH+" : "+timeStringM+" "+newItem);
                Item.setText("");
                System.out.println("Added: " + newItem);
                System.out.println("Item added successfully!!!");
                System.out.println("-------------------------------------------");
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }

}
