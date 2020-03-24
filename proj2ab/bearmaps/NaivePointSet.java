package bearmaps;


import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point current = new Point(x, y);
        Point nearest = points.get(0);
        double nearestDistance = Point.distance(nearest, current);
        for (int i = 1; i < points.size(); i++) {
            if (nearestDistance > Point.distance(points.get(i), current)) {
                nearest = points.get(i);
            }
        }
        return nearest;
    }
}
