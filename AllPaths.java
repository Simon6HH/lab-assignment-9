import java.util.ArrayList;

public class AllPaths {

    int[][] graphs; /** 2d graphs initialization **/
    ArrayList<ArrayList<Integer>> paths = new ArrayList<>(); /** arraylist paths **/

    public int[][] create(int[][] input) { /** adjacency graph creation **/
        graphs = new int[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                graphs[i][input[i][j]] = 1; /** if 1, it points, if 0, it doesn't **/
            }
        }
        return graphs;
    }

    public boolean tryPath(ArrayList<Integer> previous, int current) {
        ArrayList<Integer> path = (ArrayList)previous.clone(); /** clone of path **/
        path.add(current);
        for (int i = 0; i < graphs[current].length; i++) {
            if (graphs[current][i] == 1) {
                if (i == graphs.length - 1) { /** path ends if its -1  **/
                    path.add(i);
                    paths.add(path);
                    return true;
                } else {
                    tryPath(path, i);
                }
            }
        }
        return false;
    }

    public int[][] allPaths(int[][] input) {
        paths.clear(); /** need to clear because its a global variable, need to clear for this check function **/
        graphs = create(input); /** creates graph with the input **/
        ArrayList previous = new ArrayList<>();
        tryPath(previous,0);
        int[][] arrPaths = new int[paths.size()][]; /** new array path 2d array which converts the integer array **/
        for (int i = 0; i < paths.size(); i++) {
            arrPaths[i] = new int[paths.get(i).size()];
            for (int j = 0; j < paths.get(i).size(); j++) {
                arrPaths[i][j] = paths.get(i).get(j);
            }
        }
        return arrPaths; /** returns 2d array with 0 and -1 vertex's **/
    }

    private void toString(int[][] arr) { /** prints the graph in row column form, binary sort **/
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        int[][] TEST1 = new int[][] {{1,2},{3},{3},{}};
        int[][] TEST2 = new int[][] {{1},{}};
        int[][] TEST3 = new int[][] {{4,3,1},{3,2,4},{3},{4},{}};
        AllPaths test = new AllPaths();

        int[][] path1 = test.allPaths(TEST1);
        System.out.println("TEST 1 : ");
        test.toString(path1);

        int[][] path2 = test.allPaths(TEST2);
        System.out.println("TEST 2 : ");
        test.toString(path2);

        int[][] path3 = test.allPaths(TEST3);
        System.out.println("TEST 3 : ");
        test.toString(path3);
    }
}