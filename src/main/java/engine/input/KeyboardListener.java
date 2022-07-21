package engine.input;

import engine.common.Observable;
import engine.common.Observer;
import engine.input.inputCombination.InputElement;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import static engine.input.inputCombination.ActionType.DOWN;
import static engine.input.inputCombination.ActionType.UP;

public class KeyboardListener implements Observable, KeyListener {

    private static final int KEY_NUMBER = 256;
    private final boolean[] pressed;
    private final List<Observer> observers;

    KeyboardListener() {
        pressed = new boolean[KEY_NUMBER];
        Arrays.fill(pressed, false);
        observers = new LinkedList<>();
    }

    public Set<InputElement> getActivatedInputElements() {
        Set<InputElement> currentCombination = new HashSet<>();
        for (int i = 0; i < KEY_NUMBER; i++) {
            InputEvent inputEvent = InputElement.getKeyboardInputEventByKeycode(i);
            currentCombination.add(new InputElement(pressed[i] ? DOWN : UP, inputEvent));
        }
        return currentCombination;
    }

    public boolean isActivated(InputElement inputElement) {
        int keyCode = ((KeyEvent) inputElement.getInputEvent()).getKeyCode();
        return switch (inputElement.getActionType()) {
            case UP -> !pressed[keyCode];
            case DOWN -> pressed[keyCode];
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
            observer.update();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(!pressed[keyCode]){
            pressed[keyCode] = true;
            notifyObservers();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressed[keyCode] = false;
        notifyObservers();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
