package Sokoban.Searchs.Uninformed;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Model.Result;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class BFSTest{

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
        expected.add(new Node(5, 5));
        BFS<Node> bfs = new BFS<>();
        Result<Node> result = bfs.search(root);
        Assert.assertNotNull(result);
        Assert.assertArrayEquals(expected.toArray(), result.getPath().toArray());
    }

}
