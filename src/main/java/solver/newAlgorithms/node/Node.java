package solver.newAlgorithms.node;

public interface Node {

    int[] getBranchesIndices();

    void stepIn(int branchIndex);

    void stepOut();
}
