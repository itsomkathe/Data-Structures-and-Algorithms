import java.util.*;
import java.io.*;
public class Q3ConnectedComponents {
    static class Edge{
        int src;
        int nbr;
        int wt;

        Edge(int src,int nbr,int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File file = new File("output.txt");
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);

        int vtces = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());

        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0;i<edges; i++){
            String[] edge = br.readLine().split(" ");
            int v1 = Integer.parseInt(edge[0]);
            int v2 = Integer.parseInt(edge[1]);
            int wt = Integer.parseInt(edge[2]);

            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }
        
        boolean[] visited = new boolean[vtces];
        for(int i = 0;i<vtces; i++){
            if(!visited[i]){
                ArrayList<Integer> list = new ArrayList<>();
                dfs(i, graph, visited, list);
                System.out.println(list);
            }
        }
        
        br.close();
    }

    static void dfs(int src, ArrayList<Edge>[] graph, boolean[] visited, ArrayList<Integer> list){
        if(visited[src]) return;

        visited[src] = true;
        list.add(src);
        ArrayList<Edge> nbrs = graph[src];
        for (Edge edge : nbrs) {
            int nbr = edge.nbr;
            dfs(nbr, graph, visited, list);
        }
    }

}

/*GeeksForGeeks Solution (Adjecancy Matrix)*/
class Solution {
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        int provinces = 0;
        for(int i=0; i<V; i++){
            if(!visited[i]){
                provinces++;
                dfs(i, adj, visited);
            }
        }
        return  provinces;
    }
    
    static void dfs(int src, ArrayList<ArrayList<Integer>> adj, boolean[] visited ){
        if(visited[src]) return;
        
        visited[src] = true;
        ArrayList<Integer> nbrs = adj.get(src);
        for(int i=0; i<adj.size(); i++){
            if(i == src) continue;
            int nbr = nbrs.get(i);
            if(nbr == 1){
                dfs(i, adj, visited);
            }
        }
    }
};
