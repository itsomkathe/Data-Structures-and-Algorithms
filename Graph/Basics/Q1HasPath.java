import java.util.*;
import java.io.*;
public class Q1HasPath {
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

        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());

        ArrayList<Edge>[] graph = new ArrayList[vtces];
        Arrays.fill(graph, new ArrayList<>());

        for(int i = 0;i<edges; i++){
            String[] edge = br.readLine().split(" ");
            int v1 = Integer.parseInt(edge[0]);
            int v2 = Integer.parseInt(edge[1]);
            int wt = Integer.parseInt(edge[2]);

            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }
        System.out.println(hasPath(graph, new boolean[vtces], src, dest));
        br.close();
    }

    static boolean hasPath(ArrayList<Edge>[] graph, boolean[] visited, int src, int dest){
        if(src == dest) return true;

        visited[src] = true;
        ArrayList<Edge> curr = graph[src];
        for (Edge ed : curr) {
            if(!visited[ed.nbr]){
                boolean nbrPath = hasPath(graph, visited, ed.nbr, dest);
                if(nbrPath){
                    return true;
                }
            }
        }
        return false;
    }
}