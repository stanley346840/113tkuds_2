import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void LC01_TwoSum_THSRHoliday(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long target = sc.nextLong();
        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long need = target - seats[i];
            if (map.containsKey(need)) {
                System.out.println(map.get(need) + " " + i);
                return;
            }
            map.put(seats[i], i);
        }

        System.out.println("-1 -1");
    }
}
