package medium.binaryIndexedTree;

/**
 * 307. Range Sum Query - Mutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 *
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 * Solution:
 * We can use Binary Indexed Tree to solve this.
 * Reference on Binary Indexed Tree: https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 *
 * Be careful that:
 * 1. in the update function, we want to update the difference between the new value and the previous value in the BIT
 * 2. in the sumRange function, we want to include number at index i.
 */
public class RangeSumQueryMutable {

    class BinaryIndexedTree {
        private int maxVal;
        private int[] tree;

        BinaryIndexedTree(final int[] nums) {
            constructTree(nums);
        }

        void update(int idx, int value) {
            while (idx <= maxVal) {
                this.tree[idx] += value;
                idx += (idx & -idx);
            }
        }

        int read(int idx) {
            int ret = 0;
            while (idx > 0) {
                ret += this.tree[idx];
                idx -= (idx & -idx);
            }

            return ret;
        }

        private void constructTree(int[] nums) {
            this.maxVal = nums.length;
            this.tree = new int[maxVal + 1];
            for (int i = 0; i < nums.length; i++) {
                update(i + 1, nums[i]);
            }
        }

    }

    private BinaryIndexedTree bit;
    private int[] nums;

    public RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        this.bit = new BinaryIndexedTree(nums);
    }

    void update(int i, int val) {
        bit.update(i + 1, val - this.nums[i]);
        this.nums[i] = val;

    }

    public int sumRange(int i, int j) {
        return bit.read(j + 1) - bit.read(i);
    }

    public static void main(String[] args) {
        RangeSumQueryMutable r = new RangeSumQueryMutable(new int[] {1, 3, 5});
        System.out.println(r.sumRange(0, 2));
        r.update(1, 2);
        System.out.println(r.sumRange(0, 2));
    }

}
