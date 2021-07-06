import java.io.*;
        import java.util.*;
        import java.text.*;
        import java.math.*;
        import java.util.regex.*;

public class BfsShortestReach {
    public static class Graph {

        int node;
        Integer[][] edges;
        public Graph(int size) {
            node = size;
            edges = new Integer[size][size];

            for (int i=0; i<size; i++)
                for(int j=0; j<size; j++)
                {
                    if(i==j)
                        edges[i][j] = 0;
                    else
                        edges[i][j] = Integer.MAX_VALUE;
                }
        }

        public void addEdge(int first, int second) {
            edges[first][second]=6;
            edges[second][first]=6;
        }

        public int[] shortestReach(int startId) { // 0 indexed

            int[] visited = new int[node];

            List<Integer> willVisit = new ArrayList<Integer>();
            willVisit.add(startId);
            while (willVisit.isEmpty()==false){
                int currentId = willVisit.get(0);
                visited[currentId]=1;
                willVisit.remove(0);

                for (int j=0; j<edges[currentId].length; j++) {
                    if(currentId==j || edges[currentId][j] == Integer.MAX_VALUE){
                        continue;
                    }

                    if(visited[j]==1)
                        continue;

                    willVisit.add(j);
                    edges[startId][j]= Math.min(edges[startId][j], edges[currentId][j]+edges[startId][currentId]);
                }
            }

            int[] nodeWeight = new int[node];

            for(int i=0; i< node; i++){
                if(startId == i){
                    nodeWeight[i]=0;
                    continue;
                }
                if(edges[startId][i]==Integer.MAX_VALUE)
                {
                    nodeWeight[i]=-1;
                    continue;
                }

                nodeWeight[i]= edges[startId][i];
            }

            return nodeWeight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
