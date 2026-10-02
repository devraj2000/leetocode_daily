class Solution {

    public static void help (String s, int dep, int n, List<String> result){
        if (dep < 0) return;

        if (dep > n - s.length()) return;

        if (s.length() == n) {
            result.add(s);
            return;
        }
        help(s + "(", dep + 1, n, result);
        help(s + ")", dep - 1, n, result);
    }

    public List<String> generateParenthesis(int n) {
        
        List<String> result = new ArrayList<>();

        help("", 0, n * 2, result);

        return result;
    }
}