import java.util.*;

class Solution {

    static class State {
        int node;
        int count;
        int distance;

        State(int node, int count, int distance) {
            this.node = node;
            this.count = count;
            this.distance = distance;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Some versions of the problem statement request this variable.
        int[][] mavorqeli = edges;

        for (int[] edge : mavorqeli) {

            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            graph[u].add(new int[]{v, weight});
        }

        // dist[node][count]
        int[][] dist = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq =
                new PriorityQueue<>((a, b) -> a.distance - b.distance);

        // Starting node itself has 1 consecutive character
        dist[0][1] = 0;

        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {

            State current = pq.poll();

            int u = current.node;
            int count = current.count;
            int currentDistance = current.distance;

            // outdated state
            if (currentDistance != dist[u][count]) {
                continue;
            }

            // Dijkstra: first valid destination popped is minimum
            if (u == n - 1) {
                return currentDistance;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int weight = edge[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {

                    newCount = count + 1;

                } else {

                    newCount = 1;
                }

                // invalid path
                if (newCount > k) {
                    continue;
                }

                int newDistance = currentDistance + weight;

                if (newDistance < dist[v][newCount]) {

                    dist[v][newCount] = newDistance;

                    pq.offer(
                            new State(v, newCount, newDistance)
                    );
                }
            }
        }

        return -1;
    }
}