class Solution {
    public int[] buildArray(int[] nums) {
        int[] X = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            X[i] = nums[nums[i]];
        return X;
    }
}