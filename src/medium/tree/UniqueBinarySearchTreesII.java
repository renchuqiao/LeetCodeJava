package medium.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *
 * 1         3     3      2      1
 *  \       /     /      / \     \
 *  3     2      1      1   3     2
 * /     /       \                 \
 * 2    1        2                 3
 *
 *
 * Solution (Recursion):
 * Note that we want to construct BST from its in-order traversal.
 * For a given i in [1..n], we notice that [1, i - 1] is the left-subtree and [i + 1, n] is the right-subtree.
 * The helper function will return a list of root nodes (with subtree properly assigned).
 * In the recursive body of the helper function, we need to iterate through every combinations of the left and right subtrees
 * And then assign left-subtrees and right-subtrees to the root. And then add root to the list and return it to the upper level.
 * This function update list every time it returned to the upper level. Therefore, we don't need to worry about
 * deleting nodes that is not root from this list, and this list correctly returns every roots from the lower level in the
 * recursive stack.
 *
 */
public class UniqueBinarySearchTreesII {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //The following is the recursive solution
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return generateTrees(1, n);
    }

    //Recursive solution helper function
    private List<TreeNode> generateTrees(int start, int end) {
        //base case
        List<TreeNode> ret = new ArrayList<>();
        if (start > end) {
            ret.add(null);
            return ret;
        }

        if (start == end) {
            ret.add(new TreeNode(start));
            return ret;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTrees(start, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ret.add(root);
                }
            }
        }

        return ret;
    }
}
