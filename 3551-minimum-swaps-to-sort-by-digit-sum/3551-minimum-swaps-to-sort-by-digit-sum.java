class Solution {
    public int minSwaps(int[] nums) {
        int arrayLength = nums.length;
      
        // Create a 2D array to store [digitSum, originalValue] pairs
        int[][] digitSumValuePairs = new int[arrayLength][2];
        for (int i = 0; i < arrayLength; i++) {
            digitSumValuePairs[i][0] = f(nums[i]);  // Calculate digit sum
            digitSumValuePairs[i][1] = nums[i];      // Store original value
        }
      
        // Sort array by digit sum first, then by value if digit sums are equal
        Arrays.sort(digitSumValuePairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);  // Compare by digit sum
            }
            return Integer.compare(a[1], b[1]);      // Compare by value if digit sums are equal
        });
      
        // Create a map from sorted values to their target positions
        Map<Integer, Integer> valueToTargetPosition = new HashMap<>();
        for (int i = 0; i < arrayLength; i++) {
            valueToTargetPosition.put(digitSumValuePairs[i][1], i);
        }
      
        // Track visited positions to identify cycles
        boolean[] visited = new boolean[arrayLength];
      
        // Count cycles in the permutation graph
        // Each cycle of length k requires (k-1) swaps
        int numberOfCycles = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (!visited[i]) {
                numberOfCycles++;
              
                // Traverse the current cycle
                int currentPosition = i;
                while (!visited[currentPosition]) {
                    visited[currentPosition] = true;
                    // Move to where the current element should go
                    currentPosition = valueToTargetPosition.get(nums[currentPosition]);
                }
            }
        }
      
        // Total swaps = total elements - number of cycles
        return arrayLength - numberOfCycles;
    }

    /**
     * Calculates the sum of digits of a given number
     * @param x the input number
     * @return sum of all digits in x
     */
    private int f(int x) {
        int digitSum = 0;
        while (x != 0) {
            digitSum += x % 10;  // Add the last digit
            x /= 10;              // Remove the last digit
        }
        return digitSum;
    }
}
