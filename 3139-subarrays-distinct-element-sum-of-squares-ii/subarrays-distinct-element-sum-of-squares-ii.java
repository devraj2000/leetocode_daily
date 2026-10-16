
//Think from a math POV , It will be easy 



class Solution {
    public int sumCounts(int[] nums) {
        final long MOD = 1_000_000_007L;
        int n = nums.length;
        
        int[] last = new int[100001];
        int[] bitCnt = new int[n + 1];
        long[] bitSum = new long[n + 1];
        
        long ans = 0;
        long curSq = 0;
        long curSum = 0;
        long c1 = 0;
        
        for (int j = 1; j <= n; ++j) {
            int x = nums[j - 1];
            int p = last[x];
            last[x] = j;
            
            if (p == 0) {
                curSq = (curSq + 2 * curSum + j) % MOD;
                curSum += j;
                c1++;
            } else {
                long cnt = 0, sumM = 0;
                for (int idx = p - 1; idx > 0; idx -= idx & -idx) {
                    cnt += bitCnt[idx];
                    sumM += bitSum[idx];
                }
                
                long prefixD = p * c1 - (long) p * (p - 1) / 2 + p * cnt - sumM;
                long subSum = curSum - prefixD;
                
                int len = j - p;
                curSq = (curSq + 2 * subSum + len) % MOD;
                curSum += len;
                
                for (int idx = p; idx <= n; idx += idx & -idx) {
                    bitCnt[idx] += 1;
                    bitSum[idx] += p;
                }
            }
            
            ans = (ans + curSq) % MOD;
        }
        
        return (int) ans;
    }
}