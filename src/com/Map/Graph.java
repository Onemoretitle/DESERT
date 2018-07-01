
package com.Map;

//import game.SquareForField;
import java.util.ArrayList;
import static java.util.Arrays.fill;

public class Graph {
    
    public static final int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
    private int vNum;                       // количество вершин
    private int[][] graph;                  // матрица смежности
    /*private SquareForField vertexes[];
    private HashMap<SquareForField, Integer> indexesObj;*/
    private int indexV = 0;
    
    
    private int[] dist; // массив расстояния. dist[v] = минимальное_расстояние(start, v)
    private int[] prev;
    
    public Graph (int _vNum) {
        vNum = _vNum;
        graph = new int[vNum][vNum];
        for (int i = 0; i < vNum; i++)
            for (int j = 0; j < vNum; j++)
                graph[i][j] = INF;
        for (int i = 0; i < vNum; i++)
            graph[i][i] = 0;
        /*vertexes =  new SquareForField[vNum];
        indexesObj = new HashMap<>(vNum);*/
        dist = new int [vNum]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)
        prev = new int [vNum];
    }
    
    /*public void addVertex(SquareForField newVertex) {
        vertexes[indexV] = newVertex;
        indexesObj.put(newVertex, indexV++);
    }*/
    
    public void addEdge (int v, int u, int len) {
         if (u < vNum && v < vNum) {
            graph[v][u] = len;
            graph[u][v] = len;
         } else
               System.out.println("ERROR");
    }
    public void removeEdge(int v, int u) {
        if (u < vNum && v < vNum) {
            graph[v][u] = INF;
            graph[u][v] = INF;
        }
    }
    
    
    /* Алгоритм Дейкстры за O(V^2) */
    public void dijkstra(int start) {
        boolean[] used = new boolean [vNum]; // массив пометок
        
        
        fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
        fill(prev, -1); 
        dist[start] = 0; // для начальной вершины положим 0

        for (;;) {
            int v = -1;
            for (int nv = 0; nv < vNum; nv++) // перебираем вершины
                if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                    v = nv;
            if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее
            for (int nv = 0; nv < vNum; nv++)
                if (!used[nv] && graph[v][nv] < INF){ // для всех непомеченных смежных
                    if (dist[nv] > dist[v] + graph[v][nv]) {
                        prev[nv] = v;
                        dist[nv] = dist[v] + graph[v][nv];  // улучшаем оценку расстояния (релаксация)
                    }
                    //dist[nv] = min(dist[nv], dist[v] + graph[v][nv]); 
                }
        }
    }
    
    /*public SquareForField getVertex(int i) {
        if (i < 0 || i > indexV)
            return null;
        return vertexes[i];
    }*/
    
    /*public int getIndexOfVertex(SquareForField obj) {
        if (obj == null)
            return -1;
        Integer res = indexesObj.get(obj);
        return res;
    }*/
    
        //Восстановление пути
    public ArrayList<Integer> getPathToVertex(int numVertex) {
        ArrayList<Integer> path = null;
        if (numVertex >= 0 && numVertex < vNum) {
            path = new ArrayList<>();
            int i;
            for (i = numVertex; prev[i] != -1; i = prev[i])
                path.add(i);
            path.add(i);
        }
        return path;
    }
    
    
   /* public void display() {
        for (int i = 0; i < indexV; i++)
            System.out.println(vertexes[i]);
    }*/
    
     /*public void render(Graphics2D g) {
         Color oldCol = g.getColor();
         g.setColor(Color.WHITE);
         for (int i = 0; i < vNum; i++)
            for (int j = 0; j < vNum; j++)
                if (graph[i][j] != Graph.INF) {
                    Point<Integer> p1 = vertexes[i].getPoint();
                    Point<Integer> p2 = vertexes[j].getPoint();
                    g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                }
        g.setColor(oldCol);
     } */
    
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < vNum; i++) {
            for (int j = 0; j < vNum; j++)
                str += graph[i][j];
            str += "\n";
        }
        return str;
    }
    
    
}
