import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        int n = nums.length;
        
        // Step 2: Initialize closestSum with the sum of the first three elements
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // Step 3: Iterate through the array to fix the first element
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            // Use two pointers to find the other two elements
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // Step 4: If currentSum is exactly the target, we have found the best solution
                if (currentSum == target) {
                    return currentSum;
                }
                
                // Check if the current sum is closer to the target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // Step 5: Adjust pointers based on the sum
                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        // Step 6: Return the closest sum
        return closestSum;
    }
}