class Solution {
    public int longestValidParentheses(String s) {
       int answer = 0, depth = 0, start = -1;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++depth;
            }
            else if (--depth < 0) {
                start = i;
                depth = 0;
            }
            else if (depth == 0) {
                answer = Math.max(answer, i - start);
                
            }
        }
        depth = 0;
        start = s.length();
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == ')') {
                ++depth;
            }
            else if (--depth < 0) {
                start = i;
                depth = 0;
            }
            else if (depth == 0) {
                answer = Math.max(answer, start - i);
            }
        }
        return answer;
        
    } 
    
}