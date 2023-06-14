package solver.newAlgorithms.node;

public interface Node {

    void stepIn(int branchIndex);

    void stepOut();

    int[] getChildren();

    int getValue();

    boolean isTerminal();
}
