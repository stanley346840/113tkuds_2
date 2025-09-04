import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void LC03_NoRepeat_TaipeiMetroTap(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;  // 左界右移
            }

            map.put(c, right);
            ans = Math.max(ans, right - left + 1);
        }

        System.out.println(ans);
    }
}
