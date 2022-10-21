import java.util.*;
import java.io.*;
public class Q2AllPaths {
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
        
        printAllPaths(src, dest, graph, new boolean[vtces], "");
        br.close();
    }

    static void printAllPaths(int src, int dest, ArrayList<Edge>[] graph, boolean[] visited, String psf){
        if(src == dest){
            psf+=src;
            System.out.println(psf);
            return;
        }
        visited[src] = true;
        ArrayList<Edge> nbrs = graph[src];
        for (Edge edge : nbrs) {
            int nbr = edge.nbr;
            if(!visited[nbr]){
                printAllPaths(nbr, dest, graph, visited, psf+src+"-");
            }
        }
        visited[src] = false;
    }


    /* LeetCode Solution */
    /* Directed Acyclic Graph */
    static class Solution {
        static List<List<Integer>> paths = new ArrayList<>();
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            int vtces = graph.length;
            paths.clear();
            getPaths(0, vtces-1, graph, new boolean[vtces], new ArrayList<Integer>());
            return paths;
        }
        
        void getPaths(int src, int dest, int[][] graph, boolean[] visited, List<Integer> list){
            if(src == dest){
                paths.add(new ArrayList<Integer>(list));
                return;
            }
            if(src == 0) list.add(0);
            visited[src] = true;
            int[] nbrs = graph[src];
            
            for(int nbr: nbrs){
                if(!visited[nbr]){
                    list.add(nbr);
                    getPaths(nbr, dest, graph, visited, list);
                    list.remove(list.size()-1);
                }
            }
            if(src == 0) list.remove(list.size()-1);
            visited[src] = false;
        }
    }
}
