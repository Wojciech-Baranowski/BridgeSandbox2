package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;
import solver.Algorithm;
import solver.algorithms.principalVariationSearch.PVSWithLookup;
import solver.newAlgorithms.algorithm.*;

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
            AlgorithmChangerOption algorithmChangerOption = new AlgorithmChangerOption(
                    drawableFactory, background, algorithmNames.get(i), algorithms.get(i), i);
            algorithmChangerOptions.add(algorithmChangerOption);
        }

        algorithmsBundle = new RadioButtonBundle(algorithmChangerOptions
                .stream()
                .map(AlgorithmChangerOption::getRadioButton)
                .toList());
        algorithmsBundle.update(algorithmChangerOptions.get(1).getRadioButton());
    }

    public void addAlgorithms() {

        algorithms.add(new PVSWithLookup());
        algorithmNames.add("PVS with lookup");

        algorithms.add(new PrincipalVariationSearch());
        algorithmNames.add("Principal Variation Search");

        algorithms.add(new NegaScout());
        algorithmNames.add("NegaScout");

        algorithms.add(new Mtdf());
        algorithmNames.add("MTD(f)");

        algorithms.add(new NegaCStar());
        algorithmNames.add("NegaC*");

        algorithms.add(new SssStar());
        algorithmNames.add("SSS*");

        algorithms.add(new DualStar());
        algorithmNames.add("Dual*");

        algorithms.add(new NegamaxAlphaBeta());
        algorithmNames.add("Negamax Alpha-Beta");
    }

}
