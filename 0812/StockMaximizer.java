import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int n = prices.length;
        int i = 0;

        while (i < n - 1) {
            // 找局部低點（買入點）
            while (i < n - 1 && prices[i] >= prices[i + 1]) i++;
            int buy = prices[i];

            // 找局部高點（賣出點）
            while (i < n - 1 && prices[i] <= prices[i + 1]) i++;
            int sell = prices[i];

            if (sell > buy) {
                maxHeap.offer(sell - buy);
            }
        }

        // 取前 K 筆最大獲利
        int profit = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            profit += maxHeap.poll();
            k--;
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2,4,1})); // 2
        System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3})); // 7
        System.out.println(maxProfit(2, new int[]{1,2,3,4,5})); // 4
    }
}
