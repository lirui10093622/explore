package interview;

/**
 * Created by neil on 2025/1/10.
 * leecode: https://leetcode.cn/problems/path-sum/
 */
public class TreePathSum {

    public static void main(String[] args) {


    }

    public boolean hasPathSum(Node node, int targetSum) {
        if (node == null) return false;

        if (node.left == null && node.right == null) {
            return targetSum == node.val;
        }

        boolean result = false;

        if (node.left != null) {
            result = hasPathSum(node.left, targetSum - node.val);
        }

        if (!result && node.right != null) {
            result = hasPathSum(node.right, targetSum - node.val);
        }

        return result;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
    }
}

