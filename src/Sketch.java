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

    int barX = 325;
    int barWidth = 150;

    @Override
    public void setup() {
        
    }

    @Override
    public void draw() {
        background (300, 200, 100);
        
        if(keyPressed) {
            if(keyCode == LEFT) {
                if(barX > 0) {
                    barX -= 5;
                }
            } else if (keyCode == RIGHT){
                if(barX < width - barWidth) {
                    barX += 5;
                }
            }
        }

        drawBar(barX);
    }

    private void drawBar(int x){
        fill(255);
        rect(x, 700, 150, 20, 10);
    }

    /** Additional helper methods below */

}
