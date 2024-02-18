package struct;

/**
 * @author humeishan
 * @version 1.0
 */
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public Graph(int[][] adjMatrix) {
        this.adjacencyMatrix = adjMatrix;
        this.numVertices = adjMatrix.length;
    }

    public void DFS(int v) {
        boolean[] visited = new boolean[numVertices];
        DFSUtil(v, visited);
    }

    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }

    public void BFS(int v) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.offer(v);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

}