package medium.array;

/**
 * 2340. Minimum Adjacent Swaps to Make a Valid Array
 */
public class MinimumAdjacentSwapsToMakeAValidArray {

    public int minimumSwaps(int[] nums) {
        //Find the min number distance to the leftmost and find the max number distance to the rightmost
        //We need to record the max and min index to determine if there is any overlap step
        int min_dis = 0, max_dis = 0, min_ind = 0, max_ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[min_ind]) {
                min_dis = i;
                min_ind = i;
            }
            if (nums[i] >= nums[max_ind]) {
                max_dis = nums.length - i - 1;
                max_ind = i;
            }
        }
        // In case of [5, 2, 1], we need to swap 5 to the rightmost and swap 1 to the leftmost, and there will be one overlap swap when we try to swap 5 and 1
        // e.g. at the time (2, 5, 1) or (5, 1, 2)
        // We need to subtract the redundant step from distance.
        return min_ind > max_ind ? min_dis + max_dis - 1 : min_dis + max_dis;
    }

    public static void main(String[] args) {
        MinimumAdjacentSwapsToMakeAValidArray m = new MinimumAdjacentSwapsToMakeAValidArray();
        System.out.println(m.minimumSwaps(new int[]{2,1}));
    }
}
