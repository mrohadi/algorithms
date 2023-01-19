package weektwo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingCapacityQueueOfString {

    private String[] s;
    private int first;
    private int last;
    private int size;

    public ResizingCapacityQueueOfString() {
        s = new String[1];
        first = 0;
        last = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(String item) {
        if (size == s.length) resizingArray(s.length * 2);
        s[last++] = item;
        size++;
        StdOut.println("enqueue => Size: " + size + " | Length: " + s.length + " | Last: " + last + " | First: " + first);
    } 

    public String dequeue() {
        String item = s[first++];
        if (size == (s.length / 4)) resizingArray(s.length / 2);
        size--;
        StdOut.println("dequeue => Size: " + size + " | Length: " + s.length + " | Last: " + last + " | First: " + first);
        return item;
    }

    private void resizingArray(int capacity) {
        String[] newArr = new String[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = s[i];
        }
        first = 0;
        last = size - 1;
        s = newArr;
    }

    public static void main(String[] args) {
        ResizingCapacityQueueOfString queue = new ResizingCapacityQueueOfString();
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
