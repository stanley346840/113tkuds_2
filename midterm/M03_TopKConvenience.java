import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q) { name = n; qty = q; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            list.add(new Item(name, qty));
        }

        // 依銷量降序排序
        list.sort((a, b) -> b.qty - a.qty);

        for (int i = 0; i < Math.min(K, list.size()); i++) {
            System.out.println(list.get(i).name + " " + list.get(i).qty);
        }
    }
}


