package display;

import java.util.Collection;
import java.util.EventListener;

public interface Display {

    void draw();
    void setObjectsToDraw(Collection<Drawable> objects);
    void addWindowListener(EventListener listener);
    DrawableFactory getDrawableFactory();

}
