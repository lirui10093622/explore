package org.experiment.dubbo.demo.provider;

/**
 * Created by neil on 2024/12/27.
 */
public class Test2 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return root != null && judge(targetSum, 0, root);
    }

    private boolean judge(int targetSum, int ancestralSum, TreeNode currentNode) {
        boolean result = false;
        if (isLeafNode(currentNode)) {
            result = targetSum == ancestralSum + currentNode.val;
        }

        if (!result && currentNode.left != null) {
            result = judge(targetSum, ancestralSum + currentNode.val, currentNode.left);
        }

        if (!result && currentNode.right != null) {
            result = judge(targetSum, ancestralSum + currentNode.val, currentNode.right);
        }
        return result;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}