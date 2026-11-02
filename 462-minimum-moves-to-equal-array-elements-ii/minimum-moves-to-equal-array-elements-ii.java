class Solution {
    public int minMoves2(int[] nums) {
        
    Arrays.sort(nums);

    int median = nums[nums.length / 2];

    return (int) Arrays.stream(nums).mapToLong(num -> Math.abs((long) num - median)).sum();
        
    }
}