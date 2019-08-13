package mcts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node {
    State state;
    Node parent;
    List<Node> childArray;
    Random random = new Random();

    public Node(Node node) {
        this.childArray = new ArrayList<>();
        this.state = new State(node.getState());
        if (node.getParent() != null)
            this.parent = node.getParent();
        List<Node> childArray = node.getChildArray();
        for (Node child : childArray) {
            this.childArray.add(new Node(child));
        }
	}

	public Node(State state2) {
        this.state = state2;
        this.childArray = new ArrayList<Node>();
	}

	public Node() {
        state = new State();
        childArray = new ArrayList<Node>();
	}

	public State getState(){
        return state;
    }

	public List<Node> getChildArray() {
		return childArray;
	}

	public Node getRandomChildNode() {
		return childArray.get(random.nextInt(childArray.size()));
	}

	public Node getChildWithMaxScore() {
        return Collections.max(this.childArray, Comparator.comparing(c -> {
            return c.getState().getWinScore(); 
        }));
	}

	public void setParent(Node node) {
        parent = node;
	}

	public Node getParent() {
		return parent;
	}
}