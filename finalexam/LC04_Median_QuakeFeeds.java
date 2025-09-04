import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void LC04_Median_QuakeFeeds(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();

        if (n > m) { // 保證 A 是短的
            double[] tmp = A; A = B; B = tmp;
            int t = n; n = m; m = t;
        }

        int total = n + m;
        int half = (total + 1) / 2;
        int l = 0, r = n;

        while (l <= r) {
            int i = (l + r) / 2;
            int j = half - i;

            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                double median;
                if (total % 2 == 1) {
                    median = Math.max(Aleft, Bleft);
                } else {
                    median = (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
                System.out.printf("%.1f\n", median);
                return;
            } else if (Aleft > Bright) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
    }
}
