package Sokoban.Searchs.Uninformed;
import Sokoban.Interfaces.Neighbors;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class BFSTest{

    @Test
    public void searchNoResultTest() {
        Node root = new Node(4);
        root.neighbors.add(new Node(3));
        root.neighbors.add(new Node(1));
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, new Node(7));
        Assert.assertNull(result);
    }

    @Test
    public void searchNoResultTest2() {
        Node root = new Node(1);
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, new Node(7));
        Assert.assertNull(result);
    }

    @Test
    public void searchResultFoundTest() {
        Node root = new Node(4);
        root.neighbors.add(new Node(5));
        root.neighbors.add(new Node(7));
        List<Node> expected = new ArrayList<>();
        expected.add(new Node(4));
        expected.add(new Node(7));
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, new Node(7));
        Assert.assertNotNull(result);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void searchResultFoundTest2() {
        Node root = new Node(3);
        Node aux1 = new Node(7);
        aux1.neighbors.add(new Node(9));
        root.neighbors.add(aux1);
        Node aux2 = new Node(4);
        aux2.neighbors.add(new Node(1));
        aux2.neighbors.add(new Node(8));
        root.neighbors.add(aux2);
        List<Node> expected = new ArrayList<>();
        expected.add(new Node(3));
        expected.add(new Node(4));
        expected.add(new Node(1));
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, new Node(1));
        Assert.assertNotNull(result);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void correctOrderTest(){
        Node root = new Node(6);
        root.neighbors.add(new Node(5));
        Node aux = new Node(7);
        aux.neighbors.add(new Node(5));
        aux.neighbors.add(new Node(9));
        root.neighbors.add(aux);
        List<Node> expected = new ArrayList<>();
        expected.add(new Node(6));
        expected.add(new Node(5));
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, new Node(5));
        Assert.assertNotNull(result);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void NullRootTest(){
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(null, null);
        Assert.assertNull(result);
    }

    @Test
    public void onlyNodeNotFoundTest(){
        Node root = new Node(1);
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, new Node(3));
        Assert.assertNull(result);
    }

    @Test
    public void onlyNodeFoundTest(){
        Node root = new Node(7);
        BFS<Node> bfs = new BFS<>();
        List<Node> result = bfs.search(root, root);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(root, result.get(0));
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

        @Override
        public List<Node> getNeighbors() {return this.neighbors;}

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.value, o.value);
        }

        public String toString(){
            return String.valueOf(this.value);
        }

        public boolean equals(Object o){
            if (o == this) return true;
            if (o == null || o.getClass() != this.getClass()) return false;
            Node n = (Node) o;
            return this.value == n.value;
        }
    }
}
