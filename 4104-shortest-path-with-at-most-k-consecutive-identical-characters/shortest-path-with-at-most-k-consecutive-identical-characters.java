class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(new int[]{e[1], e[2]});
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0][1] = 0;
        pq.add(new long[]{0, 0, 1});
        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long d = t[0];
            int u = (int) t[1], r = (int) t[2];
            if (d > dist[u][r]) continue;
            if (u == n - 1) return (int) d;
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                int nr = labels.charAt(v) == labels.charAt(u) ? r + 1 : 1;
                if (nr > k) continue;
                if (d + w < dist[v][nr]) {
                    dist[v][nr] = d + w;
                    pq.add(new long[]{d + w, v, nr});
                }
            }
        }
        return -1;
    }
}