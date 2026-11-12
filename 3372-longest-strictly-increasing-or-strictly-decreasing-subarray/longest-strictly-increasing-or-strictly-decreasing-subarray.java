// //any point can be a starting point of and sub array , so i am checking every index, then we find the length of all starting point 

// class Solution {
//     public int longestMonotonicSubarray(int[] nums) {
//         int n = nums.length;
//         int best = 1;
        
//         for (int i = 0; i < n; i++) {
//             int up = 1;
//             for (int j = i + 1; j < n && nums[j] > nums[j - 1]; j++) {
//                 up++;
//             }
            
//             int down = 1;
//             for (int j = i + 1; j < n && nums[j] < nums[j - 1]; j++) {
//                 down++;
//             }
//             best = Math.max(best, Math.max(up, down));
//         }
        
//         return best;
//     }
// }

//Every step between neighbours is an arrow: up, down or flat. A strictly monotone subarray is a streak of equal arrows

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int increasing = 1;
        int decreasing = 1;
        int maxLength = 1;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > nums[i - 1]) {
                increasing++;
                decreasing = 1;
            } 
            else if (nums[i] < nums[i - 1]) {
                decreasing++;
                increasing = 1;
            } 
            else {
                increasing = 1;
                decreasing = 1;
            }

            maxLength = Math.max(maxLength,
                    Math.max(increasing, decreasing));
        }

        return maxLength;
    }
}
