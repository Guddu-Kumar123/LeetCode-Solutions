class Solution {
    /**
     * Find all indices where target would appear in a sorted array.
     *
     * @param nums   The input array of integers
     * @param target The target value to find indices for
     * @return A list of indices where target appears after sorting
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        // Sort the array in ascending order
        Arrays.sort(nums);

        // Initialize result list to store target indices
        List<Integer> result = new ArrayList<>();

        // Iterate through sorted array to find all occurrences of target
        for (int i = 0; i < nums.length; i++) {
            // If current element equals target, add its index to result
            if (nums[i] == target) {
                result.add(i);
            }
        }

        return result;
    }
}
