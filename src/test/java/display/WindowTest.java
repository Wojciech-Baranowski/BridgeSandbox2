package display;

import org.junit.Test;

public class WindowTest {

    @Test
    public void draw_manual_test() {
        //given
        int[] inputPixels = new int[Window.w * Window.h];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                inputPixels[i + j * Window.w] = 0xFFFF0000;
            }
            for(int j = 100; j < 200; j++){
                inputPixels[i + j * Window.w] = 0xFF00FF00;
            }
            for(int j = 200; j < 300; j++){
                inputPixels[i + j * Window.w] = 0xFF0000FF;
            }
        }
        Window window = new Window();
        //when
        window.draw(inputPixels);
        //then
        //three 100 x 100 squares placed vertically, colored red, green and blue
    }

}
