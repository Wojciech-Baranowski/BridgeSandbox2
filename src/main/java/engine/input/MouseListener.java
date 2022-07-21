package engine.input;

import engine.common.Observer;
import engine.input.inputCombination.ActionType;
import engine.input.inputCombination.InputElement;
import lombok.Getter;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class MouseListener implements engine.common.Observable, java.awt.event.MouseListener, MouseMotionListener {

    private static final int BUTTONS_NUMBER = 5;
    private final boolean[] pressed;
    private final List<engine.common.Observer> observers;
    @Getter
    private int x;
    @Getter
    private int y;

    MouseListener() {
        pressed = new boolean[BUTTONS_NUMBER];
        Arrays.fill(pressed, false);
        observers = new LinkedList<>();
        x = 0;
        y = 0;
    }

    public Set<InputElement> getActivatedInputElements() {
        Set<InputElement> currentCombination = new HashSet<>();
        for (int i = 0; i < BUTTONS_NUMBER; i++) {
            InputEvent inputEvent = InputElement.getMouseInputEventByKeycode(i);
            currentCombination.add(new InputElement(pressed[i] ? ActionType.DOWN : ActionType.UP, inputEvent));
        }
        return currentCombination;
    }

    public boolean isActivated(InputElement inputElement) {
        int buttonCode = ((MouseEvent) inputElement.getInputEvent()).getButton();
        return switch (inputElement.getActionType()) {
            case UP -> !pressed[buttonCode];
            case DOWN -> pressed[buttonCode];
        };
    }

    @Override
    public void attach(engine.common.Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(engine.common.Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int buttonCode = e.getButton();
        pressed[buttonCode] = true;
        notifyObservers();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int buttonCode = e.getButton();
        pressed[buttonCode] = false;
        notifyObservers();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        notifyObservers();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
