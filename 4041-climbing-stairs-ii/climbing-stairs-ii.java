class Solution {
    public int climbStairs(int n, int[] costs) {
        int x=0,y=0,z=0;
        for (int cost: costs){
            int current = cost + Math.min( x+1, Math.min(y+4,z+9));
            z=y;y=x;x= current;
        }
        return x;
    }
}