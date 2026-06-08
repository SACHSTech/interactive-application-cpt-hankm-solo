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
    int barHeight = 20;

    // Ball variables
    float ballX = 400;
    float ballY = 400;
    float ballSpeedX = 10;
    float ballSpeedY = 10;
    int ballSize = 20;

    boolean user1PressedLeft = false;
    boolean user1PressedRight = false;
    boolean user2PressedLeft = false;
    boolean user2PressedRight = false;

    @Override
    public void setup() {
       frameRate(5); 
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
        
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        if(ballX + ballSize / 2 <= topBarX + barWidth 
        && ballX - ballSize / 2 >= topBarX 
        && ballY - ballSize / 2 >= 100 + barHeight 
        && ballY + ballSize / 2 <= 100 + barHeight + ballSize
        ){
            ballY = 100 + barHeight + ballSize / 2;
            ballSpeedY *= -1;
        }

        if(ballX + ballSize / 2 <= bottomBarX + barWidth 
        && ballX - ballSize / 2 >= bottomBarX 
        && ballY - ballSize / 2 >= 700 + ballSize 
        && ballY + ballSize / 2 <= 700 
        ){
            ballY = 700 - ballSize / 2;
            ballSpeedY *= -1;
        }

        drawBottomBar(bottomBarX);
        drawTopBar(topBarX);
        drawDottedLine();
        drawBall();
    }

    private void drawBall(){
        fill(255);
        circle(ballX, ballY, ballSize);
    }

    private void drawDottedLine(){
        int i = 0;
        int j = 0;
        fill(0);
        while(i <= width){
            rect((j * 65) - 15, 397, 50, 6);
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
        fill(0, 0, 255);
        rect(x, 700, 150, 20, 10);
    }

    private void drawTopBar(int x) {
        fill(255, 0, 0);
        rect(x, 100, 150, 20, 10);
    }

    /** Additional helper methods below */

}
