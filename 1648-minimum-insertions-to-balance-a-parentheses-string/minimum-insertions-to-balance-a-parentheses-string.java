class Solution {
    public int minInsertions(String s) {
        int result = 0, d = 0;
        int n = s.length();
        
        for (int i = 0; i < n; ) {

            while (i < n && s.charAt(i) == '(') {
                d++;
                i++;
            }
            int t = 0;
            while (i < n && s.charAt(i) == ')') {
                t++;
                i++;
            }
            
            if (t > 0) {
                result += t & 1;

                d -= (t + 1) >> 1;

                if (d < 0) {
                    result -= d;
                    d = 0;       
                }
            }
        }
        return result + d * 2;
    }
}
