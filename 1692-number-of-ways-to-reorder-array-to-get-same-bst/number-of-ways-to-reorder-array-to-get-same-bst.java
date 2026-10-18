
class Solution {
    private static final int MOD = 1_000_000_007;

    private int add(int x, int y) {
        x += y;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    private int mul(long x, long y) {
        return (int) (x * y % MOD);
    }

    private int rev(int x) {
        int result = 1;
        int power = MOD - 2;

        while (power > 0) {
            if ((power & 1) == 1) {
                result = mul(result, x);
            }
            x = mul(x, x);
            power >>= 1;
        }

        return result;
    }

    private int dfs(List<Integer> nums, int[] fact, int[] invFact) {
        if (nums.size() <= 2) {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        int root = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            int value = nums.get(i);

            if (value < root) {
                left.add(value);
            } else {
                right.add(value);
            }
        }

        int leftWays = dfs(left, fact, invFact);
        int rightWays = dfs(right, fact, invFact);

        int combinations = mul(
            mul(fact[nums.size() - 1], invFact[left.size()]),
            invFact[right.size()]
        );

        return mul(mul(leftWays, rightWays), combinations);
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        int[] fact = new int[n + 1];
        int[] invFact = new int[n + 1];

        fact[0] = 1;
        invFact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = mul(fact[i - 1], i);
            invFact[i] = rev(fact[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return add(dfs(list, fact, invFact), MOD - 1);
    }
}
