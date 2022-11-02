import java.util.*;
import java.io.*;
public class Q6Hamiltonian {
    static class Edge{
        int src;
        int nbr;

        Edge(int src,int nbr){
            this.src = src;
            this.nbr = nbr;
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

            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }
        System.out.println(dfs(graph, 0, new HashSet<Integer>(), "0->"));
        br.close();
    }

/* Hamiltonian Path: Path that visits ALL vertices once*/
/* Hamiltonian Cycle: In hamiltonian path, if there is an edge between first and last node, it is a cycle */
    static boolean dfs(ArrayList<Edge>[] graph, int src, HashSet<Integer> visited, String path){
        visited.add(src);
        if(visited.size() == graph.length){
            System.out.println(path);
            return true;
        }

        ArrayList<Edge> nbrs = graph[src];
        for(Edge edge: nbrs){
            int nbr = edge.nbr;
            if(!visited.contains(nbr)){
                boolean res =  dfs(graph, nbr, visited, path+nbr+"->");
                if(res) return true;
            }
        }

        visited.remove(src);
        return false;
    }
}
