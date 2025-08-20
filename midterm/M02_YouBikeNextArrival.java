public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        String[] times = new String[n];
        for (int i = 0; i < n; i++) {
            times[i] = sc.nextLine().trim();
        }
        String query = sc.nextLine().trim();

        String ans = "No bike";
        for (int i = 0; i < n; i++) {
            if (times[i].compareTo(query) > 0) { // 已排序，找到第一個晚於 query
                ans = times[i];
                break;
            }
        }
        System.out.println(ans);
    }
}
