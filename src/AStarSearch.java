import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {

    private static final double V_H_MOVE_COST = 1.0;
    private static final double DIAGONAL_MOVE_COST = 1.414;

    private int tar;
    private Node startNode;
    private Node targetNode;

    private PriorityQueue<Node> openNodes; // nodes to be examined
    private boolean[][] closedNodes; // nodes already examined
    private Node[][] grid;

    private AStarSearch(Node startNode, Node targetNode, int width, int height, int[][] obstacles) {

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
                grid[x][y].setSolution(false);
            }
        }

        grid[startNode.getX()][startNode.getY()].setFinalCost(0.0);
    }

    private void findPath() {
        openNodes.add(startNode); // add start node to open list

        Node current;
        while (!openNodes.isEmpty()) {
            current = openNodes.poll(); // returns node with lowest f
            if (current == null) break;

            closedNodes[current.getX()][current.getY()] = true;
            if (isTarget(current.getX(), current.getY())) return;

            // processing all adjacent nodes
            if (current.getX() - 1 >= 0) {
                processNode(current.getX() - 1, current.getY(), current, V_H_MOVE_COST);

                if (current.getY() - 1 >= 0) {
                    processNode(current.getX() - 1, current.getY() - 1, current, DIAGONAL_MOVE_COST);
                }
                if (current.getY() + 1 < grid[0].length) {
                    processNode(current.getX() - 1, current.getY() + 1, current, +DIAGONAL_MOVE_COST);
                }
            }

            if (current.getY() - 1 >= 0) {
                processNode(current.getX(), current.getY() - 1, current, V_H_MOVE_COST);
            }

            if (current.getY() + 1 < grid[0].length) {
                processNode(current.getX(), current.getY() + 1, current, V_H_MOVE_COST);
            }

            if (current.getX() + 1 < grid.length) {
                processNode(current.getX() + 1, current.getY(), current, V_H_MOVE_COST);

                if (current.getY() - 1 >= 0) {
                    processNode(current.getX() + 1, current.getY() - 1, current, DIAGONAL_MOVE_COST);
                }

                if (current.getY() + 1 < grid[0].length) {
                    processNode(current.getX() + 1, current.getY() + 1, current, DIAGONAL_MOVE_COST);
                }
            }
        }

//            processNode(current.getX() - 1, current.getY(), current, V_H_MOVE_COST);
//            processNode(current.getX() - 1, current.getY() - 1, current, DIAGONAL_MOVE_COST);
//            processNode(current.getX() - 1, current.getY() + 1, current, DIAGONAL_MOVE_COST);
//            processNode(current.getX(), current.getY() - 1, current, V_H_MOVE_COST);
//            processNode(current.getX(), current.getY() + 1, current, V_H_MOVE_COST);
//            processNode(current.getX() + 1, current.getY(), current, V_H_MOVE_COST);
//            processNode(current.getX() + 1, current.getY() - 1, current, DIAGONAL_MOVE_COST);
//            processNode(current.getX() + 1, current.getY() + 1, current, DIAGONAL_MOVE_COST);
    }

    private void processNode(int x, int y, Node current, double moveCost) {
        if (grid[x][y] == null || closedNodes[x][y]) return; // ignore this node

        double gNew = current.getgCost() + moveCost;
        double hNew = grid[x][y].getHeuristicCost();
        double fNew = gNew + hNew;

        if (!openNodes.contains(grid[x][y]) || grid[x][y].getFinalCost() > fNew) {
            grid[x][y].setFinalCost(fNew);
            grid[x][y].setParent(current);

            if (!openNodes.contains(grid[x][y])) openNodes.add(grid[x][y]);
        }
    }

    private void tracePath() {
        if (closedNodes[targetNode.getX()][targetNode.getY()]) {
            System.out.println("=================PATH=================");
            Node current = grid[targetNode.getX()][targetNode.getY()];
            grid[targetNode.getX()][targetNode.getY()].setSolution(true);

            while (current.getParent() != null) {
                String isStartNode = current.getParent().equals(startNode) ? " " : "<------";

                System.out.print("[" + current.getParent().getX() + ","
                + current.getParent().getY() + "]" + isStartNode);
                grid[current.getParent().getX()][current.getParent().getY()].setSolution(true);
                current = current.getParent();
            }

            System.out.println("\n");
            System.out.println("=================GRID=================");
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[x].length; y++) {
                    if (x == startNode.getX() && y == startNode.getY()) {
                        System.out.print("SN  ");
                    } else if (x == targetNode.getX() && y == targetNode.getY()) {
                        System.out.print("DN  ");
                    } else if (grid[x][y] != null) {
                        System.out.printf("%-3s ", grid[x][y].isSolution() ? "X" : "0");
                    } else System.out.print("BN ");
                }
                System.out.println();
            }
            System.out.println();
        } else System.out.println("=================PATH NOT FOUND================");
    }

    private boolean isTarget(int x, int y) {
        return ((x == targetNode.getX()) && (y == targetNode.getY()));
    }

    private boolean isValid(int x, int y) {
        return grid[x][y] != null;
    }

    public static void main(String[] args) {
        AStarSearch aStarSearch = new AStarSearch(new Node(4, 2), new Node(17, 19),
                20, 20, new int[][]{{0, 2}, {0, 4}, {1, 3}});

        aStarSearch.findPath();
        aStarSearch.tracePath();
    }
}
