

public class Main {

    public static void main(String[] args) {
        AStarSearch aStarSearch = new AStarSearch(new Node(4, 2), new Node(17, 19),
                20, 20, new int[][]{{0, 2}, {0, 4}, {1, 3}});

        long startTime = System.currentTimeMillis();
        aStarSearch.findPath();
        long endTime = System.currentTimeMillis();
        aStarSearch.tracePath();
        long runtime = endTime - startTime;
        System.out.println("Completed in " + runtime + "ms");
    }
}
