import org.junit.Assert;
import org.junit.Test;

public class TestUnionFind {

    @Test
    public void testGeneral() {
        UnionFind unionFind = new UnionFind(8);
        int actaul = unionFind.sizeOf(0);
        int expected = 1;
        Assert.assertEquals(expected, actaul);
    }

    @Test
    public void testFind() {
        UnionFind unionFind = new UnionFind(8);
        int actaul = unionFind.find(0);
        int expected = 0;
        Assert.assertEquals(expected, actaul);

        actaul = unionFind.find(7);
        expected = 7;
        Assert.assertEquals(expected, actaul);
    }

    @Test
    public void testConnected() {
        UnionFind unionFind = new UnionFind(8);
        boolean actual = unionFind.connected(0, 7);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSize() {
        UnionFind unionFind = new UnionFind(8);
        unionFind.union(0, 7);
        unionFind.union(7, 3);
        unionFind.union(1, 2);
        unionFind.union(2, 3);

        int actual = unionFind.sizeOf(3);
        int expected = 5;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUnion() {
        UnionFind unionFind = new UnionFind(8);
        unionFind.union(0, 7);
        unionFind.union(7, 3);
        unionFind.union(1, 2);
        unionFind.union(2, 3);
        boolean actual = unionFind.connected(1, 7);
        boolean expected = true;
        Assert.assertEquals(expected, actual);

    }
}
