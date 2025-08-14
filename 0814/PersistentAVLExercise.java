import java.util.*;

class PersistentAVLTree {
    static class Node {
        final int key;        // 玩家分數 (唯一)
        final String name;    // 玩家名稱
        final int height;     
        final int size;       // 子樹節點數，用於排名
        final Node left, right;

        Node(int key, String name, Node left, Node right) {
            this.key = key;
            this.name = name;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
            this.size = 1 + size(left) + size(right);
        }

        static int height(Node n) { return n == null ? 0 : n.height; }
        static int size(Node n) { return n == null ? 0 : n.size; }
    }

    private final List<Node> versions = new ArrayList<>(); // 版本列表

    public PersistentAVLTree() {
        versions.add(null); // 版本 0: 空樹
    }

    // --------- 插入（產生新版本）---------
    public void insert(int score, String name) {
        Node newRoot = insert(versions.get(versions.size() - 1), score, name);
        versions.add(newRoot);
    }

    private Node insert(Node node, int key, String name) {
        if (node == null) return new Node(key, name, null, null);
        if (key < node.key) node = new Node(node.key, node.name, insert(node.left, key, name), node.right);
        else if (key > node.key) node = new Node(node.key, node.name, node.left, insert(node.right, key, name));
        else return node; // 分數相同不處理
        return balance(node);
    }

    // --------- 刪除（產生新版本）---------
    public void delete(int key) {
        Node newRoot = delete(versions.get(versions.size() - 1), key);
        versions.add(newRoot);
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;

        if (key < node.key) node = new Node(node.key, node.name, delete(node.left, key), node.right);
        else if (key > node.key) node = new Node(node.key, node.name, node.left, delete(node.right, key));
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = minNode(node.right);
            node = new Node(successor.key, successor.name, node.left, deleteMin(node.right));
        }
        return balance(node);
    }

    private Node minNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        return balance(new Node(node.key, node.name, deleteMin(node.left), node.right));
    }

    // --------- 平衡處理 ---------
    private Node balance(Node node) {
        int balanceFactor = Node.height(node.left) - Node.height(node.right);
        if (balanceFactor > 1) {
            if (Node.height(node.left.left) >= Node.height(node.left.right))
                return rotateRight(node); // 右旋
            else
                return rotateLeftRight(node); // 左右旋
        } else if (balanceFactor < -1) {
            if (Node.height(node.right.right) >= Node.height(node.right.left))
                return rotateLeft(node); // 左旋
            else
                return rotateRightLeft(node); // 右左旋
        }
        return node;
    }

    // 左旋
    private Node rotateLeft(Node y) {
        Node x = y.right;
        return new Node(x.key, x.name, new Node(y.key, y.name, y.left, x.left), x.right);
    }

    // 右旋
    private Node rotateRight(Node y) {
        Node x = y.left;
        return new Node(x.key, x.name, x.left, new Node(y.key, y.name, x.right, y.right));
    }

    // 左右旋
    private Node rotateLeftRight(Node node) {
        return rotateRight(new Node(node.key, node.name, rotateLeft(node.left), node.right));
    }

    // 右左旋
    private Node rotateRightLeft(Node node) {
        return rotateLeft(new Node(node.key, node.name, node.left, rotateRight(node.right)));
    }

    // --------- 範圍查詢 ---------
    public List<String> rangeQuery(int min, int max, int version) {
        List<String> result = new ArrayList<>();
        rangeQuery(versions.get(version), min, max, result);
        return result;
    }

    private void rangeQuery(Node node, int min, int max, List<String> result) {
        if (node == null) return;
        if (node.key > min) rangeQuery(node.left, min, max, result);
        if (node.key >= min && node.key <= max) result.add(node.name + "(" + node.key + ")");
        if (node.key < max) rangeQuery(node.right, min, max, result);
    }

    // --------- 排名功能 ---------
    public int getRank(int key, int version) {
        return getRank(versions.get(version), key);
    }

    private int getRank(Node node, int key) {
        if (node == null) return 0;
        if (key < node.key) return getRank(node.left, key);
        if (key > node.key) return Node.size(node.left) + 1 + getRank(node.right, key);
        return Node.size(node.left) + 1;
    }

    public List<String> topK(int k, int version) {
        List<String> result = new ArrayList<>();
        collectTopK(versions.get(version), k, result);
        return result;
    }

    private void collectTopK(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        collectTopK(node.right, k, result);
        if (result.size() < k) result.add(node.name + "(" + node.key + ")");
        collectTopK(node.left, k, result);
    }

    // --------- 測試 ---------
    public static void main(String[] args) {
        PersistentAVLTree tree = new PersistentAVLTree();

        tree.insert(50, "Alice");
        tree.insert(30, "Bob");
        tree.insert(70, "Charlie");
        tree.insert(20, "David");
        tree.insert(40, "Eve");
        tree.insert(60, "Frank");
        tree.insert(80, "Grace");

        System.out.println("版本 7 Top 3: " + tree.topK(3, 7));
        System.out.println("範圍查詢 35~75 (版本7): " + tree.rangeQuery(35, 75, 7));

        tree.delete(70); // 版本 8
        System.out.println("刪除 70 後 Top 3 (版本8): " + tree.topK(3, 8));

        System.out.println("版本 7 排名 Alice(50): " + tree.getRank(50, 7));
    }
}

