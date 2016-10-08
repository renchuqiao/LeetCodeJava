package hard.unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 305. Number of Islands II
 *
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns
 * the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after
 * each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 * vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 *
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 *
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 *
 * Union Find Solution:
 * Construct a union-find structure. For each element in positions, do a search on its surrounding neighbours (four directions).
 * If any of the neighbor has a root (not-null entry in union-find structure), then do a union. If not, this entry is a root.
 * We want to improve the running time by using Path compression and weighting union (reference: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf)
 *
 * Using OOD, we created another class called UnionFind. We maintain two arrays in this class, unionFind array that contains
 * index to root. Size array that contains the number of children in each rooted tree.
 * 1. Every time we do an add in Union-Find structure, we created a root (rootNum++).
 * 2. Then we union this entry to its surrounding entries. (rootNum--) for each successful union.
 * 3. Return rootNum.
 *
 * Running time:
 * O(k log(m*n))
 *
 * Here k is the size of array and log(m * n) is the worst case time for weighted union.
 *
 * Note: we need to implement path compression for find operation as well to keep the tree structure flat.
 *
 */
public class NumberOfIslandsII {
    class UnionFind {
        final int m;
        final int n;
        int rootNum;
        Integer[] unionFind;
        int[] size;

        UnionFind(final int m, final int n) {
            this.m = m;
            this.n = n;
            unionFind = new Integer[m * n];
            size = new int[m * n];
            rootNum = 0;
        }





    }

//    public List<Integer> numIslands2(int m, int n, int[][] positions) {
//        int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
//        UnionFind unionFind = new UnionFind(m, n);
//        List<Integer> ret = new ArrayList<>();
//
//        for (int i = 0; i < positions.length; i++) {
//            Integer idx = unionFind.add(positions[i][0], positions[i][1]);
//            for (int[] dir : direction) {
//                Integer newIdx = unionFind.getIdx(positions[i][0] + dir[0], positions[i][1] + dir[1]);
//                if (newIdx != null) {
//                    unionFind.union(idx, newIdx);
//                }
//            }
//
//            ret.add(unionFind.size);
//        }
//
//        return ret;
//    }
}
