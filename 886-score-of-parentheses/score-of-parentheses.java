class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();

                int currentScore;
                if (innerScore == 0) {
                    currentScore = 1;
                } else {
                    currentScore = 2 * innerScore;
                }

                stack.push(stack.pop() + currentScore);
            }
        }

        return stack.pop();
    }
}