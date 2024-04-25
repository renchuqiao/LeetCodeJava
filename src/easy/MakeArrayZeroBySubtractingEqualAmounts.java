package easy;

import java.util.HashSet;

/**
 * 2357. Make Array Zero by Subtracting Equal Amounts
 * Very intuitive solution
 * The number of operations is the number of distinct non-zero numbers in the array.
 */
public class MakeArrayZeroBySubtractingEqualAmounts {
    public int minimumOperations(int[] nums) {
        //Check unique numbers
        HashSet<Integer> u = new HashSet<>();
        for (int a : nums) {
            if (a == 0) continue;
            u.add(a);
        }
        return u.size();
    }

    public static void main(String[] args) {

    }
}
