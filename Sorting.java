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
    public void insert(int data) {
        Node newNode = new Node(data);
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

// public class Sorting {
//     public static void main(String[] args) {
//         DoublyLinkedList dll = new DoublyLinkedList();
//         dll.insert(5);
//         dll.insert(2);
//         dll.insert(8);
//         dll.insert(1);

//         System.out.println("Original Linked List:");
//         dll.display();

//         dll.sort();

//         System.out.println("\nSorted Linked List:");
//         dll.display();
//     }
// }






// public void actionPerformed(ActionEvent e) {
//     if ("Add".equals(e.getActionCommand())) { // ถ้ากด Add
//         String newItem = Item.getText();
//         String timeString = (String) timeH.getSelectedItem();
        
//         if (!newItem.isEmpty()) {
//             int time = Integer.parseInt(timeString); // แปลง String เป็น int
//             String formattedTime = String.format("%02d", time); // จัดรูปแบบให้เป็น 2 ตัวอักษร
//             TDL.addElement("Time " + formattedTime + " " + newItem);
//             Item.setText("");
//             System.out.println("The value inside is : " + TDL);
//             System.out.println("Item added successfully!!!");
//             System.out.println("Time = " + formattedTime);
//             System.out.println("-------------------------------------------");
//             //newFrameComplete();
//         } else {
//             System.out.println("The list field is empty.");
//         }

//     }
// }

