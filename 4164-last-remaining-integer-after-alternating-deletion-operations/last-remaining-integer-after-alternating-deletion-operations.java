class Solution {
    public long lastInteger(long n) {
        if (n == 1) return 1;

        long mid = (n + 1) / 2;
        return 2 * (mid + 1 - lastInteger(mid)) - 1;
    }
}