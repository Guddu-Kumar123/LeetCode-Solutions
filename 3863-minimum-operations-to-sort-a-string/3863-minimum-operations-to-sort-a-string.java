class Solution {
    public int minOperations(String s) {

        int n = s.length();

        // 1. Check if already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // 2. Unsorted string of length 2 cannot be fixed
        if (n == 2) {
            return -1;
        }

        // 3. Find minimum and maximum character
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        for (int i = 1; i < n; i++) {

            if (s.charAt(i) < minChar) {
                minChar = s.charAt(i);
            }

            if (s.charAt(i) > maxChar) {
                maxChar = s.charAt(i);
            }
        }

        // 4. Can be sorted in one operation
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {

            return 1;
        }

        // 5. Check ONLY middle characters
        for (int i = 1; i < n - 1; i++) {

            if (s.charAt(i) == minChar ||
                s.charAt(i) == maxChar) {

                return 2;
            }
        }

        // 6. Otherwise need 3 operations
        return 3;
    }
}