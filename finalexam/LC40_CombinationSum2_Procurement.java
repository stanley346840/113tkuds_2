import java.util.*;

public class LC40_CombinationSum2_Procurement {
    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for(int i=0;i<n;i++) candidates[i]=sc.nextInt();
        Arrays.sort(candidates);

        backtrack(candidates, 0, new ArrayList<>(), target);

        for(List<Integer> comb : res){
            for(int i=0;i<comb.size();i++){
                if(i>0) System.out.print(" ");
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
    }

    static void backtrack(int[] candidates, int start, List<Integer> path, int remain){
        if(remain==0){
            res.add(new ArrayList<>(path));
            return;
        }
        int prev = -1;
        for(int i=start;i<candidates.length;i++){
            if(candidates[i]>remain) break;
            if(prev == candidates[i]) continue; // 同層跳過重複
            path.add(candidates[i]);
            backtrack(candidates, i+1, path, remain - candidates[i]); // 每個數字只能用一次
            path.remove(path.size()-1);
            prev = candidates[i];
        }
    }
}
