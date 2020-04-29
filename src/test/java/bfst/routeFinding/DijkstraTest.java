package bfst.routeFinding;

import bfst.OSMReader.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    private Graph graph = new Graph();

    private Node node0 = new Node(0, 0, 0);
    private Node node1 = new Node(1, 1, 0);
    private Node node2 = new Node(2, 1, 1);
    private Node node3 = new Node(3, 2, 3);
    private Node node4 = new Node(4, 4, 0);
    private Node node5 = new Node(5, 5, 5);
    private Node node6 = new Node(6, 6, 6);
    private Node node7 = new Node(7, 7, 7);
    private Node node8 = new Node(8, 8, 8);
    private Node node9 = new Node(9, -1, -1);

    private Edge edge0;
    private Edge edge1;
    private Edge edge2;
    private Edge edge3;
    private Edge edge4;
    private Edge edge5;
    private Edge edge6;
    private Edge edge7;
    private Edge edge8;

    private Street street;


    public void setVariables() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("highway");
        tags.add("primary");
        street = new Street(tags, 80);

        ArrayList<String> oneWayTags = new ArrayList<>();
        oneWayTags.add("highway");
        oneWayTags.add("primary");
        oneWayTags.add("oneway");
        oneWayTags.add("");

        Street oneWayStreet = new Street(oneWayTags, 80);
        edge0 = new Edge(node1, node0, street);
        edge1 = new Edge(node1, node2, street);
        edge2 = new Edge(node3, node1, oneWayStreet);
        edge3 = new Edge(node0, node2, street);
        edge4 = new Edge(node4, node1, street);
        edge5 = new Edge(node5, node6, street);
        edge6 = new Edge(node7, node4, street);
        edge7 = new Edge(node8, node7, street);
        edge8 = new Edge(node0, node9, street);

        graph.addEdge(edge0);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        graph.addEdge(edge8);

    }

    @Test
    void testDijkstra() {
        try {
            setVariables();
            Dijkstra dijkstra = new Dijkstra(graph, 4, 1, "Car", true);
            Assertions.assertEquals(1, dijkstra.getLastNode());
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertThrows(Exception.class, () -> {
            setVariables();
            Dijkstra dijkstra = new Dijkstra(graph, 0, 3, "Car", true);
        });

        Assertions.assertThrows(Exception.class, () -> {
            setVariables();
            Dijkstra dijkstra = new Dijkstra(graph, 0, 15, "Car", true);
        });
/*
        Assertions.assertThrows(Exception.class, () -> {
            setVariables();
            Dijkstra dijkstra = new Dijkstra(graph, 0, 1, "Bicycle", true);
        });

        Assertions.assertThrows(Exception.class, () -> {
            setVariables();
            Dijkstra dijkstra = new Dijkstra(graph, 0, 1, "Walk", true);
        });

 */
    }


    @Test
    void testPathTo() {
        try {
            setVariables();
            Dijkstra dijkstra = new Dijkstra(graph, 8, 9, "Car", true);

            ArrayList<Edge> part1 = dijkstra.pathTo(dijkstra.getLastNode(), 1);
            ArrayList<Edge> part2 = dijkstra.pathTo(dijkstra.getLastNode(), 2);
            System.out.println("last:" + dijkstra.getLastNode());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

}