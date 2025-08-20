import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class M09_AVLValidate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (!isAVL(root).isBalanced) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
    }

    // 建樹
    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();
            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.add(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    // BST 驗證
    static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // AVL 驗證
    static class AVLInfo {
        boolean isBalanced;
        int height;
        AVLInfo(boolean b, int h) { isBalanced = b; height = h; }
    }

    static AVLInfo isAVL(TreeNode node) {
        if (node == null) return new AVLInfo(true, 0);
        AVLInfo L = isAVL(node.left);
        AVLInfo R = isAVL(node.right);
        boolean balanced = L.isBalanced && R.isBalanced && Math.abs(L.height - R.height) <= 1;
        int h = Math.max(L.height, R.height) + 1;
        return new AVLInfo(balanced, h);
    }
}


