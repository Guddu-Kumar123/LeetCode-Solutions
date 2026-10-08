class Solution {
    public String removeOuterParentheses(String s) {
        // StringBuilder to store the result after removing outer parentheses
        StringBuilder result = new StringBuilder();
      
        // Counter to track the depth of nested parentheses
        int depth = 0;
      
        // Iterate through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
          
            if (currentChar == '(') {
                // Opening parenthesis case
                // Increment depth first, then check if it's not an outer parenthesis
                depth++;
                if (depth > 1) {
                    // Not an outer opening parenthesis, so add it to result
                    result.append(currentChar);
                }
            } else {
                // Closing parenthesis case
                // Decrement depth first, then check if it's not an outer parenthesis
                depth--;
                if (depth > 0) {
                    // Not an outer closing parenthesis, so add it to result
                    result.append(currentChar);
                }
            }
        }
      
        // Convert StringBuilder to String and return
        return result.toString();
    }
}
