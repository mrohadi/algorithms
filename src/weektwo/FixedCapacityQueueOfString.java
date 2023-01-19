package weektwo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityQueueOfString {

    private String[] s;
    private int first;
    private int last;
    private int size;

    public FixedCapacityQueueOfString(int capacity) {
        s = new String[capacity];
        size = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(String item) {
        s[last++] = item;
        size++;
    }

    public String dequeue() {
        String item = s[first];
        s[first++] = null;
        size--;
        return item;
    }

    public static void main(String[] args) {
        FixedCapacityQueueOfString queue = new FixedCapacityQueueOfString(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) 
                StdOut.println(queue.dequeue());
            else
                queue.enqueue(item);
        }
        StdOut.println("Size: " + queue.getSize());
    }
}
