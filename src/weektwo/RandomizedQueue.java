package weektwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == items.length) resizeArray(items.length * 2);
        if (n == 0) {
            items[n++] = item;
            return;
        }

        int randomIndex = StdRandom.uniform(n);
        Item temp = items[randomIndex];
        items[randomIndex] = item;
        items[n++] = temp;
    }

    //remove and return a randome item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (n == items.length / 4) resizeArray(items.length / 2);
        int randomIndex = StdRandom.uniform(n);
        Item item = items[randomIndex];
        items[randomIndex] = items[--n];
        items[n] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[StdRandom.uniform(n)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private int[] randomIndices;
        
        public RandomArrayIterator() {
            i = 0;
            randomIndices = new int[n];
            for (int j = 0; j < n; j++) {
                randomIndices[j] = j;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[randomIndices[i++]];
        }
    }

    private void resizeArray(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i);
        }
        StdOut.println("size: " + queue.size());
        for (int i : queue) {
            StdOut.println(i);
        }
        StdOut.println("Sample => " + queue.sample());
        StdOut.println("dequeue");
        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
        StdOut.println("size: " + queue.size());
    }
}
