package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private List<Point> points;

    public KDTree(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        return null;
    }
}
