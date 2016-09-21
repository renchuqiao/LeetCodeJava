package hard.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 145. Binary Tree Postorder Traversal
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 * Given binary tree {1,#,2,3},
 *   1
 *    \
 *    2
 *   /
 *  3
 *
 * return [3,2,1].
 *
 * Solution:
 * Similar to our initial naive thought, where we put node into stack with order root, right, left. But in this naive way
 * we cannot easily determine if right child is iterated or not. Therefore, the we want to push in order of right, root, left.
 * when we are trying to determine whether we have iterated through right sub-tree or not, we can easily use our information in stack.
 * That is, if stack.peek is the right child of current node (after we pop this current node), then we are sure that this child
 * along with its subtree has not iterated yet. Therefore, we want to nominate this right child to be the new root and iterate the
 * right sub-tree in the same manner. (before this step, we want to first push current node, in order to keep track of old root. That is, in this way,
 * we swap right node and root in stack (order now is root, right, left))
 *
 * In more details:
 * 1. push root.right (if exists) and root, then root.left becomes root. do this until root.left doesn't exists.
 * 2. pop stack (curNode). If curNode has right child and its right child equals stack.peek, we put curNode back to stack, and now root becomes
 * right child. repeat step 1. If curNode doesn't have right child or right child doesn't equals stack.peek, we can pop this node,
 * and root becomes null.
 * 3. repeat until stack is empty.
 *
 * Note:
 * function postorderTraversal and makeStack is written by myself
 * function postorderTraversalBetter is a better solution online which used a do-while loop
 *
 */
public class BinaryTreePostOrderTraversal {
    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> ret = new LinkedList<>();
        makeStack(root, stack);
        while (stack.size() > 0) {
            TreeNode curNode = stack.pop();
            if (curNode.right != null && curNode.right == stack.peek()) {
                TreeNode newRoot = stack.pop();
                stack.push(curNode);
                makeStack(newRoot, stack);
            } else {
                ret.add(curNode.val);
            }
        }

        return ret;
    }

    private void makeStack(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            if (root.right != null) {
                stack.push(root.right);
            }
            stack.push(root);
            root = root.left;
        }
    }

    public List<Integer> postorderTraversalBetter(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ret = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curNode;
        do {
            //step 1
            if (root != null) {
                if (root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            } else {
                //Step 2
                curNode = stack.pop();
                if (curNode.right != null && curNode.right == stack.peek()) {
                    root = stack.pop();
                    stack.push(curNode);
                } else {
                    ret.add(curNode.val);
                    root = null;
                }
            }
        } while (stack.size() > 0);
        return ret;
    }

    public static void main(String[] args) {
        BinaryTreePostOrderTraversal binaryTreePostOrderTraversal = new BinaryTreePostOrderTraversal();
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);

        first.right = second;
        second.left = third;

        System.out.println(binaryTreePostOrderTraversal.postorderTraversalBetter(first));
    }
}
