package bearmaps;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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

}
