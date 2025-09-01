import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    // 解法：HashMap O(n)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            map.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] result1 = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]"); // [0, 1]

        int[] result2 = twoSum(new int[]{3, 2, 4}, 6);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]"); // [1, 2]

        int[] result3 = twoSum(new int[]{3, 3}, 6);
        System.out.println("[" + result3[0] + ", " + result3[1] + "]"); // [0, 1]
    }
}
