class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        // Initialize the maximum length to 1 (single element is always monotonic)
        int maxLength = 1;
      
        // First pass: Find the longest strictly increasing subarray
        // currentLength tracks the length of current increasing sequence
        int currentLength = 1;
        for (int i = 1; i < nums.length; i++) {
            // If current element is greater than previous, extend the sequence
            if (nums[i - 1] < nums[i]) {
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                // Reset counter when sequence breaks
                currentLength = 1;
            }
        }
      
        // Second pass: Find the longest strictly decreasing subarray
        // Reset currentLength for decreasing sequence check
        currentLength = 1;
        for (int i = 1; i < nums.length; i++) {
            // If current element is less than previous, extend the sequence
            if (nums[i - 1] > nums[i]) {
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                // Reset counter when sequence breaks
                currentLength = 1;
            }
        }
      
        // Return the maximum length found from both increasing and decreasing sequences
        return maxLength;
    }
}
