class Solution {
    // Days in each month (non-leap year)
    private int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Counts the number of days Alice and Bob are in town together.
     * 
     * @param arriveAlice Alice's arrival date in "MM-DD" format
     * @param leaveAlice Alice's departure date in "MM-DD" format
     * @param arriveBob Bob's arrival date in "MM-DD" format
     * @param leaveBob Bob's departure date in "MM-DD" format
     * @return The number of days they are both in town
     */
    public int countDaysTogether(
        String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
      
        // Find the later arrival date (start of overlap period)
        String overlapStart = arriveAlice.compareTo(arriveBob) < 0 ? arriveBob : arriveAlice;
      
        // Find the earlier departure date (end of overlap period)
        String overlapEnd = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
      
        // Convert dates to day of year
        int startDay = convertToDayOfYear(overlapStart);
        int endDay = convertToDayOfYear(overlapEnd);
      
        // Calculate overlap days (inclusive), return 0 if no overlap
        return Math.max(endDay - startDay + 1, 0);
    }

    /**
     * Converts a date string to the day number of the year.
     * 
     * @param dateString Date in "MM-DD" format
     * @return The day number of the year (1-365)
     */
    private int convertToDayOfYear(String dateString) {
        // Extract month (convert from 1-based to 0-based index)
        int monthIndex = Integer.parseInt(dateString.substring(0, 2)) - 1;
      
        // Sum up days from all previous months
        int totalDays = 0;
        for (int month = 0; month < monthIndex; month++) {
            totalDays += daysInMonth[month];
        }
      
        // Add the day of the current month
        totalDays += Integer.parseInt(dateString.substring(3));
      
        return totalDays;
    }
}
