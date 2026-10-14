class Solution {
    public String maxSumOfSquares(int num, int sum) {

        if (sum > 9 * num) return "";

        int nines = sum / 9;
        int remainder = sum % 9;

        return "9".repeat(nines)
                + (remainder == 0 ? "" : remainder)
                    + "0".repeat(num - nines - (remainder == 0 ? 0 : 1));
        
    }
}