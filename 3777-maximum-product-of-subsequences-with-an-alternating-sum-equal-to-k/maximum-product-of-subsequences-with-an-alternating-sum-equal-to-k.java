/**
 * Finds the maximum product of a non-empty subsequence whose alternating
 * sum equals k and whose product does not exceed limit.
 *
 * <p>The solution uses dynamic programming with bitsets to efficiently
 * represent reachable alternating sums for each product and subsequence
 * parity. Odd-length and even-length subsequences are tracked separately
 * because the sign of the next selected element depends on the current
 * subsequence length.
 *
 * <p>The sum range is bounded by the sum of all array elements. An offset
 * converts potentially negative alternating sums into non-negative bit
 * indices. Each long stores 64 possible sums, reducing memory usage.
 *
 * <p>Products greater than limit are grouped into a special overflow state
 * because a subsequent zero can reduce their product to zero. Zero and one
 * are handled separately to optimize their transitions.
 *
 * @param nums the input array of non-negative integers
 * @param k the required alternating sum
 * @param limit the maximum allowed product
 * @return the maximum product of a valid non-empty subsequence, or -1
 *         if no such subsequence exists
 */


class Solution {
    private int words;
    private long lastMask;
    public int maxProduct(int[] nums, int k, int limit) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (Math.abs(k) > total) {
            return -1;
        }
        int offset = total;
        int bitCount = total * 2 + 1;
        words = (bitCount + 63) >> 6;
        int remainingBits = bitCount & 63;
        lastMask = remainingBits == 0 ? -1L : (1L << remainingBits) - 1;
        int over = limit + 1;
        long[][] odd = new long[over + 1][words];
        long[][] even = new long[over + 1][words];
        boolean[] active = new boolean[over + 1];
        long[] tempOdd = new long[words];
        long[] tempEven = new long[words];
        for (int num : nums) {
            if (num == 0) {
                Arrays.fill(tempOdd, 0);
                Arrays.fill(tempEven, 0);
                for (int product = 0; product <= over; product++) {
                    if (!active[product]) {
                        continue;
                    }
                    for (int word = 0; word < words; word++) {
                        tempOdd[word] |= even[product][word];
                        tempEven[word] |= odd[product][word];
                    }
                }
                for (int word = 0; word < words; word++) {
                    odd[0][word] |= tempOdd[word];
                    even[0][word] |= tempEven[word];
                }
                setBit(odd[0], offset);
                active[0] = true;
                continue;
            }
            if (num == 1) {
                for (int product = 0; product <= over; product++) {
                    if (!active[product]) {
                        continue;
                    }
                    System.arraycopy(odd[product], 0, tempOdd, 0, words);
                    System.arraycopy(even[product], 0, tempEven, 0, words);
                    orShift(even[product], tempOdd, -1);
                    orShift(odd[product], tempEven, 1);
                }
                setBit(odd[1], offset + 1);
                active[1] = true;
                continue;
            }
            if (active[0]) {
                System.arraycopy(odd[0], 0, tempOdd, 0, words);
                System.arraycopy(even[0], 0, tempEven, 0, words);
                orShift(even[0], tempOdd, -num);
                orShift(odd[0], tempEven, num);
            }
            if (active[over]) {
                System.arraycopy(odd[over], 0, tempOdd, 0, words);
                System.arraycopy(even[over], 0, tempEven, 0, words);
                orShift(even[over], tempOdd, -num);
                orShift(odd[over], tempEven, num);
            }
            for (int product = limit; product >= 1; product--) {
                if (!active[product]) {
                    continue;
                }
                int nextProduct = product * num;
                if (nextProduct > limit) {
                    nextProduct = over;
                }
                orShift(even[nextProduct], odd[product], -num);
                orShift(odd[nextProduct], even[product], num);
                active[nextProduct] = true;
            }
            int product = num <= limit ? num : over;
            setBit(odd[product], offset + num);
            active[product] = true;
        }
        int target = offset + k;
        int targetWord = target >> 6;
        long targetBit = 1L << (target & 63);
        for (int product = limit; product >= 0; product--) {
            if (((odd[product][targetWord] | even[product][targetWord]) & targetBit) != 0) {
                return product;
            }
        }
        return -1;
    }
    private void setBit(long[] bits, int index) {
        bits[index >> 6] |= 1L << (index & 63);
    }
    private void orShift(long[] target, long[] source, int shift) {
        if (shift > 0) {
            int wordShift = shift >> 6;
            int bitShift = shift & 63;
            for (int i = 0; i < words; i++) {
                long value = source[i];
                if (value == 0) {
                    continue;
                }
                int position = i + wordShift;
                if (position < words) {
                    target[position] |= value << bitShift;
                }
                if (bitShift != 0 && position + 1 < words) {
                    target[position + 1] |= value >>> (64 - bitShift);
                }
            }
        } else {
            int amount = -shift;
            int wordShift = amount >> 6;
            int bitShift = amount & 63;
            for (int i = 0; i < words; i++) {
                long value = source[i];
                if (value == 0) {
                    continue;
                }
                int position = i - wordShift;
                if (position >= 0) {
                    target[position] |= value >>> bitShift;
                }
                if (bitShift != 0 && position - 1 >= 0) {
                    target[position - 1] |= value << (64 - bitShift);
                }
            }
        }
        target[words - 1] &= lastMask;
    }
}