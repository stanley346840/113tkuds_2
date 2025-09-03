import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 避免重複的 i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // 避免重複的 j
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                long sumTwo = (long) nums[i] + nums[j];
                long remainingTarget = target - sumTwo;
                
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sumTwoPointers = (long) nums[left] + nums[right];
                    
                    if (sumTwoPointers == remainingTarget) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // 找到一個解後，移動指針並跳過重複的元素
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sumTwoPointers < remainingTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}