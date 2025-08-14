
import java.util.*;

class Leaderboard {
    class Node {
        String name;
        int score;
        Node left, right;
        int height, size; // size = 子樹節點數量

        Node(String name, int score) {
            this.name = name;
            this.score = score;
            height = 1;
            size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>();

    // ===== 工具 =====
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    private void update(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node.size = size(node.left) + size(node.right) + 1;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // ===== 旋轉 =====
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        update(y);
        update(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        update(x);
        update(y);
        return y;
    }

    private Node rebalance(Node node) {
        update(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0)
                return rotateRight(node); // LL
            else {
                node.left = rotateLeft(node.left); // LR
                return rotateRight(node);
            }
        }
        if (balance < -1) {
            if (getBalance(node.right) <= 0)
                return rotateLeft(node); // RR
            else {
                node.right = rotateRight(node.right); // RL
                return rotateLeft(node);
            }
        }
        return node;
    }

    // ===== 插入（分數由高到低排序，分數相同以名字排序）=====
    private Node insert(Node node, String name, int score) {
        if (node == null) return new Node(name, score);

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0))
            node.left = insert(node.left, name, score);
        else
            node.right = insert(node.right, name, score);

        return rebalance(node);
    }

    // ===== 刪除玩家 =====
    private Node delete(Node node, String name, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0))
            node.left = delete(node.left, name, score);
        else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0))
            node.right = delete(node.right, name, score);
        else {
            // 找到要刪的節點
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node successor = minValueNode(node.right);
            node.name = successor.name;
            node.score = successor.score;
            node.right = delete(node.right, successor.name, successor.score);
        }

        return rebalance(node);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // ===== 公共 API =====
    public void addOrUpdate(String name, int score) {
        if (playerScores.containsKey(name)) {
            int oldScore = playerScores.get(name);
            root = delete(root, name, oldScore); // 刪除舊紀錄
        }
        root = insert(root, name, score);
        playerScores.put(name, score);
    }

    public int getRank(String name) {
        if (!playerScores.containsKey(name)) return -1;
        return getRank(root, name, playerScores.get(name));
    }

    private int getRank(Node node, String name, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            return getRank(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            return size(node.left) + 1 + getRank(node.right, name, score);
        } else {
            return size(node.left) + 1;
        }
    }

    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topK(root, k, result);
        return result;
    }

    private void topK(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        topK(node.left, k, result);
        if (result.size() < k) result.add(node.name + " (" + node.score + ")");
        topK(node.right, k, result);
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();
        lb.addOrUpdate("Alice", 100);
        lb.addOrUpdate("Bob", 200);
        lb.addOrUpdate("Charlie", 150);
        lb.addOrUpdate("David", 180);

        System.out.println("Top 3: " + lb.topK(3));
        System.out.println("Alice 排名: " + lb.getRank("Alice"));
        System.out.println("Bob 排名: " + lb.getRank("Bob"));

        lb.addOrUpdate("Alice", 210); // 更新分數
        System.out.println("更新後 Top 3: " + lb.topK(3));
        System.out.println("Alice 排名: " + lb.getRank("Alice"));
    }
}

