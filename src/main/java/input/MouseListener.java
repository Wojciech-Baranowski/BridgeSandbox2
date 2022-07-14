package input;

import common.Observable;
import common.Observer;
import input.inputCombination.InputElement;
import lombok.Getter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.*;

import static input.inputCombination.ActionType.*;

public class MouseListener implements Observable, java.awt.event.MouseListener, MouseMotionListener {

    private static final int BUTTONS_NUMBER = 5;
    private final boolean[] upJust;
    private final boolean[] downJust;
    private final boolean[] upLast;
    private final boolean[] downLast;
    private final List<Observer> observers;
    @Getter
    private int x;
    @Getter
    private int y;

    MouseListener() {
        upJust = new boolean[BUTTONS_NUMBER];
        downJust = new boolean[BUTTONS_NUMBER];
        upLast = new boolean[BUTTONS_NUMBER];
        downLast = new boolean[BUTTONS_NUMBER];
        Arrays.fill(upJust, true);
        Arrays.fill(downJust, false);
        Arrays.fill(upLast, false);
        Arrays.fill(downLast, false);
        observers = new LinkedList<>();
        x = 0;
        y = 0;
    }

    public Set<InputElement> getActivatedInputElements() {
        Set<InputElement> currentCombination = new HashSet<>();
        for (int i = 0; i < BUTTONS_NUMBER; i++) {
            InputEvent inputEvent = getMouseEventWithKeyCode(i);
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
        int buttonCode = ((MouseEvent) inputElement.getInputEvent()).getButton();
        return switch (inputElement.getActionType()) {
            case UP -> upJust[buttonCode];
            case FREE -> upLast[buttonCode];
            case DOWN -> downJust[buttonCode];
            case HELD -> downLast[buttonCode];
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
    public void mousePressed(MouseEvent e) {
        int buttonCode = e.getButton();
        upLast[buttonCode] = !downLast[buttonCode] && !downJust[buttonCode];
        upJust[buttonCode] = false;
        downLast[buttonCode] = downLast[buttonCode] || downJust[buttonCode];
        downJust[buttonCode] = upLast[buttonCode] && !downJust[buttonCode];
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int buttonCode = e.getButton();
        downLast[buttonCode] = !upLast[buttonCode] && !upJust[buttonCode];
        downJust[buttonCode] = false;
        upLast[buttonCode] = upLast[buttonCode] || upJust[buttonCode];
        upJust[buttonCode] = downLast[buttonCode] && !upJust[buttonCode];
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
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

    public static InputEvent getMouseEventWithKeyCode(int keycode) {
        Component dummyComponent = new Button();
        return new MouseEvent(
                dummyComponent, 0, 0, 0, 0, 0, 0, false, keycode);
    }

}
