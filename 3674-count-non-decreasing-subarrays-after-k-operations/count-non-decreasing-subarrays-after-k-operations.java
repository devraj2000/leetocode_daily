
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long operations = 0;

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        List<List<Integer>> cnt = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            cnt.add(new ArrayList<>());
        }

        int[] pos = new int[n];
        Arrays.fill(pos, n);

        Deque<Integer> stack = new ArrayDeque<>();

        int left = 0;

        for (int right = 0; right < n; right++) {

            while (!stack.isEmpty() &&
                   nums[right] >= nums[stack.peek()]) {

                pos[stack.peek()] = right;
                stack.pop();
            }

            if (!stack.isEmpty()) {
                cnt.get(stack.peek()).add(right);
            }

            stack.push(right);

            pq.offer(new int[]{nums[right], right});

            operations += Math.max(0, pq.peek()[0] - nums[right]);

            while (operations > k) {

                for (int i : cnt.get(left)) {
                    operations -= (long) (nums[left] - nums[i])
                        * (Math.min(pos[i], right + 1) - i);
                }

                left++;
            }

            while (!pq.isEmpty() && pq.peek()[1] < left) {
                pq.poll();
            }

            ans += right - left + 1;
        }

        return ans;
    }
}