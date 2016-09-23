package hard.tree;

/**
 * 124. Binary Tree Maximum Path Sum
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.
 *
 * Example:
 * Given the below binary tree,
 *
 *   1
 *  / \
 * 2   3
 *
 * Return 6.
 *
 *  Solution:
 *  Bottom up recursion solution.
 *  At each root, check left branch and right branch. If neither left solution + root.val nor right solution + root.val
 *  is bigger than 0, we shall return 0. If one of them is bigger than 0, return the maximum of (left, right, left + right - root.val, histotyMax).
 *  (Note: here we have left + right - root.val because as shown in the example, the total value should be left branch + right branch plus root.val (once).
 *  But in my solution, left = left_solution + root.val and right = right_solution + root.val. So left + right has two root.val.)
 *  res is an integer array that stores the maximum value so far. It has to be an integer array rather than a int because we want to
 *  retrieve and update value across different recursion level. Integer, on the other hand, will be initiate as a new value
 *  at every recursion level, which defeats our main purpose of updating the this value.
 *
 *  This problem is very similar to one of the DP problem Maximum Subarray [https://leetcode.com/problems/maximum-subarray/]
 *
 */
public class BinaryTreeMaximumPathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxPathSum(TreeNode root) {
        // Write your solution here.
        int[] res = new int[]{Integer.MIN_VALUE};
        helper(root, res);
        return res[0];
    }

    private int helper(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = root.val + helper(root.left, res);
        int right = root.val + helper(root.right, res);
        if (left <=0 && right <= 0) {
            res[0] = Math.max(res[0],root.val);
            return 0;
        }
        int sum = left + right - root.val;
        left = Math.max(left, right);
        sum = Math.max(left, sum);
        res[0] = Math.max(res[0], sum);
        return left;
    }
}
