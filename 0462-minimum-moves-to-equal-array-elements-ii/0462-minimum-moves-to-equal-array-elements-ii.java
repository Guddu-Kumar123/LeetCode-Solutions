class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to find the median
        Arrays.sort(nums);
      
        // Get the median value (middle element for odd length, right-middle for even length)
        // Using bit shift for division by 2
        int median = nums[nums.length >> 1];
      
        // Calculate total moves needed
        int totalMoves = 0;
      
        // Sum up the absolute differences between each element and the median
        for (int num : nums) {
            totalMoves += Math.abs(num - median);
        }
      
        return totalMoves;
    }
}
