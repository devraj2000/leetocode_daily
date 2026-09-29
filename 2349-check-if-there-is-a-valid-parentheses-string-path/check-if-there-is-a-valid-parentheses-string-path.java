class Solution {

    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

  
        if ((m + n) % 2 == 0) {
            return false;
        }
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        Set<Integer>[][] dp = new HashSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }

        dp[0][0].add(1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                if (i > 0) {
                    addBalances(dp[i][j], dp[i - 1][j], grid[i][j]);
                }
                if (j > 0) {
                    addBalances(dp[i][j], dp[i][j - 1], grid[i][j]);
                }
            }
        }

        return dp[m - 1][n - 1].contains(0);
    }

    private void addBalances(Set<Integer> current,
                             Set<Integer> previous,
                             char ch) {

        for (int balance : previous) {

            int newBalance;

            if (ch == '(') {
                newBalance = balance + 1;
            } else {
                newBalance = balance - 1;
            }
            if (newBalance >= 0) {
                current.add(newBalance);
            }
        }
    }
}