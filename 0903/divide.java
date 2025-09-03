class Solution {
    public int divide(int dividend, int divisor) {
        // 處理整數溢出的邊界情況
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 判斷結果的符號
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // 將數字轉換為 long 的絕對值，以避免溢出
        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);
        
        long quotient = 0;
        
        // 使用位元移位來實現快速相減
        while (lDividend >= lDivisor) {
            long tempDivisor = lDivisor;
            long multiple = 1;

            // 尋找最大的可以被減去的倍數
            while (lDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }
            
            lDividend -= tempDivisor;
            quotient += multiple;
        }

        // 根據符號返回最終結果
        if (isNegative) {
            return (int) -quotient;
        } else {
            return (int) quotient;
        }
    }
}