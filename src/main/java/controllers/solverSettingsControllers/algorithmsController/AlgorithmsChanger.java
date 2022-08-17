package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;
import solver.Solver;
import solver.algorithms.Naive;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmsChanger {

    private final List<AlgorithmChangerOption> algorithmChangerOptions;
    @Getter
    private final List<Solver> solvers;
    @Getter
    private final RadioButtonBundle algorithmsBundle;

    AlgorithmsChanger(DrawableFactory drawableFactory, Drawable background) {
        solvers = new ArrayList<>();
        solvers.add(new Solver(new Naive(), "Naive algorithm"));

        algorithmChangerOptions = new ArrayList<>();
        for (int i = 0; i < solvers.size(); i++) {
            AlgorithmChangerOption algorithmChangerOption =
                    new AlgorithmChangerOption(drawableFactory, background, solvers.get(i), i);
            algorithmChangerOptions.add(algorithmChangerOption);
        }

        algorithmsBundle = new RadioButtonBundle(algorithmChangerOptions.stream().map(AlgorithmChangerOption::getRadioButton).toList());
        algorithmsBundle.update(algorithmChangerOptions.get(0).getRadioButton());
    }

}
