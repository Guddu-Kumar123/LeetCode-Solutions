class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;      // Total score of the balanced parentheses string
        int depth = 0;      // Current nesting depth (number of unclosed '(' brackets)
      
        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Opening bracket increases the depth
                depth++;
            } else {  // s.charAt(i) == ')'
                // Closing bracket decreases the depth
                depth--;
              
                // Check if this closing bracket forms a "()" pair
                // This happens when the previous character is '('
                if (s.charAt(i - 1) == '(') {
                    // Each "()" at depth d contributes 2^d to the total score
                    // Using bit shift: 1 << d equals 2^d
                    score += 1 << depth;
                }
            }
        }
      
        return score;
    }
}
