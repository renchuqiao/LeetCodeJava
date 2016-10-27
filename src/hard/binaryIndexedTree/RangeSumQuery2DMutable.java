package hard.binaryIndexedTree;

/**
 * 308. Range Sum Query 2D - Mutable
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Example:
 * Given matrix = [
 *  [3, 0, 1, 4, 2],
 *  [5, 6, 3, 2, 1],
 *  [1, 2, 0, 1, 5],
 *  [4, 1, 0, 1, 7],
 *  [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * Solution:
 * We can use a 2D version of Binary Indexed Tree to solve this problem.
 * Reference: https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/#prob
 *
 */
public class RangeSumQuery2DMutable {
    class BinaryIndexedTree2D {

        int maxRow;
        int maxCol;
        int[][] bit2D;

        BinaryIndexedTree2D(int[][] matrix) {
            constructTree(matrix);
        }

        void update(int row, int col, int val) {
            int curCol;
            while(row <= maxRow) {
                curCol = col;
                while (curCol <= maxCol) {
                    bit2D[row][curCol] += val;
                    curCol += (curCol & -curCol);
                }
                row += (row & -row);
            }
        }

        int read(int row, int col) {
            int ret = 0;
            int curCol;
            while (row > 0) {
                curCol = col;
                while (curCol > 0) {
                    ret += bit2D[row][curCol];
                    curCol -= (curCol & -curCol);
                }
                row -= (row & -row);
            }
            return ret;
        }

        void constructTree(int[][] matrix) {
            this.maxRow = matrix.length;
            if (maxRow == 0) {
                this.bit2D = new int[maxRow][];
                return;
            }
            this.maxCol = matrix[0].length;
            this.bit2D = new int[maxRow + 1][maxCol + 1];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    update(row + 1, col + 1, matrix[row][col]);
                }
            }
        }
    }

    private BinaryIndexedTree2D binaryIndexedTree2D;
    private int[][] matrix;

    public RangeSumQuery2DMutable(int[][] matrix) {
        this.matrix = matrix;
        this.binaryIndexedTree2D = new BinaryIndexedTree2D(matrix);
    }

    public void update(int row, int col, int val) {
        binaryIndexedTree2D.update(row + 1, col + 1, val - this.matrix[row][col]);
        this.matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return binaryIndexedTree2D.read(row2 + 1, col2 + 1) - binaryIndexedTree2D.read(row1, col2 + 1)
                                                    - binaryIndexedTree2D.read(row2 + 1, col1)
                                                    + binaryIndexedTree2D.read(row1, col1);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                new int[] {3, 0, 1, 4, 2},
        new int[] {5, 6, 3, 2, 1},
        new int[] {1, 2, 0, 1, 5},
        new int[] {4, 1, 0, 1, 7},
        new int[] {1, 0, 3, 0, 5}
        };

        int[][] matrix2 = new int[1][1];
        matrix2[0][0] = 1;
        RangeSumQuery2DMutable r = new RangeSumQuery2DMutable(matrix2);
        System.out.println(r.sumRegion(0,0,0,0));
        r.update(0,0,-1);
        System.out.println(r.sumRegion(0,0,0,0));
    }
}
