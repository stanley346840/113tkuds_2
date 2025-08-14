class AVLDeleteExercise {
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

    // ===== 刪除 =====
    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // 找到要刪的節點
            if (node.left == null && node.right == null) {
                return null; // 1. 葉子節點
            } else if (node.left == null) {
                return node.right; // 2. 只有右子節點
            } else if (node.right == null) {
                return node.left; // 2. 只有左子節點
            } else {
                // 3. 兩個子節點 → 找後繼(右子樹最小值)
                Node successor = minValueNode(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }
        }

        return rebalance(node);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // ===== 輸出 =====
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
        AVLDeleteExercise tree = new AVLDeleteExercise();

        // 插入測試
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) tree.insert(v);

        System.out.print("初始中序: ");
        tree.inorder();

        // 刪除葉子節點
        System.out.println("刪除葉子節點 25");
        tree.delete(25);
        tree.inorder();

        // 刪除只有一個子節點的節點
        System.out.println("刪除只有一個子節點的節點 50");
        tree.delete(50);
        tree.inorder();

        // 刪除有兩個子節點的節點
        System.out.println("刪除有兩個子節點的節點 30");
        tree.delete(30);
        tree.inorder();
    }
}
