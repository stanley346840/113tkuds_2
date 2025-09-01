public class ReverseInteger {
    public static int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // 檢查正向溢出
            if (res > Integer.MAX_VALUE / 10 || 
               (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // 檢查負向溢出
            if (res < Integer.MIN_VALUE / 10 || 
               (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            res = res * 10 + pop;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));   // 321
        System.out.println(reverse(-123));  // -321
        System.out.println(reverse(120));   // 21
        System.out.println(reverse(1534236469)); // 0 (溢出)
    }
}
