package medium.dynamicProgramming;

/**
 * 300. Longest Increasing Subsequence
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * For example,
 *
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 *
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *
 * Your algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * Solution:
 * Dynamic Programming with Binary Search
 *
 * The number in helper array is sorted. The first entry is deprecated.
 * Find the first entry in the helper array that is larger than or equal to the input, and replace it with input.
 * Output the index of this entry.
 * If not found, append input to the very end of this helper array.
 *
 * input: (7, 1, 6, 8, 8, 2, 4)
 *
 * input 7:
 * helper[]: null, 7,
 * result[]:       1
 *
 * input 1:
 * helper[]: null, 1
 * result[]:       1, 1
 *
 * input 6:
 * helper[]: null, 1, 6
 * result[]:       1, 1, 2
 *
 * input 8:
 * helper[]: null, 1, 6, 8
 * result[]:       1, 1, 2, 3
 *
 * input 8:
 * helper[]: null, 1, 6, 8
 * result[]:       1, 1, 2, 3, 3
 *
 * input 2:
 * helper[]: null, 1, 2, 8
 * result[]:       1, 1, 2, 3, 3, 2
 *
 * input 4:
 * helper[]: null, 1, 2, 4
 * result[]:       1, 1, 2, 3, 3, 2, 3
 *
 * final output: 3 (max in result[])
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int result = 0;
        int[] help = new int[len + 1];
        int end = 0;
        for (int i = 0; i < len; i++) {
            int tmp = binarySearch(help, end, nums[i]);
            System.out.println(tmp);
            end = Math.max(end, tmp);
            result = Math.max(result, tmp);
        }
        return result;
    }

    public int binarySearch(int[] arr, int end, int target) {
        if (end == 0) {
            arr[++end] = target;
            return end;
        }
        int left = 1;
        int right = end;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid;
            } else if (arr[mid] == target) {
                return mid;
            } else {
                right = mid;
            }
        }

        //Here we want to add equals case if we skip the while loop
        if (arr[left] >= target) {
            arr[left] = target;
            return left;
        }
        if (arr[right] <= target) {
            arr[++right] = target;
            return right;
        }
        arr[right] = target;
        return right;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        System.out.println(l.lengthOfLIS(new int[] {4,10,4,3,8,9}));
    }
}
