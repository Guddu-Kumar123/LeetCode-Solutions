class Solution {
    /**
     * Calculates the minimum number of parentheses additions needed to make a valid string.
     * Uses a stack to track unmatched parentheses.
     * 
     * @param s Input string containing '(' and ')' characters
     * @return Minimum number of parentheses to add to make the string valid
     */
    public int minAddToMakeValid(String s) {
        // Stack to store unmatched parentheses
        Deque<Character> stack = new ArrayDeque<>();
      
        // Process each character in the string
        for (char currentChar : s.toCharArray()) {
            // Check if current character can form a valid pair with top of stack
            if (currentChar == ')' && !stack.isEmpty() && stack.peek() == '(') {
                // Found a matching pair, remove the opening parenthesis
                stack.pop();
            } else {
                // No match found, add current character to stack
                stack.push(currentChar);
            }
        }
      
        // The size of stack represents unmatched parentheses
        // Each unmatched parenthesis needs a corresponding one to be added
        return stack.size();
    }
}
