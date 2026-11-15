class Solution {
    public int numOfSubarrays(int[] arr) {

        //cumulates each element of a given array in-place in parallel, using a supplied associative binary operator

        Arrays.parallelPrefix(arr, Integer::sum);  
        long odd = Arrays.stream(arr).map(p -> p & 1).sum();  
        return (int) (odd * (arr.length + 1 - odd) % 1_000_000_007);  
    }
}