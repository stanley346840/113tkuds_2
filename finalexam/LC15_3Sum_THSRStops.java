import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void LC15_3Sum_THSRStops(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = threeSum(nums);
        for (List<Integer> t : res) {
            System.out.println(t.get(0) + " " + t.get(1) + " " + t.get(2));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            if (nums[i] > 0) break;

            int L = i + 1, R = n - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    L++;
                    R--;
                    while (L < R && nums[L] == nums[L-1]) L++;
                    while (L < R && nums[R] == nums[R+1]) R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return ans;
    }
}
