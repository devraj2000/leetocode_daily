class Solution {
    public int addDigits(int num) {
        return  IntStream.of(num)
            .map(n -> n == 0 ? 0 : 1 + (n - 1) % 9)
            .findFirst()
            .orElse(0);
    }
}