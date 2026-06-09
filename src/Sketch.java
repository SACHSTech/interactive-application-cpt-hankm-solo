import processing.core.PApplet;

/**
 * A two player pong game where the first player to score 3 points wins
 * Player 1 is blue and plays on the bottom, Player 2 is red and plays on the top.
 * Player 1 control: LEFT and RIGHT arrow keys
 * Player 2 control: A and D keys
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

    // Paddle variables
    int bottomBarX = 325;
    int topBarX = 325;
    int barWidth = 150;
    int barHeight = 20;

    // Ball variables
    float ballX = 400;
    float ballY = 400;
    float ballSpeedX = 5;
    float ballSpeedY = 5;
    int ballSize = 20;

    // Keyboard Input
    boolean user1PressedLeft = false;
    boolean user1PressedRight = false;
    boolean user2PressedLeft = false;
    boolean user2PressedRight = false;

    // Array to keep track of each player's score
    // scores[0] is the blue player, scores[1] is the red player
    int[] scores = new int[2];

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        background(300, 200, 100);
        
        movePaddles();
        moveBall();
        checkWallBounce();
        checkPaddleBounce();
        checkBallOutOfBounds();
        drawBottomBar(bottomBarX);
        drawTopBar(topBarX);
        drawDottedLine();
        drawBall();
        drawScores();
    }

    // Move both paddles left or right based on which keys are being held down
    private void movePaddles() {
        if (user1PressedLeft) {
            if (bottomBarX > 0) {
                bottomBarX -= 10;
            }
        }

        if (user1PressedRight) {
            if (bottomBarX < width - barWidth) {
                bottomBarX += 10;
            }
        }

        if (user2PressedLeft) {
            if (topBarX > 0) {
                topBarX -= 10;
            }
        }

        if (user2PressedRight) {
            if (topBarX < width - barWidth) {
                topBarX += 10;
            }
        }
    }

    // Moves the ball by adding its speed to its position each frame
    private void moveBall() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;
    }

    // Checks if the ball hits the left or right wall and bounces it back
    private void checkWallBounce() {
        // Bounce off the left wall
        if (ballX - ballSize / 2 <= 0) {
            ballSpeedX *= -1;
        }

        // Bounce off the right wall
        if (ballX + ballSize / 2 >= 800) {
            ballSpeedX *= -1;
        }
    }

    // Checks if the ball hits either paddle and bounces it back
    private void checkPaddleBounce() {
        // Check if ball hits the top paddle
        if (
            ballX + ballSize / 2 <= topBarX + barWidth              // Left of the right edge
            && ballX - ballSize / 2 >= topBarX                      // Right of the left edge
            && ballY - ballSize / 2 >= 100 + barHeight              // Below the bottom edge
            && ballY + ballSize / 2 <= 100 + barHeight + ballSize   // Above the bottom limit
        ) {
            ballSpeedY *= -1;
        }

        // Check if ball hits the bottom paddle
        if (
            ballX + ballSize / 2 <= bottomBarX + barWidth           // Left of the right edge
            && ballX - ballSize / 2 >= bottomBarX                   // Right of the left edge
            && ballY - ballSize / 2 >= 700 - ballSize               // Below the top limit
            && ballY + ballSize / 2 <= 700                          // Above the bottom edge
        ) {
            ballSpeedY *= -1;
        }
    }

    // Checks if the ball has gone past the top or bottom edge
    // Awards a point to the correct player and resets the game's setup or ends the game if a player has reached 3 points
    private void checkBallOutOfBounds() {
        // Balll passed the top edge - Blue Scores
        if (ballY - ballSize / 2 <= 0) {
            scores[0]++;
            if (scores[0] == 3) {
                fill(0, 0, 255);
                textSize(40);
                text("BLUE WINS", width / 2 - 70, height - 700);
                noLoop();
            } else {
                resetBall();
            }
        }

        // Ball passed the bottom edge - Red Scores
        if (ballY + ballSize / 2 >= 800) {
            scores[1]++;
            if (scores[1] == 3) {
                fill(255, 0, 0);
                textSize(40);
                text("RED WINS", width / 2 - 70, height - 700);
                noLoop();
            } else {
                resetBall();
            }
        }
    }

    // Reset the ball back to the center of the screen after a point is scored
    private void resetBall() {
        ballX = 400;
        ballY = 400;
        ballSpeedX = 5;
        ballSpeedY = 5;
        bottomBarX = 325;
        topBarX = 325;
    }

    // Draws both players scores on the screen
    private void drawScores() {
        textSize(30);

        // Blue player score on the bottom half
        fill(0, 0, 255);
        text("Blue: " + scores[0], 10, 500);

        // Red player score on the top half
        fill(255, 0, 0);
        text("Red: " + scores[1], 10, 300);
    }

    // Draws the ball as a white circle at its current position
    private void drawBall() {
        fill(255);
        circle(ballX, ballY, ballSize);
    }

    // Draws a dotted line across the middle of the screen to divide the two sides
    private void drawDottedLine() {
        int i = 0;
        int j = 0;
        
        fill(0);
        while (i <= width) {
            rect((j * 65) - 15, 397, 50, 6);
            j++;
            i += 50;
        }
    }

    // Sets the matching boolean to true so the paddle moves in draw()
    public void keyPressed() {
        if (keyCode == LEFT) {
            user1PressedLeft = true;
        } else if (keyCode == RIGHT) {
            user1PressedRight = true;
        } else if (key == 'a') {
            user2PressedLeft = true;
        } else if (key == 'd') {
            user2PressedRight = true;
        }
    }

    // Sets the matching boolean to false so the paddle stops moving
    public void keyReleased() {
        if (keyCode == LEFT) {
            user1PressedLeft = false;
        } else if (keyCode == RIGHT) {
            user1PressedRight = false;
        } else if (key == 'a') {
            user2PressedLeft = false;
        } else if (key == 'd') {
            user2PressedRight = false;
        }
    }

    /**
     * Draws the blue paddle at the bottom of the screen
     * @param x the current X position of the paddle
     */
    private void drawBottomBar(int x) {
        fill(0, 0, 255);
        rect(x, 700, 150, 20, 10);
    }

    /**
     * Draws the red paddle at the top of the screen
     * @param x the current X position of the paddle
     */
    private void drawTopBar(int x) {
        fill(255, 0, 0);
        rect(x, 100, 150, 20, 10);
    }
}
