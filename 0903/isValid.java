import java.util.Stack;
import java.util.HashMap;

class Solution {
    public boolean isValid(String s) {
        // 快速處理奇數長度的字串，因為無法配對
        if (s.length() % 2 != 0) {
            return false;
        }

        // 建立一個映射表，將閉合括號對應到開放括號
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        // 使用堆疊來追蹤開放括號
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) { // 如果是閉合括號
                // 檢查堆疊是否為空，或堆疊頂部是否與當前括號配對
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            } else { // 如果是開放括號
                stack.push(ch);
            }
        }

        // 遍歷結束，如果堆疊為空，表示所有括號都已正確配對
        return stack.isEmpty();
    }
}