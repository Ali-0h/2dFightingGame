package src.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import src.entities.Player;

public class Game extends Canvas implements Runnable {
    private boolean running = false;
    private Thread gameThread;
    private Player player1; // First player
    private Player player2; // Second player

    public Game() {
        JFrame frame = new JFrame("2D Fighting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setSize(800, 600); // Default resolution
        frame.setVisible(true);

        player1 = new Player("Knight1", 100, 300); // Initialize Player 1
        player2 = new Player("Knight2", 600, 300); // Initialize Player 2
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            tick();
            render();
        }
        stop();
    }

    private void tick() {
        player1.tick(); // Update Player 1 logic
        player2.tick(); // Update Player 2 logic
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        // Set background color
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Render players
        player1.render(g);
        player2.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game().start();
    }
}