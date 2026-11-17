class Solution {
    public int minimumChanges(String s, int k) {
      int n = s.length();
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                cost[l][r] = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int groups = len / d;
                    int changes = 0;

                    for (int i = 0; i < d; i++) {
                        for (int j = 0; j < groups / 2; j++) {
                            int left = l + i + j * d;
                            int right = l + i + (groups - 1 - j) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        int INF = 1000000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 2 * parts; i <= n; i++) {
                for (int j = 2 * (parts - 1); j <= i - 2; j++) {
                    dp[parts][i] = Math.min(
                        dp[parts][i],
                        dp[parts - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];  
    }
}