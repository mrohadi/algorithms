package weektwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node node = new Node(item);
            node.next = first;
            first.prev = node;
            first = node;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            last = new Node(item);
            first = last;
        } else {
            Node node = new Node(item);
            last.next = node;
            node.prev = last;
            last = node;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = first.item;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            first.next.prev = null;
            first = first.next;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = last.item;
        if (size() == 0) {
            first = null;
            last = null;
        } else {
            last.prev.next = null;
            last = last.prev;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node current;

        public LinkedListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                Node node = current;
                current = current.next;
                return node.item;
            }
        }
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Deque");
        Deque<Integer> deque = new Deque<>();
        StdOut.println(deque.size());
        for (int i = 1; i <= 5; i++) {
            deque.addFirst(i);
        }
        StdOut.println(deque.size());

        for (Integer i : deque) {
            StdOut.println(i);
        }
    }
}
