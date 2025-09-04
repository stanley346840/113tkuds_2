import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void LC11_MaxArea_FuelHoliday(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = sc.nextInt();
        }

        System.out.println(maxArea(h));
    }

    public static long maxArea(int[] h) {
        int l = 0, r = h.length - 1;
        long ans = 0;
        while (l < r) {
            long area = (long)(r - l) * Math.min(h[l], h[r]);
            ans = Math.max(ans, area);
            if (h[l] < h[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}
