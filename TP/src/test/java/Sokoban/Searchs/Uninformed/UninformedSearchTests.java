package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.UninformedSearch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//clase para probar todos los métodos de búsqueda desinformada
public class UninformedSearchTests {

    List<UninformedSearch<Node>> searches;

    @Before
    public void setupSearches(){
        searches = new ArrayList<>();
        searches.add(new BFS<>());
        searches.add(new DFS<>());
    }

    @Test
    public void NullRootTest(){
        searches.stream().map(s -> s.search(null)).forEach(Assert::assertNull);
    }

    @Test
    public void searchNoResultTest() {
        searches.stream().map(s -> s.search(new Node(4, 3))).forEach(Assert::assertNull);
    }

    @Test
    public void searchNoResultTest1() {
        for(UninformedSearch<Node> search : searches){
            List<Node> result = (List<Node>) search.search(new Node(4, 4));
            Assert.assertNotNull(result);
            Assert.assertEquals(1, result.size());
            Assert.assertEquals(4, result.get(0).getValue());
        }
    }

    @Test
    public void searchResultFoundTest() {
        Node root = new Node(4, 7);
        root.neighbors.add(new Node(5, 7));
        root.neighbors.add(new Node(7, 7));
        List<Node> expected = new LinkedList<>();
        expected.add(new Node(4));
        expected.add(new Node(7));
        for(UninformedSearch<Node> search : searches){
            List<Node> result = (List<Node>) search.search(root);
            Assert.assertNotNull(result);
            Assert.assertEquals(2, result.size());
            Assert.assertArrayEquals(expected.toArray(), result.toArray());
        }
    }

    @Test
    public void searchResultFoundTest2() {
        Node root = new Node(3, 1);
        Node aux1 = new Node(7, 1);
        aux1.neighbors.add(new Node(9, 1));
        root.neighbors.add(aux1);
        Node aux2 = new Node(4, 1);
        aux2.neighbors.add(new Node(1, 1));
        aux2.neighbors.add(new Node(8, 1));
        root.neighbors.add(aux2);
        List<Node> expected = new ArrayList<>();
        expected.add(new Node(3, 1));
        expected.add(new Node(4, 1));
        expected.add(new Node(1, 1));
        for(UninformedSearch<Node> search : searches){
            List<Node> result = (List<Node>) search.search(root);
            Assert.assertNotNull(result);
            Assert.assertEquals(3, result.size());
            Assert.assertArrayEquals(expected.toArray(), result.toArray());
        }
    }
}
