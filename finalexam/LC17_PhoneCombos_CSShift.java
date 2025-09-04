import java.util.*;

public class LC17_PhoneCombos_CSShift {
    // 2~9 的對應表，利用 digits.charAt(idx) - '2' 做索引
    static final String[] MAP = {
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNextLine() ? sc.nextLine().trim() : "";
        if (digits.isEmpty()) return; // 空字串：不輸出任何行

        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb);
    }

    private static void dfs(String digits, int idx, StringBuilder sb) {
        if (idx == digits.length()) {
            System.out.println(sb.toString());
            return;
        }
        int mapIdx = digits.charAt(idx) - '2'; // '2'..'9' -> 0..7
        String letters = MAP[mapIdx];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            dfs(digits, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
