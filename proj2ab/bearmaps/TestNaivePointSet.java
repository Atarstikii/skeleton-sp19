package bearmaps;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestNaivePointSet {
    @Test
    public void testGeneral() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(Arrays.asList(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        double actual1 = ret.getX(); // evaluates to 3.3
        double actual2 = ret.getY(); // evaluates to 4.4

        double expected1 = 3.3;
        double expected2 = 4.4;
        Assert.assertEquals(expected1, actual1, 1);
        Assert.assertEquals(expected2, actual2, 1);

    }
}
