class Solution {
    public String countAndSay(int n) {
        String result = "1"; // base case

        for (int i = 2; i <= n; i++) {
            result = encode(result);
        }

        return result;
    }

    // 對字串做 RLE
    private String encode(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }

        return sb.toString();
    }
}
