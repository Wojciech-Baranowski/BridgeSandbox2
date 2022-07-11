package input;

import common.Observable;
import common.Observer;
import input.inputCombination.InputElement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    public boolean isActivated(InputElement inputElement) {
        int keyCode = ((KeyEvent)inputElement.getInputEvent()).getKeyCode();
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

}
