class Solution {
    /**
     * Checks if a string containing '(', ')', and '*' characters is valid.
     * '*' can represent '(', ')', or an empty string.
     * A valid string has balanced parentheses.
     * 
     * @param s the input string to validate
     * @return true if the string can be made valid, false otherwise
     */
    public boolean checkValidString(String s) {
        int length = s.length();
      
        // dp[i][j] represents whether substring s[i..j] can be valid
        boolean[][] dp = new boolean[length][length];
      
        // Base case: single character substrings
        // Only '*' can be valid as it can represent an empty string
        for (int i = 0; i < length; i++) {
            dp[i][i] = s.charAt(i) == '*';
        }
      
        // Fill the DP table for substrings of increasing length
        // Process from right to left for start index
        for (int startIndex = length - 2; startIndex >= 0; startIndex--) {
            // Process from left to right for end index
            for (int endIndex = startIndex + 1; endIndex < length; endIndex++) {
                char startChar = s.charAt(startIndex);
                char endChar = s.charAt(endIndex);
              
                // Check if current substring can form a valid pair
                // First and last characters must be able to form '(' and ')'
                boolean canFormPair = (startChar == '(' || startChar == '*') && 
                                     (endChar == '*' || endChar == ')');
              
                // For a valid pair, either it's just two characters,
                // or the middle substring must also be valid
                boolean isValidPair = canFormPair && 
                                    (startIndex + 1 == endIndex || dp[startIndex + 1][endIndex - 1]);
              
                dp[startIndex][endIndex] = isValidPair;
              
                // If not valid as a single group, try splitting into two valid substrings
                for (int splitPoint = startIndex; splitPoint < endIndex && !dp[startIndex][endIndex]; splitPoint++) {
                    // Check if we can split at position k into two valid parts
                    dp[startIndex][endIndex] = dp[startIndex][splitPoint] && 
                                               dp[splitPoint + 1][endIndex];
                }
            }
        }
      
        // Return whether the entire string is valid
        return dp[0][length - 1];
    }
}
