class Solution {
    /**
     * Repeatedly groups digits and replaces them with their sum until string length <= k
     * @param s - input string containing only digits
     * @param k - target maximum length
     * @return final string after all transformations
     */
    public String digitSum(String s, int k) {
        // Continue processing while string length exceeds k
        while (s.length() > k) {
            int stringLength = s.length();
            StringBuilder resultBuilder = new StringBuilder();
          
            // Process the string in groups of k characters
            for (int startIndex = 0; startIndex < stringLength; startIndex += k) {
                int groupSum = 0;
              
                // Calculate sum of digits in current group
                // Use Math.min to handle the last group which might be smaller than k
                for (int currentIndex = startIndex; currentIndex < Math.min(startIndex + k, stringLength); currentIndex++) {
                    // Convert character digit to integer and add to sum
                    groupSum += s.charAt(currentIndex) - '0';
                }
              
                // Append the sum of current group to result
                resultBuilder.append(groupSum);
            }
          
            // Update string with the new transformed result
            s = resultBuilder.toString();
        }
      
        return s;
    }
}

