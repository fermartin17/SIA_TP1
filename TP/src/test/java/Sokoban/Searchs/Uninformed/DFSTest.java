package Sokoban.Searchs.Uninformed;

import Sokoban.Model.Result;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DFSTest {

    @Test
    public void correctOrderTest(){
        Node root = new Node(6, 5);
        Node aux = new Node(7, 5);
        aux.neighbors.add(new Node(5, 5));
        aux.neighbors.add(new Node(9, 5));
        root.neighbors.add(aux);
        root.neighbors.add(new Node(5, 5));
        List<Node> expected = new ArrayList<>();
        expected.add(new Node(6, 5));
        expected.add(new Node(7, 5));
        expected.add(new Node(5, 5));
        DFS<Node> dfs = new DFS<>();
        Result<Node> result = dfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.getPath().size());
        Assert.assertArrayEquals(expected.toArray(), result.getPath().toArray());
    }


        @Test
        public void NodesInFrontier(){
            Node root = new Node(6, 5);
            Node aux1 = new Node(7, 5);
            Node aux2 = new Node(9, 5);
            Node aux3 = new Node(10, 5);
            Node aux4= new Node(11, 5);
            aux1.neighbors.add(new Node(5, 5));
            aux1.neighbors.add(new Node(9, 5));
            root.neighbors.add(aux1);
            root.neighbors.add(aux2);
            root.neighbors.add(aux3);
            root.neighbors.add(aux4);
            DFS<Node> dfs = new DFS<>();
            Result<Node> result = dfs.search(root);
            Assert.assertNotNull(result);
            Assert.assertEquals(5,result.getNodesInFrontier());
        }
    }


