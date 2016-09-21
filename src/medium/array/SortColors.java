package medium.array;

/**
 * 75. Sort Colors
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 *
 * Solution:
 * Three pointers
 * before i: all 0
 * between i and j: all 1
 * between j and k: unsorted
 * after k: all 2
 *
 * initialization:
 * i = j = 0;
 * k = length - 1
 *
 * move j until j >= k
 * if nums[j] == 0: swap(i++, j++)
 * if nums[j] == 1: j++
 * if nums[j] == 2: swap(j, k--)
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int i, j;
        i = j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i++, j++);
            } else if (nums[j] == 1) {
                j++;
            } else {
                swap(nums, j, k--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void printArray(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] ret = new int[] {0, 1, 1, 0, 2, 1};
        sortColors.sortColors(ret);
        sortColors.printArray(ret);
    }
}
