

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> numbers = new TreeSet<>();
        boolean[] used = new boolean[digits.length];

        generate(digits, used, 0, 0, numbers);

        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    private void generate(int[] digits, boolean[] used, int count,
                          int number, Set<Integer> numbers) {

        if (count == 3) {
            if (number % 2 == 0) {
                numbers.add(number);
            }
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (used[i]) {
                continue;
            }

            if (count == 0 && digits[i] == 0) {
                continue;
            }

            if (count == 2 && digits[i] % 2 != 0) {
                continue;
            }

            used[i] = true;

            generate(
                digits,
                used,
                count + 1,
                number * 10 + digits[i],
                numbers
            );

            used[i] = false; 
        }
    }
}
