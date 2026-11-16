class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < s.length(); i += k) {
                int sum = s.substring(i, Math.min(i + k, s.length())).chars().map(c -> c - '0').sum();
                result.append(sum);
            }
            s = result.toString();
        }
        return s;
    }
}