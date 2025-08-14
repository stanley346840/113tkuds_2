class AVLTreeTest {
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
        System.out.println("執行右旋 (Right Rotation)");
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        System.out.println("執行左旋 (Left Rotation)");
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private Node rotateLeftRight(Node node) {
        System.out.println("執行左右旋 (Left-Right Rotation)");
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private Node rotateRightLeft(Node node) {
        System.out.println("執行右左旋 (Right-Left Rotation)");
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    // ===== 插入節點（觸發旋轉）=====
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; // 不允許重複

        updateHeight(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        // LR
        if (balance > 1 && key > node.left.key)
            return rotateLeftRight(node);

        // RL
        if (balance < -1 && key < node.right.key)
            return rotateRightLeft(node);

        return node;
    }

    // ===== 輔助輸出 =====
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

    public static void main(String[] args) {
        AVLTreeTest tree = new AVLTreeTest();

        System.out.println("=== 測試 LL: 右旋 ===");
        tree.insert(30);
        tree.insert(20);
        tree.insert(10); // LL

        tree.root = null; // 清空

        System.out.println("\n=== 測試 RR: 左旋 ===");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // RR

        tree.root = null; // 清空

        System.out.println("\n=== 測試 LR: 左右旋 ===");
        tree.insert(30);
        tree.insert(10);
        tree.insert(20); // LR

        tree.root = null; // 清空

        System.out.println("\n=== 測試 RL: 右左旋 ===");
        tree.insert(10);
        tree.insert(30);
        tree.insert(20); // RL
    }
}
