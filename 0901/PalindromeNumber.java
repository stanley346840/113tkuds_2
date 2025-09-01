public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        // 負數或尾數為 0 但不是 0 本身 -> false
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int original = x;
        int reversed = 0;

        while (x > 0) {
            int digit = x % 10;
            x /= 10;
            // 檢查是否溢出
            if (reversed > (Integer.MAX_VALUE - digit) / 10) {
                return false; // 溢出就不可能是迴文
            }
            reversed = reversed * 10 + digit;
        }

        return original == reversed;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));  // true
        System.out.println(isPalindrome(-121)); // false
        System.out.println(isPalindrome(10));   // false
    }
}
