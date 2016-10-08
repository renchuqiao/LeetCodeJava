package hard.graph;

import java.util.*;

/**
 * 317. Shortest Distance from All Buildings
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can
 * only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 *
 * Solutions:
 * There are two solutions to this problem
 *
 * Solution 1:
 * First, find the total number of houses.
 * Then, for each 0, use BFS to find the shortest path from this given point to all the 1's.
 * (Note: in BFS, don't add 2 to the queue)
 * everytime we find a valid path sum for a given 0 point, update the minimum distance
 *
 * Solution 2:
 * BFS all 1's to find the shortest path from 1 to all 0's. Store the path sum for each 0 in a matrix. At the same time,
 * count the number of houses. In order to determine if 1 can reach all 0's, store the number of house that can reach this
 * given 0 in an matrix as well. Then in the last for loop, find the minimum path sum while the number of houses matches
 * the total number of houses.
 */
public class ShortestDistanceFromAllBuildings {
    class Point {
            int row;
            int col;
            int dis;
        Point(final int row, final int col, final int dis) {
            this.row = row;
            this.col = col;
            this.dis = dis;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return p.col == this.col && p.row == this.row;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + col;
            result = result * 31 + row;
            return result;
        }
    }

    public int shortestDistance(int[][] grid) {
        int minDis = Integer.MAX_VALUE;

        int houseNum = 0;
        //count # of houses
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    houseNum += 1;
                }
            }
        }

        //BFS
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0) {
                    int newMin = BFS(grid, new Point(row, col, 0), houseNum);
                    if (newMin > -1) {
                        minDis = Math.min(newMin, minDis);
                    }
                }
            }
        }

        return (minDis == Integer.MAX_VALUE) ? -1 : minDis;
    }

    private int BFS(int[][] grid, Point s, int houseNum) {
        Deque<Point> queue = new LinkedList<>();
        Set<Point> isVisited = new HashSet<>();
        int[][] direction = {{0, -1},{0, 1},{1, 0}, {-1, 0}};
        queue.offerLast(s);
        int sumDis = 0;
        int curHouse = 0;
        while (!queue.isEmpty()) {
            Point p = queue.pollFirst();
            isVisited.add(p);
            for (int i = 0; i < 4; i++) {
                int newRow = p.row + direction[i][0];
                int newCol = p.col + direction[i][1];
                Point newP = new Point(newRow, newCol, p.dis + 1);
                if (newRow < grid.length && newRow > -1 && newCol < grid[0].length && newCol > -1 && !isVisited.contains(newP)) {
                    if (grid[newRow][newCol] == 0) {
                        queue.offerLast(newP);
                    } else if (grid[newRow][newCol] == 1) {
                        sumDis += p.dis + 1;
                        curHouse += 1;
                        isVisited.add(newP);
                    }
                }
            }
        }

        return (curHouse == houseNum) ? sumDis : -1;
    }

    public int shortestDistance2(int[][] grid) {
        int minDis = Integer.MAX_VALUE;
        int[][] dis = new int[grid.length][grid[0].length];
        int[][] house = new int[grid.length][grid[0].length];

        int houseNum = 0;
        //count # of houses and BFS
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    BFS2(grid, new Point(row, col, 0), house, dis);
                    houseNum += 1;
                }
            }
        }

        //find Min
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.println(house[row][col]);
                if (grid[row][col] == 0 && house[row][col] == houseNum) {
                    minDis = Math.min(minDis, dis[row][col]);
                }
            }
        }


        return (minDis == Integer.MAX_VALUE) ? -1 : minDis;
    }

    private void BFS2(int[][] grid, Point s, int[][] house, int[][] dis) {
        Deque<Point> queue = new LinkedList<>();
        Set<Point> isVisited = new HashSet<>();
        int[][] direction = {{0, -1},{0, 1},{1, 0}, {-1, 0}};
        queue.offerLast(s);
        while (!queue.isEmpty()) {
            Point p = queue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int newRow = p.row + direction[i][0];
                int newCol = p.col + direction[i][1];
                Point newP = new Point(newRow, newCol, p.dis + 1);
                if (newRow < grid.length && newRow > -1 && newCol < grid[0].length && newCol > -1 && !isVisited.contains(newP)) {
                    if (grid[newRow][newCol] == 0) {
                        queue.offerLast(newP);
                        isVisited.add(newP);
                        dis[newRow][newCol] += p.dis + 1;
                        house[newRow][newCol] += 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][] grid = {{0,2,2,0,2,0,0,0,2,2,0,2,0,2,2,0,2,2,2,0,0,0,0,2,1,0,0,2,2,0,0,0,2,0,0,2,0},{0,2,2,2,0,1,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0,2,0,0,0,0,2,0,0,0,2,2,0,0,0,0,0},{0,0,2,2,2,0,2,2,2,2,0,2,0,0,0,0,2,2,2,0,2,2,2,1,0,2,0,0,2,0,0,0,0,2,0,0,2},{0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,0,2,0,0,2,0,0,0,2,2,2,2},{2,2,2,2,2,0,0,0,2,2,2,2,0,2,2,2,2,0,0,2,1,0,0,2,0,0,2,0,2,2,2,2,1,2,0,0,0},{0,2,1,2,0,0,0,0,0,0,0,0,0,2,2,0,2,2,0,2,0,0,2,2,0,0,2,0,0,0,2,0,2,2,0,0,2},{0,0,0,0,2,0,0,0,2,0,2,0,2,2,2,0,0,0,2,2,0,0,2,0,0,2,0,0,0,0,2,0,0,0,0,2,0},{2,0,2,0,2,0,0,2,2,0,0,0,0,2,0,0,2,2,0,2,2,0,2,2,1,2,2,0,0,0,2,2,1,2,0,0,2},{2,0,2,2,0,0,0,0,0,0,0,2,0,2,2,1,0,2,0,2,0,2,0,0,0,0,0,2,0,0,0,2,2,0,2,0,2},{2,0,0,0,2,2,0,2,0,0,0,2,0,2,0,2,0,2,2,2,0,0,0,1,0,0,0,0,1,0,0,0,0,2,0,0,0},{0,2,0,0,0,2,0,2,0,0,0,1,0,2,0,0,2,0,2,0,0,0,0,2,0,0,0,2,2,2,0,0,2,0,2,0,0},{0,0,0,0,2,2,0,1,2,0,2,2,0,0,0,0,0,0,2,0,2,0,0,2,0,2,0,0,2,0,0,2,0,0,2,0,1},{0,1,2,2,0,2,0,0,2,0,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2,2,2,0,0,0,0},{2,2,2,2,2,2,2,0,2,0,0,2,2,0,2,0,0,0,0,2,0,0,0,2,2,2,0,0,0,2,2,2,0,0,2,0,0}};
        int[][] grid = {{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
        ShortestDistanceFromAllBuildings s = new ShortestDistanceFromAllBuildings();
        System.out.println(s.shortestDistance2(grid));
    }
}
