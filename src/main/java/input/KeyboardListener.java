package input;

import common.Observable;
import common.Observer;
import input.inputCombination.InputElement;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.*;

import static input.inputCombination.ActionType.*;

public class KeyboardListener implements Observable, KeyListener {

    private static final int KEY_NUMBER = 256;
    private final boolean[] upJust;
    private final boolean[] downJust;
    private final boolean[] upLast;
    private final boolean[] downLast;
    private final List<Observer> observers;

    KeyboardListener() {
        upJust = new boolean[KEY_NUMBER];
        downJust = new boolean[KEY_NUMBER];
        upLast = new boolean[KEY_NUMBER];
        downLast = new boolean[KEY_NUMBER];
        Arrays.fill(upJust, true);
        Arrays.fill(downJust, false);
        Arrays.fill(upLast, false);
        Arrays.fill(downLast, false);
        observers = new LinkedList<>();
    }

    public Set<InputElement> getActivatedInputElements() {
        Set<InputElement> currentCombination = new HashSet<>();
        for (int i = 0; i < KEY_NUMBER; i++) {
            InputEvent inputEvent = getKeyboardEventWithKeyCode(i);
            if (upJust[i]) {
                currentCombination.add(new InputElement(UP, inputEvent));
            }
            if (upLast[i]) {
                currentCombination.add(new InputElement(FREE, inputEvent));
            }
            if (downJust[i]) {
                currentCombination.add(new InputElement(DOWN, inputEvent));
            }
            if (downLast[i]) {
                currentCombination.add(new InputElement(HELD, inputEvent));
            }
        }
        return currentCombination;
    }

    public boolean isActivated(InputElement inputElement) {
        int keyCode = ((KeyEvent) inputElement.getInputEvent()).getKeyCode();
        return switch (inputElement.getActionType()) {
            case UP -> upJust[keyCode];
            case FREE -> upLast[keyCode];
            case DOWN -> downJust[keyCode];
            case HELD -> downLast[keyCode];
        };
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        upLast[keyCode] = !downLast[keyCode] && !downJust[keyCode];
        upJust[keyCode] = false;
        downLast[keyCode] = downLast[keyCode] || downJust[keyCode];
        downJust[keyCode] = upLast[keyCode] && !downJust[keyCode];
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        downLast[keyCode] = !upLast[keyCode] && !upJust[keyCode];
        downJust[keyCode] = false;
        upLast[keyCode] = upLast[keyCode] || upJust[keyCode];
        upJust[keyCode] = downLast[keyCode] && !upJust[keyCode];
    }

    public static InputEvent getKeyboardEventWithKeyCode(int keycode) {
        Component dummyComponent = new Button();
        return new KeyEvent(dummyComponent, 0, 0, 0, keycode, '0', 0);
    }

}
