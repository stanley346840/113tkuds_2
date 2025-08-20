import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = computeTax(income);
            double avgRate = (income == 0) ? 0.0 : (double) tax / income;
            System.out.printf("%d %.2f%n", tax, avgRate);
        }
    }

    static long computeTax(long income) {
        long tax = 0;
        if (income <= 120000) {
            tax = Math.round(income * 0.05);
        } else if (income <= 500000) {
            tax = Math.round(120000 * 0.05 + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = Math.round(120000 * 0.05 + (500000 - 120000) * 0.12
                    + (income - 500000) * 0.20);
        } else {
            tax = Math.round(120000 * 0.05 + (500000 - 120000) * 0.12
                    + (1000000 - 500000) * 0.20
                    + (income - 1000000) * 0.30);
        }
        return tax;
    }
}
