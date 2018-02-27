package medium.permutation;

import java.util.*;

/**
 * 47. Permutations II
 * - Similiar to Permutations I but at each recursive layer add a HashSet to record which letters we have encountered.
 * There are also two solutions to this problem. I will only write out the sorted one.
 */
public class PermutationII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        boolean[] used = new boolean[nums.length];
        helper(nums, new ArrayList<Integer>(), used, result);
        return result;
    }

    private void helper(int[] nums, List<Integer> temp, boolean[] used, List<List<Integer>> result) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!used[i] && set.add(nums[i])) {
                //Note: since Java used short-circuit evaluation, it will not execute set.add unless !used[i] is true.
                //Therefore, here we first correct our index to the first letter of the remaining array,
                //then use hashset to check if the letter has duplicates or not
                temp.add(nums[i]);
                used[i] = true;
                helper(nums, temp, used, result);
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
        PermutationII p = new PermutationII();
        p.printResult(p.permuteUnique(new int[] {1, 1, 2}));
    }

}
