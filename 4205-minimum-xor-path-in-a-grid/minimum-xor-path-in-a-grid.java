class Solution {
    public int minCost(int[][] grid) {
         int m = grid.length;
        int n = grid[0].length;
        boolean[][][] dp = new boolean[m][n][1024];   
        dp[0][0][grid[0][0]] = true;          
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 1024; k++) {
                    if (dp[i][j][k] == false)   
                        continue;
                    if (m > i + 1) {
                        int x = k ^ grid[i + 1][j]; 
                        dp[i + 1][j][x] = true;
                    }
                    if (n > j + 1) {
                        int x = k ^ grid[i][j + 1]; 
                        dp[i][j + 1][x] = true;
                    }
                }    
        for (int i = 0; i < 1024; i++)
            if (dp[m - 1][n - 1][i])
                return i;
        return 0;
    }
}