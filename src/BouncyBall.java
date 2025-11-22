import javax.swing.*;
import java.awt.*;


// Guided lesson to teach MCHS Computer Science Club


public class BouncyBall extends JPanel {
    // Ball properties
    private int x = 100;           // X position
    private int y = 100;           // Y position
    private final int diameter = 30;     // Ball size
    private int vx = 20;            // X velocity (speed and direction)
    private int vy = 20; // Y Velocity
    int ballCount = 0;

    public BouncyBall() {
        // Start the animation timer
        Timer timer = new Timer(16, e -> {  // ~60 FPS (1000ms/60 = approx. 16ms)
            updateBall();
            repaint();  // Tells Java to redraw the panel
        });
        timer.start(); // Start animation timer officially
    }

    private void updateBall() {
        // Move the ball
        x += vx;
        y += vy;

        // Detects if ball on x-axis hit the wall, if it did reverse its direction "Bounce off the wall"
        if (x <= 0 || x + diameter >= getWidth()) {
             ballCount += 1;
            vx = -vx;  // Reverse horizontal direction
        }

        // Detects if ball hit the floor or ceiling, reverses direction
        // This all happens every time the ball gets updated

        if (y <= 0 || y + diameter >= getHeight()) {
            vy = -vy;  // Reverse vertical direction
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);  // clears the background

        // Ball = graphics
        graphics.setColor(Color.PINK); // Set ball color


        graphics.fillOval(x, y, diameter, diameter);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncy Ball"); // Create the actual window "Frame"
        BouncyBall panel = new BouncyBall(); // Create canvas where ball is displayed

        frame.add(panel); // Add canvas to frame
        frame.setSize(600, 400); // Set frame window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Show frame to User
    }
}