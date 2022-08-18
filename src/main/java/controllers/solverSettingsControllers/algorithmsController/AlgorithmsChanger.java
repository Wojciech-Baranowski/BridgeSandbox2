package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;
import solver.Algorithm;
import solver.algorithms.naive.Naive;

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
        algorithms.add(new Naive());
        algorithmNames.add("Naive algorithm");

        for (int i = 0; i < algorithms.size(); i++) {
            AlgorithmChangerOption algorithmChangerOption =
                    new AlgorithmChangerOption(drawableFactory, background, algorithmNames.get(i), algorithms.get(i), i);
            algorithmChangerOptions.add(algorithmChangerOption);
        }

        algorithmsBundle = new RadioButtonBundle(algorithmChangerOptions.stream().map(AlgorithmChangerOption::getRadioButton).toList());
        algorithmsBundle.update(algorithmChangerOptions.get(0).getRadioButton());
    }

}
