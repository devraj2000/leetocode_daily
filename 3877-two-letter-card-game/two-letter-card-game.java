 /**
     * Finds the maximum number of compatible card pairs containing x.
     *
     * Cards containing x are divided into two groups: x? and ?x.
     * Cards "xx" can pair with any non-"xx" card containing x.
     *
     * We use as many "xx" cards as possible, pairing them with
     * non-"xx" cards. We try every possible allocation of these
     * cards between the two groups to maximize the remaining pairs.
     *
     * Within each group, two cards are compatible when their other
     * characters differ. For a fixed number of remaining cards,
     * we minimize the largest character frequency to maximize pairs.
     *
     * @param cards the array of two-character cards
     * @param x the required character
     * @return the maximum possible number of points
     */


class Solution {
   
    public int score(String[] cards, char x) {
        int[] left = new int[10];
        int[] right = new int[10];
        int both = 0;

        for (String card : cards) {
            char a = card.charAt(0);
            char b = card.charAt(1);

            if (a == x && b == x) {
                both++;
            } else if (a == x) {
                left[b - 'a']++;
            } else if (b == x) {
                right[a - 'a']++;
            }
        }

        int leftTotal = sum(left);
        int rightTotal = sum(right);
        int pairsWithBoth = Math.min(both, leftTotal + rightTotal);

        int answer = 0;

        for (int useLeft = Math.max(0, pairsWithBoth - rightTotal);
             useLeft <= Math.min(pairsWithBoth, leftTotal);
             useLeft++) {

            int useRight = pairsWithBoth - useLeft;

            answer = Math.max(answer,
                    pairsWithBoth
                    + maxPairs(left, useLeft)
                    + maxPairs(right, useRight));
        }

        return answer;
    }

    private int sum(int[] freq) {
        int total = 0;

        for (int count : freq) {
            total += count;
        }

        return total;
    }

    private int maxPairs(int[] freq, int removed) {
        int total = sum(freq) - removed;

        if (total < 2) {
            return 0;
        }

        int low = 0;
        int high = total;

        while (low < high) {
            int mid = (low + high) / 2;
            int available = 0;

            for (int count : freq) {
                available += Math.min(count, mid);
            }

            if (available >= total) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return Math.min(total / 2, total - low);
    }
}