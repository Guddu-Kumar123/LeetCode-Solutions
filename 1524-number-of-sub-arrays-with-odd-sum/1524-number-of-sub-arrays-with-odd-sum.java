class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
      
        // Count of prefix sums by parity: [even count, odd count]
        // Initially, we have one prefix with sum 0 (even)
        int[] prefixCountByParity = {1, 0};
      
        int result = 0;
        int prefixSum = 0;
      
        for (int num : arr) {
            // Update the running prefix sum
            prefixSum += num;
          
            // Determine current prefix sum parity (0 for even, 1 for odd)
            int currentParity = prefixSum & 1;
          
            // To get odd subarray sum ending at current position:
            // - If current prefix is odd, we need even prefix before
            // - If current prefix is even, we need odd prefix before
            // XOR with 1 flips the parity (0^1=1, 1^1=0)
            int requiredParity = currentParity ^ 1;
          
            // Add count of prefixes with required parity to result
            result = (result + prefixCountByParity[requiredParity]) % MOD;
          
            // Increment count for current prefix sum parity
            prefixCountByParity[currentParity]++;
        }
      
        return result;
    }
}
