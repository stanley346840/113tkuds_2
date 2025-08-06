public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 初始化變數
        int total = 0;
        int max = scores[0];
        int min = scores[0];
        int aboveAverageCount = 0;

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        // 第一次走訪：總分、最高分、最低分、等第
        for (int score : scores) {
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;

            // 分級
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        // 計算平均
        double average = total / (double) scores.length;

        // 第二次走訪：高於平均的人數
        for (int score : scores) {
            if (score > average) aboveAverageCount++;
        }

        // 輸出報表
        System.out.println("=== 成績統計報表 ===");
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("高於平均分的人數：" + aboveAverageCount);

        System.out.println("\n等第分布：");
        System.out.println("A (90~100)：" + countA + " 人");
        System.out.println("B (80~89) ：" + countB + " 人");
        System.out.println("C (70~79) ：" + countC + " 人");
        System.out.println("D (60~69) ：" + countD + " 人");
        System.out.println("F (<60)   ：" + countF + " 人");

        System.out.println("\n詳細成績：");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("學生 %2d：%3d 分\n", i + 1, scores[i]);
        }
    }
}
