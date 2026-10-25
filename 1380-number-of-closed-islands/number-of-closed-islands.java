
class Solution {

    private boolean dfs(int[][] grid, int x, int y) {

        //we need to check whether we are outside the grid
        if (x < 0 || x >= grid.length ||
            y < 0 || y >= grid[0].length) {
            return true;
        }
        // Check whether the cell is water or already visited
        if (grid[x][y] == 1) {
            return false;
        }

        grid[x][y] = 1; // marking  the iland visited 


        //now to check all the direction
        boolean out = dfs(grid, x - 1, y);
        out = dfs(grid, x + 1, y) || out;
        out = dfs(grid, x, y - 1) || out;

        return dfs(grid, x, y + 1) || out;
    }

    public int closedIsland(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                
                //now we are just counting the closed iland
                if (grid[i][j] == 0 && !dfs(grid, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }
}
