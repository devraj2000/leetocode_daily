class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1;
        int n = nums.length;

        for (int i=0;i<n;i++){
            if(nums[i]!=i){
                k &= nums[i];
                }
            if (k == 0) return 0;
        }
        return Math.max(k, 0);
    }
}


// class Solution {
//     public int sortPermutation(int[] nums) {
//         return Math.max(IntStream.range(0, nums.length).filter(i -> nums[i] != i).map(i -> nums[i]).reduce(-1, (a, b) -> a & b), 0);
//     }
// }