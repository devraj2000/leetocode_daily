class Solution {
    public boolean equalFrequency(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            freq[i]--;

            int count = -1;
            boolean equal = true;

            for (int f : freq) {
                if (f == 0) continue;

                if (count == -1) count = f;
                else if (count != f) {
                    equal = false;
                    break;
                }
            }

            freq[i]++;

            if (equal) return true;
        }

        return false;
    }
}