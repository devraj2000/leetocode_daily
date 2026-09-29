class Solution {

    boolean[][][] seen;
    int m, n, mid;
    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        if ((m + n) % 2 == 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        mid = (m + n) / 2;
        seen = new boolean[m][n][mid + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] g, int r, int c, int bal) {

        bal += g[r][c] == '(' ? 1 : -1;

        if (bal < 0 || bal > mid) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        if (seen[r][c][bal]) {
            return false;
        }

        seen[r][c][bal] = true;

        if (r + 1 < m && dfs(g, r + 1, c, bal)) {
            return true;
        }

        return c + 1 < n && dfs(g, r, c + 1, bal);
    }
}