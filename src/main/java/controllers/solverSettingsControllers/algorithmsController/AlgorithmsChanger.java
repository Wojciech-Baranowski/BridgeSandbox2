package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;
import solver.Algorithm;
import solver.algorithms.principalVariationSearch.*;
import solver.algorithms.alphaBeta.AlphaBeta;
import solver.algorithms.alphaBetaWithMemory.AlphaBetaWithMemory;
import solver.algorithms.minmax.Minmax;
import solver.algorithms.mtd.Mtd;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmsChanger {

    private final List<AlgorithmChangerOption> algorithmChangerOptions;
    @Getter
    private final List<Algorithm> algorithms;
    @Getter
    private final List<String> algorithmNames;
    @Getter
    private final RadioButtonBundle algorithmsBundle;

    AlgorithmsChanger(DrawableFactory drawableFactory, Drawable background) {
        algorithms = new ArrayList<>();
        algorithmNames = new ArrayList<>();
        algorithmChangerOptions = new ArrayList<>();
        addAlgorithms();

        for (int i = 0; i < algorithms.size(); i++) {
            AlgorithmChangerOption algorithmChangerOption =
                    new AlgorithmChangerOption(drawableFactory, background, algorithmNames.get(i), algorithms.get(i), i);
            algorithmChangerOptions.add(algorithmChangerOption);
        }

        algorithmsBundle = new RadioButtonBundle(algorithmChangerOptions.stream().map(AlgorithmChangerOption::getRadioButton).toList());
        algorithmsBundle.update(algorithmChangerOptions.get(1).getRadioButton());
    }

    public void addAlgorithms() {

        algorithms.add(new PVSWithLookup());
        algorithmNames.add("PVS with lookup");

        algorithms.add(new PVSWithCutoff());
        algorithmNames.add("PVS with cutoff");

        algorithms.add(new PVSKillerHeuristicAtuAndHighestFirst());
        algorithmNames.add("PVS killer heuristic atu then highest first");

        algorithms.add(new PVSKillerHeuristicHighestFirst());
        algorithmNames.add("PVS killer heuristic highest first");

        algorithms.add(new PVSKillerHeuristicAtuFirst());
        algorithmNames.add("PVS killer heuristic atu first");

        algorithms.add(new PrincipalVariationSearch());
        algorithmNames.add("Principal Variation Search");

        algorithms.add(new Mtd());
        algorithmNames.add("MTD(f)");

        algorithms.add(new AlphaBetaWithMemory());
        algorithmNames.add("Alpha-beta with memory");

        algorithms.add(new AlphaBeta());
        algorithmNames.add("Alpha-beta");

        algorithms.add(new Minmax());
        algorithmNames.add("Minmax");
    }

}
