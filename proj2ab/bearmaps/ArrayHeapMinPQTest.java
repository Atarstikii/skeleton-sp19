package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

public class ArrayHeapMinPQTest {
    @Test
    public void testGeneral() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add("A", 1d);
        minPQ.add("B", 2d);
        minPQ.add("C", 3d);
        minPQ.removeSmallest();
        int actual = minPQ.size();
        int expected = 2;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testChangePriority() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 50; i++) {
            minPQ.add("A" + i, i);
        }

        minPQ.changePriority("A0", 3);
        String actual = minPQ.getSmallest();
        String expected = "A1";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddTime() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        Stopwatch stopwatch1 = new Stopwatch();
        for (int i = 0; i < 5000000; i++) {
            minPQ.add("A" + i, i);
        }
        System.out.println("Total time elapsed: " + stopwatch1.elapsedTime() +  " seconds.");

        ExtrinsicMinPQ<String> naiveMinPQ = new NaiveMinPQ<>();
        Stopwatch stopwatch2 = new Stopwatch();
        for (int i = 0; i < 5000000; i++) {
            naiveMinPQ.add("A" + i, i);
        }
        System.out.println("Total time elapsed: " + stopwatch2.elapsedTime() +  " seconds.");

    }

    @Test
    public void testRemoveTime() {
        ExtrinsicMinPQ<String> minPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 5_000_000; i++) {
            minPQ.add("A" + i, i);
        }
        Stopwatch stopwatch1 = new Stopwatch();
        for (int i = 0; i < 5_000_000; i++) {
            minPQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + stopwatch1.elapsedTime() +  " seconds.");

        ExtrinsicMinPQ<String> naiveMinPQ = new NaiveMinPQ<>();
        for (int i = 0; i < 5_000_000; i++) {
            naiveMinPQ.add("A" + i, i);
        }
        Stopwatch stopwatch2 = new Stopwatch();
        for (int i = 0; i < 5; i++) {
            naiveMinPQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + stopwatch2.elapsedTime() +  " seconds.");

    }
}
