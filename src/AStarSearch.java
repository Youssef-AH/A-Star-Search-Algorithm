import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {

    private int runtime;
    private Node startNode;
    private Node targetNode;

    private PriorityQueue<Node> openNodes; // nodes to be examined
    private boolean[][] closedNodes; // nodes already examined
    private int[][] grid; //

    public AStarSearch(int[][] grid, Node startNode, Node targetNode) {

        this.grid = grid;
        this.startNode = startNode;
        this.targetNode = targetNode;

        this.openNodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Double.compare(node1.getFinalCost(), node2.getFinalCost());
            }
        });
    }
}
