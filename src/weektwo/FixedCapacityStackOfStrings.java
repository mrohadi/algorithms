package weektwo;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings implements Iterable<String> {

    private String[] s;
    private int n;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int getSize() {
        return n;
    }

    public void push(String item) {
        s[n++] = item;
    }

    public void pop() {
        s[n] = null;
        n--;
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<String> {
        private String[] current = s;
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0; 
        }

        @Override
        public String next() {
            String s = current[--i];
            return s;
        }
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(50);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) stack.pop();
            else stack.push(item);
        }
        StdOut.println("Size: " + stack.getSize());

        for (String s : stack) {
            StdOut.println(s + " ");
        }
    }
}
