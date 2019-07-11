import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {

    private int runtime;
    private Node startNode;
    private Node targetNode;

    private PriorityQueue<Node> openNodes; // nodes to be examined
    private boolean[][] closedNodes; // nodes already examined
    private Node[][] grid;

    public AStarSearch(Node startNode, Node targetNode, int width, int height, int[][] blocks) {

        this.startNode = startNode;
        this.targetNode = targetNode;

        this.closedNodes = new boolean[width][height];
        this.openNodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Double.compare(node1.getFinalCost(), node2.getFinalCost());
            }
        });
    }
}
