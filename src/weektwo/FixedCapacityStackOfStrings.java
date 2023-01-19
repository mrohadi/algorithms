package weektwo;

public class FixedCapacityStackOfStrings {

    private String[] s;
    private int n;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String item) {
        s[n++] = item;
    }

    public String pop() {
        return s[n--];    
    }
}
