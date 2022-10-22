import java.util.*;
import java.io.*;
public class Q4NumberOfIslands {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File file = new File("output.txt");
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[][] graph = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        int count = 0;

        for(int i = 0;i<m; i++){
            String[] arr = br.readLine().split(" ");
            for(int j = 0;j<n; j++){
                graph[i][j] = Integer.parseInt(arr[j]);
            }
        }
        
        for(int i = 0;i<m; i++){
            for(int j = 0;j<n; j++){
                int val = graph[i][j];
                if(val == 1 && !visited[i][j]){
                    count++;
                    dfs(graph, i, j, visited);
                }
            }
        }
        
        System.out.println(count);
        
        br.close();
    }

    static void dfs(int[][] graph, int row, int col, boolean[][] visited) {
        if(row<0 || col<0 || row >= graph.length || col >= graph[0].length || graph[row][col] == 0 || visited[row][col]){
            return;
        }

        visited[row][col] = true;
        dfs(graph, row-1, col, visited);
        dfs(graph, row, col+1, visited);
        dfs(graph, row+1, col, visited);
        dfs(graph, row, col-1, visited);
    }
}
