import java.util.*;

class AVLTree {
    class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    private Node root;

    // ===== 工具方法 =====
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // ===== 旋轉操作 =====
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node rotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private Node rotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    // ===== 平衡節點 =====
    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node); // LL
            } else {
                return rotateLeftRight(node); // LR
            }
        } else if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node); // RR
            } else {
                return rotateRightLeft(node); // RL
            }
        }
        return node;
    }

    // ===== 插入 =====
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; // 不允許重複
        return rebalance(node);
    }

    // ===== 範圍查詢 =====
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    private void rangeQuery(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 左邊還有可能在範圍內
        if (node.key > min) {
            rangeQuery(node.left, min, max, result);
        }

        // 當前節點在範圍內
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 右邊還有可能在範圍內
        if (node.key < max) {
            rangeQuery(node.right, min, max, result);
        }
    }

    // ===== 中序輸出 =====
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {10, 20, 30, 40, 50, 25, 5, 35};
        for (int v : values) tree.insert(v);

        System.out.print("中序遍歷: ");
        tree.inorder();

        System.out.println("範圍查詢 [15, 40]: " + tree.rangeQuery(15, 40));
        System.out.println("範圍查詢 [5, 10]: " + tree.rangeQuery(5, 10));
        System.out.println("範圍查詢 [45, 60]: " + tree.rangeQuery(45, 60));
    }
}
