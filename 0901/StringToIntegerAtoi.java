public class StringToIntegerAtoi {
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // Step 1: 忽略前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: 處理正負號
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: 讀取數字
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Step 4: 檢查溢出
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));        // 42
        System.out.println(myAtoi("   -042"));   // -42
        System.out.println(myAtoi("1337c0d3"));  // 1337
        System.out.println(myAtoi("0-1"));       // 0
        System.out.println(myAtoi("words 987")); // 0
        System.out.println(myAtoi("91283472332")); // 2147483647 (overflow)
    }
}
