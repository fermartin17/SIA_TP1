package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;

import java.util.ArrayList;
import java.util.List;

public class Node implements Neighbors<Node>, Comparable<Node>, Stateful<Node> {

    int value;
    int lookingFor; //value to test
    List<Node> neighbors;

    public Node(int value){
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    public Node(int value, int lookingFor){
        this.value = value;
        this.lookingFor = lookingFor;
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

    public Boolean isDone(){
        return this.value == this.lookingFor;
    }

    public Boolean isValid(){
        return true;
    }
}
