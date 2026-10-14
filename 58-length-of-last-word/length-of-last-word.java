class Solution {
    public int lengthOfLastWord(String s) {
        
        return Arrays.stream(s.split(" "))
                     .filter(word -> !word.isEmpty())
                     .reduce((first, second) -> second)
                     .map(String::length)
                     .orElse(0);
    }
}