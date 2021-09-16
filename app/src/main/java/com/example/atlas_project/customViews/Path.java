package com.example.atlas_project.customViews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Path {
    private int dist[];
    private int prev[] ;
    private Set<Integer> visited;
    private PriorityQueue<Node> pq;
    private int total_points;
  //  private enum Direction {north , south , east , west} ;
    List<List<Node>> adj;

    public Path(int V)
    {
        this.total_points = V;
        dist = new int[V];
        prev = new int[V];
        visited = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V , new Node());
    }
    private void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < total_points; i++)
            dist[i] = Integer.MAX_VALUE;


        pq.add(new Node(src, 0));


        dist[src] = 0;
        prev[src] = 0 ;
        while (visited.size() != total_points) {

            if(pq.isEmpty())
                return ;

            int u = pq.remove().node;


            visited.add(u);

            visit_neighbours(u);
        }
    }


    private void visit_neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;


        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);


            if (!visited.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;


                if (newDistance < dist[v.node])
                {    dist[v.node] = newDistance;
                    prev[v.node] = u ;
                }

                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    private  ArrayList<Integer> find_path (String endpoint , int source ) {

        String[] e = endpoint.split("-");
        int point = Integer.parseInt(e[0]);


        System.out.println("The shorted path from node :");


        Stack<Integer> path = new Stack<>();
        path.push(point);
        while (point != source) {
            path.push(prev[point]);
            point = prev[point];
        }
        ArrayList<Integer> p = new ArrayList<>() ;
        while(!path.empty()){
            p.add(path.pop());
        }
    return p ;
    }


    public ArrayList<Integer> getPath(String endpoint , int source ,List<List<Node> > adj  ){
            dijkstra(adj ,source);

            return find_path(endpoint ,source);
        }



    }

class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}

