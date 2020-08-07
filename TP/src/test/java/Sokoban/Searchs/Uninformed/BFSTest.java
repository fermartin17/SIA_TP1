package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;


public class BFSTest{

    @Test
    public void searchNoResultTest() {
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(4);
        root.neighbors.add(new Node(3));
        root.neighbors.add(new Node(1));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNull(result);
    }

    @Test
    public void searchNoResultTest2() {
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(1);
        root.neighbors.add(new Node(0));
        root.neighbors.add(new Node(-1));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNull(result);
    }

    @Test
    public void searchResultFoundTest() {
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(4);
        root.neighbors.add(new Node(5));
        root.neighbors.add(new Node(7));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
    }

    @Test
    public void correctOrderTest(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(6);
        root.neighbors.add(new Node(5));
        root.neighbors.add(new Node(7));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.value);
    }

    @Test
    public void correctOrderTest2(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(1);
        root.neighbors.add(new Node(6));
        root.neighbors.add(new Node(7));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.value);
    }

    @Test
    public void correctOrderTest3(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(1);
        Node aux1 = new Node(2);
        aux1.neighbors.add(new Node(3));
        aux1.neighbors.add(new Node(8));
        root.neighbors.add(aux1);
        root.neighbors.add(new Node(9));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertEquals(9, result.value);
    }

    @Test
    public void NullRootTest(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(null);
        Assert.assertNull(result);
    }

    @Test
    public void onlyNodeNotFoundTest(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(1);
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNull(result);
    }

    @Test
    public void onlyNodeFoundTest(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5
        heuristics.add((i)->(i.value > 5));
        Node root = new Node(7);
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
    }

    @Test
    public void multipleHeuristicsTest(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5 y menor a 7
        heuristics.add((i)->(i.value > 5));
        heuristics.add((i)->(i.value < 7));
        Node root = new Node(6);
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.value);
    }

    @Test
    public void multipleHeuristicsTest2(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5 y menor a 7
        heuristics.add((i)->(i.value > 5));
        heuristics.add((i)->(i.value < 5));
        Node root = new Node(6);
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNull(result);
    }

    @Test
    public void multipleHeuristicsTest3(){
        List<Heuristic<Node>> heuristics = new ArrayList<>();
        //defino heurística, busco a algún elemento mayor a 5 y menor a 7
        heuristics.add((i)->(i.value > 5));
        heuristics.add((i)->(i.value < 7));
        Node root = new Node(8);
        root.neighbors.add(new Node(1));
        root.neighbors.add(new Node(6));
        BFS<Node> bfs = new BFS<>(heuristics);
        Node result = bfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.value);
    }

    private class Node implements Neighbors<Node>, Comparable<Node>{
        int value;
        List<Node> neighbors;

        public Node(int value){
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public int getValue(){
            return this.value;
        }

        public List<Node> getNeighbors() {return this.neighbors;}

        @Override
        public List<Node> getNeighbors(Node n) {return n.getNeighbors();}

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.value, o.value);
        }

        public String toString(){
            return String.valueOf(this.value);
        }
    }
}
