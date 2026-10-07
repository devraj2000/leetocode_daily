class Solution {

    private Set<String> result = new HashSet<>();
    private int removeLeft;
    private int removeRight;

    public List<String> removeInvalidParentheses(String s) {

        for (char c : s.toCharArray()) {
            if (c == '(') {
                removeLeft++;
            } else if (c == ')') {
                if (removeLeft > 0) {
                    removeLeft--;
                } else {
                    removeRight++;
                }
            }
        }

        backtrack(s, 0, 0, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void backtrack(
            String s,
            int index,
            int balance,
            StringBuilder path) {

        if (balance < 0) {
            return;
        }

        if (index == s.length()) {
            if (balance == 0 && removeLeft == 0 && removeRight == 0) {
                result.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(' && removeLeft > 0) {
            removeLeft--;

            backtrack(s, index + 1, balance, path);

            removeLeft++;
        }

        else if (c == ')' && removeRight > 0) {
            removeRight--;

            backtrack(s, index + 1, balance, path);

            removeRight++;
        }

        path.append(c);

        if (c == '(') {
            backtrack(s, index + 1, balance + 1, path);
        }
        else if (c == ')' && balance > 0) {
            backtrack(s, index + 1, balance - 1, path);
        }
        else if (c != ')') {
            backtrack(s, index + 1, balance, path);
        }

        path.deleteCharAt(path.length() - 1);
    }
}