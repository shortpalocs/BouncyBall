import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


// Guided lesson to teach MCHS Computer Science Club


public class BouncyBall extends JPanel {

    private ArrayList<Ball> balls = new ArrayList<>();
    private final int diameter = 200; // Use 60 for 600x400, 100 for 1920x1800
    private Random random = new Random(); // Random position for balls to spawn
    private int iteration = 0;

    class Ball {
        // Define velocity & color
        int x, y;
        int vx, vy;
        Color color;
        int ballCount = 0;

        Ball(int x, int y, int vx, int vy, Color color) // Define new ball
        {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
        }


        public void updateBall() {
            // Move the ball
            x += vx;
            y += vy;

            // Detects if ball on x-axis hit the wall, if it did reverse its direction "Bounce off the wall"
            if (x <= 0 || x + diameter >= getWidth()) {
                vx = -vx;  // Reverse horizontal direction
            }

            // Detects if ball hit the floor or ceiling, reverses direction
            // This all happens every time the ball gets updated

            if (y <= 0 || y + diameter >= getHeight()) {
                vy = -vy;  // Reverse vertical direction
            }


            // TODO: COLLISIONS









        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // Draw all balls
        for (Ball ball : balls) {
            graphics.setColor(ball.color);
            graphics.fillOval(ball.x, ball.y, diameter, diameter);
        }
    }

    public  BouncyBall() { // Constructor
        // Start the animation timer




        Timer timer = new Timer(16, e -> {
            UpdateBalls(); // Do these actions every click of timer
            repaint();
            iteration++;
            System.out.println("Iteration: " + iteration);
        });
        timer.start(); // Officially start animation timer






        addBall(); // Start animation timer officially
    }


    private void addBall() {
        int x = random.nextInt(Math.max(1, getWidth() - diameter));
        int y = random.nextInt(Math.max(1, getHeight() - diameter));

        if (x == 0)
        {
            x = 70;
        }
        if (y == 0)
        {
            y = 70;
        }


        // Random velocity between 25 and 30, avoiding zero obviously
         //int vx = random.nextInt(6) + 5;
         //int vy = random.nextInt(6) + 5;
        int vx = 30;
        int vy = 30;

        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); // Generate color for RGB value between 0-256

        balls.add(new Ball(x, y, vx, vy, color)); // Add new Ball object to the balls ArrayList (i hate array lists)
    }

    private void UpdateBalls() {
        for (Ball ball : balls) // For every ball in ArrayList, update ball and draw it
        {
            ball.updateBall();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncy Ball"); // Create the actual window "Frame"
        BouncyBall panel = new BouncyBall(); // Create canvas where ball is displayed
        JButton button = new JButton("Ball: "); // Make button to increase number of balls on screen

        frame.add(panel); // Add canvas to frame
        panel.add(button);
        frame.setSize(1920, 1800); // Set frame window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






        button.addActionListener(e -> {
            panel.addBall();
            button.setText("Balls: " + panel.balls.size());
        });






        frame.setVisible(true); // Show frame to User
    }
}
