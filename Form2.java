import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

// หน้าหลัก

public class Form2 extends JFrame implements ActionListener, KeyListener, NewFrame {
    public Container cp;
    public JLabel t, t2, timet, M2;
    public JButton Add, Delete;
    public JPanel p1, p2;
    public JTextField Item;
    public JList<String> toDo, toDoList;
    public DefaultListModel<String> TDL;
    public JComboBox<String> timeH, timeM;
    public BufferedImage Image2;
    // public Integer Time;

    public Form2() {
        super("To Do List !!");
        SortedListModel model = new SortedListModel();
        // toDoList = new JList<>(TDL);
        Initial();
        setComponent();
        JPanel();
        Time();
        Finally();
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

        TDL = new DefaultListModel<>();
        toDo = new JList<String>(TDL);
        toDo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        t2 = new JLabel("Your List : ");
        t2.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        t2.setBounds(20, 65, 80, 35);
        t2.setForeground(Color.white);

        Item = new JTextField("", 40);
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

        Add.setActionCommand("Add");
        Delete.setActionCommand("Delete");

        Add.addActionListener(this);
        Add.addKeyListener(this);
        Delete.addActionListener(this);

        Item.addActionListener(this);
        Item.addKeyListener(this);

        p2.add(t);
        p2.add(t2);
        p2.add(Item);
        p2.add(Add);
        p2.add(Delete);
        cp.add(p2);
    }

    // หน้าต่างของ list
    public void JPanel() {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBounds(600, 35, 400, 500);
        p1.setBackground(new Color(255, 255, 255, 0));

        // เพิ่ม JList สำหรับแสดงรายการ To-Do
        toDoList = new JList<>(TDL);
        JScrollPane scrollPane = new JScrollPane(toDoList);
        scrollPane.setPreferredSize(new Dimension(300, 480));
        // scrollPane.setBackground(new Color(178, 178, 178));
        p1.add(scrollPane);
        cp.add(p1);
        cp.setComponentZOrder(p1, 0);
    }

    // ComboBox ต่างๆ
    public void Time() {

        timet = new JLabel("Time : ");
        timet.setFont(new Font("Harlow Solid Itailc", Font.PLAIN, 16));
        timet.setBounds(49, 115, 100, 16);
        timet.setForeground(Color.white);
        p2.add(timet);

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
        timeM.setBounds(170, 110, 50, 25);
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


    class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    class DoublyLinkedList {
        Node head;

        // เพิ่มโหนดในสิ่งที่ชี้ไปยังคำสั่งสุดท้ายของลิงก์ลิสต์
        public void insert(int time) {
            Node newNode = new Node(time);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
                newNode.prev = current;
            }
        }

        // เรียงลำดับค่าในลิงก์ลิสต์จากน้อยไปหามาก
        public void sort() {
            if (head == null || head.next == null) {
                return;
            }

            Node current, index;
            int temp;
            current = head;

            while (current != null) {
                index = current.next;
                while (index != null) {
                    if (current.data > index.data) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }

        // แสดงค่าในลิงก์ลิสต์
        public void display() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

        // SortedListModel model = new SortedListModel();
        // JList list = new JList();
        // list.setModel(model);

    public void actionPerformed(ActionEvent e) {
        String newItem = Item.getText();
    if ("Add".equals(e.getActionCommand())) { // ถ้ากด Add
        if ( !newItem.isEmpty() ) {
        String timeStringH = (String) timeH.getSelectedItem();
        String timeStringM = (String) timeM.getSelectedItem();
            TDL.addElement("Time " +timeStringH+" "+timeStringM+" "+newItem);
            Item.setText("");
            System.out.println("The value inside is : " + TDL);
            System.out.println("Item added successfully!!!");
            System.out.println("-------------------------------------------");
            // newFrameComplete();
        } else {
            System.out.println("The list field is empty.");
        }
    }

    // กดปุ่ม Add Delete
    // public void actionPerformed(ActionEvent e) {
    // if ("Add".equals(e.getActionCommand())) { // ถ้ากด Add
    // String newItem = Item.getText();
    // String Time = (String) timeH.getSelectedItem();
    // if (!newItem.isEmpty()) {
    // TDL.addElement("Time "+Time+" "+newItem);
    // Item.setText("");
    // System.out.println("The value inside is : " + TDL); // ดู List
    // ที่อยู่ข้างในในปัจจุบัน
    // System.out.println("Item added successfully!!!");
    // System.out.println("Time = "+Time);
    // System.out.println("-------------------------------------------");
    // //newFrameComplete();
    // } else {
    // System.out.println("The list field is empty.");
    // //newFrameWrong();
    // }
    // }
    
    if ("Delete".equals(e.getActionCommand())) { // ถ้ากด Delete
    if (!TDL.isEmpty()) {
    int Line = toDoList.getSelectedIndex(); // สร้าง Line มาดูบรรทัดที่เลือกเริ่มตั้งแต่ 0
    String Value = toDoList.getSelectedValue(); // สร้าง Value ไว้ดูค่าใน Lineนั้นๆ
    if (Line != -1) { // Line ต้องไม่เป็น-1 เพราะต้อง
    System.out.println("Line : " + Line + " ---> Value is : " + Value);
    TDL.remove(Line);
    System.out.println("The value inside is " + TDL); // ดู List ที่อยู่ข้างในในปัจจุบัน
    System.out.println("-------------------------------------------");
    }
    }
    }

}


    // }


    // SortedListModel
    class SortedListModel extends AbstractListModel {

        SortedSet model;

        public SortedListModel() {
            model = new TreeSet();
        }

        public int getSize() {
            return model.size();
        }

        public Object getElementAt(int index) {
            return model.toArray()[index];
        }

        public void add(Object element) {
            if (model.add(element)) {
                fireContentsChanged(this, 0, getSize());
            }
        }

        public void addAll(Object elements[]) {
            Collection c = Arrays.asList(elements);
            model.addAll(c);
            fireContentsChanged(this, 0, getSize());
        }

        public void clear() {
            model.clear();
            fireContentsChanged(this, 0, getSize());
        }

        public boolean contains(Object element) {
            return model.contains(element);
        }

        public Object firstElement() {
            return model.first();
        }

        public Iterator iterator() {
            return model.iterator();
        }

        public Object lastElement() {
            return model.last();
        }

        public boolean removeElement(Object element) {
            boolean removed = model.remove(element);
            if (removed) {
                fireContentsChanged(this, 0, getSize());
            }
            return removed;
        }
    }

    // คีย์บอร์ด
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) { // ถ้ากด ENTER จะ add
            String newItem = Item.getText(); // ลบช่องว่างหน้าและหลังข้อความ
            if (!newItem.isEmpty()) {
                TDL.addElement(newItem);
                Item.setText("");
                System.out.println("Added: " + newItem);
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void newFrameComplete() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Item added successfully!!!");
    }
    
    @Override
    public void newFrameWrong() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "The list field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void newFrameWrongPass() {
    }

}