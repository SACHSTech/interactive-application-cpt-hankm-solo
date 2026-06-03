import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Your Name
 */
public class Sketch extends PApplet {
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    @Override
    public void settings() {
        size(800, 800); 
    }

    int bottomBarX = 325;
    int topBarX = 325;
    int barWidth = 150;

    @Override
    public void setup() {
        
    }

    @Override
    public void draw() {
        background (300, 200, 100);
        
        if(keyPressed) {
            if(keyCode == LEFT) {
                if(bottomBarX > 0) {
                    bottomBarX -= 5;
                }
            } else if (keyCode == RIGHT){
                if(bottomBarX < width - barWidth) {
                    bottomBarX += 5;
                }
            }
        }
        drawBottomBar(bottomBarX);

        if(keyPressed) {
            if(key == 'a') {
                if(topBarX > 0) {
                    topBarX -= 5;
                }
            } else if (key == 'd'){
                if(topBarX < width - barWidth) {
                    topBarX += 5;
                }
            }
        }

        drawTopBar(topBarX);
    }

    private void drawBottomBar(int x){
        fill(255);
        rect(x, 700, 150, 20, 10);
    }

    private void drawTopBar(int y) {
        fill(255);
        rect(y, 100, 150, 20, 10);
    }

    /** Additional helper methods below */

}
