class Solution {
    public int maxSum(List<Integer> nums, int k) {
        // Define modulo constant for preventing integer overflow
        final int MOD = (int) 1e9 + 7;
      
        // Array to count the number of set bits at each bit position (0-30)
        // cnt[i] represents how many numbers have bit i set to 1
        int[] bitCount = new int[31];
      
        // Count set bits at each position across all numbers
        for (int number : nums) {
            for (int bitPosition = 0; bitPosition < 31; bitPosition++) {
                // Check if bit at position 'bitPosition' is set (equals 1)
                if ((number >> bitPosition & 1) == 1) {
                    bitCount[bitPosition]++;
                }
            }
        }
      
        // Variable to store the sum of squares
        long sumOfSquares = 0;
      
        // Construct k numbers to maximize the sum of their squares
        while (k-- > 0) {
            int constructedNumber = 0;
          
            // Build a number by taking one bit from each position where available
            for (int bitPosition = 0; bitPosition < 31; bitPosition++) {
                if (bitCount[bitPosition] > 0) {
                    // Set the bit at current position in the constructed number
                    constructedNumber |= 1 << bitPosition;
                    // Decrement the count of available bits at this position
                    bitCount[bitPosition]--;
                }
            }
          
            // Add the square of the constructed number to the sum
            // Use long multiplication to prevent overflow
            sumOfSquares = (sumOfSquares + 1L * constructedNumber * constructedNumber) % MOD;
        }
      
        // Return the final sum as an integer
        return (int) sumOfSquares;
    }
}
