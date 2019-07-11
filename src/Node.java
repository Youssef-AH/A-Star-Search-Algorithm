

public class Node {

    // grid coordinates
    private int x, y;

    // parent node
    private Node parent;

    // finalCost = heuristicCost + gCosts
    private double finalCost, heuristicCost, gCost;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }
}
