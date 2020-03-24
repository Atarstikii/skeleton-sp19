package bearmaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private final static int DEFAULT_CAPACITY = 20;
    private Node[] items;
    private HashSet<T> hashSet;
    private int size = 0;

    public ArrayHeapMinPQ() {
        items = new Node[DEFAULT_CAPACITY];
        hashSet = new HashSet<>();

    }

    @Override
    public void add(T item, double priority) {
        if (hashSet.contains(item)) {
            throw new IllegalArgumentException("Already contains!");
        }
        ensureCapacity();
        size++;
        items[size]= new Node(item, priority);
        hashSet.add(item);
        swim(size);
    }

    private void ensureCapacity() {
        if (size + 1 >= items.length) {
            items = Arrays.copyOf(items, size << 1);
        }
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    @Override
    public boolean contains(T item) {
        return hashSet.contains(item);
    }

    @Override
    public T getSmallest() {
        if (hashSet.isEmpty()) {
            throw new RuntimeException("Empty");
        }
        return (T) items[1].getItem();
    }

    @Override
    public T removeSmallest() {
        Node<T> smallest = items[1];
        exch(1, size);
        items[size] = null;
        size--;
        sink(1);
        return smallest.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new RuntimeException("Not Contain");
        }
        int k = 0;
        double prev = 0;
        for (int i = 1; i <= size; i++) {
            if (item.equals(items[i].getItem())) {
                k = i;
                prev = items[i].getPriority();
                items[i].setPriority(priority);
                break;
            }
        }
        if (priority > prev) {
            sink(k);
        } else {
            swim(k);
        }
    }

    private void sink(int k) {
        int N = size;
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) > 0;
    }

    private void exch(int i, int j) {
        Node<T> temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private class Node<T> implements Comparable<Node<T>> {

        T item;
        double priority;

        public Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public double getPriority() {
            return priority;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(item, node.item);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item);
        }

        @Override
        public int compareTo(Node<T> o) {
            if (null == o) {
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }
    }
}
