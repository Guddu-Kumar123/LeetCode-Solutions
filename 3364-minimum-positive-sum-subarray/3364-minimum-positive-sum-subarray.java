class Solution {
    /**
     * Finds the minimum sum of a subarray with positive sum and length between l and r.
     * 
     * @param nums The input list of integers
     * @param l The minimum allowed subarray length
     * @param r The maximum allowed subarray length
     * @return The minimum positive sum of valid subarrays, or -1 if no such subarray exists
     */
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        final int INF = Integer.MAX_VALUE;
        int minSum = INF;
      
        // Iterate through all possible starting positions
        for (int start = 0; start < n; start++) {
            int currentSum = 0;
          
            // Extend the subarray from the current starting position
            for (int end = start; end < n; end++) {
                // Add current element to the running sum
                currentSum += nums.get(end);
              
                // Calculate the current subarray length
                int subarrayLength = end - start + 1;
              
                // Check if subarray meets all conditions:
                // 1. Length is within the range [l, r]
                // 2. Sum is positive
                if (subarrayLength >= l && subarrayLength <= r && currentSum > 0) {
                    // Update minimum sum if current sum is smaller
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
      
        // Return -1 if no valid subarray was found, otherwise return the minimum sum
        return minSum == INF ? -1 : minSum;
    }
}

