
        class Solution {
    public int minInsertions(String s) {
        int insertionsNeeded = 0;  // Count of characters we need to insert
        int openParentheses = 0;    // Count of unmatched opening parentheses
        int length = s.length();
      
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
          
            if (currentChar == '(') {
                // Found an opening parenthesis, increment counter
                openParentheses++;
            } else {
                // Found a closing parenthesis ')'
              
                // Check if we have a pair of closing parentheses '))'
                if (i < length - 1 && s.charAt(i + 1) == ')') {
                    // We have '))', skip the next character
                    i++;
                } else {
                    // We only have single ')', need to insert one more ')'
                    insertionsNeeded++;
                }
              
                // Now we have a complete '))', check if there's a matching '('
                if (openParentheses == 0) {
                    // No opening parenthesis available, need to insert one '('
                    insertionsNeeded++;
                } else {
                    // Match this '))' with an available '('
                    openParentheses--;
                }
            }
        }
      
        // Each remaining opening parenthesis needs two closing parentheses
        insertionsNeeded += openParentheses * 2;
      
        return insertionsNeeded;
    }
}

    
