import java.util.*;

public class MultiLevelCacheSystem<K, V> {

    class CacheItem {
        K key;
        V value;
        int freq;
        int level; // 1=L1, 2=L2, 3=L3
        long lastAccessTime;
        public CacheItem(K k, V v, int l, long time) {
            key = k; value = v; freq=1; level=l; lastAccessTime = time;
        }
        double score() {
            return (double) freq / cost[level-1];
        }
    }

    private final int[] capacity = {2,5,10};
    private final int[] cost = {1,3,10};
    private Map<K, CacheItem> map = new HashMap<>();
    private long timestamp = 0;

    // 三個層級 heap，分數低的排在頂端（優先被淘汰）
    private PriorityQueue<CacheItem>[] levels = new PriorityQueue[3];

    public MultiLevelCacheSystem() {
        for(int i=0; i<3; i++) {
            final int idx = i;
            levels[i] = new PriorityQueue<>((a,b) -> {
                int cmp = Double.compare(a.score(), b.score());
                if(cmp != 0) return cmp;
                return Long.compare(a.lastAccessTime, b.lastAccessTime);
            });
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) return null;
        CacheItem item = map.get(key);
        item.freq++;
        item.lastAccessTime = ++timestamp;

        // 調整層級與排序
        adjustLevel(item);
        return item.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            CacheItem item = map.get(key);
            item.value = value;
            get(key); // 更新頻率和位置
            return;
        }
        // 新增放L3
        CacheItem item = new CacheItem(key, value, 3, ++timestamp);
        map.put(key, item);
        addToLevel(item, 2); // index 2是L3

        // 若容量超過，淘汰最低分資料
        evictIfNeeded(2);
    }

    private void addToLevel(CacheItem item, int levelIdx) {
        item.level = levelIdx + 1;
        levels[levelIdx].offer(item);
    }

    private void removeFromLevel(CacheItem item, int levelIdx) {
        levels[levelIdx].remove(item);
    }

    private void adjustLevel(CacheItem item) {
        int currIdx = item.level - 1;
        removeFromLevel(item, currIdx);

        // 向上移動判斷
        while (currIdx > 0) {
            CacheItem lowestUpper = levels[currIdx - 1].peek();
            if (lowestUpper == null || item.score() > lowestUpper.score()) {
                // 交換
                levels[currIdx - 1].poll();
                lowestUpper.level = currIdx + 1;
                levels[currIdx].offer(lowestUpper);
                item.level = currIdx;
                currIdx--;
            } else {
                break;
            }
        }
        // 向下移動判斷
        while (currIdx < 2) {
            CacheItem highestLower = getHighest(levels[currIdx + 1]);
            if (highestLower != null && highestLower.score() > item.score()) {
                levels[currIdx + 1].remove(highestLower);
                highestLower.level = currIdx + 1;
                levels[currIdx].offer(highestLower);

                item.level = currIdx + 2;
                currIdx++;
            } else {
                break;
            }
        }
        levels[currIdx].offer(item);

        // 淘汰過多資料
        for (int i = 0; i < 3; i++) {
            evictIfNeeded(i);
        }
    }

    private void evictIfNeeded(int levelIdx) {
        while (levels[levelIdx].size() > capacity[levelIdx]) {
            CacheItem evicted = levels[levelIdx].poll();
            map.remove(evicted.key);
        }
    }

    // PriorityQueue 沒提供 peek 最大，自己找
    private CacheItem getHighest(PriorityQueue<CacheItem> pq) {
        CacheItem res = null;
        for (CacheItem it : pq) {
            if (res == null || it.score() > res.score() ||
                (it.score() == res.score() && it.lastAccessTime > res.lastAccessTime)) {
                res = it;
            }
        }
        return res;
    }

    // 測試用列印
    public void printStatus() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("L%d: ", i+1);
            List<CacheItem> list = new ArrayList<>(levels[i]);
            list.sort((a,b) -> Double.compare(b.score(), a.score()));
            for (CacheItem item : list) {
                System.out.printf("[%d:%s(freq=%d,score=%.2f)] ", item.key, item.value, item.freq, item.score());
            }
            System.out.println();
        }
    }

    // 測試案例
    public static void main(String[] args) {
        MultiLevelCacheSystem<Integer,String> cache = new MultiLevelCacheSystem<>();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printStatus();  // L1:[2,3], L2:[1], L3:[]

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printStatus();  // 1被提升到L1

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printStatus();
    }
}
