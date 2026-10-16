
//Think from a math POV , It will be easy 


class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        int[] last = new int[100001];

        long MOD = 1_000_000_007L;
        long ans = 0;

        long[] sum = new long[4 * n];
        long[] sumSq = new long[4 * n];
        long[] lazy = new long[4 * n];

        for (int j = 1; j <= n; j++) {
            int x = nums[j - 1];
            int prev = last[x];
            last[x] = j;

            update(1, 1, n, prev + 1, j,
                   sum, sumSq, lazy, MOD);

            ans = (ans + sumSq[1]) % MOD;
        }

        return (int) ans;
    }

    private void update(
            int node, int left, int right,
            int ql, int qr,
            long[] sum, long[] sumSq,
            long[] lazy, long MOD) {

        if (ql <= left && right <= qr) {
            long len = right - left + 1L;
            long add = lazy[node] + 1;

            sumSq[node] = (sumSq[node]
                    + 2 * sum[node] + len) % MOD;

            sum[node] = (sum[node] + len) % MOD;
            lazy[node] = add;
            return;
        }

        pushDown(node, left, right, sum, sumSq, lazy, MOD);

        int mid = (left + right) / 2;

        if (ql <= mid) {
            update(node * 2, left, mid, ql, qr,
                   sum, sumSq, lazy, MOD);
        }

        if (qr > mid) {
            update(node * 2 + 1, mid + 1, right, ql, qr,
                   sum, sumSq, lazy, MOD);
        }

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void pushDown(
            int node, int left, int right,
            long[] sum, long[] sumSq,
            long[] lazy, long MOD) {

        long add = lazy[node];

        if (add == 0 || left == right) {
            return;
        }

        int mid = (left + right) / 2;
        apply(node * 2, mid - left + 1, add, sum, sumSq, lazy, MOD);
        apply(node * 2 + 1, right - mid, add, sum, sumSq, lazy, MOD);

        lazy[node] = 0;
    }

    private void apply(
            int node, long len, long add,
            long[] sum, long[] sumSq,
            long[] lazy, long MOD) {

        sumSq[node] = (sumSq[node]
                + 2 * add % MOD * sum[node] % MOD
                + add * add % MOD * len % MOD) % MOD;

        sum[node] = (sum[node] + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }
}