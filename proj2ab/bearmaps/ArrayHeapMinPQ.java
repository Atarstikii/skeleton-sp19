package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu.mingwei
 * @date 2020/3/12 14:20
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private List<T> items;
    private Map<T,Double> prioritys;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        prioritys = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (prioritys.containsKey(item)) {
            throw new IllegalArgumentException("Already contains!");
        }
        items.add(item);
        prioritys.put(item, priority);
        swim(items.size());
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    @Override
    public boolean contains(T item) {
        return prioritys.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return items.get(0);
    }

    @Override
    public T removeSmallest() {
        T smallest = items.get(0);
        exch(1, items.size());
        items.remove(smallest);
        sink(1);
        return smallest;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        int k = items.indexOf(item);
        double prev = prioritys.get(item);
        prioritys.put(item, priority);
        if (priority > prev) {
            sink(k + 1);
        } else {
            swim(k + 1);
        }
    }

    private void sink(int k) {
        int N = items.size();
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
        int m = i - 1;
        int n = j - 1;
        return prioritys.get(items.get(m)) > prioritys.get(items.get(n));
    }

    private void exch(int i, int j) {
        int m = i - 1;
        int n = j - 1;
        T temp = items.get(m);
        items.set(m, items.get(n));
        items.set(n, temp);
    }
}
