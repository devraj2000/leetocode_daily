class Solution {
     public static char[][] grid;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    dfs(row, col);
                    count++;
                }
            }
        }
        return count;
        
    }

    public static void dfs(int row, int col ){
        if (row <0 || col<0 || row>= grid.length || col >=grid[0].length){return;}
        if(grid[row][col] != '1'){return;}
        grid[row][col] = '0';
        dfs(row + 1, col);
        dfs(row - 1, col);
        dfs(row, col + 1);
        dfs(row, col - 1);
        
    }
}