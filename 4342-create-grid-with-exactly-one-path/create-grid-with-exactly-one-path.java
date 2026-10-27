// The main idea is to create the first row with only dots (.) and fill all remaining rows with hashes (#), except for the last column, which contains a dot (.).
// 1. Create the grid: Initialize a string array of size m to store all rows.
// 2. Prepare the repeated row: Use "#".repeat(n - 1) + "." to create a row with n - 1 hashes followed by one dot.
// 3. Fill the first row: Use ".".repeat(n) to create a row containing only dots.
// 4. Fill the remaining rows: Loop from row 1 to m - 1 and assign the prepared row to each position.
// 5. Return the grid: Return the completed string array.


class Solution {
    public String[] createGrid(int m, int n) {
        String[] result = new String[m];
        String rest = "#".repeat(n - 1) + ".";
        result[0] = ".".repeat(n);
        for (int i = 1; i < m; i++) {
            result[i] = rest;
        }
        return result;
    }
}