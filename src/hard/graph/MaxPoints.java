package hard.graph;

/**
 * 149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Solution:
 * Define line by y = (x1 - x2)/(y1 - y2) and b = y1 - k * x1
 * Then for each line iterate (or use a HashMap to store the result) each points to find if the third point is on the line
 *
 * Note:
 * 1. Make sure to check if two points are identical e.g. [[3, 10], [3, 10], [0, 2], [0, 2]]
 * 2. Divide by 0
 * 3. Int to Double before taking division
 * 4. Allow a delta for accuracy (for Leetcode the delta is 0.000000001)
 */

    /**
     * Definition for a point.
     * class Point {
     *     int x;
     *     int y;
     *     Point() { x = 0; y = 0; }
     *     Point(int a, int b) { x = a; y = b; }
     * }
     */
class MaxPoints {
        static class Point {
            int x;
            int y;
            Point(int a, int b) { x = a; y = b;}
        }

    public int maxPoints(Point[] points) {
        int max = 0;
        int count = 2;
        double k = 0;
        double mod = 0;
        double b = 0;
        boolean isSame = false;
        if (points.length < 3){
            return points.length;
        }

        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                Point p1 = points[i];
                Point p2 = points[j];
                double p1x = (double) p1.x;
                double p2x = (double) p2.x;
                double p1y = (double) p1.y;
                double p2y = (double) p2.y;
                count = (isSame) ? count : 2;
                if (p1.x == p2.x) {
                    if (p1.y == p2.y) {
                        count++;
                        max = Math.max(count, max);
                        isSame = true;
                        continue;
                    } else {
                        k = Integer.MAX_VALUE;
                    }
                } else {
                    k = (p1.y - p2.y)/(p1.x - p2.x);
                    mod = (p1.y - p2.y)%(p1.x - p2.x);
                }
                b = p1y - (k * (p1x - p2x) + mod) * p1x / (p1x - p2x);
                int curCount = count;
                for (int m = j + 1; m < points.length; m++) {
                    Point p3 = points[m];
                    double p3y = p3.y;
                    double p3x = p3.x;
                    if (k == Integer.MAX_VALUE) {
                        curCount = (p3.x == p2.x) ? curCount + 1 : curCount;
                    } else {
                        if (Math.abs(p3y - ((k * (p1x - p2x) + mod) * p3x / (p1x - p2x) + b)) < 0.000000001) {
                            curCount++;
                        }
                    }
                }
                max = Math.max(curCount, max);
            }
            isSame = false;
        }
        return max;
    }
}
