package weektwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedListOfQueue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public LinkedListOfQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else
            oldLast.next = last;
        size++;
    }

    public void dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        first = first.next;
        size--;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        LinkedListOfQueue<Integer> queue = new LinkedListOfQueue<Integer>();
        while (!StdIn.isEmpty()) {
            int num = StdIn.readInt();
            queue.enqueue(num);
        }
        StdOut.println("Size: " + queue.getSize());

        for (Integer s : queue) {
            StdOut.println(s);            
        }
    }
}
