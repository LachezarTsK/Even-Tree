import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Solution {
  private static List[] nodesAndEdges;
  private static int[] totalNodes_inSubtrees;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfNodes = scanner.nextInt();
    int numberOfEdges = scanner.nextInt();

    initialize_nodesAndEdges(numberOfNodes);
    initialize_totalNodes_inSubtrees(numberOfNodes);

    for (int i = 0; i < numberOfEdges; i++) {
      int nodeOne = scanner.nextInt();
      int nodeTwo = scanner.nextInt();

      // As per the challenge statement, adding undirected edges.
      nodesAndEdges[nodeOne].add(nodeTwo);
      nodesAndEdges[nodeTwo].add(nodeOne);
    }
    scanner.close();

    int result = find_maxNumberOfEvenSubtrees(numberOfNodes);
    System.out.println(result);
  }

  /**
   * Finds the maxium number of subtrees that contain an even number of nodes.
   *
   * The root node of the tree has a value of '1' and the root node of each subtree has a value that
   * is less than the value of each node in its subtree.
   *
   * @return An integer, representing the number of edges to be cut, in order to have maxium number
   *         of subtrees that contain even number of nodes.
   */
  private static int find_maxNumberOfEvenSubtrees(int numberOfNodes) {

    int totalEdgesToCut_for_maxNumberOfEvenSubtrees = 0;
    for (int i = numberOfNodes; i > 1; i--) {

      List<Integer> edges = nodesAndEdges[i];

      // Start counting from leaves.
      for (int j = 0; j < edges.size(); j++) {

        // Count only nodes with less value than the current node, i.e. only nodes in subtrees.
        if (edges.get(j) > i) {
          totalNodes_inSubtrees[i] += totalNodes_inSubtrees[edges.get(j)];
        }
      }
      
      if (totalNodes_inSubtrees[i] % 2 == 0) {
        totalEdgesToCut_for_maxNumberOfEvenSubtrees++;
      }
    }

    return totalEdgesToCut_for_maxNumberOfEvenSubtrees;
  }

  /** 
   * Initializes the adjacency list that will stores tree. 
   * Values at index '0' are not applied in the solution,
   * so that each index corresponds to a node value.
   */
  @SuppressWarnings("unchecked")
  private static void initialize_nodesAndEdges(int numberOfNodes) {
    nodesAndEdges = new List[numberOfNodes + 1];
    for (int i = 1; i < numberOfNodes + 1; i++) {
      nodesAndEdges[i] = new ArrayList<Integer>();
    }
  }

  /** 
   * Assign an initial value of '1' to each node, since each node is a subtree itself. 
   * Values at index '0' are not applied in the solution,
   * so that each index corresponds to a node value.
   */
  private static void initialize_totalNodes_inSubtrees(int numberOfNodes) {
    totalNodes_inSubtrees = new int[numberOfNodes + 1];
    for (int i = 1; i < numberOfNodes + 1; i++) {
      totalNodes_inSubtrees[i] = 1;
    }
  }
}
