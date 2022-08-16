package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

    private final List<Algorithm> algorithms;
    private final List<String> algorithmNames;
    private final List<Integer> algorithmMethods;
    private final RadioButtonBundle algorithmsBundle;

    Algorithms(DrawableFactory drawableFactory, Drawable background) {
        algorithmNames = new ArrayList<>();
        algorithmNames.add("test1");
        algorithmNames.add("test2");
        algorithmNames.add("test3");

        algorithmMethods = new ArrayList<>();
        algorithmMethods.add(1);
        algorithmMethods.add(2);
        algorithmMethods.add(3);

        algorithms = new ArrayList<>();
        for(int i = 0; i < algorithmMethods.size(); i++) {
            Algorithm algorithm =
                    new Algorithm(drawableFactory, background, algorithmNames.get(i), algorithmMethods.get(i), i);
            algorithms.add(algorithm);
        }

        algorithmsBundle = new RadioButtonBundle(algorithms.stream().map(Algorithm::getRadioButton).toList());
        algorithmsBundle.update(algorithms.get(0).getRadioButton());
    }

}
