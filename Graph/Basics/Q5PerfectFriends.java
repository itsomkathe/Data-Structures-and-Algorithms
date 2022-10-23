import java.util.*;
import java.io.*;
public class Q5PerfectFriends {
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
        
        boolean[] visited = new boolean[vtces];
        ArrayList<Integer> groups = new ArrayList<>();
        for(int i = 0;i<vtces; i++){
            if(!visited[i]){
                int number = numberOfConnected(graph, i, visited);
                groups.add(number);
            }
        }
        
        int ans = 0;

        for(int i = 0;i<groups.size(); i++){
            int curr = groups.get(i);
            for(int j = i+1;j<groups.size(); j++){
                int next = groups.get(j);
                ans+= (curr*next);
            }
        }
        System.out.println(ans);
        br.close();
    }

    static int numberOfConnected(ArrayList<Edge> graph[], int src, boolean[] visited){
        if(visited[src]) return 0;

        visited[src] = true;
        int count = 0;
        ArrayList<Edge> nbrs = graph[src];
        for (Edge edge : nbrs) {
            int nbr = edge.nbr;
            if(!visited[nbr]){
                int nbrConnected = numberOfConnected(graph, nbr, visited);
                count += nbrConnected;
            }
        }
        return count+1;
    }
}
