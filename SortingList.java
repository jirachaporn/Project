import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
//w ww  .  j  a  v  a 2s .  co m
import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class SortingList {
  // public static void main(String args[]) {
  // JFrame frame = new JFrame("Custom Tip Demo");
  // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  // JList list = new JList();
  // SortedListModel model = new SortedListModel();
  // list.setModel(model);
  // model.add("Time | 01 : 50");
  // model.add("Time | 23 : 02");
  // model.add("Time | 22 : 15");
  // model.add("Time | 05 : 00");
  // model.add("Time | 03 : 40");
  // JScrollPane scrollPane = new JScrollPane(list);
  // frame.getContentPane().add(scrollPane);
  // frame.setSize(300, 300);
  // frame.setVisible(true);
  // }

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
}