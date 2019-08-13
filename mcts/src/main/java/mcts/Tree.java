package mcts;

public class Tree {
    Node root;

    public Tree(){
        root = new Node();
    }

    public Node getRoot(){
        return root;
    }

	public void setRoot(Node winnerNode) {
        root = winnerNode;
	}
}