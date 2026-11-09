class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Array to track the number of requests handled by each server
        int[] requestCount = new int[k];
      
        // Min heap to store busy servers, sorted by their end time
        // Each element is [endTime, serverIndex]
        PriorityQueue<int[]> busyServers = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
      
        // TreeSet to maintain available servers in sorted order
        TreeSet<Integer> availableServers = new TreeSet<>();
      
        // Initialize all servers as available
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
      
        // Process each incoming request
        for (int i = 0; i < arrival.length; i++) {
            int requestArrivalTime = arrival[i];
            int requestEndTime = requestArrivalTime + load[i];
          
            // Free up servers that have completed their tasks before current request arrives
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= requestArrivalTime) {
                int[] completedServer = busyServers.poll();
                availableServers.add(completedServer[1]);
            }
          
            // Skip this request if no servers are available
            if (availableServers.isEmpty()) {
                continue;
            }
          
            // Find the target server using round-robin with preference for server (i % k)
            // First try to find a server with index >= (i % k)
            Integer targetServer = availableServers.ceiling(i % k);
          
            // If no such server exists, wrap around and take the first available server
            if (targetServer == null) {
                targetServer = availableServers.first();
            }
          
            // Assign the request to the target server
            requestCount[targetServer]++;
            busyServers.offer(new int[] {requestEndTime, targetServer});
            availableServers.remove(targetServer);
        }
      
        // Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
      
        // Collect all servers that handled the maximum number of requests
        List<Integer> busiestServersList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiestServersList.add(i);
            }
        }
      
        return busiestServersList;
    }
}
