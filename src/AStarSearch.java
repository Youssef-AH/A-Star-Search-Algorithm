import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {

    private static final double V_H_MOVE_COST = 1.0;
    private static final double DIAGONAL_MOVE_COST = 1.414;

    private int runtime;
    private Node startNode;
    private Node targetNode;

    private PriorityQueue<Node> openNodes; // nodes to be examined
    private boolean[][] closedNodes; // nodes already examined
    private Node[][] grid;

    public AStarSearch(Node startNode, Node targetNode, int width, int height, int[][] obstacles) {

        this.grid = new Node[width][height];
        this.startNode = startNode;
        this.targetNode = targetNode;

        this.closedNodes = new boolean[width][height];
        this.openNodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Double.compare(node1.getFinalCost(), node2.getFinalCost());
            }
        });

        // initialising the nodes and their heuristics
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Node(x, y);
                grid[x][y].setHeuristicCost(Math.abs(x - targetNode.getX()) +
                        Math.abs(y - targetNode.getY()));

            }
        }

        startNode.setgCost(0.0);
        findPath();
    }

    private void findPath() {
        openNodes.add(startNode);

        while (!openNodes.isEmpty()) {
            Node current = openNodes.poll(); // returns node with lowest f cost
            closedNodes[current.getX()][current.getY()] = true;

            // generating all adjacent nodes
            double gNew, hNew, fNew;

            if (isValid(current.getX() - 1, current.getY())) {

            }
        }
    }

    private boolean isTarget(Node node) {
        return ((node.getX() == targetNode.getX()) && (node.getY() == targetNode.getY()));
    }

    private boolean isValid(int x, int y) {
        return (x >= 0) && (x < grid.length) && (y >= 0) && (y < grid.length);
    }
}
