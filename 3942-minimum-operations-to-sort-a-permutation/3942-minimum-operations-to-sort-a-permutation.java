class Solution {

    public int minOperations(int[] nums) {

        int n = nums.length;

        if (n <= 1) {
            return 0;
        }

        int indexOfZero = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                indexOfZero = i;
                break;
            }
        }

        // Case 1:
        // nums is a rotation of increasing order
        boolean increasingCycle = true;

        for (int i = 0; i < n; i++) {

            int next = (i + 1) % n;

            if ((nums[i] + 1) % n != nums[next]) {
                increasingCycle = false;
                break;
            }
        }

        if (increasingCycle) {

            // Option 1:
            // Rotate left until 0 comes to index 0
            int rotateOnly = indexOfZero;

            // Option 2:
            // Reverse -> rotations -> reverse
            int reverseRoute = n - indexOfZero + 2;

            return Math.min(rotateOnly, reverseRoute);
        }


        // Case 2:
        // nums is a rotation of decreasing order
        boolean decreasingCycle = true;

        for (int i = 0; i < n; i++) {

            int next = (i + 1) % n;

            int expected =
                (nums[i] - 1 + n) % n;

            if (expected != nums[next]) {
                decreasingCycle = false;
                break;
            }
        }

        if (decreasingCycle) {

            int ans = Integer.MAX_VALUE;

            // Option 1:
            // Reverse first
            // After reverse, determine how many left rotations needed
            int rotationsAfterReverse =
                (n - 1 - indexOfZero);

            ans = Math.min(
                ans,
                1 + rotationsAfterReverse
            );

            // Option 2:
            // Rotate first, then reverse
            int rotateBeforeReverse =
                (indexOfZero + 1) % n;

            ans = Math.min(
                ans,
                rotateBeforeReverse + 1
            );

            return ans;
        }

        return -1;
    }
}