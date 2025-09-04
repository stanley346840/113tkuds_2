import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++) nums[i] = sc.nextInt();

        int[] res = searchRange(nums, target);
        System.out.println(res[0]+" "+res[1]);
    }

    public static int[] searchRange(int[] nums, int target){
        int n = nums.length;
        if(n==0) return new int[]{-1,-1};

        int left = 0, right = n-1, l=-1, r=-1;

        // 找左界
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]<target) left=mid+1;
            else right=mid-1;
        }
        if(left>=n || nums[left]!=target) return new int[]{-1,-1};
        l = left;

        // 找右界
        left = 0; right = n-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]<=target) left=mid+1;
            else right=mid-1;
        }
        r = right;

        return new int[]{l,r};
    }
}
