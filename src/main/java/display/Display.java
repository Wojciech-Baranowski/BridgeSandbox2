package display;

import common.Visual;
import display.drawable.DrawableFactory;

import java.util.Collection;
import java.util.EventListener;

public interface Display {

    void draw();

    void setObjectsToDraw(Collection<Visual> objects);

    void addWindowListener(EventListener listener);

    DrawableFactory getDrawableFactory();

}
