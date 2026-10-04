class Solution {
    public boolean checkValidString(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[0][0] = true;
        
        for (int i = 1; i <= n; ++i) {
            int from = -1, to = 1;
            if (s.charAt(i - 1) == '(') {
                from = to = 1;
            } else if (s.charAt(i - 1) == ')') {
                from = to = -1;
            }
            for (int j = from; j <= to; ++j) {
    
                for (int k = Math.max(-j, 0); k <= n && k + j <= i && k + j <= n; ++k) {
                    if (dp[i - 1][k]) {
                        dp[i][k + j] = true;
                    }
                }
            }
        }
        return dp[n][0];
    }
}
