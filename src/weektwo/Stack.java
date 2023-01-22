package weektwo;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> {

    private Node<Item> first;
    private int size;

    public Stack() { 
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item; 
        first = first.next;
        size--;
        return item;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) 
                StdOut.println(stack.pop());
            else
                stack.push(item);
        }
        StdOut.println("Size: " + stack.getSize());
    }
}
