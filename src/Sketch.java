import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Hank Ma
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

    boolean user1PressedLeft = false;
    boolean user1PressedRight = false;
    boolean user2PressedLeft = false;
    boolean user2PressedRight = false;

    @Override
    public void setup() {
        
    }

    @Override
    public void draw() {
        background (300, 200, 100);
        
        if(user1PressedLeft) {
            if(bottomBarX > 0) {
                bottomBarX -= 10;
            }
        } 
        if (user1PressedRight){
            if(bottomBarX < width - barWidth) {
                bottomBarX += 10;
            }
        }

        if(user2PressedLeft) {
            if(topBarX > 0) {
                topBarX -= 10;
            }
        } 
        if (user2PressedRight){
            if(topBarX < width - barWidth) {
                topBarX += 10;
            }
        }

        
       
       drawBottomBar(bottomBarX);

        drawTopBar(topBarX);

        drawDottedLine();
    }

    private void drawDottedLine(){
        int i = 0;
        int j = 0;
        fill(0);
        while(i <= width){
            rect(j * 65, 397, 50, 6);
            j++;
            i += 50;
        }
    }

    public void keyPressed(){
        if (keyCode == LEFT) {
            user1PressedLeft = true;
        } else if (keyCode == RIGHT) {
            user1PressedRight = true;
        } else if (key == 'a'){
            user2PressedLeft = true;
        } else if (key == 'd') {
            user2PressedRight = true;
        }
    }

    public void keyReleased(){
        if (keyCode == LEFT) {
            user1PressedLeft = false;
        } else if (keyCode == RIGHT) {
            user1PressedRight = false;
        } else if (key == 'a'){
            user2PressedLeft = false;
        } else if (key == 'd') {
            user2PressedRight = false;
        }
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
