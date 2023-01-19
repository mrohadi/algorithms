package weektwo;

import java.util.NoSuchElementException;

import analysis_algo.NodeString;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {
    private NodeString first;
    private int size;
    
    public StackOfStrings() {
        first = null;
        size = 0;
    }

    public void push(String item) {
        NodeString oldFirst = first;
        first = new NodeString();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public String pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack overflow");
        String item = first.item;
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

    public void printStack() {
        while (!isEmpty()) {
            StdOut.println(first.item); 
            first = first.next;
        }
    }

    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.println(stack.pop());
            else
                stack.push(s);
        }
        StdOut.println(stack.getSize());
        StdOut.println();
        stack.printStack();
    }
}
