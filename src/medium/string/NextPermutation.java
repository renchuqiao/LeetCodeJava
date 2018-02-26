package medium.string;

/**
 * 31. Next Permutation
 *
 * Solution:
 * Suppose we have a string n of length N.
 * - From right to left, find first i such that n[i, N] is not descending. (n[i + 1, N])
 * - Swap n[i] with one integer in n[i + 1, n - 1]
 * - reverse n[i + 1, N] so that it is ascending.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int idx = nums.length - 1;
        while (idx > 0) {
            if (nums[idx - 1] < nums[idx]) {
                break;
            }
            idx--;
        }
        if (idx != 0) {
            findAndSwap(nums, idx - 1);
        }

        reverse(nums, idx);
    }

    private void findAndSwap(int[] nums, int idx) {
        int j = nums.length - 1;
        while (j > idx) {
            if (nums[j] > nums[idx]) {
                swap(nums, idx, j);
                break;
            }
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int idx) {
        for (int i = nums.length - 1; i >= (nums.length - idx) / 2 + idx; i--) {
            swap(nums, i, nums.length - 1 - i + idx);
        }
    }

    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();
//        int[] input = new int[] {1,2,1,5,4,3,3,2,1};
        int[] input = new int[] {1, 3, 2};
        n.nextPermutation(input);
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ",");
        }
    }
}
