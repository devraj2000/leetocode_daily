// Create a HashSet to store distinct elements.
// Extend the subarray by moving j from i to the end.
// Add nums.get(j) to the set.
// Calculate the square of the number of distinct elements and add it to sum.

class Solution {
    public int sumCounts(List<Integer> nums) {
        int sum = 0;
        int n = nums.size();      

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();

            for (int j = i; j < n; j++) {
                set.add(nums.get(j));

                int distinct = set.size();
                sum += (long) distinct * distinct;
            }
        }

        return sum;
        
    }
}