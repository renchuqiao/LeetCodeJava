package medium.array;

/**
 * 209. Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 *
 * the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Solution:
 * Method 1 O(N):
 * Two pointer
 * Much like 76. Minimum Window Substring
 * fast pointer first set off to reach as far as it can, and then set off the slow pointer to minimize the size of
 * the array while maintain that sum >= s. Once sum < s, then we set off the fast pointer again trying to bring up the
 * sum once again. If the sum cannot achieve s, at the end, we need to determine if we found or not a minimum size array
 *
 * Method 2 O(NlogN):
 * Use binary search
 * First calculate the cumulative sum.
 * The array of cumulative sum is increasing, because all number in the array are positive.
 * Therefore, we can do a binary search (much like a binary indexed tree) to find the range. (Similar to RangeSumQueryImmutable)
 *
 *
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int ret = Integer.MAX_VALUE;
        int curSum = 0;
        int slow = 0; int fast = 0;


        while (fast < nums.length) {
            while (curSum < s && fast < nums.length) {
                curSum += nums[fast++];
            }
            if (curSum < s) {
                break;
            }

            while (slow < fast && curSum >=s) {
                curSum -= nums[slow++];
            }

            ret = Math.min(ret, fast - slow + 1);
        }

        return ret == Integer.MAX_VALUE ? 0 : ret;

    }

    /*
    The following code are answer from https://discuss.leetcode.com/topic/13749/two-ac-solutions-in-java-with-time-complexity-of-n-and-nlogn-with-explanation
     */

    public int minSubArrayLenSolveLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (sums[mid] >= key){
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum m = new MinimumSizeSubarraySum();
        System.out.println(m.minSubArrayLen(80, new int[] {10,5,13,4,8,4,5,11,14,9,16,10,20,8}));
    }

}
