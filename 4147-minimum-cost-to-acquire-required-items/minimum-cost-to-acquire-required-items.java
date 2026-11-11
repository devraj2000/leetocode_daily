class Solution {
    public long minimumCost(long cost1, long cost2, long costBoth, long need1, long need2) {
        long lower = Math.min(need1,need2);
        return lower*(Math.min(costBoth,cost1+cost2)) + (need1-lower)*(Math.min(costBoth,cost1)) + (need2-lower)*(Math.min(costBoth,cost2));
        
    }
}