package medium.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. Permutations
 * Two solutions:
 * Solution1:
 * - Swap each letter to the first position. Then partition first letter from the rest of the array
 * - repeat first step using the rest of the array
 * - terminate till the length of the array is 1
 * This solution doesn't need any extra space. Everything is in place.
 *
 * Solution 2: (preserve the order)
 * - Sort the array
 * - recursively construct the array so that we always pick the first int if it is not used.
 * - terminate till the length of the result array equals the length of the input array.
 */
public class Permutations {
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        helper1(nums, 0, result);
        return result;
    }

    private void helper1(int[] nums, int idx, List<List<Integer>> result) {
        if (idx == nums.length - 1) {
            List<Integer> temp = toArrayList(nums);
            result.add(temp);
            return;
        }
        for(int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            helper1(nums, idx + 1, result);
            swap(nums, idx, i);
        }
    }

    private List<Integer> toArrayList(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i : nums) {
            result.add(i);
        }
        return result;
    }

    private void swap(int[] nums, int idx, int i) {
        int temp = nums[idx];
        nums[idx] = nums[i];
        nums[i] = temp;
    }

    public List<List<Integer>> permute2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        boolean[] used = new boolean[nums.length];
        List<Integer> temp = new ArrayList<>();
        helper2(nums, used, temp, result);
        return result;
    }

    private void helper2(int[] nums, boolean[] used, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                temp.add(nums[i]);
                used[i] = true;
                helper2(nums, used, temp, result);
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    private void printResult(List<List<Integer>> result) {
        for (List<Integer> r : result) {
            for (Integer n : r) {
                System.out.print(n);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
//        p.printResult(p.permute1(new int[] {1, 2, 3}));
        p.printResult(p.permute2(new int[] {1,2,3}));
    }
}
