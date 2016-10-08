package medium.unionFind;

/**
 * 200. Number of Islands
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 *
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Answer: 3
 *
 * Union-Find Solution:
 * Union Find is a data structure to store disjoint sets
 * Both operations can be done in (essentially) constant time
 *
 * Union Find Structure
 * Main idea: represent each set by a rooted tree
 * – Every node maintains a link to its parent
 * – A root node is the “representative” of the corresponding set
 * – Example: two sets {x, y, z} and {a, b, c, d}
 *     ___
 *     \ /
 *      v
 *      x
 *      ^
 *     / \
 *    y  z
 *
 *     ___
 *     \ /
 *      v
 *      a
 *      ^
 *     / \
 *    b  c
 *    ^
 *    |
 *    d
 *
 * Reference: https://web.stanford.edu/class/cs97si/03-data-structures.pdf
 *
 * Solution Main Idea:
 * convert each pair of (x,y) to single number. Suppose the matrix is m * n (m rows and n columns). Then each (x,y)
 * can be represented as n * x + y. Iterate through every single entry and do union-find with its left and upper neighbor with 1's.
 * If these neighbors are both 0s, make this entry a disjoint set (root who points to itself in union-find structure).
 * At the end, iterate through union-find structure to find the number of roots.
 *
 * Running time:
 * O(m * n)
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m < 1) {
            return 0;
        }
        int n = grid[0].length;
        if (n < 1) {
            return 0;
        }

        int[] unionFind = new int[m * n];
        //set 0 to be a number other than 0; if not specified, every entry of array will be initiated as 0.
        unionFind[0] = Integer.MAX_VALUE;

        //first iterate through every entry in the m * n matrix to construct union-find structure.
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '0') {
                    continue;
                }
                int index = row * n + col;
                int leftIdx = row * n + col - 1;
                int upIdx = (row - 1) * n + col;
                unionFind[index] = index;

                if (col > 0) {
                    //look to its left
                    if (grid[row][col - 1] == '1') {
                        union(index, leftIdx, unionFind);
                    }
                }

                if (row >  0) {
                    //look up
                    if (grid[row - 1][col] == '1') {
                        union(index, upIdx, unionFind);
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < unionFind.length; i++) {
            if (unionFind[i] == i) {
                count++;
            }
        }

        return count;
    }

    private void union(int idx, int cmpIdx, int[] array) {
        array[find(idx, array)] = find(cmpIdx, array);
    }

    private int find(int idx, int[] array) {
        if (array[idx] == idx) {
            return idx;
        }
        int root = find(array[idx], array);
        array[idx] = root;
        return root;
    }
}
