import java.util.*;

public class MeetingRoomScheduler {

    // ---------------- Part 1: 最少會議室數量 ----------------
    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 存結束時間

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll(); // 重用會議室
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    // ---------------- Part 2: N 間會議室最大總會議時間 ----------------
    public static int maxMeetingTime(int[][] meetings, int roomCount) {
        if (meetings.length == 0 || roomCount == 0) return 0;

        // 按開始時間排序，方便分配會議室
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Min Heap: 每個會議室的結束時間
        PriorityQueue<int[]> rooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Max Heap: 待選會議（依長度排序）
        PriorityQueue<int[]> waiting = new PriorityQueue<>((a, b) -> {
            int lenA = a[1] - a[0];
            int lenB = b[1] - b[0];
            return lenB - lenA;
        });

        int totalTime = 0;

        for (int[] meeting : meetings) {
            // 釋放已經結束的會議室
            while (!rooms.isEmpty() && rooms.peek()[0] <= meeting[0]) {
                rooms.poll();
            }

            if (rooms.size() < roomCount) {
                // 有空房間直接放
                rooms.offer(new int[]{meeting[1], meeting[0]});
                totalTime += meeting[1] - meeting[0];
            } else {
                // 如果沒有空房間，看是否能替換掉短的會議
                int[] shortest = rooms.peek();
                int currentLen = shortest[0] - shortest[1];
                int newLen = meeting[1] - meeting[0];
                if (newLen > currentLen) {
                    rooms.poll();
                    totalTime -= currentLen;
                    rooms.offer(new int[]{meeting[1], meeting[0]});
                    totalTime += newLen;
                }
            }
        }

        return totalTime;
    }

    // ---------------- 測試 ----------------
    public static void main(String[] args) {
        // Part 1 測試
        System.out.println("最少會議室數量:");
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));  // 2
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));    // 2

        // Part 2 測試
        System.out.println("\nN 間會議室最大總時間:");
        System.out.println(maxMeetingTime(new int[][]{{1,4},{2,3},{4,6}}, 1)); // 5
        System.out.println(maxMeetingTime(new int[][]{{1,4},{2,5},{5,8}}, 2)); // 9
    }
}
