import java.util.*;

public class LC39_CombinationSum_PPE {
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
        for(int i=start;i<candidates.length;i++){
            if(candidates[i]>remain) break;
            path.add(candidates[i]);
            backtrack(candidates, i, path, remain - candidates[i]); // 可重複
            path.remove(path.size()-1);
        }
    }
}
