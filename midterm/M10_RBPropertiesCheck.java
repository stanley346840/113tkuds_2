import java.util.*;

class Node {
    int val;
    char color;
    Node left, right;
    Node(int v, char c) {
        val = v;
        color = c;
    }
}

public class M10_RBPropertiesCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        char[] colors = new char[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            String c = sc.next();
            colors[i] = vals[i] == -1 ? 'B' : c.charAt(0); // null 視為黑
        }

        Node root = buildTree(vals, colors);
        System.out.println(checkRB(root));
    }

    static Node buildTree(int[] vals, char[] colors) {
        if (vals.length == 0 || vals[0] == -1) return null;
        Node root = new Node(vals[0], colors[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            Node node = q.poll();
            // left child
            if (i < vals.length && vals[i] != -1) {
                node.left = new Node(vals[i], colors[i]);
                q.add(node.left);
            }
            i++;
            // right child
            if (i < vals.length && vals[i] != -1) {
                node.right = new Node(vals[i], colors[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    static String checkRB(Node root) {
        if (root == null) return "RB Valid";

        // 1) 根節點必須黑
        if (root.color != 'B') return "RootNotBlack";

        // 2) 紅紅相鄰 + 3) 黑高度一致
        Result res = dfs(root, 0);
        if (!res.isValid) return res.msg;
        return "RB Valid";
    }

    static class Result {
        boolean isValid;
        String msg;
        int blackHeight;
        Result(boolean v, String m, int h) {
            isValid = v;
            msg = m;
            blackHeight = h;
        }
    }

    static Result dfs(Node node, int index) {
        if (node == null) return new Result(true, "", 1); // NIL 黑高度=1

        // 紅紅相鄰檢查
        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R') ||
                (node.right != null && node.right.color == 'R')) {
                return new Result(false, "RedRedViolation at index " + index, 0);
            }
        }

        Result left = dfs(node.left, index*2+1);
        if (!left.isValid) return left;

        Result right = dfs(node.right, index*2+2);
        if (!right.isValid) return right;

        // 黑高度檢查
        if (left.blackHeight != right.blackHeight) {
            return new Result(false, "BlackHeightMismatch", 0);
        }

        int bh = left.blackHeight + (node.color == 'B' ? 1 : 0);
        return new Result(true, "", bh);
    }
}
