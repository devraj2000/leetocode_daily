class Solution {
    public int minOperations(int[] nums, int k) {
        int ans =0;
        for (int num : nums) ans= ans+num;
        return ans % k;

        // return Arrays.stream(nums).sum() % k;
    }
}