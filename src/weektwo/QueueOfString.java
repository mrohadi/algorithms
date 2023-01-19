package weektwo;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueOfString {
    private Node first;
    private Node last;
    private int size;

    public QueueOfString() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else 
            oldLast.next = last;
        size++;
    }

    public String dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        String item = last.item;
        first = first.next;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    private class Node  {
        String item;
        Node next; 
    }

    public static void main(String[] args) {
        QueueOfString queue = new QueueOfString();    
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
