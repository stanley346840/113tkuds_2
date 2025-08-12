import java.util.*;

class Task {
    String name;
    int priority;
    long timestamp; // 用來記錄加入時間，解決相同優先級先來先處理

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return name + "(優先級:" + priority + ")";
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> queue;
    private long timeCounter = 0; // 簡單遞增時間戳
    private Map<String, Task> taskMap; // 快速找到任務以修改優先級

    public PriorityQueueWithHeap() {
        // 比較器: 優先級大的排前面，若相同則 timestamp 小的排前面
        queue = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) {
                return b.priority - a.priority;
            } else {
                return Long.compare(a.timestamp, b.timestamp);
            }
        });
        taskMap = new HashMap<>();
    }

    // 添加任務
    public void addTask(String name, int priority) {
        Task task = new Task(name, priority, timeCounter++);
        queue.offer(task);
        taskMap.put(name, task);
    }

    // 執行最高優先級任務
    public Task executeNext() {
        Task task = queue.poll();
        if (task != null) {
            taskMap.remove(task.name);
        }
        return task;
    }

    // 查看下一個要執行的任務
    public Task peek() {
        return queue.peek();
    }

    // 修改優先級
    public void changePriority(String name, int newPriority) {
        Task oldTask = taskMap.get(name);
        if (oldTask != null) {
            // 從 queue 移除舊任務
            queue.remove(oldTask);
            // 建立新任務，更新時間戳以保留原來的先來順序 (不重置 timeCounter)
            Task newTask = new Task(name, newPriority, oldTask.timestamp);
            queue.offer(newTask);
            taskMap.put(name, newTask);
        }
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap tq = new PriorityQueueWithHeap();

        tq.addTask("備份", 1);
        tq.addTask("緊急修復", 5);
        tq.addTask("更新", 3);

        System.out.println("下一個任務: " + tq.peek()); // 緊急修復
        System.out.println("執行: " + tq.executeNext()); // 緊急修復
        System.out.println("執行: " + tq.executeNext()); // 更新
        System.out.println("執行: " + tq.executeNext()); // 備份

        // 測試 changePriority
        tq.addTask("測試", 2);
        tq.addTask("日誌清理", 4);
        tq.changePriority("測試", 6); // 提升 "測試" 優先級
        System.out.println("下一個任務: " + tq.peek()); // 測試
    }
}
