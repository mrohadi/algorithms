package weektwo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingStackOfString {
    private String[] s;
    private int n;

    public ResizingStackOfString() {
        s = new String[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String item) {
        if (n == s.length) resizingArray(s.length * 2);
        s[n++] = item;
    }

    public String pop() {
        String item = s[--n];
        if (n == (s.length / 4)) resizingArray(s.length / 2);
        return item;
    }

    public int getSize() {
        return n;
    }

    private void resizingArray(int capacity) {
        String[] newArr = new String[capacity];
        for (int i = 0; i < n; i++) {
            newArr[i] = s[i];
        }
        s = newArr;
    }

    public static void main(String[] args) {
        ResizingStackOfString stack = new ResizingStackOfString();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) 
                StdOut.println(stack.pop());
            else
                stack.push(item);
        }
        StdOut.println("Total item: " + stack.getSize());
        StdOut.println("ResizingStackOfString");
    }
}
