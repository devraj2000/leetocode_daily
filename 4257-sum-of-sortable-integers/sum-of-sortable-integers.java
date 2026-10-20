
/**
* Counts the block sizes that allow the array to be sorted
* by dividing it into equal-sized blocks and rotating each block.
*
* desc[i]: Number of decreases up to index i.
* prefixMax[i]: Maximum value from index 0 to i.
* suffixMin[i]: Minimum value from index i to n - 1.
* validCut[i]: True if a block can start at index i.
*
* A block is valid if it starts at a valid cut and has at most
* one decrease, including the circular connection from end to start.
*/

class Solution {

    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] desc = new int[n];
        for (int i = 1; i < n; i++) {
            desc[i] = desc[i - 1];
            if (nums[i - 1] > nums[i]) {
                desc[i]++;
            }
        }

        int[] prefixMax = nums.clone();
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        int[] suffixMin = nums.clone();
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i], suffixMin[i + 1]);
        }

        boolean[] validCut = new boolean[n];
        validCut[0] = true;

        for (int i = 1; i < n; i++) {
            validCut[i] = prefixMax[i - 1] <= suffixMin[i];
        }

        int answer = 0;

        for (int size = 1; size <= n; size++) {
            if (n % size != 0) {
                continue;
            }

            boolean canSort = true;

            for (int start = 0; start < n; start += size) {
                int end = start + size - 1;

                if (!validCut[start]) {
                    canSort = false;
                    break;
                }

                int decreases = desc[end] - desc[start];

                if (nums[end] > nums[start]) {
                    decreases++;
                }

                if (decreases > 1) {
                    canSort = false;
                    break;
                }
            }

            if (canSort) {
                answer += size;
            }
        }

        return answer;
    }
}